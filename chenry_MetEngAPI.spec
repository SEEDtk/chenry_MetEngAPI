/*
A KBase module: chenry_MetEngAPI
*/

module chenry_MetEngAPI {
    
    typedef structure {
		string workspace;
		string model;
    } ModelOnlyInput;
    
    typedef structure {
		list<string> headings;
		list<list<string>> data;
    } GeneTable;
    
    /*
        Retrieve master gene table
    */
    funcdef get_gene_table_from_model(ModelOnlyInput params) returns (GeneTable output);
    
    typedef structure {
		list<string> reaction_headings;
		list<list<string>> reaction_data;
		
		list<string> compound_headings;
		list<list<string>> compound_data;
		
		list<string> transportable_compounds;
		list<string> model_genes;
    } ModelData;
    
    /*
        Retrieve model data
    */
    funcdef get_model_data(ModelOnlyInput params) returns (GeneTable output);
	
	typedef structure {
		string workspace;
		string model;
		string carbon_source;
		string target;
		string base_media;
		string media_workspace;
		list<string> kos;
		mapping<string,float> kds;
		mapping<string,float> inductions;
		mapping<string,float> cofactor_stoichiometry;
    } ModelInput;
	
	typedef structure {
		string id;
		int intermediate;
		float flux;
		float max_flux;
		float min_flux;
    } PathwayReaction;
	
	typedef structure {
		list<PathwayReaction> pathway_reactions;
		float ATP_cost;
		mapping<string,float> cofactor_stoichiometry;
    } PathwayReactions;
    
    /*
        Compute the peripheral biosynthesis pathway for the selected target
    */
    funcdef compute_biosynthesis_pathway(ModelInput params) returns (PathwayReactions output);
    
    typedef structure {
		string id;
		string direction_for_competition;
		int intermediate;
		float flux;
		float max_flux;
		float min_flux;
    } CompetingReactionData;
    
    typedef structure {
		mapping<string metabolite,CompetingReactionData> competing_reactions;
    } CompetingReactions;
    
    /*
        Compute the peripheral biosynthesis pathway for the selected target
    */
    funcdef compute_competing_pathways(ModelInput params) returns (CompetingReactions output);
    
    typedef structure {
		string id;
		string direction_for_competition;
		float flux;
		float max_flux;
		float min_flux;
    } CofactorReactionData;
    
    typedef structure {
		mapping<string cofactor,CofactorReactionData> cofactor_reactions;
    } CofactorReactions;
    
    /*
        Compute the peripheral biosynthesis pathway for the selected target
    */
    funcdef compute_cofactor_consuming_pathways(ModelInput params) returns (CofactorReactions output);
    
    typedef structure {
		list<tuple<string,float> > ko_targets;
		list<tuple<string,float> > induction_targets;
    } TargetModifications;
    
    /*
        Systematically try all KO and return predicted production from each KO
    */
    funcdef systematic_target_search(ModelInput params) returns (TargetModifications output);
    
    typedef structure {
		mapping<string reaction,float flux> reaction_fluxes;
		mapping<string compound,float flux> metabolite_flux;
    } FluxData;
    
    /*
        Compute the peripheral biosynthesis pathway for the selected target
    */
    funcdef compute_flux(ModelInput params) returns (FluxData output);
    
    typedef structure {
		string model;
    } MapInput;
    
    typedef structure {
		string id;
		string name;
		list<string> reactions;
		list<string> compounds;
		list<string> genes;
		int total_reactions;
		int total_compounds;
    } MapData;
    
    typedef structure {
		list<MapData> maps;
    } MapList;
    
    /*
        List maps available for viewing
    */
    funcdef list_maps(MapInput) returns (MapList output);
    
    typedef structure {
		string map_id;
		mapping<string,float> reaction_flux;
		mapping<string,float> gene_expression;
		mapping<string,float> metabolite_values;
		string model;
		string target;
		list<string> kos;
		list<string> kds;
		list<string> inductions;
    } EscherInput;
    
    typedef structure {
		string html;
    } EscherOutput;

    /*
        Get an escher map painted with input data rendered in HTML format
    */
    funcdef get_map(EscherInput params) returns (EscherOutput output);
};
