package org.example.utilitys;

import java.util.List;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.example.dao.UserDao;
import org.example.entity.User;

public class QueryBenchmark {
    private static final Logger logger = LogManager.getLogger(QueryBenchmark.class);

    private final UserDao userDao;

    public QueryBenchmark(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getQueryPerformance() {
        Long time1 = measureTime(userDao::getUsersWithHql, "HQL");
        Long time2 = measureTime(userDao::getUsersWithNativeQuery, "Native SQL");
        Long time3 = measureTime(userDao::getUsersWithCriteria, "Criteria API");

        printResults("HQL", time1);
        printResults("Native SQL", time2);
        printResults("Criteria API", time3);
    }

    private Long measureTime(Supplier<List<User>> query, String queryType) {
        long start = System.nanoTime();
        List<User> result = query.get();
        long duration = System.nanoTime() - start;

        logger.info("{} executed, returned {} results, took {} ns", queryType, result.size(), duration);

        return duration;
    }

    private void printResults(String queryType, Long timeNs) {
        Long timeMs = timeNs / 1_000_000;
        System.out.printf("%s : %d ms %d ns %n", queryType, timeMs, timeNs);
    }
}
