package org.example.ui;

import java.util.Scanner;

public class ConsoleReader {
    private final Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
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

    public double readDouble(String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.println("Invalid input: " + prompt);
        }

        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public void close() {
        scanner.close();
    }
}
