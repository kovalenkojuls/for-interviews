package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/*
    Создайте список заказов с разными продуктами и их стоимостями.
    Группируйте заказы по продуктам.
    Для каждого продукта найдите общую стоимость всех заказов.
    Отсортируйте продукты по убыванию общей стоимости.
    Выберите три самых дорогих продукта.
    Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
 */
public class StreamCollectorsExample {

    private final static Logger logger = LoggerFactory.getLogger(StreamCollectorsExample.class);

    public static void main(String[] args) {
        StreamCollectors sc = new StreamCollectors();

        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

//        logger.info(sc.groupByProduct(orders).toString());
//        logger.info(sc.groupByProductAndByCost(orders).toString());
//        logger.info(sc.sortByCostDesc(orders).toString());
//        logger.info(sc.getThreeMostExpensiveOrder(orders).toString());

        List<Order> threeOrder = sc.getThreeMostExpensiveOrders(orders);
        double sumCost = sc.getSumCostByOrders(threeOrder);

        logger.info("The three most expensive orders: {}", threeOrder);
        logger.info("Their total cost: {}", sumCost);
    }
}
