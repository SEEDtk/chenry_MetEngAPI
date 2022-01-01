
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
 * <p>Original spec-file type: CofactorReactionData</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "direction_for_competition",
    "flux",
    "max_flux",
    "min_flux"
})
public class CofactorReactionData {

    @JsonProperty("id")
    private String id;
    @JsonProperty("direction_for_competition")
    private String directionForCompetition;
    @JsonProperty("flux")
    private Double flux;
    @JsonProperty("max_flux")
    private Double maxFlux;
    @JsonProperty("min_flux")
    private Double minFlux;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public CofactorReactionData withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("direction_for_competition")
    public String getDirectionForCompetition() {
        return directionForCompetition;
    }

    @JsonProperty("direction_for_competition")
    public void setDirectionForCompetition(String directionForCompetition) {
        this.directionForCompetition = directionForCompetition;
    }

    public CofactorReactionData withDirectionForCompetition(String directionForCompetition) {
        this.directionForCompetition = directionForCompetition;
        return this;
    }

    @JsonProperty("flux")
    public Double getFlux() {
        return flux;
    }

    @JsonProperty("flux")
    public void setFlux(Double flux) {
        this.flux = flux;
    }

    public CofactorReactionData withFlux(Double flux) {
        this.flux = flux;
        return this;
    }

    @JsonProperty("max_flux")
    public Double getMaxFlux() {
        return maxFlux;
    }

    @JsonProperty("max_flux")
    public void setMaxFlux(Double maxFlux) {
        this.maxFlux = maxFlux;
    }

    public CofactorReactionData withMaxFlux(Double maxFlux) {
        this.maxFlux = maxFlux;
        return this;
    }

    @JsonProperty("min_flux")
    public Double getMinFlux() {
        return minFlux;
    }

    @JsonProperty("min_flux")
    public void setMinFlux(Double minFlux) {
        this.minFlux = minFlux;
    }

    public CofactorReactionData withMinFlux(Double minFlux) {
        this.minFlux = minFlux;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return ((((((((((((("CofactorReactionData"+" [id=")+ id)+", directionForCompetition=")+ directionForCompetition)+", flux=")+ flux)+", maxFlux=")+ maxFlux)+", minFlux=")+ minFlux)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
