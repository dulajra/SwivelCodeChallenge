package com.example.handlers;

import com.example.models.BaseModel;
import com.example.models.Organization;
import com.example.models.Ticket;
import com.example.models.User;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class OrganizationPrintHandler extends PrintHandler {

    private List<User> allUsers;
    private List<Ticket> allTickets;

    public OrganizationPrintHandler(List<User> allUsers, List<Ticket> allTickets) {
        this.allUsers = allUsers;
        this.allTickets = allTickets;
    }

    @Override
    public void printResults(List<BaseModel> listToPrint) {
        if (!CollectionUtils.isEmpty(listToPrint) && listToPrint.get(0) instanceof Organization) {
            listToPrint.forEach(item -> {
                printBaseModel(item);

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

                System.out.print("\n");
            });
        } else if (CollectionUtils.isEmpty(listToPrint)) {
            System.out.print("\nNo results found!\n");
        } else {
            this.getNextHandler().printResults(listToPrint);
        }
    }
}
