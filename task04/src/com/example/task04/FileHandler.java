package com.example.task04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements MessageHandler {
    private final String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
