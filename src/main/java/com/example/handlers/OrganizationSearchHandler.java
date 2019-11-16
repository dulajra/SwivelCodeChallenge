package com.example.handlers;

import com.example.models.Organization;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrganizationSearchHandler {

    private final List<Organization> organizations;

    public OrganizationSearchHandler(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> search(String searchKey, SearchField searchBy) {
        List<Organization> results = new ArrayList<>();

        switch (searchBy) {
            case ID:
                results = organizations.stream().filter(org -> StringUtils.equalsIgnoreCase(String.valueOf(org.getId()), searchKey)).collect(Collectors.toList());
                break;
            case SHARED_TICKETS:
                results = organizations.stream().filter(org -> StringUtils.equalsIgnoreCase(String.valueOf(org.isSharedTickets()), searchKey)).collect(Collectors.toList());
                break;
            case EXTERNAL_ID:
                results = organizations.stream().filter(org -> StringUtils.equalsIgnoreCase(org.getExternalId(), searchKey)).collect(Collectors.toList());
                break;
            case URL:
                results = organizations.stream().filter(org -> StringUtils.containsIgnoreCase(org.getUrl(), searchKey)).collect(Collectors.toList());
                break;
            case NAME:
                results = organizations.stream().filter(org -> StringUtils.containsIgnoreCase(org.getName(), searchKey)).collect(Collectors.toList());
                break;
            case CREATED_AT:
                results = organizations.stream().filter(org -> StringUtils.containsIgnoreCase(org.getCreatedAt(), searchKey)).collect(Collectors.toList());
                break;
            case DETAILS:
                results = organizations.stream().filter(org -> StringUtils.containsIgnoreCase(org.getDetails(), searchKey)).collect(Collectors.toList());
                break;
            case DOMAIN_NAMES:
                results = organizations.stream().filter(o -> CollectionUtils.isEmpty(o.getDomainNames().stream().filter(domain -> StringUtils.containsIgnoreCase(domain, searchKey)).collect(Collectors.toList()))).collect(Collectors.toList());
                break;
            case TAGS:
                results = organizations.stream().filter(o -> CollectionUtils.isEmpty(o.getTags().stream().filter(tag -> StringUtils.containsIgnoreCase(tag, searchKey)).collect(Collectors.toList()))).collect(Collectors.toList());
                break;
            default:
                throw new UnsupportedOperationException("Invalid search field supplied");
        }

        return results;
    }

    public static enum SearchField {
        ID, URL, EXTERNAL_ID, NAME, DOMAIN_NAMES, CREATED_AT, DETAILS, SHARED_TICKETS, TAGS;
    }
}
