package com.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataLoader {
    private static final ObjectMapper om = new ObjectMapper();

    public static <T> List<T> loadData(String filePath) {
        try {
            return om.readValue(new File(filePath), new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    ;
}
