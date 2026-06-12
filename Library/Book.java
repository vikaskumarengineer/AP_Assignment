package Library;

public class Book extends LibraryItem {
    private String author;

    public Book(String title, int year, String author) {
        super(title, year);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void displayInfo() {
        System.out.println("\n==================================================");
        System.out.println("                   BOOK DETAILS");
        System.out.println("==================================================");
        System.out.println("Item ID      : " + getItemId());
        System.out.println("Title        : " + getTitle());
        System.out.println("Year         : " + getYear());
        System.out.println("Author       : " + author);
        System.out.println("Type         : BOOK");
        System.out.println("==================================================");
    }
}
