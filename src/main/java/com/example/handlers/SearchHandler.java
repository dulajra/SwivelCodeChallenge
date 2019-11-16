package com.example.handlers;

import java.util.List;

public interface SearchHandler {
    public <T> List<T> search(String searchKey, int searchBy);
}
