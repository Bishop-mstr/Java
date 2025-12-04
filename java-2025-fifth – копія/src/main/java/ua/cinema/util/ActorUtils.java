package ua.cinema.util;

import ua.cinema.model.Actor;
import java.time.Year;

public class ActorUtils {

    public static String formatFullName(String firstName, String lastName) {
        if (!ValidationHelper.isStringLengthBetween(firstName, 1, 50) ||
                !ValidationHelper.isStringLengthBetween(lastName, 1, 50)) {
            throw new IllegalArgumentException("Invalid actor name");
        }
        return capitalize(firstName) + " " + capitalize(lastName);
    }

    public static int calculateAge(Actor actor) {
        if (actor == null) throw new IllegalArgumentException("actor is null");
        int currentYear = Year.now().getValue();
        return currentYear - actor.birthYear();
    }

    private static String capitalize(String s) {
        String t = s.trim();
        if (t.isEmpty()) return t;
        return t.substring(0,1).toUpperCase() + t.substring(1).toLowerCase();
    }

    public static boolean isValidBirthYear(int year) {
        int curr = Year.now().getValue();
        return ValidationHelper.isNumberBetween(year, 1900, curr);
    }
}