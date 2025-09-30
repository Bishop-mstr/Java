package ua.cinema.model;

import ua.cinema.util.ActorUtils;

import java.util.Objects;

public class Actor {
    private String firstName;
    private String lastName;
    private int birthYear;

    public Actor() {}

    public Actor(String firstName, String lastName, int birthYear) {
        try {
            this.firstName = firstName == null ? null : firstName.trim();
            this.lastName = lastName == null ? null : lastName.trim();
            if (!ActorUtils.isValidBirthYear(birthYear)) {
                this.birthYear = 0;
            } else {
                this.birthYear = birthYear;
            }
            // validate lengths using ActorUtils.formatFullName indirectly by attempting it:
            if (this.firstName != null && this.lastName != null) {
                ActorUtils.formatFullName(this.firstName, this.lastName);
            } else {
                if (this.firstName == null) this.firstName = null;
                if (this.lastName == null) this.lastName = null;
            }
        } catch (IllegalArgumentException ex) {
            // invalid names -> null
            this.firstName = null;
            this.lastName = null;
            this.birthYear = 0;
        }
    }

    public static Actor createActor(String firstName, String lastName, int birthYear) {
        Actor a = new Actor(firstName, lastName, birthYear);
        if (a.firstName == null || a.lastName == null || a.birthYear == 0) return null;
        return a;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
        try {
            ActorUtils.formatFullName(this.firstName, this.lastName == null ? "" : this.lastName);
        } catch (Exception e) { this.firstName = null; }
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
        try {
            ActorUtils.formatFullName(this.firstName == null ? "" : this.firstName, this.lastName);
        } catch (Exception e) { this.lastName = null; }
    }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) {
        if (ActorUtils.isValidBirthYear(birthYear)) this.birthYear = birthYear;
        else this.birthYear = 0;
    }

    @Override
    public String toString() {
        String name = (firstName != null && lastName != null) ? ActorUtils.formatFullName(firstName, lastName) : "Unknown";
        return "Actor{" + name + ", birthYear=" + (birthYear > 0 ? birthYear : "N/A") + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return birthYear == actor.birthYear &&
                Objects.equals(firstName, actor.firstName) &&
                Objects.equals(lastName, actor.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthYear);
    }
}
