package ua.cinema.model;

import ua.cinema.util.HallUtils;
import java.util.logging.Logger;
import ua.cinema.exception.InvalidDataException;

public record Hall(int hallNumber, int capacity) implements Comparable<Hall> {

    private static final Logger LOGGER = Logger.getLogger(Hall.class.getName());

    public Hall {
        if (!HallUtils.isValidHallNumber(hallNumber)) {
            throw new IllegalArgumentException("Invalid hall number: " + hallNumber);
        }
        if (!HallUtils.isValidCapacity(capacity)) {
            throw new IllegalArgumentException("Invalid capacity: " + capacity);
        }
    }

    @Override
    public int compareTo(Hall other) {
        return Integer.compare(this.hallNumber, other.hallNumber);
    }

    public static Hall createHall(int hallNumber, int capacity) {
        try {
            return new Hall(hallNumber, capacity);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static Hall parseHallFromCsv(String csvLine) throws InvalidDataException {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            throw new InvalidDataException("Empty line in hall file");
        }

        String[] parts = csvLine.split(",");
        if (parts.length != 2) {
            throw new InvalidDataException("Invalid data format. Expected 2 parts, got " + parts.length);
        }

        try {
            int hallNumber = Integer.parseInt(parts[0].trim());
            int capacity = Integer.parseInt(parts[1].trim());

            return new Hall(hallNumber, capacity);

        } catch (NumberFormatException e) {
            throw new InvalidDataException("Invalid number format: not a number", e);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Invalid hall data: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return HallUtils.formatHallLabel(this);
    }
}