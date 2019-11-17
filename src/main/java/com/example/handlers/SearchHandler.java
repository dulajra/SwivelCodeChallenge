package com.example.handlers;

import com.example.models.BaseModel;
import com.example.models.SearchTO;
import lombok.Getter;

import java.util.List;

/**
 * Abstract SearchHandler to be extended by any SearchHandler implementation in handlers chain.
 *
 * @author Dulaj Atapattu
 */
abstract public class SearchHandler {
    @Getter
    private SearchHandler nextHandler;

    /**
     * Perform the search according to the implementation using parameters given with @{@link SearchTO} object
     * @param searchTO - Param object to be used for search
     * @return - Matching results for the search
     */
    abstract public List<BaseModel> search(SearchTO searchTO);
    abstract public SearchField getSearchField(int searchField);

    public final void setNextHandler(SearchHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public enum SearchField {
        ID, URL, EXTERNAL_ID, CREATED_AT, TAGS,
        NAME, DOMAIN_NAMES, DETAILS, SHARED_TICKETS,
        HAS_INCIDENTS, SUBMITTER_ID, ASSIGNEE_ID, ORGANIZATION_ID, DUE_AT, TYPE, SUBJECT, DESCRIPTION, PRIORITY, STATUS, VIA,
        LAST_LOGIN_AT, ACTIVE, VERIFIED, SHARED, SUSPENDED, ALIAS, LOCALE, TIMEZONE, EMAIL, PHONE, SIGNATURE, ROLE,
        NONE
    }
}
