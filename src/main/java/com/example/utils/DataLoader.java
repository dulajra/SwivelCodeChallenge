package com.example.utils;

import com.example.models.Organization;
import com.example.models.Ticket;
import com.example.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Loads all the data from provided JSON files.
 */
public class DataLoader {
    private static final ObjectMapper om = new ObjectMapper();

    public static List<Organization> loadOrganizations(String filePath) {
        try {
            return om.readValue(new File(filePath), new TypeReference<List<Organization>>() {
            });
        } catch (IOException e) {
            System.err.println("Error occurred while reading organizations.json file");
            e.printStackTrace();
            return null;
        }
    }

    public static List<User> loadUsers(String filePath) {
        try {
            return om.readValue(new File(filePath), new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            System.err.println("Error occurred while reading organizations.json file");
            e.printStackTrace();
            return null;
        }
    }

    public static List<Ticket> loadTickets(String filePath) {
        try {
            return om.readValue(new File(filePath), new TypeReference<List<Ticket>>() {
            });
        } catch (IOException e) {
            System.err.println("Error occurred while reading tickets.json file");
            e.printStackTrace();
            return null;
        }
    }
}
