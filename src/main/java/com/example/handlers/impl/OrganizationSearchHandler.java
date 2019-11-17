package com.example.handlers.impl;

import com.example.handlers.SearchHandler;
import com.example.models.BaseModel;
import com.example.models.Organization;
import com.example.models.SearchTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.handlers.SearchHandler.SearchField.CREATED_AT;
import static com.example.handlers.SearchHandler.SearchField.DETAILS;
import static com.example.handlers.SearchHandler.SearchField.DOMAIN_NAMES;
import static com.example.handlers.SearchHandler.SearchField.EXTERNAL_ID;
import static com.example.handlers.SearchHandler.SearchField.ID;
import static com.example.handlers.SearchHandler.SearchField.NAME;
import static com.example.handlers.SearchHandler.SearchField.NONE;
import static com.example.handlers.SearchHandler.SearchField.SHARED_TICKETS;
import static com.example.handlers.SearchHandler.SearchField.TAGS;
import static com.example.handlers.SearchHandler.SearchField.URL;

/**
 * Search Handler for searching {@link Organization}s
 *
 * @author Dulaj Atapattu
 */
public class OrganizationSearchHandler extends SearchHandler {

    private final List<Organization> organizations;

    public OrganizationSearchHandler(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public List<BaseModel> search(SearchTO searchTO) {
        if (searchTO.getSearchType() == 1) {
            switch (getSearchField(searchTO.getSearchField())) {
                case ID:
                    return organizations.stream().filter(org -> StringUtils.equalsIgnoreCase(org.getId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case SHARED_TICKETS:
                    return organizations.stream().filter(org -> StringUtils.equalsIgnoreCase(String.valueOf(org.isSharedTickets()), searchTO.getSearchKey())).collect(Collectors.toList());
                case EXTERNAL_ID:
                    return organizations.stream().filter(org -> StringUtils.equalsIgnoreCase(org.getExternalId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case URL:
                    return organizations.stream().filter(org -> StringUtils.equalsIgnoreCase(org.getUrl(), searchTO.getSearchKey())).collect(Collectors.toList());
                case NAME:
                    return organizations.stream().filter(org -> StringUtils.containsIgnoreCase(org.getName(), searchTO.getSearchKey())).collect(Collectors.toList());
                case CREATED_AT:
                    return organizations.stream().filter(org -> StringUtils.equalsIgnoreCase(org.getCreatedAt(), searchTO.getSearchKey())).collect(Collectors.toList());
                case DETAILS:
                    return organizations.stream().filter(org -> StringUtils.containsIgnoreCase(org.getDetails(), searchTO.getSearchKey())).collect(Collectors.toList());
                case DOMAIN_NAMES:
                    return organizations.stream().filter(org -> CollectionUtils.isNotEmpty(org.getDomainNames().stream().filter(domain -> StringUtils.containsIgnoreCase(domain, searchTO.getSearchKey())).collect(Collectors.toList()))).collect(Collectors.toList());
                case TAGS:
                    return organizations.stream().filter(org -> CollectionUtils.isNotEmpty(org.getTags().stream().filter(tag -> StringUtils.containsIgnoreCase(tag, searchTO.getSearchKey())).collect(Collectors.toList()))).collect(Collectors.toList());
                default:
                    throw new UnsupportedOperationException("Invalid search field supplied");
            }
        } else {
            return this.getNextHandler().search(searchTO);
        }
    }

    @Override
    public SearchField getSearchField(int searchField) {
        SearchField[] searchFields = new SearchField[]{ID, EXTERNAL_ID, CREATED_AT, URL, TAGS, DOMAIN_NAMES, SHARED_TICKETS, NAME, DETAILS};
        return searchField > searchFields.length ? NONE : searchFields[searchField - 1];
    }
}
