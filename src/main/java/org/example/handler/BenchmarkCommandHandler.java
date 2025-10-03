package org.example.handler;

import org.example.utilitys.QueryBenchmark;

public class BenchmarkCommandHandler {
    private final QueryBenchmark queryBenchmark;

    public BenchmarkCommandHandler(QueryBenchmark queryBenchmark) {
        this.queryBenchmark = queryBenchmark;
    }

    public void getQueryPerformance() {
        queryBenchmark.getQueryPerformance();
    }
}
