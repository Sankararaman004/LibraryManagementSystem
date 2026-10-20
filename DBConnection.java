
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library_db";

        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (user == null || password == null) {
            throw new SQLException(
                "Please set the DB_USER and DB_PASSWORD environment variables."
            );
        }

        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("MySQL connected successfully!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}


