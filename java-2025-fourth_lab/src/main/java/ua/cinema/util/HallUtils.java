package ua.cinema.util;

import ua.cinema.model.Hall;

public class HallUtils {

    public static String formatHallLabel(Hall hall) {
        if (hall == null) return "Hall{null}";
        return "Hall " + hall.hallNumber() + " (capacity " + hall.capacity() + ")";
    }

    public static boolean isValidCapacity(int capacity) {
        return ValidationHelper.isNumberBetween(capacity, 1, 1000);
    }

    public static boolean isValidHallNumber(int number) {
        return ValidationHelper.isNumberBetween(number, 1, 10000);
    }
}