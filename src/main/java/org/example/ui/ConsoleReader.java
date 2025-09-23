package org.example.ui;

import java.util.Scanner;

public class ConsoleReader {
    private final Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Invalid input: " + prompt);
        }

        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public long readLong(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextLong()) {
            scanner.nextLine();
            System.out.println("Invalid input: " + prompt);
        }

        long value = scanner.nextLong();
        scanner.nextLine();
        return value;
    }

    public void close() {
        scanner.close();
    }
}
