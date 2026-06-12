package Library;

import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<LibraryItem> items = new ArrayList<LibraryItem>();

        System.out.println("==================================================");
        System.out.println("         WELCOME TO LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==================================================");

        System.out.println("\n---  Default Library Items ---");

        Book book1 = new Book("Java Programming", 2020, "James Gosling");
        Book book2 = new Book("Python Programming", 2021, "Guido van Rossum");
        Book book3 = new Book("Data Structures and Algorithms", 2019, "Robert Lafore");
        Book book4 = new Book("Clean Code", 2008, "Robert C. Martin");

        DVD dvd1 = new DVD("Inception", 2010, 148, "Sci-Fi");
        DVD dvd2 = new DVD("The Dark Knight", 2008, 152, "Action");
        DVD dvd3 = new DVD("Interstellar", 2014, 169, "Sci-Fi");

        items.add(book1);
        items.add(book2);
        items.add(book3);
        items.add(book4);
        items.add(dvd1);
        items.add(dvd2);
        items.add(dvd3);

        System.out.println("Successfully loaded " + items.size() + " items");
        System.out.println("Total library items ever created: " + LibraryItem.getTotalItems());

        while (true) {
            System.out.println("\n------------------------------------------");
            System.out.println("                   MAIN MENU");
            System.out.println("-----------------------------------------------");
            System.out.println("1. Add New Book");
            System.out.println("2. Add New DVD");
            System.out.println("3. View All Items");
            System.out.println("4. Search Item by Title");
            System.out.println("5. View Library Statistics");
            System.out.println("6. Remove Item by ID");
            System.out.println("7. Exit");
            System.out.println("-------------------------------------------");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.println("\n--- Add New Book ---");
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter publication year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();

                    Book newBook = new Book(title, year, author);
                    items.add(newBook);
                    System.out.println("\nBook added successfully!");
                    System.out.println("Item ID: " + newBook.getItemId());

                } else if (choice == 2) {
                    System.out.println("\n--- Add New DVD ---");
                    System.out.print("Enter DVD title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter release year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter duration (in minutes): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();

                    DVD newDVD = new DVD(title, year, duration, genre);
                    items.add(newDVD);
                    System.out.println("\nDVD added successfully!");
                    System.out.println("Item ID: " + newDVD.getItemId());

                } else if (choice == 3) {
                    if (items.isEmpty()) {
                        System.out.println("\nNo items in the library");
                    } else {
                        System.out.println("\n==================================================");
                        System.out.println("              ALL LIBRARY ITEMS");
                        System.out.println("==================================================");
                        System.out.println("Total items: " + items.size());

                        for (LibraryItem item : items) {
                            item.displayInfo();
                        }
                    }

                } else if (choice == 4) {
                    System.out.print("\nEnter title to search: ");
                    String searchTitle = scanner.nextLine();
                    boolean found = false;

                    System.out.println("\n==================================================");
                    System.out.println("              SEARCH RESULTS");
                    System.out.println("==================================================");

                    for (LibraryItem item : items) {
                        if (item.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                            item.displayInfo();
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("No items found with title containing: " + searchTitle);
                    }

                } else if (choice == 5) {
                    int bookCount = 0;
                    int dvdCount = 0;

                    for (LibraryItem item : items) {
                        if (item instanceof Book) {
                            bookCount++;
                        } else if (item instanceof DVD) {
                            dvdCount++;
                        }
                    }

                    System.out.println("\n==================================================");
                    System.out.println("           LIBRARY STATISTICS");
                    System.out.println("==================================================");
                    System.out.println("Total items currently in library: " + items.size());
                    System.out.println("Total books: " + bookCount);
                    System.out.println("Total DVDs: " + dvdCount);
                    System.out.println("Total items ever created: " + LibraryItem.getTotalItems());
                    System.out.println("==================================================");

                } else if (choice == 6) {
                    System.out.print("\nEnter Item ID to remove: ");
                    int id = scanner.nextInt();
                    boolean found = false;

                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getItemId() == id) {
                            System.out.println("\nRemoving item:");
                            items.get(i).displayInfo();
                            items.remove(i);
                            System.out.println("Item removed successfully!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("No item found with ID: " + id);
                    }

                } else if (choice == 7) {
                    System.out.println("\n==================================================");
                    System.out.println("     Thank you for using Library System");
                    System.out.println("              Have a great day!");
                    System.out.println("==================================================");
                    break;

                } else {
                    System.out.println("\nInvalid choice! Please enter number between 1-7");
                }

            } catch (Exception e) {
                System.out.println("\nError: Invalid input. Please enter correct values");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
