package ua.cinema.model;

import ua.cinema.util.HallUtils;
import java.util.Objects;

public record Hall(int hallNumber, int capacity) {

    public Hall {
        if (!HallUtils.isValidHallNumber(hallNumber)) {
            throw new IllegalArgumentException("Invalid hall number: " + hallNumber);
        }
        if (!HallUtils.isValidCapacity(capacity)) {
            throw new IllegalArgumentException("Invalid capacity: " + capacity);
        }
    }

    public static Hall createHall(int hallNumber, int capacity) {
        try {
            return new Hall(hallNumber, capacity);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return HallUtils.formatHallLabel(this);
    }
}