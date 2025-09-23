package org.example;

import org.example.handler.HibernateHandler;
import org.example.service.UserService;
import org.example.service.UserServiceHibernateImpl;
import org.example.ui.ConsoleReader;
import org.example.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceHibernateImpl();
        ConsoleReader consoleReader = new ConsoleReader();

        ConsoleUI consoleUI = new ConsoleUI(userService, consoleReader);

        consoleUI.start();

        HibernateHandler.closeSessionFactory();
    }
}