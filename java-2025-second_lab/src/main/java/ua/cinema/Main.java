package ua.cinema;

import ua.cinema.model.*;
import ua.cinema.util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Movie matrix = Movie.createMovie(
                "The Matrix",
                Genre.SCI_FI,
                136,
                LocalDate.of(1999, 3, 31)
        );
        System.out.println("Factory-created Movie: " + matrix);

        System.out.println("Rating for " + matrix.getGenre() + ": " + getRatingForGenre(matrix.getGenre()));
        System.out.println("Rating for HORROR: " + getRatingForGenre(Genre.HORROR));


        Hall hall = Hall.createHall(5, 120);
        System.out.println("Hall: " + hall);
        if (hall != null) {
            System.out.println("Hall capacity (from record accessor): " + hall.capacity());
        }


        Actor actor = Actor.createActor("  Keanu ", "Reeves", 1964);
        System.out.println("Actor: " + actor);
        if (actor != null) {
            System.out.println("Actor age: " + ActorUtils.calculateAge(actor));
            System.out.println("Actor name (from record accessor): " + actor.firstName());
        }


        Hall badHall = Hall.createHall(-1, 999);
        System.out.println("Bad hall creation attempt: " + badHall);

        Actor badActor = Actor.createActor(null, "Reeves", 1964);
        System.out.println("Bad actor creation attempt: " + badActor);


        Screening screening = new Screening(
                matrix,
                hall,
                LocalDateTime.of(2025, 12, 25, 20, 0)
        );
        System.out.println("Screening: " + screening);

        Ticket ticket = Ticket.createTicket(
                screening,
                42,
                150.0,
                TicketStatus.RESERVED
        );
        System.out.println("Ticket: " + ticket);
    }

    public static String getRatingForGenre(Genre genre) {
        String rating = switch (genre) {
            case ACTION, HORROR, THRILLER -> "R (17+)";
            case COMEDY, SCI_FI -> "PG-13";
            case DRAMA, DOCUMENTARY -> "PG";
        };
        return rating;
    }
}