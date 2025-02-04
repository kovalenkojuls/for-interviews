package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class BlockingQueue<T> {

    private static final Logger logger = LoggerFactory.getLogger(BlockingQueue.class);

    private final int MAX_QUEUE_SIZE = 5;
    private final LinkedList<T> queue = new LinkedList<>();

    public synchronized void enqueue(T item) throws InterruptedException {
        long threadId = Thread.currentThread().getId();

        while (queue.size() >= MAX_QUEUE_SIZE) {
            logger.info("Queue full, thread {} is waiting", threadId);
            wait();
        }
        queue.offer(item);
        logger.info("Thread {}: enqueued {}", threadId, item);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        long threadId = Thread.currentThread().getId();

        while (queue.isEmpty()) {
            logger.info("Queue empty, thread {} is waiting", threadId);
            wait();
        }
        T item = queue.poll();
        logger.info("Thread {}: dequeued {}", threadId, item);
        notifyAll();
        return item;
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new BlockingQueue<>();
        int consumerCnt = 5;
        int producerCnt = 5;

        for (int i = 0; i < producerCnt; i++) {
            int producerId = i;
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        bq.enqueue(producerId * 10 + j);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }

        for (int i = 0; i < consumerCnt; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        bq.dequeue();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }
}
