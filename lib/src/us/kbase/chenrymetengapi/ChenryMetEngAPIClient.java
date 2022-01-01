package us.kbase.chenrymetengapi;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.RpcContext;

/**
 * <p>Original spec-file module name: chenry_MetEngAPI</p>
 * <pre>
 * A KBase module: chenry_MetEngAPI
 * </pre>
 */
public class ChenryMetEngAPIClient {
    private JsonClientCaller caller;
    private String serviceVersion = null;


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public ChenryMetEngAPIClient(URL url) {
        caller = new JsonClientCaller(url);
    }

    /** Get the URL of the service with which this client communicates.
     * @return the service URL.
     */
    public URL getURL() {
        return caller.getURL();
    }

    /** Set the timeout between establishing a connection to a server and
     * receiving a response. A value of zero or null implies no timeout.
     * @param milliseconds the milliseconds to wait before timing out when
     * attempting to read from a server.
     */
    public void setConnectionReadTimeOut(Integer milliseconds) {
        this.caller.setConnectionReadTimeOut(milliseconds);
    }

    /** Check if this client allows insecure http (vs https) connections.
     * @return true if insecure connections are allowed.
     */
    public boolean isInsecureHttpConnectionAllowed() {
        return caller.isInsecureHttpConnectionAllowed();
    }

    /** Deprecated. Use isInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public boolean isAuthAllowedForHttp() {
        return caller.isAuthAllowedForHttp();
    }

    /** Set whether insecure http (vs https) connections should be allowed by
     * this client.
     * @param allowed true to allow insecure connections. Default false
     */
    public void setIsInsecureHttpConnectionAllowed(boolean allowed) {
        caller.setInsecureHttpConnectionAllowed(allowed);
    }

    /** Deprecated. Use setIsInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public void setAuthAllowedForHttp(boolean isAuthAllowedForHttp) {
        caller.setAuthAllowedForHttp(isAuthAllowedForHttp);
    }

    /** Set whether all SSL certificates, including self-signed certificates,
     * should be trusted.
     * @param trustAll true to trust all certificates. Default false.
     */
    public void setAllSSLCertificatesTrusted(final boolean trustAll) {
        caller.setAllSSLCertificatesTrusted(trustAll);
    }
    
    /** Check if this client trusts all SSL certificates, including
     * self-signed certificates.
     * @return true if all certificates are trusted.
     */
    public boolean isAllSSLCertificatesTrusted() {
        return caller.isAllSSLCertificatesTrusted();
    }
    /** Sets streaming mode on. In this case, the data will be streamed to
     * the server in chunks as it is read from disk rather than buffered in
     * memory. Many servers are not compatible with this feature.
     * @param streamRequest true to set streaming mode on, false otherwise.
     */
    public void setStreamingModeOn(boolean streamRequest) {
        caller.setStreamingModeOn(streamRequest);
    }

    /** Returns true if streaming mode is on.
     * @return true if streaming mode is on.
     */
    public boolean isStreamingModeOn() {
        return caller.isStreamingModeOn();
    }

    public void _setFileForNextRpcResponse(File f) {
        caller.setFileForNextRpcResponse(f);
    }

    public String getServiceVersion() {
        return this.serviceVersion;
    }

    public void setServiceVersion(String newValue) {
        this.serviceVersion = newValue;
    }

    /**
     * <p>Original spec-file function name: get_gene_table_from_model</p>
     * <pre>
     * Retrieve master gene table
     * </pre>
     * @param   params   instance of type {@link us.kbase.chenrymetengapi.ModelOnlyInput ModelOnlyInput}
     * @return   parameter "output" of type {@link us.kbase.chenrymetengapi.GeneTable GeneTable}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GeneTable getGeneTableFromModel(ModelOnlyInput params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GeneTable>> retType = new TypeReference<List<GeneTable>>() {};
        List<GeneTable> res = caller.jsonrpcCall("chenry_MetEngAPI.get_gene_table_from_model", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_model_data</p>
     * <pre>
     * Retrieve model data
     * </pre>
     * @param   params   instance of type {@link us.kbase.chenrymetengapi.ModelOnlyInput ModelOnlyInput}
     * @return   parameter "output" of type {@link us.kbase.chenrymetengapi.GeneTable GeneTable}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GeneTable getModelData(ModelOnlyInput params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GeneTable>> retType = new TypeReference<List<GeneTable>>() {};
        List<GeneTable> res = caller.jsonrpcCall("chenry_MetEngAPI.get_model_data", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: compute_biosynthesis_pathway</p>
     * <pre>
     * Compute the peripheral biosynthesis pathway for the selected target
     * </pre>
     * @param   params   instance of type {@link us.kbase.chenrymetengapi.ModelInput ModelInput}
     * @return   parameter "output" of type {@link us.kbase.chenrymetengapi.PathwayReactions PathwayReactions}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public PathwayReactions computeBiosynthesisPathway(ModelInput params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<PathwayReactions>> retType = new TypeReference<List<PathwayReactions>>() {};
        List<PathwayReactions> res = caller.jsonrpcCall("chenry_MetEngAPI.compute_biosynthesis_pathway", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: compute_competing_pathways</p>
     * <pre>
     * Compute the peripheral biosynthesis pathway for the selected target
     * </pre>
     * @param   params   instance of type {@link us.kbase.chenrymetengapi.ModelInput ModelInput}
     * @return   parameter "output" of type {@link us.kbase.chenrymetengapi.CompetingReactions CompetingReactions}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public CompetingReactions computeCompetingPathways(ModelInput params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<CompetingReactions>> retType = new TypeReference<List<CompetingReactions>>() {};
        List<CompetingReactions> res = caller.jsonrpcCall("chenry_MetEngAPI.compute_competing_pathways", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: compute_cofactor_consuming_pathways</p>
     * <pre>
     * Compute the peripheral biosynthesis pathway for the selected target
     * </pre>
     * @param   params   instance of type {@link us.kbase.chenrymetengapi.ModelInput ModelInput}
     * @return   parameter "output" of type {@link us.kbase.chenrymetengapi.CofactorReactions CofactorReactions}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public CofactorReactions computeCofactorConsumingPathways(ModelInput params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<CofactorReactions>> retType = new TypeReference<List<CofactorReactions>>() {};
        List<CofactorReactions> res = caller.jsonrpcCall("chenry_MetEngAPI.compute_cofactor_consuming_pathways", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: systematic_target_search</p>
     * <pre>
     * Systematically try all KO and return predicted production from each KO
     * </pre>
     * @param   params   instance of type {@link us.kbase.chenrymetengapi.ModelInput ModelInput}
     * @return   parameter "output" of type {@link us.kbase.chenrymetengapi.TargetModifications TargetModifications}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public TargetModifications systematicTargetSearch(ModelInput params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<TargetModifications>> retType = new TypeReference<List<TargetModifications>>() {};
        List<TargetModifications> res = caller.jsonrpcCall("chenry_MetEngAPI.systematic_target_search", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: compute_flux</p>
     * <pre>
     * Compute the peripheral biosynthesis pathway for the selected target
     * </pre>
     * @param   params   instance of type {@link us.kbase.chenrymetengapi.ModelInput ModelInput}
     * @return   parameter "output" of type {@link us.kbase.chenrymetengapi.FluxData FluxData}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public FluxData computeFlux(ModelInput params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<FluxData>> retType = new TypeReference<List<FluxData>>() {};
        List<FluxData> res = caller.jsonrpcCall("chenry_MetEngAPI.compute_flux", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: list_maps</p>
     * <pre>
     * List maps available for viewing
     * </pre>
     * @param   arg1   instance of type {@link us.kbase.chenrymetengapi.MapInput MapInput}
     * @return   parameter "output" of type {@link us.kbase.chenrymetengapi.MapList MapList}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public MapList listMaps(MapInput arg1, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(arg1);
        TypeReference<List<MapList>> retType = new TypeReference<List<MapList>>() {};
        List<MapList> res = caller.jsonrpcCall("chenry_MetEngAPI.list_maps", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_map</p>
     * <pre>
     * Get an escher map painted with input data rendered in HTML format
     * </pre>
     * @param   params   instance of type {@link us.kbase.chenrymetengapi.EscherInput EscherInput}
     * @return   parameter "output" of type {@link us.kbase.chenrymetengapi.EscherOutput EscherOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public EscherOutput getMap(EscherInput params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<EscherOutput>> retType = new TypeReference<List<EscherOutput>>() {};
        List<EscherOutput> res = caller.jsonrpcCall("chenry_MetEngAPI.get_map", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    public Map<String, Object> status(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<Map<String, Object>>> retType = new TypeReference<List<Map<String, Object>>>() {};
        List<Map<String, Object>> res = caller.jsonrpcCall("chenry_MetEngAPI.status", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }
}
