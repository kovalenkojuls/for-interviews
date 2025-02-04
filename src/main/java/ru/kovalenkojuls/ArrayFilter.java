package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/*
    Напишите метод filter, который принимает на вход массив любого типа, вторым арументом метод должен принимать клас,
    реализующий интерфейс Filter, в котором один метод - Object apply(Object o). Метод должен быть реализован так чтобы
    возращать новый масив, к каждому элементу которого была применена функция apply
 */
interface Filter {
    Object apply(Object o);
}

public class ArrayFilter {

    private static final Logger logger = LoggerFactory.getLogger(ArrayFilter.class);

    @SuppressWarnings("unchecked")
    public static <T> T[] filter(T[] array, Filter filterFunction) {
        if (array == null || filterFunction == null) {
            throw new IllegalArgumentException("argument is null");
        }

        T[] newArray = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length; i++) {
            newArray[i] = (T) filterFunction.apply(array[i]);
        }

        return newArray;
    }

    public static void main(String[] args) {
        Filter toUpperCaseFunction = (Object o) -> {
            if (o instanceof String) {
                return ((String) o).toUpperCase();
            }
            return o;
        };

        String[] arrayStr = new String[]{"a", "b", "c"};
        logger.info(Arrays.toString(ArrayFilter.filter(arrayStr, toUpperCaseFunction)));

        Filter squaringIntegerFunction = (Object o) -> {
            if (o instanceof Integer) {
                return (Integer) o * (Integer) o;
            }
            return o;
        };

        Integer[] arrayInt = new Integer[]{2, 3, 4, 5};
        logger.info(Arrays.toString(ArrayFilter.filter(arrayInt, squaringIntegerFunction)));
    }
}
