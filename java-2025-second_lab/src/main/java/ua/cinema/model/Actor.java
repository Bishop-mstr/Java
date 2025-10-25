package ua.cinema.model;

import ua.cinema.util.ActorUtils;
import java.util.Objects;

public record Actor(String firstName, String lastName, int birthYear) {

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

    @Override
    public String toString() {
        String name = (firstName() != null && lastName() != null)
                ? ActorUtils.formatFullName(firstName(), lastName())
                : "Unknown";
        return "Actor{" + name + ", birthYear=" + (birthYear() > 0 ? birthYear : "N/A") + "}";
    }
}