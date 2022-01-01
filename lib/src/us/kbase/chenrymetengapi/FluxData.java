
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
 * <p>Original spec-file type: FluxData</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "reaction_fluxes",
    "metabolite_flux"
})
public class FluxData {

    @JsonProperty("reaction_fluxes")
    private Map<String, Double> reactionFluxes;
    @JsonProperty("metabolite_flux")
    private Map<String, Double> metaboliteFlux;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("reaction_fluxes")
    public Map<String, Double> getReactionFluxes() {
        return reactionFluxes;
    }

    @JsonProperty("reaction_fluxes")
    public void setReactionFluxes(Map<String, Double> reactionFluxes) {
        this.reactionFluxes = reactionFluxes;
    }

    public FluxData withReactionFluxes(Map<String, Double> reactionFluxes) {
        this.reactionFluxes = reactionFluxes;
        return this;
    }

    @JsonProperty("metabolite_flux")
    public Map<String, Double> getMetaboliteFlux() {
        return metaboliteFlux;
    }

    @JsonProperty("metabolite_flux")
    public void setMetaboliteFlux(Map<String, Double> metaboliteFlux) {
        this.metaboliteFlux = metaboliteFlux;
    }

    public FluxData withMetaboliteFlux(Map<String, Double> metaboliteFlux) {
        this.metaboliteFlux = metaboliteFlux;
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
        return ((((((("FluxData"+" [reactionFluxes=")+ reactionFluxes)+", metaboliteFlux=")+ metaboliteFlux)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
