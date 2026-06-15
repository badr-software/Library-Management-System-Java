package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class LibraryClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Connecting to Library Server...");

        try (
                Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Server says: " + in.readLine());

            System.out.println();
            System.out.println("========= AVAILABLE COMMANDS =========");
            System.out.println("LOGIN|username|password");
            System.out.println("REGISTER|name|email|phone|password");
            System.out.println("ADD_BOOK|bookId|bookName|author|category|quantity");
            System.out.println("VIEW_BOOKS");
            System.out.println("BORROW_BOOK|userName|bookId");
            System.out.println("RETURN_BOOK|userName|bookId");
            System.out.println("VIEW_USERS");
            System.out.println("EXIT");
            System.out.println("======================================");
            System.out.println();

            while (true) {
                System.out.print("Enter command: ");
                String command = scanner.nextLine().trim();

                if (command.isEmpty()) {
                    continue;
                }

                out.println(command);

                String response = in.readLine();
                System.out.println("Server Response: " + response);

                if (command.equalsIgnoreCase("EXIT")) {
                    break;
                }

                if (response != null && response.startsWith("BOOKS|")) {
                    printBooks(response);
                }

                if (response != null && response.startsWith("USERS|")) {
                    printUsers(response);
                }

                if (response != null && response.startsWith("BORROW_RECORDS|")) {
                    printBorrowRecords(response);
                }
            }

            System.out.println("Disconnected from server. Goodbye!");

        } catch (ConnectException e) {
            System.out.println("ERROR: Cannot connect to server!");
            System.out.println("Make sure LibraryServer is running first.");
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    private static void printBooks(String response) {
        String data = response.substring(6);

        if (data.isEmpty()) {
            System.out.println("  (No books available yet)");
            return;
        }

        System.out.println();
        System.out.println("+----------+----------------------+----------------------+----------------------+----------+");
        System.out.printf("| %-8s | %-20s | %-20s | %-20s | %-8s |%n",
                "Book ID", "Book Name", "Author", "Category", "Quantity");
        System.out.println("+----------+----------------------+----------------------+----------------------+----------+");

        for (String book : data.split(";")) {
            if (book.trim().isEmpty()) {
                continue;
            }

            String[] c = book.split(",");

            System.out.printf("| %-8s | %-20s | %-20s | %-20s | %-8s |%n",
                    c.length > 0 ? c[0] : "",
                    c.length > 1 ? c[1] : "",
                    c.length > 2 ? c[2] : "",
                    c.length > 3 ? c[3] : "",
                    c.length > 4 ? c[4] : "");
        }

        System.out.println("+----------+----------------------+----------------------+----------------------+----------+");
        System.out.println();
    }

    private static void printUsers(String response) {
        String data = response.substring(6);

        if (data.isEmpty()) {
            System.out.println("  (No users registered yet)");
            return;
        }

        System.out.println();
        System.out.println("+----------------------+-------------------------+----------------+");
        System.out.printf("| %-20s | %-23s | %-14s |%n", "Name", "Email", "Phone");
        System.out.println("+----------------------+-------------------------+----------------+");

        for (String user : data.split(";")) {
            if (user.trim().isEmpty()) {
                continue;
            }

            String[] c = user.split(",");

            System.out.printf("| %-20s | %-23s | %-14s |%n",
                    c.length > 0 ? c[0] : "",
                    c.length > 1 ? c[1] : "",
                    c.length > 2 ? c[2] : "");
        }

        System.out.println("+----------------------+-------------------------+----------------+");
        System.out.println();
    }

    private static void printBorrowRecords(String response) {
        String data = response.substring(15);

        if (data.isEmpty()) {
            System.out.println("  (No borrow records yet)");
            return;
        }

        System.out.println();
        System.out.println("+----------------------+----------+----------------+");
        System.out.printf("| %-20s | %-8s | %-14s |%n", "User Name", "Book ID", "Status");
        System.out.println("+----------------------+----------+----------------+");

        for (String record : data.split(";")) {
            if (record.trim().isEmpty()) {
                continue;
            }

            String[] c = record.split(",");

            System.out.printf("| %-20s | %-8s | %-14s |%n",
                    c.length > 0 ? c[0] : "",
                    c.length > 1 ? c[1] : "",
                    c.length > 2 ? c[2] : "");
        }

        System.out.println("+----------------------+----------+----------------+");
        System.out.println();
    }
}