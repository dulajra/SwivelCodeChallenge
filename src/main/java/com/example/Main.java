package com.example;

import com.example.handlers.OrganizationSearchHandler;
import com.example.handlers.TicketSearchHandler;
import com.example.handlers.UserSearchHandler;
import com.example.models.BaseModel;
import com.example.models.Organization;
import com.example.models.SearchTO;
import com.example.models.Ticket;
import com.example.models.User;
import com.example.utils.DataLoader;
import com.example.utils.InputUtils;
import com.example.utils.PrintUtils;
import com.example.utils.PropertyLoader;

import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = PropertyLoader.load("application.properties");

        List<Organization> organizations = DataLoader.loadOrganizations(properties.getProperty("files.json.organizations"));
        List<User> users = DataLoader.loadUsers(properties.getProperty("files.json.users"));
        List<Ticket> tickets = DataLoader.loadTickets(properties.getProperty("files.json.tickets"));

        OrganizationSearchHandler osh = new OrganizationSearchHandler(organizations);
        UserSearchHandler ush = new UserSearchHandler(users);
        TicketSearchHandler tsh = new TicketSearchHandler(tickets);

        osh.setNextHandler(ush);
        ush.setNextHandler(tsh);

        SearchTO searchTO;

        while ((searchTO = InputUtils.startSearch()) != null) {
            try {
                List<BaseModel> search = osh.search(searchTO);
                PrintUtils.printResultsWithAssociations(search, organizations, users, tickets);
            } catch (UnsupportedOperationException ex) {
                System.err.println("Oops! Something went wrong. " + ex.getMessage());
            }
        }
    }
}
