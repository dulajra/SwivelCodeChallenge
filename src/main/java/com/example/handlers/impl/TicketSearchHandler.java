package com.example.handlers.impl;

import com.example.handlers.SearchHandler;
import com.example.models.BaseModel;
import com.example.models.SearchTO;
import com.example.models.Ticket;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.handlers.SearchHandler.SearchField.ASSIGNEE_ID;
import static com.example.handlers.SearchHandler.SearchField.CREATED_AT;
import static com.example.handlers.SearchHandler.SearchField.DESCRIPTION;
import static com.example.handlers.SearchHandler.SearchField.DUE_AT;
import static com.example.handlers.SearchHandler.SearchField.EXTERNAL_ID;
import static com.example.handlers.SearchHandler.SearchField.HAS_INCIDENTS;
import static com.example.handlers.SearchHandler.SearchField.ID;
import static com.example.handlers.SearchHandler.SearchField.NONE;
import static com.example.handlers.SearchHandler.SearchField.ORGANIZATION_ID;
import static com.example.handlers.SearchHandler.SearchField.PRIORITY;
import static com.example.handlers.SearchHandler.SearchField.STATUS;
import static com.example.handlers.SearchHandler.SearchField.SUBJECT;
import static com.example.handlers.SearchHandler.SearchField.SUBMITTER_ID;
import static com.example.handlers.SearchHandler.SearchField.TAGS;
import static com.example.handlers.SearchHandler.SearchField.TYPE;
import static com.example.handlers.SearchHandler.SearchField.URL;
import static com.example.handlers.SearchHandler.SearchField.VIA;

/**
 * Search Handler for searching {@link Ticket}s
 *
 * @author Dulaj Atapattu
 */
public class TicketSearchHandler extends SearchHandler {

    private final List<Ticket> tickets;

    public TicketSearchHandler(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public List<BaseModel> search(SearchTO searchTO) {
        if (searchTO.getSearchType() == 3) {
            switch (getSearchField(searchTO.getSearchField())) {
                case ID:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case HAS_INCIDENTS:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(String.valueOf(ticket.isHasIncidents()), searchTO.getSearchKey())).collect(Collectors.toList());
                case EXTERNAL_ID:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getExternalId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case SUBMITTER_ID:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getSubmitterId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case ASSIGNEE_ID:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getAssigneeId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case ORGANIZATION_ID:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getOrganizationId(), searchTO.getSearchKey())).collect(Collectors.toList());
                case DUE_AT:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getDueAt(), searchTO.getSearchKey())).collect(Collectors.toList());
                case TYPE:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getType(), searchTO.getSearchKey())).collect(Collectors.toList());
                case PRIORITY:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getPriority(), searchTO.getSearchKey())).collect(Collectors.toList());
                case STATUS:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getStatus(), searchTO.getSearchKey())).collect(Collectors.toList());
                case VIA:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getVia(), searchTO.getSearchKey())).collect(Collectors.toList());
                case SUBJECT:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getSubject(), searchTO.getSearchKey())).collect(Collectors.toList());
                case URL:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getUrl(), searchTO.getSearchKey())).collect(Collectors.toList());
                case CREATED_AT:
                    return tickets.stream().filter(ticket -> StringUtils.equalsIgnoreCase(ticket.getCreatedAt(), searchTO.getSearchKey())).collect(Collectors.toList());
                case DESCRIPTION:
                    return tickets.stream().filter(ticket -> StringUtils.containsIgnoreCase(ticket.getDescription(), searchTO.getSearchKey())).collect(Collectors.toList());
                case TAGS:
                    return tickets.stream().filter(ticket -> CollectionUtils.isNotEmpty(ticket.getTags().stream().filter(tag -> StringUtils.containsIgnoreCase(tag, searchTO.getSearchKey())).collect(Collectors.toList()))).collect(Collectors.toList());
                default:
                    throw new UnsupportedOperationException("Invalid search field supplied");
            }
        } else {
            System.out.println("End of chain. No matching search handler found!");
            return null;
        }
    }

    @Override
    public SearchField getSearchField(int searchField) {
        SearchField[] searchFields = new SearchField[]{ID, EXTERNAL_ID, CREATED_AT, URL, TAGS, HAS_INCIDENTS, SUBMITTER_ID, ASSIGNEE_ID, ORGANIZATION_ID, DUE_AT, TYPE, SUBJECT, DESCRIPTION, PRIORITY, STATUS, VIA};
        return searchField > searchFields.length ? NONE : searchFields[searchField - 1];
    }
}
