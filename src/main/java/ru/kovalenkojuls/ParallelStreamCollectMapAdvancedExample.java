package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
    Создайте коллекцию студентов, где каждый студент содержит информацию о предметах, которые он изучает,
    и его оценках по этим предметам.
    Используйте Parallel Stream для обработки данных и создания Map, где ключ - предмет, а значение - средняя оценка
    по всем студентам.
    Выведите результат: общую Map с средними оценками по всем предметам.
 */
public class ParallelStreamCollectMapAdvancedExample {

    private static final Logger logger = LoggerFactory.getLogger(ParallelStreamCollectMapAdvancedExample.class);

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );
        ParallelStreamCollectMapAdvanced pscma = new ParallelStreamCollectMapAdvanced();

        logger.info(pscma.getAvgScoreByGrades(students).toString());
    }
}