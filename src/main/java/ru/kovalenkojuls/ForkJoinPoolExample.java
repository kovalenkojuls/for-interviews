package ru.kovalenkojuls;

import java.util.concurrent.ForkJoinPool;

/*
    Написать вычисление факториала числа с использованием ForkJoinPool.
 */
public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 10;

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(2, n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
}