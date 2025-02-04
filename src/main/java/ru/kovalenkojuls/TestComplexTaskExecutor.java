package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    Создайте класс ComplexTask, представляющий сложную задачу, которую несколько потоков будут выполнять.
    В каждой задаче реализуйте метод execute(), который выполняет часть сложной задачи. Создайте класс
    ComplexTaskExecutor, в котором будет использоваться CyclicBarrier и ExecutorService для синхронизации
    выполнения задач. Реализуйте метод executeTasks(int numberOfTasks), который создает пул потоков и
    назначает каждому потоку экземпляр сложной задачи для выполнения. Затем используйте CyclicBarrier для
    ожидания завершения всех потоков и объединения результатов их работы. В методе main создайте экземпляр
    ComplexTaskExecutor и вызовите метод executeTasks с несколькими задачами для выполнения.
 */
public class TestComplexTaskExecutor {

    private static final Logger logger = LoggerFactory.getLogger(TestComplexTaskExecutor.class);

    public static void main(String[] args) {
        ComplexTaskExecutor taskExecutor = new ComplexTaskExecutor(5);

        Runnable testRunnable = () -> {
            logger.info("{} started the test.", Thread.currentThread().getName());
            taskExecutor.executeTasks(5);
            logger.info("{} completed the test.", Thread.currentThread().getName());
        };

        Thread thread1 = new Thread(testRunnable, "TestThread-1");
        Thread thread2 = new Thread(testRunnable, "TestThread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        taskExecutor.shutdown();
    }
}
