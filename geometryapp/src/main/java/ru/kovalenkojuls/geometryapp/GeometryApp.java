package ru.kovalenkojuls.geometryapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kovalenkojuls.GeometryUtils;
import ru.kovalenkojuls.geometrylib.Circle;
import ru.kovalenkojuls.geometrylib.Rectangle;
import ru.kovalenkojuls.geometrylib.Triangle;

public class GeometryApp {

    public final static Logger logger = LoggerFactory.getLogger(GeometryApp.class);

    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);
        Triangle triangle = new Triangle(3, 4, 5);

        logger.info("Круг:");
        logger.info("Радиус: {}", circle.radius());
        logger.info("Площадь: {}", circle.getArea());
        logger.info("Периметр: {}", circle.getPerimeter());
        logger.info("------------------------");

        logger.info("Прямоугольник:");
        logger.info("Ширина: {}", rectangle.width());
        logger.info("Высота: {}", rectangle.height());
        logger.info("Площадь: {}", rectangle.getArea());
        logger.info("Периметр: {}", rectangle.getPerimeter());
        logger.info("------------------------");

        logger.info("Треугольник:");
        logger.info("Сторона a: {}", triangle.a());
        logger.info("Сторона b: {}", triangle.b());
        logger.info("Сторона c: {}", triangle.c());
        logger.info("Площадь: {}", triangle.getArea());
        logger.info("Периметр: {}", triangle.getPerimeter());
        logger.info("------------------------");

        logger.info("GeometryUtils.convertCmToMeters(1000): {}", GeometryUtils.convertCmToMeters(1000));
    }
}
