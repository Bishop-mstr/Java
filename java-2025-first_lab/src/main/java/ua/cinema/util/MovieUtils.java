package ua.cinema.util;

import ua.cinema.model.Movie;

public class MovieUtils {

    public static String formatTitle(String title) {
        if (!ValidationHelper.isStringLengthBetween(title, 1, 200)) {
            throw new IllegalArgumentException("Invalid movie title length");
        }
        return title.trim();
    }

    public static String formatGenre(String genre) {
        if (!ValidationHelper.isStringLengthBetween(genre, 1, 100)) {
            throw new IllegalArgumentException("Invalid genre length");
        }
        String trimmed = genre.trim();
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
    }

    public static String formatDuration(int minutes) {
        if (!isValidDuration(minutes)) {
            throw new IllegalArgumentException("Invalid movie duration");
        }
        int hours = minutes / 60;
        int mins = minutes % 60;
        return (hours > 0 ? (hours + "h ") : "") + mins + "m";
    }

    public static boolean isValidDuration(int minutes) {
        return ValidationHelper.isNumberBetween(minutes, 1, 600); // 1..600 minutes
    }

    public static String formatReleaseYear(Movie movie) {
        if (movie == null || movie.getReleaseDate() == null) return "";
        return String.valueOf(movie.getReleaseDate().getYear());
    }
}
