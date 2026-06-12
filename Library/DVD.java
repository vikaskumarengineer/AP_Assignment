package Library;

public class DVD extends LibraryItem {
    private int duration;
    private String genre;

    public DVD(String title, int year, int duration, String genre) {
        super(title, year);
        this.duration = duration;
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        System.out.println("\n==================================================");
        System.out.println("                   DVD DETAILS");
        System.out.println("==================================================");
        System.out.println("Item ID      : " + getItemId());
        System.out.println("Title        : " + getTitle());
        System.out.println("Year         : " + getYear());
        System.out.println("Duration     : " + duration + " minutes");
        System.out.println("Genre        : " + genre);
        System.out.println("Type         : DVD");
        System.out.println("==================================================");
    }
}
