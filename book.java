import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question1 {
    public Question1() {
    }

    public static void main(String[] args) {
        List<String> book = new ArrayList();
        book.add("physic");
        book.add("chemistry");
        book.add("mathematics");
        book.add("TOC");
        book.add("Java");
        book.add("Python");
        book.add("Javascript");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book title name to search in:");
        String name = scanner.nextLine();
        int flag = false;

        for(int i = 0; i < book.size(); ++i) {
            if (((String)book.get(i)).equals(name)) {
                flag = true;
                System.out.println("Yes, book is in this list: " + name);
                break;
            }
        }

        if (!flag) {
            System.out.println("This book is not in this list");
        }

    }
}
