package com.example;

import com.example.handlers.OrganizationSearchHandler;
import com.example.models.Organization;
import com.example.models.Ticket;
import com.example.models.User;
import com.example.utils.DataLoader;
import com.example.utils.PropertyLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Properties properties = PropertyLoader.load("application.properties");

        List<Organization> organizations = DataLoader.loadData(properties.getProperty("files.json.organizations"));
        List<User> users = DataLoader.loadData(properties.getProperty("files.json.users"));
        List<Ticket> tickets = DataLoader.loadData(properties.getProperty("files.json.tickets"));

        List<Organization> organizations1 = null;
        try {
            organizations1 = new ObjectMapper().readValue(new File(properties.getProperty("files.json.organizations")), new TypeReference<List<Organization>>() {
            });
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        OrganizationSearchHandler osh = new OrganizationSearchHandler(organizations1);

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.print("\nWhat do you want to do?" +
                "\n\t(1) Search" +
                "\n\t(2) Help" +
                "\n\t(3) Quit" +
                "\n");

        while (!"Q".equalsIgnoreCase(input = scanner.next())) {
            System.out.print("\nWhat do you want to search? (1) Organizations (2) Users (3) Tickets: ");

            switch (scanner.nextInt()){
                case 1:
                    List<Organization> result = osh.search(scanner.next(), OrganizationSearchHandler.SearchField.NAME);
                    System.out.println("");
                default:
                    System.out.println("Invalid selection");
            }
        }

    }
}
