package ru.kovalenkojuls;

import java.util.Random;

public class ComplexTask {
    public int execute() {
        int work = (new Random()).nextInt(1000) + 500;
        try {
            Thread.sleep(work);
        } catch (InterruptedException ignored) {
        }
        return work;
    }
}