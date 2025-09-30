package ua.cinema;

import ua.cinema.model.*;
import ua.cinema.util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // === MOVIE DEMO ===
        Movie johnWick = new Movie("John Wick", "Action", 101, LocalDate.of(2014, 10, 24));
        System.out.println("Movie: " + johnWick);

        // === HALL DEMO ===
        Hall hall1 = new Hall(1, 150);
        System.out.println("Hall: " + hall1);

        // === SCREENING DEMO ===
        Screening screening = new Screening(johnWick, hall1, LocalDateTime.of(2025, 10, 1, 20, 0));
        System.out.println("Screening: " + screening);

        // === TICKET DEMO ===
        Ticket ticket = new Ticket(screening, 42, 250.0);
        System.out.println("Ticket: " + ticket);

        // === ACTOR DEMO ===
        Actor keanu = new Actor("Keanu", "Reeves", 1964);
        System.out.println("Actor: " + keanu);

        // === UTILS DEMO ===
        System.out.println("Duration formatted: " + MovieUtils.formatDuration(johnWick.getDurationMinutes()));
        System.out.println("Actor age: " + ActorUtils.calculateAge(keanu));
    }
}
