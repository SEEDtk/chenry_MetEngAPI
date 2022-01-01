
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
 * <p>Original spec-file type: EscherInput</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "map_id",
    "reaction_flux",
    "gene_expression",
    "metabolite_values",
    "model",
    "target",
    "kos",
    "kds",
    "inductions"
})
public class EscherInput {

    @JsonProperty("map_id")
    private java.lang.String mapId;
    @JsonProperty("reaction_flux")
    private Map<String, Double> reactionFlux;
    @JsonProperty("gene_expression")
    private Map<String, Double> geneExpression;
    @JsonProperty("metabolite_values")
    private Map<String, Double> metaboliteValues;
    @JsonProperty("model")
    private java.lang.String model;
    @JsonProperty("target")
    private java.lang.String target;
    @JsonProperty("kos")
    private List<String> kos;
    @JsonProperty("kds")
    private List<String> kds;
    @JsonProperty("inductions")
    private List<String> inductions;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("map_id")
    public java.lang.String getMapId() {
        return mapId;
    }

    @JsonProperty("map_id")
    public void setMapId(java.lang.String mapId) {
        this.mapId = mapId;
    }

    public EscherInput withMapId(java.lang.String mapId) {
        this.mapId = mapId;
        return this;
    }

    @JsonProperty("reaction_flux")
    public Map<String, Double> getReactionFlux() {
        return reactionFlux;
    }

    @JsonProperty("reaction_flux")
    public void setReactionFlux(Map<String, Double> reactionFlux) {
        this.reactionFlux = reactionFlux;
    }

    public EscherInput withReactionFlux(Map<String, Double> reactionFlux) {
        this.reactionFlux = reactionFlux;
        return this;
    }

    @JsonProperty("gene_expression")
    public Map<String, Double> getGeneExpression() {
        return geneExpression;
    }

    @JsonProperty("gene_expression")
    public void setGeneExpression(Map<String, Double> geneExpression) {
        this.geneExpression = geneExpression;
    }

    public EscherInput withGeneExpression(Map<String, Double> geneExpression) {
        this.geneExpression = geneExpression;
        return this;
    }

    @JsonProperty("metabolite_values")
    public Map<String, Double> getMetaboliteValues() {
        return metaboliteValues;
    }

    @JsonProperty("metabolite_values")
    public void setMetaboliteValues(Map<String, Double> metaboliteValues) {
        this.metaboliteValues = metaboliteValues;
    }

    public EscherInput withMetaboliteValues(Map<String, Double> metaboliteValues) {
        this.metaboliteValues = metaboliteValues;
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

    public EscherInput withModel(java.lang.String model) {
        this.model = model;
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

    public EscherInput withTarget(java.lang.String target) {
        this.target = target;
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

    public EscherInput withKos(List<String> kos) {
        this.kos = kos;
        return this;
    }

    @JsonProperty("kds")
    public List<String> getKds() {
        return kds;
    }

    @JsonProperty("kds")
    public void setKds(List<String> kds) {
        this.kds = kds;
    }

    public EscherInput withKds(List<String> kds) {
        this.kds = kds;
        return this;
    }

    @JsonProperty("inductions")
    public List<String> getInductions() {
        return inductions;
    }

    @JsonProperty("inductions")
    public void setInductions(List<String> inductions) {
        this.inductions = inductions;
    }

    public EscherInput withInductions(List<String> inductions) {
        this.inductions = inductions;
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
        return ((((((((((((((((((((("EscherInput"+" [mapId=")+ mapId)+", reactionFlux=")+ reactionFlux)+", geneExpression=")+ geneExpression)+", metaboliteValues=")+ metaboliteValues)+", model=")+ model)+", target=")+ target)+", kos=")+ kos)+", kds=")+ kds)+", inductions=")+ inductions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
