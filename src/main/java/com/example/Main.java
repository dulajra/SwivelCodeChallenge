package com.example;

import com.example.handlers.OrganizationPrintHandler;
import com.example.handlers.OrganizationSearchHandler;
import com.example.handlers.TicketPrintHandler;
import com.example.handlers.TicketSearchHandler;
import com.example.handlers.UserPrintHandler;
import com.example.handlers.UserSearchHandler;
import com.example.models.BaseModel;
import com.example.models.Organization;
import com.example.models.SearchTO;
import com.example.models.Ticket;
import com.example.models.User;
import com.example.utils.DataLoader;
import com.example.utils.InputUtils;
import com.example.utils.PropertyLoader;

import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        // Load data file paths from application.properties file
        Properties properties = PropertyLoader.load("application.properties");

        // Deserialize data using provided JSON files
        List<Organization> organizations = DataLoader.loadOrganizations(properties.getProperty("files.json.organizations"));
        List<User> users = DataLoader.loadUsers(properties.getProperty("files.json.users"));
        List<Ticket> tickets = DataLoader.loadTickets(properties.getProperty("files.json.tickets"));

        // Initialize search handlers and build the search handlers chain.
        OrganizationSearchHandler osh = new OrganizationSearchHandler(organizations);
        UserSearchHandler ush = new UserSearchHandler(users);
        TicketSearchHandler tsh = new TicketSearchHandler(tickets);

        osh.setNextHandler(ush);
        ush.setNextHandler(tsh);

        // Initialize print handlers and build the print handlers chain.
        OrganizationPrintHandler oph = new OrganizationPrintHandler(users, tickets);
        UserPrintHandler uph = new UserPrintHandler(organizations, tickets);
        TicketPrintHandler tph = new TicketPrintHandler(users, organizations);

        oph.setNextHandler(uph);
        uph.setNextHandler(tph);

        SearchTO searchTO;

        // Start taking user inputs and search operations
        while ((searchTO = InputUtils.startSearch()) != null) {
            try {
                List<BaseModel> search = osh.search(searchTO);
                oph.printResults(search);
            } catch (UnsupportedOperationException ex) {
                System.err.println("Oops! Something went wrong. " + ex.getMessage());
            }
        }
    }
}
