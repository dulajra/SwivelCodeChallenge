package com.example.handlers;

import com.example.models.BaseModel;
import com.example.models.Organization;
import com.example.models.Ticket;
import com.example.models.User;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class TicketPrintHandler extends PrintHandler {

    private List<User> allUsers;
    private List<Organization> allOrganizations;

    public TicketPrintHandler(List<User> allUsers, List<Organization> allOrganizations) {
        this.allUsers = allUsers;
        this.allOrganizations = allOrganizations;
    }

    @Override
    public void printResults(List<BaseModel> listToPrint) {
        if (!CollectionUtils.isEmpty(listToPrint) && listToPrint.get(0) instanceof Ticket) {
            listToPrint.forEach(item -> {
                printBaseModel(item);

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

                System.out.print("\n");
            });
        } else if (CollectionUtils.isEmpty(listToPrint)) {
            System.out.print("\nNo results found!\n");
        } else {
            this.getNextHandler().printResults(listToPrint);
        }
    }
}
