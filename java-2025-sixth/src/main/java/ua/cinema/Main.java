package ua.cinema;

import ua.cinema.model.Actor;
import ua.cinema.model.Hall;
import ua.cinema.repository.ActorRepository;
import ua.cinema.repository.HallRepository;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Application started");

        ActorRepository actorRepo = new ActorRepository();
        HallRepository hallRepo = new HallRepository();

        actorRepo.add(new Actor("Solid", "Snake", 1972));
        actorRepo.add(new Actor("John", "Wick", 1969));
        actorRepo.add(new Actor("Ryan", "Gosling", 1980));
        actorRepo.add(new Actor("Keanu", "Reeves", 1964));
        actorRepo.add(new Actor("Tom", "Holland", 1996));

        hallRepo.add(new Hall(1, 150));
        hallRepo.add(new Hall(2, 200));
        hallRepo.add(new Hall(3, 50));
        hallRepo.add(new Hall(4, 100));

        System.out.println("\nActors born after 1970:");
        List<Actor> youngActors = actorRepo.findActorsBornAfter(1970);
        youngActors.forEach(System.out::println);

        System.out.println("\nList of actor last names:");
        List<String> lastNames = actorRepo.getActorLastNames();
        lastNames.forEach(name -> System.out.println("Last name: " + name));

        System.out.println("\nCalculating total cinema capacity:");
        int totalSeats = hallRepo.getTotalCinemaCapacity();
        System.out.println("Total seats: " + totalSeats);

        System.out.println("\nPerformance test results (Stream vs ParallelStream):");
        runPerformanceTest();

        LOGGER.info("Application finished");
    }

    private static void runPerformanceTest() {
        LOGGER.info("Generating test data (1,000,000 items)...");

        // FIX: We use modulo (%) to keep hall numbers between 1 and 5000.
        // This prevents the "Invalid hall number" exception from HallUtils.
        List<Hall> massiveList = IntStream.range(0, 1_000_000)
                .mapToObj(i -> new Hall((i % 5000) + 1, 100))
                .toList();

        long startSeq = System.nanoTime();
        long countSeq = massiveList.stream()
                .filter(h -> h.hallNumber() % 2 == 0)
                .count();
        long durationSeq = (System.nanoTime() - startSeq) / 1_000_000;

        System.out.println("Sequential stream processed " + countSeq + " items in " + durationSeq + " ms");

        long startPar = System.nanoTime();
        long countPar = massiveList.parallelStream()
                .filter(h -> h.hallNumber() % 2 == 0)
                .count();
        long durationPar = (System.nanoTime() - startPar) / 1_000_000;

        System.out.println("Parallel stream processed " + countPar + " items in " + durationPar + " ms");

        if (durationPar < durationSeq) {
            System.out.println("Result: Parallel stream was faster.");
        } else {
            System.out.println("Result: Sequential stream was faster.");
        }
    }
}