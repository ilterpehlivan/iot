package com.ericsson.iot.base.model;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bn",
        "e",
        "bt"
})
public class LwM2MJson {
        @JsonProperty("bn")
        private String bn;
        @JsonProperty("e")
        private List<LwM2MJsonArray> e = null;
        @JsonProperty("bt")
        private Integer bt;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("bn")
        public String getBn() {
            return bn;
        }

        @JsonProperty("bn")
        public void setBn(String bn) {
            this.bn = bn;
        }

        @JsonProperty("e")
        public List<LwM2MJsonArray> getE() {
            return e;
        }

        @JsonProperty("e")
        public void setE(List<LwM2MJsonArray> e) {
            this.e = e;
        }

        @JsonProperty("bt")
        public Integer getBt() {
            return bt;
        }

        @JsonProperty("bt")
        public void setBt(Integer bt) {
            this.bt = bt;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
}
