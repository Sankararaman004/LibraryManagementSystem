
import java.util.ArrayList;
import java.util.Scanner;

public class librarymanagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<book> books = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    boolean idExists = false;

                    for (book b : books) {
                        if (b.bookId == id) {
                            idExists = true;
                            break;
                        }
                    }

                    if (idExists) {
                        System.out.println("Book ID already exists!");
                        break;
                    }

                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();

                    book newBook = new book(id, title, author);
                    books.add(newBook);

                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    if (books.isEmpty()) {
                        System.out.println("No books available.");
                    } else {
                        System.out.println("\n===== BOOK DETAILS =====");

                        for (book b : books) {
                            b.displayBook();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to search: ");
                    int searchId = sc.nextInt();

                    boolean found = false;

                    for (book b : books) {
                        if (b.bookId == searchId) {
                            b.displayBook();
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();

                    boolean issueFound = false;

                    for (book b : books) {
                        if (b.bookId == issueId) {
                            issueFound = true;

                            if (!b.isIssued) {
                                b.isIssued = true;
                                System.out.println("Book issued successfully!");
                            } else {
                                System.out.println("Book is already issued.");
                            }

                            break;
                        }
                    }

                    if (!issueFound) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();

                    boolean returnFound = false;

                    for (book b : books) {
                        if (b.bookId == returnId) {
                            returnFound = true;

                            if (b.isIssued) {
                                b.isIssued = false;
                                System.out.println("Book returned successfully!");
                            } else {
                                System.out.println("This book was not issued.");
                            }

                            break;
                        }
                    }

                    if (!returnFound) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using the Library Management System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}

