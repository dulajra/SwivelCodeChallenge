package com.example.handlers.impl;

import com.example.models.BaseModel;
import com.example.models.SearchTO;
import com.example.models.Ticket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TicketSearchHandlerTest {

    private TicketSearchHandler tsh;
    private List<Ticket> tickets;

    @Before
    public void setUp() throws Exception {
        tickets = new ArrayList<>();
        tsh = new TicketSearchHandler(tickets);

        Ticket ticket = new Ticket();
        ticket.setId("100");
        ticket.setSubject("Bug in cart");
        tickets.add(ticket);

        ticket = new Ticket();
        ticket.setId("101");
        ticket.setSubject("High critical bug in orders page");
        ticket.setTags(Arrays.asList("bug", "critical", "production"));
        tickets.add(ticket);
    }

    @Test
    public void testSearchById() {
        // Test for exact match
        SearchTO searchTO = new SearchTO(3, 1, "101");
        List<BaseModel> results = tsh.search(searchTO);

        assertEquals(1, results.size());
        assertEquals(tickets.get(1).getId(), results.get(0).getId());
    }

    @Test
    public void testSearchBySubject() {
        // Test for partial match
        SearchTO searchTO = new SearchTO(3, 12, "orders page");
        List<BaseModel> results = tsh.search(searchTO);

        assertEquals(1, results.size());
        assertEquals(tickets.get(1).getId(), results.get(0).getId());
    }

    @Test
    public void testSearchByTags() {
        // Test for search by list type fields
        SearchTO searchTO = new SearchTO(3, 5, "prod");
        List<BaseModel> results = tsh.search(searchTO);

        assertEquals(1, results.size());
        assertEquals(tickets.get(1).getId(), results.get(0).getId());
    }

    @Test
    public void testSearchByEmptyField() {
        // Test for search by empty fields
        SearchTO searchTO = new SearchTO(3, 6, "test");
        List<BaseModel> results = tsh.search(searchTO);

        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByInvalidField() {
        // Test for search by field not exists
        SearchTO searchTO = new SearchTO(3, 100, "test");

        try {
            List<BaseModel> results = tsh.search(searchTO);
            Assert.fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof UnsupportedOperationException);
        }

    }
}