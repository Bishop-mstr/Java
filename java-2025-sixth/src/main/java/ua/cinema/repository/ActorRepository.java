package ua.cinema.repository;

import ua.cinema.model.Actor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ActorRepository {
    private static final Logger LOGGER = Logger.getLogger(ActorRepository.class.getName());

    private final GenericRepository<Actor> repo;

    public ActorRepository() {
        this.repo = new GenericRepository<>(actor -> actor.firstName() + actor.lastName());
    }

    public void add(Actor actor) {
        repo.add(actor);
    }

    public Optional<Actor> findByIdentity(String id) {
        return repo.findByIdentity(id);
    }

    public List<Actor> getAll() {
        return new ArrayList<>(repo.getAll());
    }

    public List<Actor> findActorsBornAfter(int year) {
        LOGGER.info("Searching for actors born after year: " + year);
        return repo.getAll().stream()
                .filter(actor -> actor.birthYear() > year)
                .collect(Collectors.toList());
    }

    public List<String> getActorLastNames() {
        LOGGER.info("Extracting list of actor last names");
        return repo.getAll().stream()
                .map(Actor::lastName)
                .collect(Collectors.toList());
    }

    public List<Actor> findByNameContains(String part) {
        LOGGER.info("Searching for actors with name containing: " + part);
        return repo.getAll().stream()
                .filter(actor -> actor.firstName().contains(part) || actor.lastName().contains(part))
                .collect(Collectors.toList());
    }

    public List<Actor> getAllSortedByDefault() {
        List<Actor> list = new ArrayList<>(repo.getAll());
        Collections.sort(list);
        return list;
    }

    public List<Actor> getAllSortedByAge(boolean ascending) {
        List<Actor> list = new ArrayList<>(repo.getAll());
        Comparator<Actor> byYear = Comparator.comparingInt(Actor::birthYear);
        if (!ascending) byYear = byYear.reversed();
        list.sort(byYear);
        return list;
    }
}