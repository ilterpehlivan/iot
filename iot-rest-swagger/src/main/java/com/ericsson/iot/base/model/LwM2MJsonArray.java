package com.ericsson.iot.base.model;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "n",
        "sv",
        "v",
        "ov",
        "bv"
})
public class LwM2MJsonArray {
        @JsonProperty("n")
        private String n;
        @JsonProperty("sv")
        private String sv;
        @JsonProperty("v")
        private Integer v;
        @JsonProperty("ov")
        private String ov;
        @JsonProperty("bv")
        private Boolean bv;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("n")
        public String getN() {
            return n;
        }

        @JsonProperty("n")
        public void setN(String n) {
            this.n = n;
        }

        @JsonProperty("sv")
        public String getSv() {
            return sv;
        }

        @JsonProperty("sv")
        public void setSv(String sv) {
            this.sv = sv;
        }

        @JsonProperty("v")
        public Integer getV() {
            return v;
        }

        @JsonProperty("v")
        public void setV(Integer v) {
            this.v = v;
        }

        @JsonProperty("ov")
        public String getOv() {
            return ov;
        }

        @JsonProperty("ov")
        public void setOv(String ov) {
            this.ov = ov;
        }

        @JsonProperty("bv")
        public Boolean getBv() {
            return bv;
        }

        @JsonProperty("bv")
        public void setBv(Boolean bv) {
            this.bv = bv;
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
