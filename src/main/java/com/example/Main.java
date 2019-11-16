package com.example;

import com.example.handlers.OrganizationSearchHandler;
import com.example.handlers.TicketSearchHandler;
import com.example.handlers.UserSearchHandler;
import com.example.models.BaseModel;
import com.example.models.Organization;
import com.example.models.Ticket;
import com.example.models.User;
import com.example.utils.DataLoader;
import com.example.utils.PrintUtils;
import com.example.utils.PropertyLoader;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

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
        tsh.setNextHandler(osh);

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nWhat do you want to do?" +
                "\n\t(1) Search" +
                "\n\t(2) Help" +
                "\n\t(3) Quit" +
                "\n");

        switch (scanner.nextInt()) {
            case 1:
                while (true) {
                    System.out.print("\nWhat do you want to search? (1) Organizations (2) Users (3) Tickets (0) Exit application: ");
                    int searchType = scanner.nextInt();

                    switch (searchType) {
                        case 0:
                            System.out.print("\nGood Bye!\n");
                            System.exit(0);
                        case 1:
                            System.out.print("\nUsing what field do you want to search?" +
                                    "\n\t(1) _id" +
                                    "\n\t(2) external_id" +
                                    "\n\t(3) created_at" +
                                    "\n\t(4) url" +
                                    "\n\t(5) tags" +
                                    "\n\t(6) domain_names" +
                                    "\n\t(7) shared_tickets" +
                                    "\n\t(8) name" +
                                    "\n\t(9) details" +
                                    "\n");
                            break;
                        case 2:
                            System.out.print("\nUsing what field do you want to search?" +
                                    "\n\t(1) _id" +
                                    "\n\t(2) external_id" +
                                    "\n\t(3) created_at" +
                                    "\n\t(4) url" +
                                    "\n\t(5) tags" +
                                    "\n\t(6) organization_id" +
                                    "\n\t(7) last_login_at" +
                                    "\n\t(8) active" +
                                    "\n\t(9) verified" +
                                    "\n\t(10) shared" +
                                    "\n\t(11) suspended" +
                                    "\n\t(12) name" +
                                    "\n\t(13) alias" +
                                    "\n\t(14) locale" +
                                    "\n\t(15) timezone" +
                                    "\n\t(16) email" +
                                    "\n\t(17) phone" +
                                    "\n\t(18) signature" +
                                    "\n\t(19) role" +
                                    "\n");
                            break;
                        case 3:
                            System.out.print("\nUsing what field do you want to search?" +
                                    "\n\t(1) _id" +
                                    "\n\t(2) external_id" +
                                    "\n\t(3) created_at" +
                                    "\n\t(4) url" +
                                    "\n\t(5) tags" +
                                    "\n\t(6) has_incidents" +
                                    "\n\t(7) submitter_id" +
                                    "\n\t(8) assignee_id" +
                                    "\n\t(9) organization_id" +
                                    "\n\t(10) due_at" +
                                    "\n\t(11) type" +
                                    "\n\t(12) subject" +
                                    "\n\t(13) description" +
                                    "\n\t(14) priority" +
                                    "\n\t(15) status" +
                                    "\n\t(16) via" +
                                    "\n");
                        default:
                            System.out.print("Invalid selection. Only 0, 1, 2 & 3 are valid inputs: ");
                    }

                    int searchField = scanner.nextInt();
                    System.out.print("\nEnter your search value: ");
                    String searchKey = scanner.next();
                    List<BaseModel> search = osh.search(searchType, searchKey, searchField);

                    PrintUtils.printOrganizations(search, organizations, users, tickets);
                }
            case 2:
                // Display help menu
                break;
            case 3:
                System.exit(0);
        }
    }
}
