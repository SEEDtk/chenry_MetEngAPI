
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
 * <p>Original spec-file type: MapList</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "maps"
})
public class MapList {

    @JsonProperty("maps")
    private List<MapData> maps;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("maps")
    public List<MapData> getMaps() {
        return maps;
    }

    @JsonProperty("maps")
    public void setMaps(List<MapData> maps) {
        this.maps = maps;
    }

    public MapList withMaps(List<MapData> maps) {
        this.maps = maps;
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
        return ((((("MapList"+" [maps=")+ maps)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
