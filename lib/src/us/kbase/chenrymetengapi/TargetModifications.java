
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
import us.kbase.common.service.Tuple2;


/**
 * <p>Original spec-file type: TargetModifications</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ko_targets",
    "induction_targets"
})
public class TargetModifications {

    @JsonProperty("ko_targets")
    private List<Tuple2 <String, Double>> koTargets;
    @JsonProperty("induction_targets")
    private List<Tuple2 <String, Double>> inductionTargets;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ko_targets")
    public List<Tuple2 <String, Double>> getKoTargets() {
        return koTargets;
    }

    @JsonProperty("ko_targets")
    public void setKoTargets(List<Tuple2 <String, Double>> koTargets) {
        this.koTargets = koTargets;
    }

    public TargetModifications withKoTargets(List<Tuple2 <String, Double>> koTargets) {
        this.koTargets = koTargets;
        return this;
    }

    @JsonProperty("induction_targets")
    public List<Tuple2 <String, Double>> getInductionTargets() {
        return inductionTargets;
    }

    @JsonProperty("induction_targets")
    public void setInductionTargets(List<Tuple2 <String, Double>> inductionTargets) {
        this.inductionTargets = inductionTargets;
    }

    public TargetModifications withInductionTargets(List<Tuple2 <String, Double>> inductionTargets) {
        this.inductionTargets = inductionTargets;
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
        return ((((((("TargetModifications"+" [koTargets=")+ koTargets)+", inductionTargets=")+ inductionTargets)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
