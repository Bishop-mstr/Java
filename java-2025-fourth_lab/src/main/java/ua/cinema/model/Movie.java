package ua.cinema.model;

import ua.cinema.util.MovieUtils;

import java.time.LocalDate;
import java.util.Objects;

public class Movie {
    private String title;
    private Genre genre;
    private int durationMinutes;
    private LocalDate releaseDate;

    public Movie() {
    }

    public Movie(String title, Genre genre, int durationMinutes, LocalDate releaseDate) {
        try {
            this.title = MovieUtils.formatTitle(title);
        } catch (IllegalArgumentException e) {
            this.title = null;
        }

        this.genre = genre;

        this.durationMinutes = MovieUtils.isValidDuration(durationMinutes) ? durationMinutes : 0;
        this.releaseDate = releaseDate;
    }

    public static Movie createMovie(String title, Genre genre, int durationMinutes, LocalDate releaseDate) {
        Movie m = new Movie(title, genre, durationMinutes, releaseDate);
        if (m.title == null || m.genre == null || m.durationMinutes <= 0 || m.releaseDate == null) return null;
        return m;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        try {
            this.title = MovieUtils.formatTitle(title);
        } catch (IllegalArgumentException e) {
            this.title = null;
        }
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int minutes) {
        if (MovieUtils.isValidDuration(minutes)) this.durationMinutes = minutes;
        else this.durationMinutes = 0;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{title='" + title + "', genre='" + genre + "', duration=" +
                (durationMinutes > 0 ? MovieUtils.formatDuration(durationMinutes) : "N/A") +
                ", releaseDate=" + releaseDate + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return durationMinutes == movie.durationMinutes &&
                Objects.equals(title, movie.title) &&
                genre == movie.genre &&
                Objects.equals(releaseDate, movie.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, durationMinutes, releaseDate);
    }
}
