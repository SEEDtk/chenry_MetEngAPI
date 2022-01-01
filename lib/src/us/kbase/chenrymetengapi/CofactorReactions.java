
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
 * <p>Original spec-file type: CofactorReactions</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "cofactor_reactions"
})
public class CofactorReactions {

    @JsonProperty("cofactor_reactions")
    private Map<String, CofactorReactionData> cofactorReactions;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("cofactor_reactions")
    public Map<String, CofactorReactionData> getCofactorReactions() {
        return cofactorReactions;
    }

    @JsonProperty("cofactor_reactions")
    public void setCofactorReactions(Map<String, CofactorReactionData> cofactorReactions) {
        this.cofactorReactions = cofactorReactions;
    }

    public CofactorReactions withCofactorReactions(Map<String, CofactorReactionData> cofactorReactions) {
        this.cofactorReactions = cofactorReactions;
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
        return ((((("CofactorReactions"+" [cofactorReactions=")+ cofactorReactions)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
