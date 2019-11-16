package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Organization implements Serializable {

    @JsonProperty("_id")
    private int id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("domain_names")
    private List<String> domainNames;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("shared_tickets")
    private boolean sharedTickets;

    private String url;
    private String name;
    private String details;
    private List<String> tags;

}
