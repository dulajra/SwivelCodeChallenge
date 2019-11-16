package com.example.utils;

import com.example.models.BaseModel;
import com.example.models.Organization;
import com.example.models.Ticket;
import com.example.models.User;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class PrintUtils {

    public static void printOrganizations(List<BaseModel> listToPrint, List<Organization> allOrganizations, List<User> allUsers, List<Ticket> allTickets) {
        if (!CollectionUtils.isEmpty(listToPrint)) {
            listToPrint.forEach(item -> {
                System.out.print("\n********************\n");

                System.out.printf("\n%-20s: %s", "_id", item.getId());
                System.out.printf("\n%-20s: %s", "external_id", item.getExternalId());
                System.out.printf("\n%-20s: %s", "created_at", item.getCreatedAt());
                System.out.printf("\n%-20s: %s", "url", item.getUrl());

                StringBuilder tagBuilder = new StringBuilder();
                item.getTags().forEach(tag -> tagBuilder.append(tag).append(", "));
                System.out.printf("\n%-20s: %s", "tags", tagBuilder.toString());

                if (item instanceof Organization) {
                    Organization organization = (Organization) item;

                    StringBuilder domainBuilder = new StringBuilder();
                    organization.getDomainNames().forEach(domain -> domainBuilder.append(domain).append(", "));
                    System.out.printf("\n%-20s: %s", "domain_names", domainBuilder.toString());

                    System.out.printf("\n%-20s: %b", "shared_tickets", organization.isSharedTickets());
                    System.out.printf("\n%-20s: %s", "name", organization.getName());
                    System.out.printf("\n%-20s: %s", "details", organization.getDetails());

                    StringBuilder ticketsBuilder = new StringBuilder();
                    organization.getMyTickets(allTickets).forEach(ticket -> ticketsBuilder.append(ticket.getSubject()).append(", "));
                    System.out.printf("\n%-20s: %s", "ticket_subjects", ticketsBuilder.toString());

                    StringBuilder userBuilder = new StringBuilder();
                    organization.getMyUsers(allUsers).forEach(user -> userBuilder.append(user.getName()).append(", "));
                    System.out.printf("\n%-20s: %s", "user_names", userBuilder.toString());
                } else if (item instanceof User) {
                    User user = (User) item;

                    System.out.printf("\n%-20s: %s", "organization_id", user.getOrganizationId());
                    System.out.printf("\n%-20s: %s", "last_login_at", user.getLastLoginAt());
                    System.out.printf("\n%-20s: %b", "active", user.isActive());
                    System.out.printf("\n%-20s: %b", "verified", user.isVerified());
                    System.out.printf("\n%-20s: %b", "shared", user.isShared());
                    System.out.printf("\n%-20s: %b", "suspended", user.isSuspended());
                    System.out.printf("\n%-20s: %s", "name", user.getName());
                    System.out.printf("\n%-20s: %s", "alias", user.getAlias());
                    System.out.printf("\n%-20s: %s", "locale", user.getLocale());
                    System.out.printf("\n%-20s: %s", "timezone", user.getTimezone());
                    System.out.printf("\n%-20s: %s", "email", user.getEmail());
                    System.out.printf("\n%-20s: %s", "phone", user.getPhone());
                    System.out.printf("\n%-20s: %s", "signature", user.getSignature());
                    System.out.printf("\n%-20s: %s", "role", user.getRole());
                    System.out.printf("\n%-20s: %s", "organization_name", user.getOrganizationName(allOrganizations));

                    StringBuilder assignedBuilder = new StringBuilder();
                    user.getTicketsAssignedToMe(allTickets).forEach(ticket -> assignedBuilder.append(ticket.getSubject()).append(", "));
                    System.out.printf("\n%-20s: %s", "assigned_tickets_subjects", assignedBuilder.toString());

                    StringBuilder submittedBuilder = new StringBuilder();
                    user.getTicketsSubmittedByMe(allTickets).forEach(ticket -> submittedBuilder.append(ticket.getSubject()).append(", "));
                    System.out.printf("\n%-20s: %s", "submitted_tickets_subjects", submittedBuilder.toString());

                } else if (item instanceof Ticket) {
                    Ticket ticket = (Ticket) item;

                    System.out.printf("\n%-20s: %b", "has_incidents", ticket.isHasIncidents());
                    System.out.printf("\n%-20s: %s", "submitter_id", ticket.getSubmitterId());
                    System.out.printf("\n%-20s: %s", "assignee_id", ticket.getAssigneeId());
                    System.out.printf("\n%-20s: %s", "organization_id", ticket.getOrganizationId());
                    System.out.printf("\n%-20s: %s", "due_at", ticket.getDueAt());
                    System.out.printf("\n%-20s: %s", "type", ticket.getType());
                    System.out.printf("\n%-20s: %s", "subject", ticket.getSubject());
                    System.out.printf("\n%-20s: %s", "description", ticket.getDescription());
                    System.out.printf("\n%-20s: %s", "priority", ticket.getPriority());
                    System.out.printf("\n%-20s: %s", "status", ticket.getStatus());
                    System.out.printf("\n%-20s: %s", "via", ticket.getVia());
                    System.out.printf("\n%-20s: %s", "assignee_name", ticket.getAssigneeName(allUsers));
                    System.out.printf("\n%-20s: %s", "submitter_name", ticket.getSubmitterName(allUsers));
                    System.out.printf("\n%-20s: %s", "organization_name", ticket.getOrganizationName(allOrganizations));
                }

                System.out.print("\n");
            });
        }
    }
}
