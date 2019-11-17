# Swivel Java Code Challenge
## Introduction 
This application provides simple search operations for the provided json files. 
Partial matches are supported only when searching by name, subject, details, description and tags.
In all other scenarios only exact complete matches are considered in search results. 
All search operations are case insensitive.  

```
Ex: 

Ticket {
    "subject": "A Catastrophe in Korea (North)"
}

Search by "korea" will return the above item

``` 

## How to Build, Run & Test
1. Build - `mvn clean package`
2. Run - `mvn exec:java`
3. Run unit tests - `mvn test` 