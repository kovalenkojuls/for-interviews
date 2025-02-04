package ru.kovalenkojuls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;

/*
    Напишите свой StringBuilder с добавлением дополнительного метода - undo()
*/
public class CustomStringBuilder {

    private static final Logger logger = LoggerFactory.getLogger(CustomStringBuilder.class);

    private StringBuilder sb;
    private final ArrayDeque<Memento> history = new ArrayDeque<>();

    public CustomStringBuilder() {
        this.sb = new StringBuilder();
    }

    public CustomStringBuilder(String str) {
        this.sb = new StringBuilder(str);
    }

    public CustomStringBuilder append(int i) {
        saveStateToHistory();
        sb.append(i);
        return this;
    }

    public CustomStringBuilder append(String str) {
        saveStateToHistory();
        sb.append(str);
        return this;
    }

    public void undo() {
        if (!history.isEmpty()) {
            sb = history.pop().state();
        }
    }

    public String toString() {
        return sb.toString();
    }

    private void saveStateToHistory() {
        history.push(new Memento(new StringBuilder(sb)));
    }

    private record Memento(StringBuilder state) {}

    public static void main(String[] args) {
        CustomStringBuilder csb = new CustomStringBuilder("a");
        csb.append(1).append("b").append(2).append("c").append(3);

        logger.info(String.valueOf(csb));
        csb.undo();
        logger.info(String.valueOf(csb));
        csb.undo();
        logger.info(String.valueOf(csb));
        csb.undo();
        logger.info(String.valueOf(csb));
    }
}