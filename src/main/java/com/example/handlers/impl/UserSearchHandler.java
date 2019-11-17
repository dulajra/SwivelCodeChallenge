package com.example.handlers.impl;

import com.example.handlers.SearchHandler;
import com.example.models.BaseModel;
import com.example.models.SearchTO;
import com.example.models.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.handlers.SearchHandler.SearchField.ACTIVE;
import static com.example.handlers.SearchHandler.SearchField.ALIAS;
import static com.example.handlers.SearchHandler.SearchField.CREATED_AT;
import static com.example.handlers.SearchHandler.SearchField.EMAIL;
import static com.example.handlers.SearchHandler.SearchField.EXTERNAL_ID;
import static com.example.handlers.SearchHandler.SearchField.ID;
import static com.example.handlers.SearchHandler.SearchField.LAST_LOGIN_AT;
import static com.example.handlers.SearchHandler.SearchField.LOCALE;
import static com.example.handlers.SearchHandler.SearchField.NAME;
import static com.example.handlers.SearchHandler.SearchField.NONE;
import static com.example.handlers.SearchHandler.SearchField.ORGANIZATION_ID;
import static com.example.handlers.SearchHandler.SearchField.PHONE;
import static com.example.handlers.SearchHandler.SearchField.ROLE;
import static com.example.handlers.SearchHandler.SearchField.SHARED;
import static com.example.handlers.SearchHandler.SearchField.SIGNATURE;
import static com.example.handlers.SearchHandler.SearchField.SUSPENDED;
import static com.example.handlers.SearchHandler.SearchField.TAGS;
import static com.example.handlers.SearchHandler.SearchField.TIMEZONE;
import static com.example.handlers.SearchHandler.SearchField.URL;
import static com.example.handlers.SearchHandler.SearchField.VERIFIED;

/**
 * Search Handler for searching {@link User}s
 *
 * @author Dulaj Atapattu
 */
public class UserSearchHandler extends SearchHandler {

    private final List<User> users;

    public UserSearchHandler(List<User> users) {
        this.users = users;
    }

    @Override
    public List<BaseModel> search(SearchTO searchTO) {
        if (searchTO.getSearchType() == 2) {
            switch (getSearchField(searchTO.getSearchField())) {
                case ID:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case EXTERNAL_ID:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getExternalId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case LOCALE:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getLocale(), searchTO.getSearchKey())).collect(Collectors.toList());
                case TIMEZONE:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getTimezone(), searchTO.getSearchKey())).collect(Collectors.toList());
                case EMAIL:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getEmail(), searchTO.getSearchKey())).collect(Collectors.toList());
                case PHONE:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getPhone(), searchTO.getSearchKey())).collect(Collectors.toList());
                case SIGNATURE:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getSignature(), searchTO.getSearchKey())).collect(Collectors.toList());
                case ROLE:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getRole(), searchTO.getSearchKey())).collect(Collectors.toList());
                case ACTIVE:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(String.valueOf(user.isActive()), searchTO.getSearchKey())).collect(Collectors.toList());
                case VERIFIED:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(String.valueOf(user.isVerified()), searchTO.getSearchKey())).collect(Collectors.toList());
                case SHARED:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(String.valueOf(user.isShared()), searchTO.getSearchKey())).collect(Collectors.toList());
                case SUSPENDED:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(String.valueOf(user.isSuspended()), searchTO.getSearchKey())).collect(Collectors.toList());
                case ORGANIZATION_ID:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getOrganizationId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case URL:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getUrl(), searchTO.getSearchKey())).collect(Collectors.toList());
                case LAST_LOGIN_AT:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getLastLoginAt(), searchTO.getSearchKey())).collect(Collectors.toList());
                case NAME:
                    return users.stream().filter(user -> StringUtils.containsIgnoreCase(user.getName(), searchTO.getSearchKey())).collect(Collectors.toList());
                case ALIAS:
                    return users.stream().filter(user -> StringUtils.containsIgnoreCase(user.getAlias(), searchTO.getSearchKey())).collect(Collectors.toList());
                case CREATED_AT:
                    return users.stream().filter(user -> StringUtils.equalsIgnoreCase(user.getCreatedAt(), searchTO.getSearchKey())).collect(Collectors.toList());
                case TAGS:
                    return users.stream().filter(user -> CollectionUtils.isNotEmpty(user.getTags().stream().filter(tag -> StringUtils.containsIgnoreCase(tag, searchTO.getSearchKey())).collect(Collectors.toList()))).collect(Collectors.toList());
                default:
                    throw new UnsupportedOperationException("Invalid search field supplied");
            }
        } else {
            return this.getNextHandler().search(searchTO);
        }
    }

    @Override
    public SearchField getSearchField(int searchField) {
        SearchField[] searchFields = new SearchField[]{ID, EXTERNAL_ID, CREATED_AT, URL, TAGS, ORGANIZATION_ID, LAST_LOGIN_AT, ACTIVE, VERIFIED, SHARED, SUSPENDED, NAME, ALIAS, LOCALE, TIMEZONE, EMAIL, PHONE, SIGNATURE, ROLE};
        return searchField > searchFields.length ? NONE : searchFields[searchField - 1];
    }
}
