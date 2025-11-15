package ua.cinema;

import ua.cinema.exception.InvalidDataException;
import ua.cinema.model.Actor;
import ua.cinema.model.Hall;
import ua.cinema.service.CinemaDataReader;

import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.info("--- Application Started ---");

        CinemaDataReader reader = new CinemaDataReader();

        LOGGER.info("Attempting to read actors from 'actors.csv'...");
        try {
            List<Actor> actors = reader.readActorsFromFile("actors.csv");
            System.out.println("\n--- SUCCESSFULLY LOADED ACTORS ---");
            actors.forEach(actor -> System.out.println("  " + actor));
            System.out.println("----------------------------------\n");

        } catch (InvalidDataException e) {
            LOGGER.severe("CRITICAL FAILURE reading actors: " + e.getMessage());
            System.out.println("Error processing actors file: " + e.getMessage());
        }

        LOGGER.info("Attempting to read halls from 'halls.csv'...");
        try {
            List<Hall> halls = reader.readHallsFromFile("halls.csv");
            System.out.println("\n--- SUCCESSFULLY LOADED HALLS ---");
            halls.forEach(hall -> System.out.println("  " + hall));
            System.out.println("---------------------------------\n");

        } catch (InvalidDataException e) {
            LOGGER.severe("CRITICAL FAILURE reading halls: " + e.getMessage());
            System.out.println("Error processing halls file: " + e.getMessage());
        }

        LOGGER.info("Attempting to read non-existent file 'ghost.csv'...");
        try {
            reader.readActorsFromFile("ghost.csv");
        } catch (InvalidDataException e) {
            LOGGER.warning("Successfully caught expected error: " + e.getMessage());
            System.out.println("\nSuccessfully caught expected error for 'ghost.csv'");
        }

        LOGGER.info("--- Main Demonstration Finished ---");
    }
}