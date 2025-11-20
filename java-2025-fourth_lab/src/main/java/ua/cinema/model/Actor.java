package ua.cinema.model;

import ua.cinema.util.ActorUtils;
import java.util.Objects;
import java.util.logging.Logger;
import ua.cinema.exception.InvalidDataException;

public record Actor(String firstName, String lastName, int birthYear) {

    private static final Logger LOGGER = Logger.getLogger(Actor.class.getName());

    public Actor(String firstName, String lastName, int birthYear) {
        if (!ActorUtils.isValidBirthYear(birthYear)) {
            throw new IllegalArgumentException("Invalid birth year: " + birthYear);
        }
        this.birthYear = birthYear;

        try {
            this.firstName = (firstName == null) ? null : firstName.trim();
            this.lastName = (lastName == null) ? null : lastName.trim();

            if (this.firstName == null || this.lastName == null) {
                throw new IllegalArgumentException("Name cannot be null");
            }

            ActorUtils.formatFullName(this.firstName, this.lastName);

        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid actor name");
        }
    }

    public static Actor createActor(String firstName, String lastName, int birthYear) {
        try {
            return new Actor(firstName, lastName, birthYear);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static Actor parseActorFromCsv(String csvLine) throws InvalidDataException {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            throw new InvalidDataException("Empty line in actor file");
        }

        String[] parts = csvLine.split(",");
        if (parts.length != 3) {
            throw new InvalidDataException("Invalid data format. Expected 3 parts, got " + parts.length);
        }

        try {
            String firstName = parts[0].trim();
            String lastName = parts[1].trim();
            int birthYear = Integer.parseInt(parts[2].trim());

            return new Actor(firstName, lastName, birthYear);

        } catch (NumberFormatException e) {
            throw new InvalidDataException("Invalid birth year format: not a number", e);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Invalid actor data: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        String name = (firstName() != null && lastName() != null)
                ? ActorUtils.formatFullName(firstName(), lastName())
                : "Unknown";
        return "Actor{" + name + ", birthYear=" + (birthYear() > 0 ? birthYear : "N/A") + "}";
    }
}