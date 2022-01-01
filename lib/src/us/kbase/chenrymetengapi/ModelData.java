
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
 * <p>Original spec-file type: ModelData</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "reaction_headings",
    "reaction_data",
    "compound_headings",
    "compound_data",
    "transportable_compounds",
    "model_genes"
})
public class ModelData {

    @JsonProperty("reaction_headings")
    private List<String> reactionHeadings;
    @JsonProperty("reaction_data")
    private List<List<String>> reactionData;
    @JsonProperty("compound_headings")
    private List<String> compoundHeadings;
    @JsonProperty("compound_data")
    private List<List<String>> compoundData;
    @JsonProperty("transportable_compounds")
    private List<String> transportableCompounds;
    @JsonProperty("model_genes")
    private List<String> modelGenes;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("reaction_headings")
    public List<String> getReactionHeadings() {
        return reactionHeadings;
    }

    @JsonProperty("reaction_headings")
    public void setReactionHeadings(List<String> reactionHeadings) {
        this.reactionHeadings = reactionHeadings;
    }

    public ModelData withReactionHeadings(List<String> reactionHeadings) {
        this.reactionHeadings = reactionHeadings;
        return this;
    }

    @JsonProperty("reaction_data")
    public List<List<String>> getReactionData() {
        return reactionData;
    }

    @JsonProperty("reaction_data")
    public void setReactionData(List<List<String>> reactionData) {
        this.reactionData = reactionData;
    }

    public ModelData withReactionData(List<List<String>> reactionData) {
        this.reactionData = reactionData;
        return this;
    }

    @JsonProperty("compound_headings")
    public List<String> getCompoundHeadings() {
        return compoundHeadings;
    }

    @JsonProperty("compound_headings")
    public void setCompoundHeadings(List<String> compoundHeadings) {
        this.compoundHeadings = compoundHeadings;
    }

    public ModelData withCompoundHeadings(List<String> compoundHeadings) {
        this.compoundHeadings = compoundHeadings;
        return this;
    }

    @JsonProperty("compound_data")
    public List<List<String>> getCompoundData() {
        return compoundData;
    }

    @JsonProperty("compound_data")
    public void setCompoundData(List<List<String>> compoundData) {
        this.compoundData = compoundData;
    }

    public ModelData withCompoundData(List<List<String>> compoundData) {
        this.compoundData = compoundData;
        return this;
    }

    @JsonProperty("transportable_compounds")
    public List<String> getTransportableCompounds() {
        return transportableCompounds;
    }

    @JsonProperty("transportable_compounds")
    public void setTransportableCompounds(List<String> transportableCompounds) {
        this.transportableCompounds = transportableCompounds;
    }

    public ModelData withTransportableCompounds(List<String> transportableCompounds) {
        this.transportableCompounds = transportableCompounds;
        return this;
    }

    @JsonProperty("model_genes")
    public List<String> getModelGenes() {
        return modelGenes;
    }

    @JsonProperty("model_genes")
    public void setModelGenes(List<String> modelGenes) {
        this.modelGenes = modelGenes;
    }

    public ModelData withModelGenes(List<String> modelGenes) {
        this.modelGenes = modelGenes;
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
        return ((((((((((((((("ModelData"+" [reactionHeadings=")+ reactionHeadings)+", reactionData=")+ reactionData)+", compoundHeadings=")+ compoundHeadings)+", compoundData=")+ compoundData)+", transportableCompounds=")+ transportableCompounds)+", modelGenes=")+ modelGenes)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
