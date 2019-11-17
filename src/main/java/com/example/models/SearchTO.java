package com.example.models;

import lombok.Getter;

@Getter
public class SearchTO {
    private final int searchType;
    private final int searchField;
    private final String searchKey;

    public SearchTO(int searchType, int searchField, String searchKey) {
        this.searchType = searchType;
        this.searchField = searchField;
        this.searchKey = searchKey;
    }
}
