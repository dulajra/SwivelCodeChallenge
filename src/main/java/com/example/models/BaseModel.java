package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
abstract public class BaseModel implements Serializable {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("created_at")
    private String createdAt;

    private String url;
    private List<String> tags;
}
