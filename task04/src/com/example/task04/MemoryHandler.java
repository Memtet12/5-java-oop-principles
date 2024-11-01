package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final List<String> messages;
    private final int bufferSize;
    private final MessageHandler targetHandler;

    public MemoryHandler(int bufferSize, MessageHandler targetHandler) {
        this.bufferSize = bufferSize;
        this.targetHandler = targetHandler;
        this.messages = new ArrayList<>(bufferSize);
    }

    @Override
    public void log(String message) {
        messages.add(message);
        if (messages.size() >= bufferSize) {
            flush();
        }
    }

    public void flush() {
        for (String message : messages) {
            targetHandler.log(message);
        }
        messages.clear();
    }
}