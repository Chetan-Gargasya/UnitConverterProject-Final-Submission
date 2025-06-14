package com.mycompany.unitconverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ConversionDAO {
    private static final String FILE_NAME = "conversion_log.txt";

    public static void saveConversion(Conversion conversion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(conversion.toFileString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving conversion: " + e.getMessage());
        }
    }
}