package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:library.db";
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(URL);
                createTables();
            }
        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        return conn;
    }

    private static void createTables() {
        try (Statement stmt = conn.createStatement()) {

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT," +
                    "phone TEXT," +
                    "password TEXT NOT NULL" +
                    ")"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS login_users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL UNIQUE," +
                    "password TEXT NOT NULL" +
                    ")"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "book_id TEXT NOT NULL UNIQUE," +
                    "book_name TEXT NOT NULL," +
                    "author TEXT," +
                    "category TEXT," +
                    "quantity INTEGER DEFAULT 0" +
                    ")"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS borrow_records (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_name TEXT," +
                    "book_id TEXT," +
                    "status TEXT," +
                    "borrow_date TEXT DEFAULT (date('now'))," +
                    "return_date TEXT" +
                    ")"
            );

            stmt.execute(
                    "INSERT OR IGNORE INTO login_users (username, password) VALUES ('admin', '123')"
            );

            System.out.println("Library database ready. Tables created.");

        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }
}