package ua.cinema.repository;

import ua.cinema.model.Hall;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class HallRepository {
    private static final Logger LOGGER = Logger.getLogger(HallRepository.class.getName());

    private final GenericRepository<Hall> repo;

    public HallRepository() {
        this.repo = new GenericRepository<>(Hall::hallNumber);
    }

    public void add(Hall hall) {
        repo.add(hall);
    }

    public Optional<Hall> findByIdentity(int id) {
        return repo.findByIdentity(id);
    }

    public List<Hall> getAllSortedByNumber() {
        List<Hall> list = new ArrayList<>(repo.getAll());
        Collections.sort(list);
        LOGGER.info("Sorted halls by Number (Default)");
        return list;
    }

    public List<Hall> getAllSortedByCapacity() {
        List<Hall> list = new ArrayList<>(repo.getAll());

        list.sort(Comparator.comparingInt(Hall::capacity));

        LOGGER.info("Sorted halls by Capacity");
        return list;
    }
}