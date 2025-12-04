package ua.cinema;

import ua.cinema.model.Actor;
import ua.cinema.model.Hall;
import ua.cinema.repository.ActorRepository;
import ua.cinema.repository.HallRepository;

import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ActorRepository actorRepo = new ActorRepository();
        HallRepository hallRepo = new HallRepository();

        actorRepo.add(new Actor("Solid", "Snake", 1972));
        actorRepo.add(new Actor("John", "Wick", 1969));
        actorRepo.add(new Actor("Ryan", "Gosling", 1980));
        actorRepo.add(new Actor("Keanu", "Reeves", 1964));

        hallRepo.add(new Hall(5, 120));
        hallRepo.add(new Hall(1, 150));
        hallRepo.add(new Hall(2, 200));

        System.out.println("\nActors Sorted by Name");
        actorRepo.getAllSortedByDefault().forEach(System.out::println);

        System.out.println("\nActors Sorted by Year (Oldest First)");
        actorRepo.getAllSortedByAge(true).forEach(System.out::println);

        System.out.println("\nActors Sorted by Year (Youngest First)");
        actorRepo.getAllSortedByAge(false).forEach(System.out::println);

        System.out.println("\nHalls Sorted by Number (Comparable)");
        hallRepo.getAllSortedByNumber().forEach(System.out::println);

        System.out.println("\nHalls Sorted by Capacity (Comparator)");
        hallRepo.getAllSortedByCapacity().forEach(System.out::println);

        LOGGER.info("Main Demonstration Finished");
    }
}