package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    private static final Map<String, Logger> loggers = new HashMap<>();

    private final String name;
    private Level level;

    Logger(String name) {
        this.name = name;
        this.level = Level.DEBUG;
        loggers.put(name, this);
    }
    public String getName()
    {
        return name;
    }
    public static Logger getLogger(String name)
    {
        if (loggers.get(name) == null) loggers.put(name, new Logger(name));
        return loggers.get(name);
    }
    public Level getLevel()
    {
        return level;
    }
    public void setLevel(Level level)
    {
        this.level=level;
    }

    public void log(Level level,String message)
    {
        if (this.level.ordinal()<=level.ordinal())
        {
            System.out.println(formatMessage(level, message));
        }
    }

    public void log(Level level, String format, Object... args) {
        if (this.level.ordinal() <= level.ordinal()) {
            String message = String.format(format, args);
            System.out.println(formatMessage(level, message));
        }
    }

    private String formatMessage(Level level, String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String timestamp = dateFormat.format(new Date());
        return String.format("[%s] %s %s - %s", level, timestamp, name, message);
    }

    public void debug(String massage)
    {
        log(Level.DEBUG, massage);
    }
    public void debug(String format,Object... args)
    {
        log(Level.DEBUG, format, args);
    }

    public void info(String massage)
    {
        log(Level.INFO, massage);
    }
    public void info(String format,Object... args)
    {
        log(Level.INFO, format, args);
    }

    public void warning(String massage)
    {
        log(Level.WARNING, massage);
    }
    public void warning(String format,Object... args)
    {
        log(Level.WARNING, format, args);
    }

    public void error(String massage)
    {
        log(Level.ERROR, massage);
    }
    public void error(String format,Object... args)
    {
        log(Level.ERROR, format, args);
    }
}
