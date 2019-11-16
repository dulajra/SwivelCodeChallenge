package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
@Setter
public class Ticket extends BaseModel {

    @JsonProperty("has_incidents")
    private boolean hasIncidents;

    @JsonProperty("submitter_id")
    private String submitterId;

    @JsonProperty("assignee_id")
    private String assigneeId;

    @JsonProperty("organization_id")
    private String organizationId;

    @JsonProperty("due_at")
    private String dueAt;

    private String type;
    private String subject;
    private String description;
    private String priority;
    private String status;
    private String via;

    public String getSubmitterName(List<User> allUsers) {
        return allUsers
                .stream()
                .filter(user -> StringUtils.equals(user.getId(), this.submitterId))
                .findFirst()
                .map(User::getName)
                .orElse(null);
    }

    public String getAssigneeName(List<User> allUsers) {
        return allUsers
                .stream()
                .filter(user -> StringUtils.equals(user.getId(), this.assigneeId))
                .findFirst()
                .map(User::getName)
                .orElse(null);
    }

    public String getOrganizationName(List<Organization> allOrganizations) {
        return allOrganizations
                .stream()
                .filter(organization -> StringUtils.equals(organization.getId(), this.organizationId))
                .findFirst()
                .map(Organization::getName)
                .orElse(null);
    }

}
