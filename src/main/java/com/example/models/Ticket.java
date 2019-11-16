package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Ticket implements Serializable {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("has_incidents")
    private boolean hasIncidents;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("submitter_id")
    private String submitterId;

    @JsonProperty("assignee_id")
    private String assigneeId;

    @JsonProperty("organization_id")
    private String organizationId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("due_at")
    private String dueAt;

    private String url;
    private String type;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private String via;
    private List<String> tags;

}
