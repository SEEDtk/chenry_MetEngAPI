
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
 * <p>Original spec-file type: MapData</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "reactions",
    "compounds",
    "genes",
    "total_reactions",
    "total_compounds"
})
public class MapData {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("reactions")
    private List<String> reactions;
    @JsonProperty("compounds")
    private List<String> compounds;
    @JsonProperty("genes")
    private List<String> genes;
    @JsonProperty("total_reactions")
    private Long totalReactions;
    @JsonProperty("total_compounds")
    private Long totalCompounds;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public MapData withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public MapData withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("reactions")
    public List<String> getReactions() {
        return reactions;
    }

    @JsonProperty("reactions")
    public void setReactions(List<String> reactions) {
        this.reactions = reactions;
    }

    public MapData withReactions(List<String> reactions) {
        this.reactions = reactions;
        return this;
    }

    @JsonProperty("compounds")
    public List<String> getCompounds() {
        return compounds;
    }

    @JsonProperty("compounds")
    public void setCompounds(List<String> compounds) {
        this.compounds = compounds;
    }

    public MapData withCompounds(List<String> compounds) {
        this.compounds = compounds;
        return this;
    }

    @JsonProperty("genes")
    public List<String> getGenes() {
        return genes;
    }

    @JsonProperty("genes")
    public void setGenes(List<String> genes) {
        this.genes = genes;
    }

    public MapData withGenes(List<String> genes) {
        this.genes = genes;
        return this;
    }

    @JsonProperty("total_reactions")
    public Long getTotalReactions() {
        return totalReactions;
    }

    @JsonProperty("total_reactions")
    public void setTotalReactions(Long totalReactions) {
        this.totalReactions = totalReactions;
    }

    public MapData withTotalReactions(Long totalReactions) {
        this.totalReactions = totalReactions;
        return this;
    }

    @JsonProperty("total_compounds")
    public Long getTotalCompounds() {
        return totalCompounds;
    }

    @JsonProperty("total_compounds")
    public void setTotalCompounds(Long totalCompounds) {
        this.totalCompounds = totalCompounds;
    }

    public MapData withTotalCompounds(Long totalCompounds) {
        this.totalCompounds = totalCompounds;
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
        return ((((((((((((((((("MapData"+" [id=")+ id)+", name=")+ name)+", reactions=")+ reactions)+", compounds=")+ compounds)+", genes=")+ genes)+", totalReactions=")+ totalReactions)+", totalCompounds=")+ totalCompounds)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
