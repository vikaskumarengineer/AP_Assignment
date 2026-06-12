package com.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreProcessor {

    public int processScoreFile(String filePath)
            throws FileNotFoundException {

        Scanner scanner = null;

        try {

            File file = new File(filePath);

            scanner = new Scanner(file);

            String data = scanner.nextLine();

            int score = Integer.parseInt(data);

            return score * 10;

        }

        catch (FileNotFoundException e) {

            System.out.println("Error: File not found.");
            throw e;
        }

        catch (NumberFormatException e) {

            System.out.println("Error: Invalid number format in file.");
            throw e;
        }

        finally {

            if (scanner != null) {
                scanner.close();
            }

            System.out.println("File cleanup completed");
        }
    }
}