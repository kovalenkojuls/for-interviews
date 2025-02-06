package ru.kovalenkojuls.geometrylib;

public record Circle(double radius) {

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}