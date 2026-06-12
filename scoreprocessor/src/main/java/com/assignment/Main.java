package com.assignment;

public class Main {

    public static void main(String[] args) {

        ScoreProcessor processor = new ScoreProcessor();

        try {

            int result =
                    processor.processScoreFile("validScore.txt");

            System.out.println("Processed Score: " + result);

        }

        catch (Exception e) {

            System.out.println("Program handled an exception.");
        }
    }
}