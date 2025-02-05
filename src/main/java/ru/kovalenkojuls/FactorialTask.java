package ru.kovalenkojuls;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private final long min;
    private final long max;

    public FactorialTask(long min, long max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected Long compute() {
        if (max - min < 2) {
            long result = min;
            for (long i = min + 1; i <= max; i++) {
                result *= i;
            }
            return result;
        } else {
            long mid = (max + min) / 2;

            FactorialTask leftPart = new FactorialTask(min, mid);
            FactorialTask rightPart = new FactorialTask(mid + 1, max);

            leftPart.fork();
            Long rightResult = rightPart.compute();
            Long leftResult = leftPart.join();

            return leftResult * rightResult;
        }
    }
}
