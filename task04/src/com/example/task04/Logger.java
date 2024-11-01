package com.example.task04;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger {
    private static final List<Logger> loggers = new ArrayList<>();

    private final String name;
    private Level level;
    private final List<MessageHandler> handlers;

    private Logger(String name) {
        this.name = name;
        this.level = Level.DEBUG;
        this.handlers = new ArrayList<>();
    }

    public static Logger getLogger(String name) {
        for (Logger logger : loggers) {
            if (logger.getName().equals(name)) {
                return logger;
            }
        }
        Logger logger = new Logger(name);
        loggers.add(logger);
        return logger;
    }

    public String getName() {
        return name;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        log(Level.DEBUG, format, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String format, Object... args) {
        log(Level.INFO, format, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String format, Object... args) {
        log(Level.WARNING, format, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String format, Object... args) {
        log(Level.ERROR, format, args);
    }

    public void log(Level level, String message) {
        if (this.level.ordinal() <= level.ordinal()) {
            String formattedMessage = formatMessage(level, message);
            for (MessageHandler handler : handlers) {
                handler.log(formattedMessage);
            }
        }
    }

    public void log(Level level, String format, Object... args) {
        if (this.level.ordinal() <= level.ordinal()) {
            String message = String.format(format, args);
            log(level, message);
        }
    }

    private String formatMessage(Level level, String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        return String.format("[%s] %s %s - %s", level, timestamp, name, message);
    }

}