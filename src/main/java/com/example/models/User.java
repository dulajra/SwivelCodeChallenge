package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class User implements Serializable {

    @JsonProperty("_id")
    private int id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("organization_id")
    private String organizationId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("last_login_at")
    private String lastLoginAt;

    private boolean active;
    private boolean verified;
    private boolean shared;
    private boolean suspended;
    private String url;
    private String name;
    private String alias;
    private String locale;
    private String timezone;
    private String email;
    private String phone;
    private String signature;
    private String role;
    private List<String> tags;
}
