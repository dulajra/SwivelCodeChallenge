package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class User extends BaseModel {

    @JsonProperty("organization_id")
    private String organizationId;

    @JsonProperty("last_login_at")
    private String lastLoginAt;

    private boolean active;
    private boolean verified;
    private boolean shared;
    private boolean suspended;
    private String name;
    private String alias;
    private String locale;
    private String timezone;
    private String email;
    private String phone;
    private String signature;
    private String role;

    public String getOrganizationName(List<Organization> allOrganizations) {
        return allOrganizations
                .stream()
                .filter(organization -> StringUtils.equals(organization.getId(), this.organizationId))
                .findFirst()
                .map(Organization::getName)
                .orElse(null);
    }

    public List<Ticket> getTicketsAssignedToMe(List<Ticket> allTickets) {
        return allTickets
                .stream()
                .filter(ticket -> StringUtils.equals(ticket.getAssigneeId(), this.getId()))
                .collect(Collectors.toList());
    }

    public List<Ticket> getTicketsSubmittedByMe(List<Ticket> allTickets) {
        return allTickets
                .stream()
                .filter(ticket -> StringUtils.equals(ticket.getSubmitterId(), this.getId()))
                .collect(Collectors.toList());
    }
}
