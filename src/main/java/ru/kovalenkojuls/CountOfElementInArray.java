package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/*
    Напишите метод, который получает на вход массив элементов и возвращает Map ключи в котором - элементы,
    а значения - сколько раз встретился этот элемент.
 */
public class CountOfElementInArray {

    private static final Logger logger = LoggerFactory.getLogger(CountOfElementInArray.class);

    public static <T> Map<T, Integer> countOfElement(T[] array) {
        HashMap<T, Integer> mapCount = new HashMap<>();

        for (T arrayElement: array) {
            mapCount.put(arrayElement, mapCount.getOrDefault(arrayElement, 0) + 1);
        }

        return mapCount;
    }

    public static void main(String[] args) {
        Integer[] arrayInt = new Integer[]{1, 2, 3, 1, 2, 4, 5, 6, 6, 6, 6};
        String[] arrayStr = new String[]{"a", "b", "c", "a", "a", "b", "d"};

        logger.info(countOfElement(arrayInt).toString());
        logger.info(countOfElement(arrayStr).toString());
    }
}