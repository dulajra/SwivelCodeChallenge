package com.example.handlers.impl;

import com.example.models.BaseModel;
import com.example.models.SearchTO;
import com.example.models.Ticket;
import com.example.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserSearchHandlerTest {

    private UserSearchHandler ush;
    private List<User> users;

    @Before
    public void setUp() throws Exception {
        users = new ArrayList<>();
        ush = new UserSearchHandler(users);

        User user = new User();
        user.setId("10");
        user.setName("Francisca Rasmussen");
        users.add(user);

        user = new User();
        user.setId("11");
        user.setName("Cross Barlow");
        user.setTags(Arrays.asList("senior", "engineer"));
        users.add(user);
    }

    @Test
    public void testSearchById() {
        // Test for exact match
        SearchTO searchTO = new SearchTO(2, 1, "10");
        List<BaseModel> results = ush.search(searchTO);

        assertEquals(1, results.size());
        assertEquals(users.get(0).getId(), results.get(0).getId());
    }

    @Test
    public void testSearchByName() {
        // Test for partial match
        SearchTO searchTO = new SearchTO(2, 12, "Rasmussen");
        List<BaseModel> results = ush.search(searchTO);

        assertEquals(1, results.size());
        assertEquals(users.get(0).getId(), results.get(0).getId());
    }

    @Test
    public void testSearchByTags() {
        // Test for search by list type fields
        SearchTO searchTO = new SearchTO(2, 5, "senior");
        List<BaseModel> results = ush.search(searchTO);

        assertEquals(1, results.size());
        assertEquals(users.get(1).getId(), results.get(0).getId());
    }

    @Test
    public void testSearchByEmptyField() {
        // Test for search by empty fields
        SearchTO searchTO = new SearchTO(2, 6, "test");
        List<BaseModel> results = ush.search(searchTO);

        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByInvalidField() {
        // Test for search by field not exists
        SearchTO searchTO = new SearchTO(2, 100, "test");

        try {
            List<BaseModel> results = ush.search(searchTO);
            Assert.fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof UnsupportedOperationException);
        }

    }
}