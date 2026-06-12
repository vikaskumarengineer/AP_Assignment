package com.assignment;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreProcessorTest {

    @Test
    void testValidScoreFile() throws Exception {

        FileWriter writer =
                new FileWriter("testValid.txt");

        writer.write("8");

        writer.close();

        ScoreProcessor processor =
                new ScoreProcessor();

        int result =
                processor.processScoreFile("testValid.txt");

        assertEquals(80, result);
    }

    @Test
    void testMissingFile() {

        ScoreProcessor processor =
                new ScoreProcessor();

        assertThrows(Exception.class, () -> {

            processor.processScoreFile("missingFile.txt");

        });
    }
}