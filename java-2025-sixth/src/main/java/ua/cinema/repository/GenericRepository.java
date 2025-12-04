package ua.cinema.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class GenericRepository<T> {

    private static final Logger LOGGER = Logger.getLogger(GenericRepository.class.getName());

    private final Map<Object, T> items = new HashMap<>();
    private final IdentityExtractor<T> extractor;

    public GenericRepository(IdentityExtractor<T> extractor) {
        if (extractor == null) {
            throw new IllegalArgumentException("IdentityExtractor cannot be null");
        }
        this.extractor = extractor;
    }

    public void add(T item) {
        if (item == null) {
            LOGGER.warning("Attempted to add a null item. Skipping.");
            return;
        }

        Object id = extractor.getIdentity(item);
        if (items.containsKey(id)) {
            LOGGER.warning("Item with this identity already exists. Skipping add. ID: " + id);
        } else {
            items.put(id, item);
            LOGGER.info("Added new item with ID: " + id);
        }
    }

    public void delete(T item) {
        if (item == null) {
            LOGGER.warning("Attempted to delete a null item. Skipping.");
            return;
        }

        Object id = extractor.getIdentity(item);
        if (items.remove(id) != null) {
            LOGGER.info("Removed item with ID: " + id);
        } else {
            LOGGER.warning("Attempted to delete item that does not exist. ID: " + id);
        }
    }

    public Optional<T> findByIdentity(Object id) {
        if (id == null) {
            LOGGER.warning("Attempted to find item with null ID. Returning empty.");
            return Optional.empty();
        }

        T item = items.get(id);
        if (item == null) {
            LOGGER.info("No item found with ID: " + id);
            return Optional.empty();
        } else {
            LOGGER.info("Found item with ID: " + id);
            return Optional.of(item);
        }
    }

    public Collection<T> getAll() {
        LOGGER.info("Retrieving all " + items.size() + " items.");
        return new ArrayList<>(items.values());
    }
}