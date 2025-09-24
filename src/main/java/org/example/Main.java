package org.example;

import java.util.Scanner;

import org.example.dao.UserDao;
import org.example.dao.UserDaoHibernateImpl;
import org.example.handler.HibernateHandler;
import org.example.service.UserService;
import org.example.service.UserServiceHibernateImpl;
import org.example.timeUtility.QueryBenchmark;
import org.example.ui.ConsoleReader;
import org.example.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        UserService userService = new UserServiceHibernateImpl(userDao);
        ConsoleReader consoleReader = new ConsoleReader(new Scanner(System.in));
        QueryBenchmark queryBenchmark = new QueryBenchmark(userDao);

        ConsoleUI consoleUI = new ConsoleUI(userService, consoleReader, queryBenchmark);

        consoleUI.start();

        HibernateHandler.closeSessionFactory();
    }
}