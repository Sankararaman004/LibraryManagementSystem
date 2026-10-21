
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class librarymanagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.println("7. Search Book by Title");
            System.out.println("8. View Overdue Books");
            System.out.print("Enter your choice: ");

            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);
          } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 7.");
                choice = 0;
                continue;
         }

            switch (choice) {

                
            case 1:
                System.out.print("Enter Book ID: ");

                String bookidinput = sc.nextLine();
                int id;

                try {
                id = Integer.parseInt(bookidinput);
             }  catch (NumberFormatException e) {
                    System.out.println("Invalid Book ID! Please enter a number.");
                    break;
             }

                System.out.print("Enter Book Title: ");
                String title = sc.nextLine();

                System.out.print("Enter Author Name: ");
                String author = sc.nextLine();

                String sql = "INSERT INTO books (book_id, title, author, is_issued) VALUES (?, ?, ?, FALSE)";

                try (
                    java.sql.Connection con = DBConnection.getConnection();
                    java.sql.PreparedStatement ps = con.prepareStatement(sql)
                ) {
                    ps.setInt(1, id);
                    ps.setString(2, title);
                    ps.setString(3, author);

                    ps.executeUpdate();

                    System.out.println("Book added successfully to MySQL!");

               }catch (java.sql.SQLIntegrityConstraintViolationException e) {
                    System.out.println("Book ID already exists!");

              } catch (java.sql.SQLException e) {
                    System.out.println("Unable to add book to MySQL.");
                    e.printStackTrace();
              }

                    break;


                case 2:
                    viewBooks();
                    break;

               
                case 3:
                    System.out.print("Enter Book ID to search: ");

                    String searchInput = sc.nextLine();
                    int searchId;

                    try {
                        searchId = Integer.parseInt(searchInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Book ID! Please enter a number.");
                        break;
                    }

                    String searchSql = "SELECT book_id, title, author, is_issued FROM books WHERE book_id = ?";
                    try (
                        java.sql.Connection con = DBConnection.getConnection();
                        java.sql.PreparedStatement ps = con.prepareStatement(searchSql)
                    ) {
                    ps.setInt(1, searchId);

                    try (java.sql.ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Book ID: " + rs.getInt("book_id"));
                        System.out.println("Title: " + rs.getString("title"));
                        System.out.println("Author: " + rs.getString("author"));
                        System.out.println("Status: " +
                        (rs.getBoolean("is_issued") ? "Issued" : "Available"));
                  } else {
                        System.out.println("Book not found!");
                }
            }

                  } catch (java.sql.SQLException e) {
                        System.out.println("Unable to search for the book.");
                        e.printStackTrace();
                 }

                    break;


                
                case 4:
                    System.out.print("Enter Book ID to issue: ");

                    String issueInput = sc.nextLine();
                    int issueId;

                    try {
                        issueId = Integer.parseInt(issueInput);
                  } catch (NumberFormatException e) {
                        System.out.println("Invalid Book ID! Please enter a number.");
                        break;
                 }

                    String issueSql ="UPDATE books SET is_issued = TRUE, issue_date = CURDATE(), due_date = DATE_ADD(CURDATE(), INTERVAL 14 DAY), return_date = NULL WHERE book_id = ? AND is_issued = FALSE";
                    try (
                        java.sql.Connection con = DBConnection.getConnection();
                        java.sql.PreparedStatement ps = con.prepareStatement(issueSql)
                 ) {
                        ps.setInt(1, issueId);

                        int rowsUpdated = ps.executeUpdate();

                        if (rowsUpdated > 0) {
                            System.out.println("Book issued successfully!");
                      } else {
                            String checkSql = "SELECT is_issued FROM books WHERE book_id = ?";

                            try (java.sql.PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                            checkPs.setInt(1, issueId);

                            try (java.sql.ResultSet rs = checkPs.executeQuery()) {
                                if (rs.next()) {
                                    System.out.println("Book is already issued.");
                              } else {
                                    System.out.println("Book not found!");
                            }
                        }
                    }
                 }

                          } catch (java.sql.SQLException e) {
                                System.out.println("Unable to issue the book.");
                                e.printStackTrace();
                          }

                            break;


                
                 case 5:
                    System.out.print("Enter Book ID to return: ");

                    String returnInput = sc.nextLine();
                    int returnId;

                    try {
                        returnId = Integer.parseInt(returnInput);
                  } catch (NumberFormatException e) {
                        System.out.println("Invalid Book ID! Please enter a number.");
                    break;
                  }

                    String returnSql ="UPDATE books SET is_issued = FALSE, return_date = CURDATE() WHERE book_id = ? AND is_issued = TRUE";

                    try (
                        java.sql.Connection con = DBConnection.getConnection();
                        java.sql.PreparedStatement ps = con.prepareStatement(returnSql)
                    ) {
                        ps.setInt(1, returnId);

                        int rowsUpdated = ps.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Book returned successfully!");
                  } else {
                        String checkSql = "SELECT is_issued FROM books WHERE book_id = ?";

                    try (java.sql.PreparedStatement checkPs = con.prepareStatement(checkSql)) {
                            checkPs.setInt(1, returnId);

                   try (java.sql.ResultSet rs = checkPs.executeQuery()) {
                        if (rs.next()) {
                            System.out.println("This book was not issued.");
                      } else {
                            System.out.println("Book not found!");
                      }
                    }
                } 
            }

                  } catch (java.sql.SQLException e) {
                        System.out.println("Unable to return the book.");
                        e.printStackTrace();
                 }

                    break;

                case 6:
                    System.out.println("Thank you for using the Library Management System!");
                    break;

                
                case 7:
                    System.out.println("Search by:");
                    System.out.println("1. Book Title");
                    System.out.println("2. Author Name");
                    System.out.print("Enter your choice: ");

                    String searchChoice = sc.nextLine();

                    if (!searchChoice.equals("1") && !searchChoice.equals("2")) {
                        System.out.println("Invalid search choice!");
                    break;
                    }

                    System.out.print("Enter title or author name: ");
                    String searchText = sc.nextLine();

                    String column = searchChoice.equals("1") ? "title" : "author";
                    String titleAuthorSql ="SELECT book_id, title, author, is_issued FROM books WHERE " 
        + column + " LIKE ?";

                    try (
                        java.sql.Connection con = DBConnection.getConnection();
                        java.sql.PreparedStatement ps = con.prepareStatement(titleAuthorSql)
                    ) {
                        ps.setString(1, "%" + searchText + "%");

                    try (java.sql.ResultSet rs = ps.executeQuery()) {
                        boolean bookFound = false;

                    while (rs.next()) {
                        System.out.println("Book ID: " + rs.getInt("book_id"));
                        System.out.println("Title: " + rs.getString("title"));
                        System.out.println("Author: " + rs.getString("author"));
                        System.out.println("Status: " +
                        (rs.getBoolean("is_issued") ? "Issued" : "Available"));
                        System.out.println("----------------------");

                        bookFound = true;
                    }

                    if (!bookFound) {
                        System.out.println("Book not found.");
                    }
                }

                  } catch (java.sql.SQLException e) {
                        System.out.println("Unable to search books.");
                        e.printStackTrace();
                  }

                    break;

                case 8:
                    viewOverdueBooks();
                    break;


                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }

    
public static void viewBooks() {
    String sql = "SELECT book_id, title, author, is_issued FROM books";

    try (
        java.sql.Connection con = DBConnection.getConnection();
        java.sql.Statement stmt = con.createStatement();
        java.sql.ResultSet rs = stmt.executeQuery(sql)
    ) {
        while (rs.next()) {
            System.out.println("Book ID: " + rs.getInt("book_id"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Author: " + rs.getString("author"));
            System.out.println("Status: " +
                (rs.getBoolean("is_issued") ? "Issued" : "Available"));
            System.out.println("----------------------");
        }
    } catch (java.sql.SQLException e) {
        System.out.println("Unable to retrieve books from MySQL.");
        e.printStackTrace();
    } 
}
public static void viewOverdueBooks() {
    String sql = "SELECT book_id, title, author, issue_date, due_date, " +
                 "DATEDIFF(CURDATE(), due_date) AS days_overdue " +
                 "FROM books " +
                 "WHERE is_issued = TRUE AND due_date < CURDATE()";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        boolean found = false;

        System.out.println("\n===== OVERDUE BOOKS =====");

        while (rs.next()) {
            found = true;

            System.out.println("Book ID: " + rs.getInt("book_id"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Author: " + rs.getString("author"));
            System.out.println("Issue Date: " + rs.getDate("issue_date"));
            System.out.println("Due Date: " + rs.getDate("due_date"));
            System.out.println("Days Overdue: " + rs.getInt("days_overdue"));
            System.out.println("-------------------------");
        }

        if (!found) {
            System.out.println("No overdue books found.");
        }

    } catch (SQLException e) {
        System.out.println("Unable to retrieve overdue books.");
        e.printStackTrace();
    }
}

}

