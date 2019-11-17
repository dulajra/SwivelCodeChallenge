package com.example.handlers;

import com.example.models.BaseModel;
import com.example.models.SearchTO;
import lombok.Getter;

import java.util.List;

abstract public class SearchHandler {
    @Getter
    private SearchHandler nextHandler;

    abstract public List<BaseModel> search(SearchTO searchTO);

    abstract public SearchField getSearchField(int searchField);

    public final void setNextHandler(SearchHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public enum SearchField {
        ID, URL, EXTERNAL_ID, CREATED_AT, TAGS,
        NAME, DOMAIN_NAMES, DETAILS, SHARED_TICKETS,
        HAS_INCIDENTS, SUBMITTER_ID, ASSIGNEE_ID, ORGANIZATION_ID, DUE_AT, TYPE, SUBJECT, DESCRIPTION, PRIORITY, STATUS, VIA,
        LAST_LOGIN_AT, ACTIVE, VERIFIED, SHARED, SUSPENDED, ALIAS, LOCALE, TIMEZONE, EMAIL, PHONE, SIGNATURE, ROLE
    }
}
