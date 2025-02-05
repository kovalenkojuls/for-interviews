package ru.kovalenkojuls;

import java.util.Map;

public record Student(String name, Map<String, Integer> grades) {}
