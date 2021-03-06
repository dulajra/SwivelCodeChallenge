package com.example.utils;

import com.example.models.SearchTO;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles taking user inputs require for searching.
 *
 * @author Dulaj Atapattu
 */
public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static SearchTO startSearch() {
        final String searchType, searchKey;
        int searchField;

        System.out.print("\nWhat do you want to search? (1) Organizations (2) Users (3) Tickets (0) Exit application: ");

        switch (searchType = scanner.next()) {
            case "0":
                System.out.print("\nExiting the application. Good Bye!\n");
                return null;
            case "1":
                System.out.print("\nUsing what field do you want to search?" +
                        "\n\t(1) _id" +
                        "\n\t(2) external_id" +
                        "\n\t(3) created_at" +
                        "\n\t(4) url" +
                        "\n\t(5) tags" +
                        "\n\t(6) domain_names" +
                        "\n\t(7) shared_tickets" +
                        "\n\t(8) name" +
                        "\n\t(9) details" +
                        "\n");
                break;
            case "2":
                System.out.print("\nUsing what field do you want to search?" +
                        "\n\t(1) _id" +
                        "\n\t(2) external_id" +
                        "\n\t(3) created_at" +
                        "\n\t(4) url" +
                        "\n\t(5) tags" +
                        "\n\t(6) organization_id" +
                        "\n\t(7) last_login_at" +
                        "\n\t(8) active" +
                        "\n\t(9) verified" +
                        "\n\t(10) shared" +
                        "\n\t(11) suspended" +
                        "\n\t(12) name" +
                        "\n\t(13) alias" +
                        "\n\t(14) locale" +
                        "\n\t(15) timezone" +
                        "\n\t(16) email" +
                        "\n\t(17) phone" +
                        "\n\t(18) signature" +
                        "\n\t(19) role" +
                        "\n");
                break;
            case "3":
                System.out.print("\nUsing what field do you want to search?" +
                        "\n\t(1) _id" +
                        "\n\t(2) external_id" +
                        "\n\t(3) created_at" +
                        "\n\t(4) url" +
                        "\n\t(5) tags" +
                        "\n\t(6) has_incidents" +
                        "\n\t(7) submitter_id" +
                        "\n\t(8) assignee_id" +
                        "\n\t(9) organization_id" +
                        "\n\t(10) due_at" +
                        "\n\t(11) type" +
                        "\n\t(12) subject" +
                        "\n\t(13) description" +
                        "\n\t(14) priority" +
                        "\n\t(15) status" +
                        "\n\t(16) via" +
                        "\n");
                break;
            default:
                System.err.print("\nInvalid selection! Only 0, 1, 2 & 3 are valid inputs.\n");
                return startSearch();
        }

        do {
            try {
                searchField = scanner.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.err.print("\nInvalid input! Only numbers are valid. Please reenter: ");
                scanner.nextLine();
            }
        } while (true);

        System.out.print("\nEnter your search key: ");
        searchKey = scanner.next();

        return new SearchTO(Integer.parseInt(searchType), searchField, searchKey);
    }
}
