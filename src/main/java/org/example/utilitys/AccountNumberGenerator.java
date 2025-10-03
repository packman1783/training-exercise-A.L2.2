package org.example.utilitys;

public class AccountNumberGenerator {
    private static long counter = 100L;

    public static synchronized long nextAccountNumber() {
        return counter++;
    }
}
