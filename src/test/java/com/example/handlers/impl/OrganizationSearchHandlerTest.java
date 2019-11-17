package com.example.handlers.impl;

import com.example.models.BaseModel;
import com.example.models.Organization;
import com.example.models.SearchTO;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OrganizationSearchHandlerTest {

    private OrganizationSearchHandler osh;
    private List<Organization> organizations;

    @Before
    public void setUp() throws Exception {
        organizations = new ArrayList<>();
        osh = new OrganizationSearchHandler(organizations);

        Organization organization = new Organization();
        organization.setId("1");
        organization.setName("Eagle Software");
        organizations.add(organization);

        organization = new Organization();
        organization.setId("2");
        organization.setName("Innovative solutions");
        organization.setTags(Arrays.asList("software", "product"));
        organizations.add(organization);
    }

    @Test
    public void testSearchById() {
        // Test for exact match
        SearchTO searchTO = new SearchTO(1, 1, "1");
        List<BaseModel> results = osh.search(searchTO);

        assertEquals(1, results.size());
        assertEquals(organizations.get(0).getId(), results.get(0).getId());
    }

    @Test
    public void testSearchByName() {
        // Test for partial match
        SearchTO searchTO = new SearchTO(1, 8, "solution");
        List<BaseModel> results = osh.search(searchTO);

        assertEquals(1, results.size());
        assertEquals(organizations.get(1).getId(), results.get(0).getId());
    }

    @Test
    public void testSearchByTags() {
        // Test for search by list type fields
        SearchTO searchTO = new SearchTO(1, 5, "software");
        List<BaseModel> results = osh.search(searchTO);

        assertEquals(1, results.size());
        assertEquals(organizations.get(1).getId(), results.get(0).getId());
    }

    @Test
    public void testSearchByEmptyField() {
        // Test for search by empty fields
        SearchTO searchTO = new SearchTO(1, 9, "test");
        List<BaseModel> results = osh.search(searchTO);

        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByInvalidField() {
        // Test for search by field not exists
        SearchTO searchTO = new SearchTO(1, 100, "test");

        try{
            List<BaseModel> results = osh.search(searchTO);
            Assert.fail();
        } catch (Exception ex){
            assertTrue(ex instanceof UnsupportedOperationException);
        }

    }
}