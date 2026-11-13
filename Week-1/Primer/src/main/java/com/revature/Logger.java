package com.revature;

public class Logger {
    public enum LogType {
        Prompt,
        Text,
        Info,
        Warning,
        Error,
        Success
    }

    private static final Object ConsoleLock = new Object();

    private static boolean hasWrittenOutput;
    private static boolean lastLineWasBlank;

    private static String getLogString(LogType type) {
        return switch (type) {
            case Prompt -> "[Prompt]";
            case Text -> "";
            case Warning -> "[Warning]";
            case Error -> "[Error]";
            case Success -> "[Success]";
            default -> "[Info]";
        };
    }

    private static String getLogColor(LogType type) {
        return switch (type) {
            case Prompt -> "\u001B[34m";
            case Text -> "\u001B[30m";
            case Info -> "\u001B[36m";
            case Warning -> "\u001B[33m";
            case Error -> "\u001B[31m";
            case Success -> "\u001B[32m";
            default -> "\u001B[37m";
        };
    }

    private static String formatLogString(LogType type, String message) {
        return switch (type) {
            case Text -> message;
            default -> String.format("%s: %s", getLogString(type), message);
        };
    }

    public static void logMessage(LogType type, String message, boolean newLine) {
        synchronized (ConsoleLock) {
            String formattedMessage = formatLogString(type, message);

            if (newLine && isNullOrWhitespace(formattedMessage)) {
                writeBlankLineInternal();

                return;
            }

            System.out.println(getLogColor(type));

            try {
                if (newLine) {
                    System.out.println(formattedMessage);
                } else {
                    System.out.print(formattedMessage);
                }
            } finally {
                System.out.println("\u001B[0m");
            }

            hasWrittenOutput = true;
            lastLineWasBlank = false;
        }
    }

    public static void blankLine() {
        synchronized (ConsoleLock) {
            writeBlankLineInternal();
        }
    }

    public static void writeRawLine(String message, boolean writeToError) {
        synchronized (ConsoleLock) {
            if (isNullOrWhitespace(message)) {
                return;
            }

            if (writeToError) {
                System.err.println(message);
            } else {
                System.out.println(message);
            }

            hasWrittenOutput = true;
            lastLineWasBlank = false;
        }
    }

    public static void resetSpacing() {
        synchronized (ConsoleLock) {
            hasWrittenOutput = false;
            lastLineWasBlank = false;
        }
    }

    private static void writeBlankLineInternal() {
        if (!hasWrittenOutput || lastLineWasBlank) {
            return;
        }

        System.out.println();

        lastLineWasBlank = true;
    }

    private static boolean isNullOrWhitespace(String string) {
        return string == null || string.isBlank();
    }
}
