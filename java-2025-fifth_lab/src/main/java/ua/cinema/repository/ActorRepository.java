package ua.cinema.repository;

import ua.cinema.model.Actor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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

    public List<Actor> getAllSortedByDefault() {
        List<Actor> list = new ArrayList<>(repo.getAll());
        Collections.sort(list);
        LOGGER.info("Sorted actors by Name (Default)");
        return list;
    }

    public List<Actor> getAllSortedByAge(boolean ascending) {
        List<Actor> list = new ArrayList<>(repo.getAll());

        Comparator<Actor> byYear = (a1, a2) -> Integer.compare(a1.birthYear(), a2.birthYear());

        if (!ascending) {
            byYear = byYear.reversed();
        }

        list.sort(byYear);
        LOGGER.info("Sorted actors by Birth Year (Ascending: " + ascending + ")");
        return list;
    }
}