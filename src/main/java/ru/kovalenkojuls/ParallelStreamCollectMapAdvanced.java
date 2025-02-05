package ru.kovalenkojuls;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStreamCollectMapAdvanced {

    public Map<String, Double> getAvgScoreByGrades(List<Student> students) {
        return students.parallelStream()
                .flatMap(s -> s.grades().entrySet().stream())
                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.averagingDouble(Map.Entry::getValue)
                        )
                );
    }
}
