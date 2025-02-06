package ru.kovalenkojuls.geometrylib;

public record Rectangle(double width, double height) {

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }
}
