
package us.kbase.chenrymetengapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: ModelInput</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "workspace",
    "model",
    "carbon_source",
    "target",
    "base_media",
    "media_workspace",
    "kos",
    "kds",
    "inductions",
    "cofactor_stoichiometry"
})
public class ModelInput {

    @JsonProperty("workspace")
    private java.lang.String workspace;
    @JsonProperty("model")
    private java.lang.String model;
    @JsonProperty("carbon_source")
    private java.lang.String carbonSource;
    @JsonProperty("target")
    private java.lang.String target;
    @JsonProperty("base_media")
    private java.lang.String baseMedia;
    @JsonProperty("media_workspace")
    private java.lang.String mediaWorkspace;
    @JsonProperty("kos")
    private List<String> kos;
    @JsonProperty("kds")
    private Map<String, Double> kds;
    @JsonProperty("inductions")
    private Map<String, Double> inductions;
    @JsonProperty("cofactor_stoichiometry")
    private Map<String, Double> cofactorStoichiometry;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("workspace")
    public java.lang.String getWorkspace() {
        return workspace;
    }

    @JsonProperty("workspace")
    public void setWorkspace(java.lang.String workspace) {
        this.workspace = workspace;
    }

    public ModelInput withWorkspace(java.lang.String workspace) {
        this.workspace = workspace;
        return this;
    }

    @JsonProperty("model")
    public java.lang.String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(java.lang.String model) {
        this.model = model;
    }

    public ModelInput withModel(java.lang.String model) {
        this.model = model;
        return this;
    }

    @JsonProperty("carbon_source")
    public java.lang.String getCarbonSource() {
        return carbonSource;
    }

    @JsonProperty("carbon_source")
    public void setCarbonSource(java.lang.String carbonSource) {
        this.carbonSource = carbonSource;
    }

    public ModelInput withCarbonSource(java.lang.String carbonSource) {
        this.carbonSource = carbonSource;
        return this;
    }

    @JsonProperty("target")
    public java.lang.String getTarget() {
        return target;
    }

    @JsonProperty("target")
    public void setTarget(java.lang.String target) {
        this.target = target;
    }

    public ModelInput withTarget(java.lang.String target) {
        this.target = target;
        return this;
    }

    @JsonProperty("base_media")
    public java.lang.String getBaseMedia() {
        return baseMedia;
    }

    @JsonProperty("base_media")
    public void setBaseMedia(java.lang.String baseMedia) {
        this.baseMedia = baseMedia;
    }

    public ModelInput withBaseMedia(java.lang.String baseMedia) {
        this.baseMedia = baseMedia;
        return this;
    }

    @JsonProperty("media_workspace")
    public java.lang.String getMediaWorkspace() {
        return mediaWorkspace;
    }

    @JsonProperty("media_workspace")
    public void setMediaWorkspace(java.lang.String mediaWorkspace) {
        this.mediaWorkspace = mediaWorkspace;
    }

    public ModelInput withMediaWorkspace(java.lang.String mediaWorkspace) {
        this.mediaWorkspace = mediaWorkspace;
        return this;
    }

    @JsonProperty("kos")
    public List<String> getKos() {
        return kos;
    }

    @JsonProperty("kos")
    public void setKos(List<String> kos) {
        this.kos = kos;
    }

    public ModelInput withKos(List<String> kos) {
        this.kos = kos;
        return this;
    }

    @JsonProperty("kds")
    public Map<String, Double> getKds() {
        return kds;
    }

    @JsonProperty("kds")
    public void setKds(Map<String, Double> kds) {
        this.kds = kds;
    }

    public ModelInput withKds(Map<String, Double> kds) {
        this.kds = kds;
        return this;
    }

    @JsonProperty("inductions")
    public Map<String, Double> getInductions() {
        return inductions;
    }

    @JsonProperty("inductions")
    public void setInductions(Map<String, Double> inductions) {
        this.inductions = inductions;
    }

    public ModelInput withInductions(Map<String, Double> inductions) {
        this.inductions = inductions;
        return this;
    }

    @JsonProperty("cofactor_stoichiometry")
    public Map<String, Double> getCofactorStoichiometry() {
        return cofactorStoichiometry;
    }

    @JsonProperty("cofactor_stoichiometry")
    public void setCofactorStoichiometry(Map<String, Double> cofactorStoichiometry) {
        this.cofactorStoichiometry = cofactorStoichiometry;
    }

    public ModelInput withCofactorStoichiometry(Map<String, Double> cofactorStoichiometry) {
        this.cofactorStoichiometry = cofactorStoichiometry;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((((((((((((((((((((("ModelInput"+" [workspace=")+ workspace)+", model=")+ model)+", carbonSource=")+ carbonSource)+", target=")+ target)+", baseMedia=")+ baseMedia)+", mediaWorkspace=")+ mediaWorkspace)+", kos=")+ kos)+", kds=")+ kds)+", inductions=")+ inductions)+", cofactorStoichiometry=")+ cofactorStoichiometry)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
