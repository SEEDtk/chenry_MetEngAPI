# -*- coding: utf-8 -*-
#BEGIN_HEADER
from os.path import exists
import json
import cobra
#import cplex
#import cobrakbase
import escher
from escher import Builder
from cobrakbase.core.kbase_object_factory import KBaseObjectFactory
from modelseedpy import MSPackageManager, FBAHelper

def validate_args(params,required,defaults):
    for item in required:
        if item not in params:
            raise ValueError('Required argument '+item+' is missing!')
    for key in defaults:
        if key not in params:
            params[key] = defaults[key]
    return params
#END_HEADER


class chenry_MetEngAPI:
    '''
    Module Name:
    chenry_MetEngAPI

    Module Description:
    A KBase module: chenry_MetEngAPI
    '''

    ######## WARNING FOR GEVENT USERS ####### noqa
    # Since asynchronous IO can lead to methods - even the same method -
    # interrupting each other, you must be *very* careful when using global
    # state. A method could easily clobber the state set by another while
    # the latter method is running.
    ######################################### noqa
    VERSION = "0.0.1"
    GIT_URL = "https://github.com/cshenry/chenry_MetEngAPI.git"
    GIT_COMMIT_HASH = "b78ae37d4107dba9773cd210bc2c73c96bf077f9"

    #BEGIN_CLASS_HEADER
    def clear_context(self):
        self.ctx = None
        self.kbase_api = None
    
    def initialize_call(self,ctx):
        self.warning = []
        self.clear_context()
        self.ctx = ctx
        self.kbase_api = None
        #if "token" in self.ctx:
            #self.kbase_api = cobrakbase.KBaseAPI(token=self.ctx['token'],config=self.config)
    
    def get_media(self,media_id):
        if exists(self.config["data_dir"]+"/media/"+media_id):
            factory = KBaseObjectFactory()
            return factory.build_object_from_file(self.config["data_dir"]+"/media/"+media_id, 'KBaseBiochem.Media')  
        elif len(media_id.split("/")) > 1:
            return self.kbase_api.get_from_ws(media_id,None)
        return None
    
    def get_model(self,model_id):
        if exists(self.config["data_dir"]+"/models/"+model_id+".kb"):
            factory = KBaseObjectFactory()
            return factory.build_object_from_file(self.config["data_dir"]+"/models/"+model_id, 'KBaseFBA.FBAModel')
        elif exists(self.config["data_dir"]+"/models/"+model_id+".json"):
            return cobra.io.load_json_model(self.config["data_dir"]+"/models/"+model_id+".json") 
        elif exists(self.config["data_dir"]+"/models/"+model_id+".xml"):
            return cobra.io.read_sbml_model(self.config["data_dir"]+"/models/"+model_id+".xml")
        elif len(model_id.split("/")) > 1:
            return self.kbase_api.get_from_ws(model_id,None)
        return None
    
    def configure_fba_from_model_input(self,params,media,model):
        #model.solver = 'optlang-cplex'
        if type(media) is dict:
            for rxn in model.reactions:
                if rxn.lower_bound > 0:
                    rxn.lower_bound = 0
                if rxn.id[0:3] == "EX_":
                    rxn.lower_bound = 0
            for cpdid in media:
                if "EX_"+cpdid in model.reactions:
                    rxn = model.reactions.get_by_id("EX_"+cpdid)
                    rxn.lower_bound = -1*media[cpdid]
                elif cpdid in model.metabolites:
                    model.add_reactions([FBAHelper.add_drain_from_metabolite_id(model,cpdid, media[cpdid], 100,'EX_', 'Exchange for ')])
                else:
                    msid_hash = FBAHelper.msid_hash(model)
                    if cpdid in msid_hash:
                        cpd = msid_hash[cpdid][0]
                        model.add_reactions([FBAHelper.add_drain_from_metabolite_id(model,cpd.id, media[cpdid], 100,'EX_', 'Exchange for ')])
                    else:
                        self.warning.append("Media compound "+cpdid+" not found in model!")
        else:
            pkgmgr = MSPackageManager.get_pkg_mgr(model,1)
            pkgmgr.getpkg("KBaseMediaPkg").build_package(media)
        for rxnid in params["reaction_bounds"]:
            if rxnid in model.reactions:
                rxn = model.reactions.get_by_id(rxnid)
                rxn.lower_bound = params["reaction_bounds"][rxnid][0]
                rxn.upper_bound = params["reaction_bounds"][rxnid][1]
        if params["target"] not in model.reactions:
            raise ValueError("Target reaction "+params["target"]+" not in model!")
        model.objective = params["target"]
        if params["ref_flux"] == None:
            params["ref_flux"] = {}
            solution = cobra.flux_analysis.pfba(model)
            for reaction in model.reactions:
                params["ref_flux"][reaction.id] = solution.fluxes[reaction.id]
        for kd in params["kds"]:
            if kd in params["ref_flux"]:
                if params["ref_flux"] < 0:
                    reaction.lower_bound = params["ref_flux"][kd]*params["kds"][kd]
                else:
                    reaction.upper_bound = params["ref_flux"][kd]*params["kds"][kd]
        for ind in params["inductions"]:
            if ind in params["ref_flux"]:
                if params["ref_flux"] < 0:
                    reaction.upper_bound = params["ref_flux"][ind]*params["inductions"][ind]
                    if reaction.lower_bound > reaction.upper_bound:
                        reaction.lower_bound = reaction.upper_bound
                else:
                    reaction.lower_bound = params["ref_flux"][ind]*params["inductions"][ind]
                    if reaction.upper_bound < reaction.lower_bound:
                        reaction.upper_bound = reaction.lower_bound
        for ko in params["kos"]:
            if ko in model.reactions:
                model.reactions.get_by_id(ko).knock_out()
            elif ko in model.genes:
                model.genes.get_by_id(ko).knock_out()
    #END_CLASS_HEADER

    # config contains contents of config file in a hash or None if it couldn't
    # be found
    def __init__(self, config):
        #BEGIN_CONSTRUCTOR
        self.config = config
        escher.rc['never_ask_before_quit'] = True
        #END_CONSTRUCTOR
        pass


    def get_gene_table_from_model(self, ctx, params):
        """
        Retrieve master gene table
        :param params: instance of type "ModelOnlyInput" -> structure:
           parameter "workspace" of String, parameter "model" of String
        :returns: instance of type "GeneTable" -> structure: parameter
           "headings" of list of String, parameter "data" of list of list of
           String
        """
        # ctx is the context object
        # return variables are: output
        #BEGIN get_gene_table_from_model
        self.initialize_call(ctx)
        params = validate_args(params,["model"],{
            "type":None,
            "object":None,
            "input_ref":None,
            "input_workspace":None,
            "query_events":None,
            "query_genes":None,
        })
        genome = {}
        if exists(self.config["data_dir"]+"/genomes/"+params["model"]+".json"):
            with open(self.config["data_dir"]+"/genomes/"+params["model"]+".json", 'r') as fh:
                genome = json.load(fh)
        elif len(params["model"].split("/")) > 1:
            model = self.kbase_api.get_object(params["model"],None)
            genome = self.kbase_api.get_object(model["genome_ref"],None)
        output = {
            "headings":["id","locus","gene","type","functions","contig","start","length"],
            "data":[]
        }
        ftrs = genome["features"]
        for ftr in ftrs:
            data = [ftr["id"],None,None,"peg",ftr["functions"],ftr["location"][0][0],ftr["location"][0][1],ftr["location"][0][3]]
            if ftr["location"][0][2] == "-":
                data[7] = -1*data[7]
            for alias in ftr["aliases"]:
                if alias[0] == "PATRIC_id":
                    data[0] = alias[1]
                if alias[0] == "locus":
                    data[1] = alias[1]
                if alias[0] == "gene_name":
                    data[2] = alias[1]
            output["data"].append(data)
        ftrs = genome["non_coding_features"]
        for ftr in ftrs:
            data = [ftr["id"],None,None,ftr["type"],ftr["functions"],ftr["location"][0][0],ftr["location"][0][1],ftr["location"][0][3]]
            if ftr["location"][0][2] == "-":
                data[7] = -1*data[7]
            for alias in ftr["aliases"]:
                if alias[0] == "PATRIC_id":
                    data[0] = alias[1]
                if alias[0] == "locus":
                    data[1] = alias[1]
                if alias[0] == "gene_name":
                    data[2] = alias[1]
            output["data"].append(data)
        #END get_gene_table_from_model

        # At some point might do deeper type checking...
        if not isinstance(output, dict):
            raise ValueError('Method get_gene_table_from_model return value ' +
                             'output is not type dict as required.')
        # return the results
        return [output]

    def get_model_data(self, ctx, params):
        """
        Retrieve model data
        :param params: instance of type "ModelOnlyInput" -> structure:
           parameter "workspace" of String, parameter "model" of String
        :returns: instance of type "GeneTable" -> structure: parameter
           "headings" of list of String, parameter "data" of list of list of
           String
        """
        # ctx is the context object
        # return variables are: output
        #BEGIN get_model_data
        self.initialize_call(ctx)
        params = validate_args(params,["model"],{})
        model = self.get_model(params["model"])
        output = {
            "compounds":{
                "headings":["id","name","formula","charge"],
                "data":[]
            },
            "transportable":[],
            "reactions":{
                "headings":["id","name","equation","definition"],
                "data":[]
            },
            "genes":[]
        }
        for cpd in model.metabolites:
            output["compounds"]["data"].append([cpd.id,cpd.name,cpd.formula,cpd.charge])
        transportable_hash = {}
        for rxn in model.reactions:
            if rxn.id[0:3] == "EX_":
                for met in rxn.metabolites:
                    transportable_hash[met.id] = 1
            else:
                equation = ""
                definition = ""
                eq_prod = ""
                def_prod = ""
                for met in rxn.metabolites:
                    number = ""
                    if abs(rxn.metabolites[met]) != 1:
                        number = "("+str(abs(rxn.metabolites[met]))+") "
                    if rxn.metabolites[met] < 0:
                        if len(equation) > 0:
                            equation += " + "
                            definition += " + "
                        equation += number+met.id
                        definition += number+met.name
                    else:
                        if len(eq_prod) > 0:
                            eq_prod += " + "
                            def_prod += " + "
                        eq_prod += number+met.id
                        def_prod += number+met.name
                if rxn.upper_bound > 0:
                    if rxn.lower_bound < 0:
                        equation += " <=> "+eq_prod
                        definition += " <=> "+def_prod
                    else:
                        equation += " => "+eq_prod
                        definition += " => "+def_prod
                elif rxn.lower_bound < 0:
                    definition += " <= "+def_prod
                    equation += " <= "+eq_prod
                output["reactions"]["data"].append([rxn.id,rxn.name,equation,definition])
        output["transportable"] = list(transportable_hash.keys())
        for gene in model.genes:
            output["genes"].append(gene.id)
        #END get_model_data

        # At some point might do deeper type checking...
        if not isinstance(output, dict):
            raise ValueError('Method get_model_data return value ' +
                             'output is not type dict as required.')
        # return the results
        return [output]

    def compute_biosynthesis_pathway(self, ctx, params):
        """
        Compute the peripheral biosynthesis pathway for the selected target
        :param params: instance of type "ModelInput" -> structure: parameter
           "workspace" of String, parameter "model" of String, parameter
           "carbon_source" of String, parameter "target" of String, parameter
           "base_media" of String, parameter "media_workspace" of String,
           parameter "kos" of list of String, parameter "kds" of mapping from
           String to Double, parameter "inductions" of mapping from String to
           Double, parameter "cofactor_stoichiometry" of mapping from String
           to Double
        :returns: instance of type "PathwayReactions" -> structure: parameter
           "pathway_reactions" of list of type "PathwayReaction" ->
           structure: parameter "id" of String, parameter "intermediate" of
           Long, parameter "flux" of Double, parameter "max_flux" of Double,
           parameter "min_flux" of Double, parameter "ATP_cost" of Double,
           parameter "cofactor_stoichiometry" of mapping from String to Double
        """
        # ctx is the context object
        # return variables are: output
        #BEGIN compute_biosynthesis_pathway
        self.initialize_call(ctx)
        params = validate_args(params,["model","target","base_media"],{
            "kos":[],
            "kds":{},
            "inductions":{},
            "additional_cofactors":[],
            "additional_sources":[],
            "reaction_bounds":{},
            "ref_flux":None
        })
        model = self.get_model(params["model"])
        media = self.get_model(params["base_media"])
        self.configure_fba_from_model_input(params,media,model)
        #Add drain fluxes for all central carbon compounds and cofactors
        cofactors = ["cpd00062","cpd00014","cpd00091","cpd00052","cpd00096","cpd00046","cpd00038","cpd00031","cpd00126","cpd00002","cpd00008","cpd00018","cpd00097","cpd00986","cpd00109","cpd00110","cpd11620","cpd11621","cpd00228","cpd00823","cpd11665","cpd11669","cpd00733","cpd00734","cpd11807","cpd11808","cpd00364","cpd00415","cpd12505","cpd12576","cpd12669","cpd12694","cpd00003","cpd00004","cpd00005","cpd00006","cpd00074","cpd03422","cpd01997","cpd12370","cpd15666","cpd11574","cpd10516","cpd10515","cpd00971","cpd00254","cpd00244","cpd00205","cpd00149","cpd00063","cpd00058","cpd00048","cpd00034","cpd00030","cpd00047","cpd15560","cpd00001","cpd00009","cpd00010","cpd00011","cpd00012","cpd00013","cpd00015","cpd11609","cpd11610","cpd00067","cpd00099","cpd12713","cpd00242","cpd00007","cpd00025"]
        sources = ["cpd00103","cpd00171","cpd00146","cpd00020","cpd00024","cpd00169","cpd00102","cpd00072","cpd00032","cpd00079","cpd00022","cpd00236","cpd00101","cpd00061","cpd00041","cpd00002","cpd00038","cpd00023","cpd00053"]
        for item in params["additional_cofactors"]:
            found = 0
            for cof in cofactors:
                if cof == item:
                    found = 1
            if found == 0:
                cofactors.append(item)
        for item in params["additional_sources"]:
            found = 0
            for src in sources:
                if src == item:
                    found = 1
            if found == 0:
                sources.append(item)
        msid_hash = FBAHelper.msid_hash(model)
        for cof in cofactors:
            if cof in msid_hash:
                for cpd in msid_hash[cof]:
                    model.add_reactions([FBAHelper.add_drain_from_metabolite_id(model, cpd.id, 100, 100,'COF_', 'Cofactor exchange for ')])
        for source in sources:
            if source in msid_hash:
                for cpd in msid_hash[source]:
                    model.add_reactions([FBAHelper.add_drain_from_metabolite_id(model, cpd.id, 100, 0,'SRC_', 'Source uptake for ')])
        solution = cobra.flux_analysis.pfba(model)
        output = { 
            "pathway_reactions":{},
            "cofactor_stoichiometry":{},
            "main_stoichiometry":{}
        }
        for reaction in model.reactions:
            flux = solution.fluxes[reaction.id]
            if flux != 0:
                if reaction.id[0:3] == "EX_":
                    output["main_stoichiometry"][reaction.id[3:]] = flux
                elif reaction.id[0:4] == "COF_":
                    output["cofactor_stoichiometry"][reaction.id[4:]] = flux
                elif reaction.id[0:4] == "SRC_":
                    output["main_stoichiometry"][reaction.id[4:]] = flux
                else:
                    output["pathway_reactions"][reaction.id[3:]] = flux
        #END compute_biosynthesis_pathway

        # At some point might do deeper type checking...
        if not isinstance(output, dict):
            raise ValueError('Method compute_biosynthesis_pathway return value ' +
                             'output is not type dict as required.')
        # return the results
        return [output]

    def compute_competing_pathways(self, ctx, params):
        """
        Compute the peripheral biosynthesis pathway for the selected target
        :param params: instance of type "ModelInput" -> structure: parameter
           "workspace" of String, parameter "model" of String, parameter
           "carbon_source" of String, parameter "target" of String, parameter
           "base_media" of String, parameter "media_workspace" of String,
           parameter "kos" of list of String, parameter "kds" of mapping from
           String to Double, parameter "inductions" of mapping from String to
           Double, parameter "cofactor_stoichiometry" of mapping from String
           to Double
        :returns: instance of type "CompetingReactions" -> structure:
           parameter "competing_reactions" of mapping from String to type
           "CompetingReactionData" -> structure: parameter "id" of String,
           parameter "direction_for_competition" of String, parameter
           "intermediate" of Long, parameter "flux" of Double, parameter
           "max_flux" of Double, parameter "min_flux" of Double
        """
        # ctx is the context object
        # return variables are: output
        #BEGIN compute_competing_pathways
        self.initialize_call(ctx)
        params = validate_args(params,["model","target","base_media"],{
            "kos":[],
            "kds":{},
            "inductions":{},
            "cofactor_stoichiometry":{},
            "reaction_bounds":{},
            "ref_flux":None
        })
        model = self.get_model(params["model"])
        media = self.get_model(params["base_media"])
        self.configure_fba_from_model_input(params,media,model)
        solution = cobra.flux_analysis.pfba(model)
        original_flux = {}
        for rxn in model.reactions:
            original_flux[rxn.id] = solution.fluxes[rxn.id]   
        #Now increase flux through carbon source while fixing the target flux at it's previous optimum
        rxn = model.reactions.get_by_id(params["target"])
        rxn.upper_bound = solution.fluxes[rxn.id] 
        rxn.lower_bound = solution.fluxes[rxn.id] 
        for rxn in model.reactions:
            if rxn.id[0:4] == "SRC_":
                rxn.upper_bound = 1.1*solution.fluxes[rxn.id]
                rxn.lower_bound = 1.1*solution.fluxes[rxn.id]
        solution = cobra.flux_analysis.pfba(model)
        output = {"competing_reactions":{}}
        for rxn in model.reactions:
            flux = solution.fluxes[rxn.id] 
            if flux != 0 and original_flux[rxn.id] == 0:
                if flux > 0:
                    output["competing_reactions"][rxn.id] = ">"
                else:
                    output["competing_reactions"][rxn.id] = "<"
        #END compute_competing_pathways

        # At some point might do deeper type checking...
        if not isinstance(output, dict):
            raise ValueError('Method compute_competing_pathways return value ' +
                             'output is not type dict as required.')
        # return the results
        return [output]

    def compute_cofactor_consuming_pathways(self, ctx, params):
        """
        Compute the peripheral biosynthesis pathway for the selected target
        :param params: instance of type "ModelInput" -> structure: parameter
           "workspace" of String, parameter "model" of String, parameter
           "carbon_source" of String, parameter "target" of String, parameter
           "base_media" of String, parameter "media_workspace" of String,
           parameter "kos" of list of String, parameter "kds" of mapping from
           String to Double, parameter "inductions" of mapping from String to
           Double, parameter "cofactor_stoichiometry" of mapping from String
           to Double
        :returns: instance of type "CofactorReactions" -> structure:
           parameter "cofactor_reactions" of mapping from String to type
           "CofactorReactionData" -> structure: parameter "id" of String,
           parameter "direction_for_competition" of String, parameter "flux"
           of Double, parameter "max_flux" of Double, parameter "min_flux" of
           Double
        """
        # ctx is the context object
        # return variables are: output
        #BEGIN compute_cofactor_consuming_pathways
        self.initialize_call(ctx)
        params = validate_args(params,["model","target","base_media"],{
            "kos":[],
            "kds":{},
            "inductions":{},
            "cofactor_stoichiometry":{},
            "reaction_bounds":{},
            "ref_flux":None
        })
        model = self.get_model(params["model"])
        media = self.get_model(params["base_media"])
        self.configure_fba_from_model_input(params,media,model)
        cobra.flux_analysis.pfba(model)
        #END compute_cofactor_consuming_pathways

        # At some point might do deeper type checking...
        if not isinstance(output, dict):
            raise ValueError('Method compute_cofactor_consuming_pathways return value ' +
                             'output is not type dict as required.')
        # return the results
        return [output]

    def systematic_target_search(self, ctx, params):
        """
        Systematically try all KO and return predicted production from each KO
        :param params: instance of type "ModelInput" -> structure: parameter
           "workspace" of String, parameter "model" of String, parameter
           "carbon_source" of String, parameter "target" of String, parameter
           "base_media" of String, parameter "media_workspace" of String,
           parameter "kos" of list of String, parameter "kds" of mapping from
           String to Double, parameter "inductions" of mapping from String to
           Double, parameter "cofactor_stoichiometry" of mapping from String
           to Double
        :returns: instance of type "TargetModifications" -> structure:
           parameter "ko_targets" of list of tuple of size 2: String, Double,
           parameter "induction_targets" of list of tuple of size 2: String,
           Double
        """
        # ctx is the context object
        # return variables are: output
        #BEGIN systematic_target_search
        self.initialize_call(ctx)
        params = validate_args(params,["model","target","base_media"],{
            "kos":[],
            "kds":{},
            "inductions":{},
            "cofactor_stoichiometry":{},
            "reaction_bounds":{},
            "ref_flux":None
        })
        model = self.get_model(params["model"])
        media = self.get_model(params["base_media"])
        self.configure_fba_from_model_input(params,media,model)
        ref_solution = cobra.flux_analysis.pfba(model)
        original_prod = ref_solution.fluxes[params["target"]]
        output = {
            "ko_targets":[],
            "induction_targets":[]
        }
        #Simulating modification of all active reactions with MOMA
        for rxn in model.reactions:
            flux = ref_solution.fluxes[rxn.id] 
            if flux != 0:
                #Simulation KO
                with model:
                    rxn.upper_bound = 0
                    rxn.lower_bound = 0
                    new_sol = cobra.flux_analysis.moma(model,ref_solution)
                    if new_sol.fluxes[params["target"]] > original_prod:
                        output["ko_targets"].append([rxn.id,new_sol.fluxes[params["target"]]])
                #Simulating induction
                with model:
                    rxn.upper_bound = 1.1*flux
                    rxn.lower_bound = 1.1*flux
                    new_sol = cobra.flux_analysis.moma(model,ref_solution)
                    if new_sol.fluxes[params["target"]] > original_prod:
                        output["induction_targets"].append([rxn.id,new_sol.fluxes[params["target"]]])
        #END systematic_target_search

        # At some point might do deeper type checking...
        if not isinstance(output, dict):
            raise ValueError('Method systematic_target_search return value ' +
                             'output is not type dict as required.')
        # return the results
        return [output]

    def compute_flux(self, ctx, params):
        """
        Compute the peripheral biosynthesis pathway for the selected target
        :param params: instance of type "ModelInput" -> structure: parameter
           "workspace" of String, parameter "model" of String, parameter
           "carbon_source" of String, parameter "target" of String, parameter
           "base_media" of String, parameter "media_workspace" of String,
           parameter "kos" of list of String, parameter "kds" of mapping from
           String to Double, parameter "inductions" of mapping from String to
           Double, parameter "cofactor_stoichiometry" of mapping from String
           to Double
        :returns: instance of type "FluxData" -> structure: parameter
           "reaction_fluxes" of mapping from String to Double, parameter
           "metabolite_flux" of mapping from String to Double
        """
        # ctx is the context object
        # return variables are: output
        #BEGIN compute_flux
        self.initialize_call(ctx)
        params = validate_args(params,["model","target","base_media"],{
            "kos":[],
            "kds":{},
            "inductions":{},
            "cofactor_stoichiometry":{},
            "reaction_bounds":{},
            "ref_flux":None
        })
        model = self.get_model(params["model"])
        if type(params["base_media"]) is not dict:
            media = self.get_media(params["base_media"])
        else:
            media = params["base_media"]
        self.configure_fba_from_model_input(params,media,model)
        solution = cobra.flux_analysis.pfba(model)
        output = { 
            "reaction_fluxes":{},
            "metabolite_flux":{},
            "objective":solution.fluxes[params["target"]]
        }
        for reaction in model.reactions:
            if solution.fluxes[reaction.id] != 0:
                if reaction.id[0:3] == "EX_":
                    output["metabolite_flux"][reaction.id[3:]] = solution.fluxes[reaction.id]
                else:
                    output["reaction_fluxes"][reaction.id] = solution.fluxes[reaction.id]
        #END compute_flux

        # At some point might do deeper type checking...
        if not isinstance(output, dict):
            raise ValueError('Method compute_flux return value ' +
                             'output is not type dict as required.')
        # return the results
        return [output]

    def list_maps(self, ctx, MapInput):
        """
        List maps available for viewing
        :param MapInput: instance of type "MapInput" -> structure: parameter
           "model" of String
        :returns: instance of type "MapList" -> structure: parameter "maps"
           of list of type "MapData" -> structure: parameter "id" of String,
           parameter "name" of String, parameter "reactions" of list of
           String, parameter "compounds" of list of String, parameter "genes"
           of list of String, parameter "total_reactions" of Long, parameter
           "total_compounds" of Long
        """
        # ctx is the context object
        # return variables are: output
        #BEGIN list_maps
        self.initialize_call(ctx)
        output = {"maps":[{
            "id":"ecoli_cc",
            "name":"Central carbon",
            "reactions":[],
            "compounds":[],
            "total_reactions":0,
            "total_compounds":0
        }]}
        #END list_maps

        # At some point might do deeper type checking...
        if not isinstance(output, dict):
            raise ValueError('Method list_maps return value ' +
                             'output is not type dict as required.')
        # return the results
        return [output]

    def get_map(self, ctx, params):
        """
        Get an escher map painted with input data rendered in HTML format
        :param params: instance of type "EscherInput" -> structure: parameter
           "map_id" of String, parameter "reaction_flux" of mapping from
           String to Double, parameter "gene_expression" of mapping from
           String to Double, parameter "metabolite_values" of mapping from
           String to Double, parameter "model" of String, parameter "target"
           of String, parameter "kos" of list of String, parameter "kds" of
           list of String, parameter "inductions" of list of String
        :returns: instance of type "EscherOutput" -> structure: parameter
           "html" of String
        """
        # ctx is the context object
        # return variables are: output
        #BEGIN get_map
        self.initialize_call(ctx)
        params = validate_args(params,["map_id","model"],{
            "reaction_flux":{},
            "gene_expression":{},
            "metabolite_values":{},
            "target":None,
            "kos":[],
            "kds":[],
            "inductions":[],
            "height":600
        })
        model = self.get_model(params["model"])
        mapjson = None
        if exists(self.config["data_dir"]+"/maps/"+params["map_id"]+".json"):
            with open(self.config["data_dir"]+"/maps/"+params["map_id"]+".json", 'r') as fh:
                mapjson = fh.read()
        elif len(params["model"].split("/")) > 1:
            mapjson = json.dump(self.kbase_api.get_object(params["map_id"],None))
        builder = Builder(
            height=600,
            map_name=None,
            map_json=mapjson,
        ) 
        builder.model = model
        if len(params["reaction_flux"]) > 0:
            builder.reaction_data = params["reaction_flux"]
        if len(params["metabolite_values"]) > 0:
            builder.metabolite_data = params["metabolite_values"]
        if len(params["gene_expression"]) > 0:
            builder.gene_data = params["gene_expression"]
        builder.save_html(self.config["scratch"]+params["map_id"]+".html")
        output = {"html":""}
        with open(self.config["scratch"]+params["map_id"]+".html", 'r') as fh:
            output["html"] = fh.read()
        #END get_map

        # At some point might do deeper type checking...
        if not isinstance(output, dict):
            raise ValueError('Method get_map return value ' +
                             'output is not type dict as required.')
        # return the results
        return [output]
    def status(self, ctx):
        #BEGIN_STATUS
        returnVal = {'state': "OK",
                     'message': "",
                     'version': self.VERSION,
                     'git_url': self.GIT_URL,
                     'git_commit_hash': self.GIT_COMMIT_HASH}
        #END_STATUS
        return [returnVal]
