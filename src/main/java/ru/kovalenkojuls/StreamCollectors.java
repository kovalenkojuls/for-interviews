package ru.kovalenkojuls;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamCollectors {
    
    public Map<String, List<Order>> groupByProduct(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::product));
    }

    public Map<String, Double> groupByProductAndByCost(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::product,
                        Collectors.summingDouble(Order::cost)
                ));
    }

    public List<Order> sortByCostDesc(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparingDouble(Order::cost).reversed())
                .collect(Collectors.toList());
    }

    public List<Order> getThreeMostExpensiveOrders(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparingDouble(Order::cost).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public double getSumCostByOrders(List<Order> orders) {
        return orders.stream()
                .mapToDouble(Order::cost)
                .sum();
    }
}
