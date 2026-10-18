public class book {
    int bookId;
    String title;
    String author;
    boolean isIssued;

    public book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    void displayBook() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);

        if (isIssued) {
            System.out.println("Status: Issued");
      } else {
            System.out.println("Status: Available");
        }

            System.out.println("----------------------");
        }
    }