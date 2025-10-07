package ua.cinema;

import ua.cinema.model.*;
import ua.cinema.util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // === MOVIE DEMO ===
        Movie movie = new Movie("John Wick", "Action", 101, LocalDate.of(2014, 10, 24));
        System.out.println("Movie: " + movie);
        System.out.println("Formatted duration: " + MovieUtils.formatDuration(movie.getDurationMinutes()));

        // === ACTOR DEMO ===
        Actor actor = new Actor("Keanu", "Reeves", 1964);
        System.out.println("Actor: " + actor);
        System.out.println("Actor age: " + ActorUtils.calculateAge(actor));
        System.out.println("Full name formatted: " + ActorUtils.formatFullName("  keanu ", "REEVES"));

        // === HALL DEMO ===
        Hall hall = new Hall(1, 150);
        System.out.println("Hall: " + hall);

        // === SCREENING + TICKET DEMO ===
        Screening screening = new Screening(movie, hall, LocalDateTime.of(2025, 10, 1, 20, 0));
        System.out.println("Screening: " + screening);

        Ticket ticket = new Ticket(screening, 42, 250.0);
        System.out.println("Ticket: " + ticket);

        // === VALIDATION DEMO ===
        System.out.println("Valid birth year? 1964 -> " + ActorUtils.isValidBirthYear(1964));
        System.out.println("Valid birth year? 3000 -> " + ActorUtils.isValidBirthYear(3000));

        // === STATIC FACTORY DEMO ===
        Movie factoryMovie = Movie.createMovie("Matrix", "Sci-Fi", 136, LocalDate.of(1999, 3, 31));
        System.out.println("Factory-created Movie: " + factoryMovie);
    }
}
