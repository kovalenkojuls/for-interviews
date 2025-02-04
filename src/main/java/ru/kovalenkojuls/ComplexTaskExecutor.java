package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ComplexTaskExecutor {

    private static final Logger logger = LoggerFactory.getLogger(ComplexTaskExecutor.class);
    private final CyclicBarrier barrier;
    private final ExecutorService executor;
    private final AtomicInteger sum;

    ComplexTaskExecutor(int numThreads) {
        barrier = new CyclicBarrier(numThreads + 1);
        executor = Executors.newFixedThreadPool(numThreads);
        sum = new AtomicInteger(0);
    }

    public synchronized void executeTasks(int numberOfTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            int taskNumber = i;
            executor.submit(() -> {
                logger.info("{}: starting task {}", Thread.currentThread().getName(), taskNumber);
                ComplexTask task = new ComplexTask();
                int result = task.execute();
                logger.info("{}: task {} finished. Result: {}", Thread.currentThread().getName(), taskNumber, result);
                sum.addAndGet(result);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });
        }

        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        logger.info("{}: Total result: {}", Thread.currentThread().getName(), sum.get());
        sum.set(0);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
