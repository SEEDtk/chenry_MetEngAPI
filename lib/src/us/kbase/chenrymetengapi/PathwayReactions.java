
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
 * <p>Original spec-file type: PathwayReactions</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "pathway_reactions",
    "ATP_cost",
    "cofactor_stoichiometry"
})
public class PathwayReactions {

    @JsonProperty("pathway_reactions")
    private List<PathwayReaction> pathwayReactions;
    @JsonProperty("ATP_cost")
    private java.lang.Double ATPCost;
    @JsonProperty("cofactor_stoichiometry")
    private Map<String, Double> cofactorStoichiometry;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("pathway_reactions")
    public List<PathwayReaction> getPathwayReactions() {
        return pathwayReactions;
    }

    @JsonProperty("pathway_reactions")
    public void setPathwayReactions(List<PathwayReaction> pathwayReactions) {
        this.pathwayReactions = pathwayReactions;
    }

    public PathwayReactions withPathwayReactions(List<PathwayReaction> pathwayReactions) {
        this.pathwayReactions = pathwayReactions;
        return this;
    }

    @JsonProperty("ATP_cost")
    public java.lang.Double getATPCost() {
        return ATPCost;
    }

    @JsonProperty("ATP_cost")
    public void setATPCost(java.lang.Double ATPCost) {
        this.ATPCost = ATPCost;
    }

    public PathwayReactions withATPCost(java.lang.Double ATPCost) {
        this.ATPCost = ATPCost;
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

    public PathwayReactions withCofactorStoichiometry(Map<String, Double> cofactorStoichiometry) {
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
        return ((((((((("PathwayReactions"+" [pathwayReactions=")+ pathwayReactions)+", ATPCost=")+ ATPCost)+", cofactorStoichiometry=")+ cofactorStoichiometry)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
