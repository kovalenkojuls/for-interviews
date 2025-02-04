package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ComplexTaskExecutor {

    private static final Logger logger = LoggerFactory.getLogger(ComplexTaskExecutor.class);
    private final CyclicBarrier barrier;
    private final ExecutorService executor;
    private final AtomicInteger totalResult;

    ComplexTaskExecutor(int numThreads) {
        totalResult = new AtomicInteger(0);
        barrier = new CyclicBarrier(numThreads, () -> {
            logger.info("{}: Total result: {}", Thread.currentThread().getName(), totalResult.get());
            totalResult.set(0);
        });
        executor = Executors.newFixedThreadPool(numThreads);
    }

    public synchronized void executeTasks(int numberOfTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            int taskNumber = i;
            executor.submit(() -> {
                logger.info("{}: starting task {}", Thread.currentThread().getName(), taskNumber);
                ComplexTask task = new ComplexTask();
                int result = task.execute();
                logger.info("{}: task {} finished. Result: {}", Thread.currentThread().getName(), taskNumber, result);
                totalResult.addAndGet(result);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
