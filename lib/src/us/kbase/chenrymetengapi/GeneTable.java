
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
 * <p>Original spec-file type: GeneTable</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "headings",
    "data"
})
public class GeneTable {

    @JsonProperty("headings")
    private List<String> headings;
    @JsonProperty("data")
    private List<List<String>> data;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("headings")
    public List<String> getHeadings() {
        return headings;
    }

    @JsonProperty("headings")
    public void setHeadings(List<String> headings) {
        this.headings = headings;
    }

    public GeneTable withHeadings(List<String> headings) {
        this.headings = headings;
        return this;
    }

    @JsonProperty("data")
    public List<List<String>> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public GeneTable withData(List<List<String>> data) {
        this.data = data;
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
        return ((((((("GeneTable"+" [headings=")+ headings)+", data=")+ data)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
