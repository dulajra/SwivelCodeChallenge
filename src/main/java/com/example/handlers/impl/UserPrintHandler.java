package com.example.handlers.impl;

import com.example.handlers.PrintHandler;
import com.example.models.BaseModel;
import com.example.models.Organization;
import com.example.models.Ticket;
import com.example.models.User;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class UserPrintHandler extends PrintHandler {

    private List<Organization> allOrganizations;
    private List<Ticket> allTickets;

    public UserPrintHandler(List<Organization> allOrganizations, List<Ticket> allTickets) {
        this.allOrganizations = allOrganizations;
        this.allTickets = allTickets;
    }

    @Override
    public void printResults(List<BaseModel> listToPrint) {
        if (!CollectionUtils.isEmpty(listToPrint) && listToPrint.get(0) instanceof User) {
            listToPrint.forEach(item -> {
                printBaseModel(item);

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

                System.out.print("\n");
            });
        } else if (CollectionUtils.isEmpty(listToPrint)) {
            System.out.print("\nNo results found!\n");
        } else {
            this.getNextHandler().printResults(listToPrint);
        }
    }
}
