package ua.cinema.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Screening {
    private Movie movie;
    private Hall hall;
    private LocalDateTime screeningDateTime;

    public Screening() {}

    public Screening(Movie movie, Hall hall, LocalDateTime screeningDateTime) {
        this.movie = movie;
        this.hall = hall;
        this.screeningDateTime = screeningDateTime;
    }

    public static Screening createScreening(Movie movie, Hall hall, LocalDateTime screeningDateTime) {
        Screening s = new Screening(movie, hall, screeningDateTime);
        if (s.movie == null || s.hall == null || s.screeningDateTime == null) return null;
        return s;
    }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public Hall getHall() { return hall; }
    public void setHall(Hall hall) { this.hall = hall; }

    public LocalDateTime getScreeningDateTime() { return screeningDateTime; }
    public void setScreeningDateTime(LocalDateTime screeningDateTime) { this.screeningDateTime = screeningDateTime; }

    @Override
    public String toString() {
        return "Screening{movie=" + (movie != null ? movie.getTitle() : "null") +
                ", hall=" + (hall != null ? hall.getHallNumber() : "null") +
                ", datetime=" + screeningDateTime + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Screening)) return false;
        Screening screening = (Screening) o;
        return Objects.equals(movie, screening.movie) &&
                Objects.equals(hall, screening.hall) &&
                Objects.equals(screeningDateTime, screening.screeningDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, hall, screeningDateTime);
    }
}
