package ua.cinema.service;

import ua.cinema.exception.InvalidDataException;
import ua.cinema.model.Actor;
import ua.cinema.model.Hall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CinemaDataReader {

    private static final Logger LOGGER = Logger.getLogger(CinemaDataReader.class.getName());

    public List<Actor> readActorsFromFile(String filePath) throws InvalidDataException {
        List<Actor> actors = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Actor actor = Actor.parseActorFromCsv(line);
                    actors.add(actor);
                    LOGGER.info("Successfully parsed Actor: " + actor.firstName() + " " + actor.lastName());
                } catch (InvalidDataException e) {
                    LOGGER.warning("Skipping invalid actor data: " + e.getMessage() + " [Line: " + line + "]");
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.severe("Failed to find actor file: " + filePath);
            throw new InvalidDataException("Actor file not found at " + filePath, e);
        } catch (IOException e) {
            LOGGER.severe("Error reading actor file: " + e.getMessage());
            throw new InvalidDataException("Error reading actor file: " + filePath, e);
        }

        LOGGER.info("Successfully read " + actors.size() + " actors from " + filePath);
        return actors;
    }

    public List<Hall> readHallsFromFile(String filePath) throws InvalidDataException {
        List<Hall> halls = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Hall hall = Hall.parseHallFromCsv(line);
                    halls.add(hall);
                    LOGGER.info("Successfully parsed Hall: " + hall.hallNumber());
                } catch (InvalidDataException e) {
                    LOGGER.warning("Skipping invalid hall data: " + e.getMessage() + " [Line: " + line + "]");
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.severe("Failed to find hall file: " + filePath);
            throw new InvalidDataException("Hall file not found at " + filePath, e);
        } catch (IOException e) {
            LOGGER.severe("Error reading hall file: " + e.getMessage());
            throw new InvalidDataException("Error reading hall file: " + filePath, e);
        }

        LOGGER.info("Successfully read " + halls.size() + " halls from " + filePath);
        return halls;
    }
}