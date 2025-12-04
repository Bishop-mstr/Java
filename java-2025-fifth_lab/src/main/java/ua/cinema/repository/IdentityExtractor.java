package ua.cinema.repository;

@FunctionalInterface
public interface IdentityExtractor<T> {
    Object getIdentity(T item);
}