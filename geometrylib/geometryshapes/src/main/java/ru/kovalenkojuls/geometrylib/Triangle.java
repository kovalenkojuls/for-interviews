package ru.kovalenkojuls.geometrylib;

public record Triangle(double a, double b, double c) {

    public double getArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public double getPerimeter() {
        return a + b + c;
    }
}

