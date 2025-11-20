package ua.cinema;

import ua.cinema.model.Actor;
import ua.cinema.model.Hall;
import ua.cinema.repository.GenericRepository;
import ua.cinema.repository.IdentityExtractor;

import java.util.Optional;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        IdentityExtractor<Actor> actorExtractor =
                actor -> actor.firstName() + actor.lastName();

        IdentityExtractor<Hall> hallExtractor = Hall::hallNumber;


        GenericRepository<Actor> actorRepo = new GenericRepository<>(actorExtractor);
        GenericRepository<Hall> hallRepo = new GenericRepository<>(hallExtractor);


        LOGGER.info("\nPopulating Actor Repository");
        Actor snake = new Actor("Solid", "Snake", 1972);
        Actor wick = new Actor("John", "Wick", 1969);
        Actor gosling = new Actor("Ryan", "Gosling", 1980);

        actorRepo.add(snake);
        actorRepo.add(wick);
        actorRepo.add(gosling);


        LOGGER.info("\nPopulating Hall Repository");
        Hall hallA = new Hall(140, 85);
        Hall hallB = new Hall(141, 15);

        hallRepo.add(hallA);
        hallRepo.add(hallB);

        LOGGER.info("\nDemonstration:");

        Optional<Actor> foundActor = actorRepo.findByIdentity("SolidSnake");
        foundActor.ifPresentOrElse(
                actor -> System.out.println("FOUND: " + actor),
                () -> System.out.println("NOT FOUND: SolidSnake")
        );

        Optional<Hall> foundHall = hallRepo.findByIdentity(140);
        foundHall.ifPresentOrElse(
                hall -> System.out.println("FOUND: " + hall),
                () -> System.out.println("NOT FOUND: Hall 140")
        );

        Optional<Hall> missingHall = hallRepo.findByIdentity(999);
        missingHall.ifPresentOrElse(
                hall -> System.out.println("FOUND: " + hall),
                () -> System.out.println("NOT FOUND: Hall 999 (Success)")
        );


        LOGGER.info("\nDemonstrating Duplicate Handling");
        LOGGER.info("Attempting to add 'John Wick' a second time...");

        actorRepo.add(wick);


        LOGGER.info("\nDemonstrating getAll (Actors)");
        actorRepo.getAll().forEach(System.out::println);

        LOGGER.info("\nDemonstrating getAll (Halls)");
        hallRepo.getAll().forEach(System.out::println);


        LOGGER.info("\nMain Demonstration Finished");
    }
}