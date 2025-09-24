package org.example.timeUtility;

import org.example.dao.UserDao;

public class QueryBenchmark {
    private final UserDao userDao;

    public QueryBenchmark(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getQueryPerformance() {
        Long time1 = measureTime(userDao::getUsersWithHql);
        Long time2 = measureTime(userDao::getUsersWithNativeQuery);
        Long time3 = measureTime(userDao::getUsersWithCriteria);

        printResults("HQL", time1);
        printResults("Native SQL", time2);
        printResults("Criteria SQL", time3);
    }

    private Long measureTime(Runnable query) {
        long start = System.nanoTime();
        query.run();

        return System.nanoTime() - start;
    }

    private void printResults(String queryType, Long timeNs) {
        Long timeMs = timeNs / 1_000_000;
        System.out.printf("%s : %d ms %d ns %n", queryType, timeMs, timeNs);
    }
}
