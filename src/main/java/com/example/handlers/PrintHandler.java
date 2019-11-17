package com.example.handlers;

import com.example.models.BaseModel;

import java.util.List;

public abstract class PrintHandler {

    private PrintHandler nextHandler;

    abstract public void printResults(List<BaseModel> listToPrint);

    public final PrintHandler getNextHandler() {
        return nextHandler;
    }

    public final void setNextHandler(PrintHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void printBaseModel(BaseModel item) {
        System.out.print("\n********************\n");

        System.out.printf("\n%-20s: %s", "_id", item.getId());
        System.out.printf("\n%-20s: %s", "external_id", item.getExternalId());
        System.out.printf("\n%-20s: %s", "created_at", item.getCreatedAt());
        System.out.printf("\n%-20s: %s", "url", item.getUrl());

        StringBuilder tagBuilder = new StringBuilder();
        item.getTags().forEach(tag -> tagBuilder.append(tag).append(", "));
        System.out.printf("\n%-20s: %s", "tags", tagBuilder.toString());
    }
}
