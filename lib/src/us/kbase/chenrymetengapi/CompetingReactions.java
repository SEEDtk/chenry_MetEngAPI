
package us.kbase.chenrymetengapi;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: CompetingReactions</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "competing_reactions"
})
public class CompetingReactions {

    @JsonProperty("competing_reactions")
    private Map<String, CompetingReactionData> competingReactions;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("competing_reactions")
    public Map<String, CompetingReactionData> getCompetingReactions() {
        return competingReactions;
    }

    @JsonProperty("competing_reactions")
    public void setCompetingReactions(Map<String, CompetingReactionData> competingReactions) {
        this.competingReactions = competingReactions;
    }

    public CompetingReactions withCompetingReactions(Map<String, CompetingReactionData> competingReactions) {
        this.competingReactions = competingReactions;
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
        return ((((("CompetingReactions"+" [competingReactions=")+ competingReactions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
