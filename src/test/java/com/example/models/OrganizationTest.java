package com.example.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrganizationTest {

    private List<Ticket> tickets;
    private List<User> users;

    @Before
    public void setUp() throws Exception {
        tickets = new ArrayList<>();

        Ticket ticket = new Ticket();
        ticket.setId("100");
        ticket.setOrganizationId("1");
        tickets.add(ticket);

        ticket = new Ticket();
        ticket.setId("101");
        ticket.setOrganizationId("2");
        tickets.add(ticket);

        users = new ArrayList<>();

        User user = new User();
        user.setId("10");
        user.setOrganizationId("1");
        users.add(user);

        user = new User();
        user.setId("11");
        user.setOrganizationId("3");
        users.add(user);
    }

    @Test
    public void getMyTickets() {
        Organization organization = new Organization();
        organization.setId("1");

        List<Ticket> myTickets = organization.getMyTickets(tickets);

        assertEquals(1, myTickets.size());
        assertEquals(tickets.get(0).getId(), myTickets.get(0).getId());
    }

    @Test
    public void getMyUsers() {
        Organization organization = new Organization();
        organization.setId("1");

        List<User> myUsers = organization.getMyUsers(users);

        assertEquals(1, myUsers.size());
        assertEquals(users.get(0).getId(), myUsers.get(0).getId());
    }
}