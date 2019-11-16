package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Organization extends BaseModel {

    @JsonProperty("domain_names")
    private List<String> domainNames;

    @JsonProperty("shared_tickets")
    private boolean sharedTickets;

    private String name;
    private String details;

    public List<Ticket> getMyTickets(List<Ticket> allTickets) {
        return allTickets
                .stream()
                .filter(ticket -> StringUtils.equals(ticket.getOrganizationId(), this.getId()))
                .collect(Collectors.toList());
    }

    public List<User> getMyUsers(List<User> allUsers) {
        return allUsers
                .stream()
                .filter(user -> StringUtils.equals(user.getOrganizationId(), this.getId()))
                .collect(Collectors.toList());
    }
}
