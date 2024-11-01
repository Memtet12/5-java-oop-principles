package com.example.task04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String baseFilePath;
    private final ChronoUnit rotationInterval;
    private LocalDateTime lastRotationTime;

    public RotationFileHandler(String baseFilePath, ChronoUnit rotationInterval) {
        this.baseFilePath = baseFilePath;
        this.rotationInterval = rotationInterval;
        this.lastRotationTime = LocalDateTime.now();
    }

    @Override
    public void log(String message) {
        LocalDateTime now = LocalDateTime.now();
        if (lastRotationTime.plus(1, rotationInterval).isBefore(now)) {
            lastRotationTime = now;
        }

        String filePath = baseFilePath + "_" + lastRotationTime.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".log";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}