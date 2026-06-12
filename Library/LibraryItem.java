package Library;

public abstract class LibraryItem {
    private static int itemCounter = 0;
    private int itemId;
    private String title;
    private int year;

    public LibraryItem(String title, int year) {
        this.title = title;
        this.year = year;
        itemCounter++;
        this.itemId = itemCounter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getItemId() {
        return itemId;
    }

    public static int getTotalItems() {
        return itemCounter;
    }

    public abstract void displayInfo();
}
