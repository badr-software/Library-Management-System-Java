package Client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class LibraryServer {

    private static final int PORT = 5000;

    private static ArrayList<String> users = new ArrayList<>();
    private static ArrayList<String> books = new ArrayList<>();
    private static ArrayList<String> borrowRecords = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Library Server Starting on Port " + PORT + " ===");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is RUNNING. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                new Thread(() -> handleClient(clientSocket)).start();
            }

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            out.println("WELCOME|Library Management Server is Ready!");

            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received: " + request);

                String response = processCommand(request);
                out.println(response);

                if (request.equalsIgnoreCase("EXIT")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }

    private static String processCommand(String request) {
        String[] parts = request.split("\\|");
        String command = parts[0].toUpperCase();

        switch (command) {

            case "LOGIN":
                // LOGIN|username|password
                if (parts.length < 3) {
                    return "ERROR|Missing username or password";
                }
                return login(parts[1], parts[2]);

            case "REGISTER":
                // REGISTER|name|email|phone|password
                if (parts.length < 5) {
                    return "ERROR|Missing user details";
                }
                return register(parts[1], parts[2], parts[3], parts[4]);

            case "VIEW_USERS":
                return viewUsers();

            case "ADD_BOOK":
                // ADD_BOOK|bookId|bookName|author|category|quantity
                if (parts.length < 6) {
                    return "ERROR|Missing book details";
                }
                return addBook(parts[1], parts[2], parts[3], parts[4], parts[5]);

            case "VIEW_BOOKS":
                return viewBooks();

            case "BORROW_BOOK":
                // BORROW_BOOK|userName|bookId
                if (parts.length < 3) {
                    return "ERROR|Missing borrow details";
                }
                return borrowBook(parts[1], parts[2]);

            case "RETURN_BOOK":
                // RETURN_BOOK|userName|bookId
                if (parts.length < 3) {
                    return "ERROR|Missing return details";
                }
                return returnBook(parts[1], parts[2]);

            case "VIEW_BORROW_RECORDS":
                return viewBorrowRecords();

            case "EXIT":
                return "BYE|Goodbye!";

            default:
                return "ERROR|Unknown command: " + command;
        }
    }

    private static String login(String username, String password) {
        if (username.equals("admin") && password.equals("123")) {
            return "SUCCESS|Login successful! Welcome " + username;
        }

        return "ERROR|Incorrect username or password";
    }

    private static String register(String name, String email, String phone, String password) {
        String user = name + "," + email + "," + phone + "," + password;
        users.add(user);
        saveToFile("registered_users.txt", user);

        return "SUCCESS|User registered: " + name;
    }

    private static String viewUsers() {
        StringBuilder sb = new StringBuilder("USERS|");

        for (String user : users) {
            sb.append(user).append(";");
        }

        return sb.toString();
    }

    private static String addBook(String bookId, String bookName, String author, String category, String quantity) {
        try {
            Integer.parseInt(quantity);

            String book = bookId + "," + bookName + "," + author + "," + category + "," + quantity;
            books.add(book);
            saveToFile("books.txt", book);

            return "SUCCESS|Book added: " + bookName;

        } catch (NumberFormatException e) {
            return "ERROR|Quantity must be a number";
        }
    }

    private static String viewBooks() {
        StringBuilder sb = new StringBuilder("BOOKS|");

        for (String book : books) {
            sb.append(book).append(";");
        }

        return sb.toString();
    }

    private static String borrowBook(String userName, String bookId) {
        for (int i = 0; i < books.size(); i++) {
            String[] bookData = books.get(i).split(",");

            if (bookData[0].equals(bookId)) {
                int quantity = Integer.parseInt(bookData[4]);

                if (quantity <= 0) {
                    return "ERROR|Book is not available";
                }

                quantity--;

                String updatedBook =
                        bookData[0] + "," +
                        bookData[1] + "," +
                        bookData[2] + "," +
                        bookData[3] + "," +
                        quantity;

                books.set(i, updatedBook);

                String record = userName + "," + bookId + ",BORROWED";
                borrowRecords.add(record);
                saveToFile("borrow_records.txt", record);

                return "SUCCESS|Book borrowed successfully";
            }
        }

        return "ERROR|Book ID not found";
    }

    private static String returnBook(String userName, String bookId) {
        for (int i = 0; i < books.size(); i++) {
            String[] bookData = books.get(i).split(",");

            if (bookData[0].equals(bookId)) {
                int quantity = Integer.parseInt(bookData[4]);
                quantity++;

                String updatedBook =
                        bookData[0] + "," +
                        bookData[1] + "," +
                        bookData[2] + "," +
                        bookData[3] + "," +
                        quantity;

                books.set(i, updatedBook);

                String record = userName + "," + bookId + ",RETURNED";
                borrowRecords.add(record);
                saveToFile("borrow_records.txt", record);

                return "SUCCESS|Book returned successfully";
            }
        }

        return "ERROR|Book ID not found";
    }

    private static String viewBorrowRecords() {
        StringBuilder sb = new StringBuilder("BORROW_RECORDS|");

        for (String record : borrowRecords) {
            sb.append(record).append(";");
        }

        return sb.toString();
    }

    private static void saveToFile(String fileName, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(data);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("File save error: " + e.getMessage());
        }
    }
}