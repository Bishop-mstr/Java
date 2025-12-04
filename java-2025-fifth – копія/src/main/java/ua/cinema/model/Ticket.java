// ========== REPLACE: ua/cinema/model/Ticket.java ==========
package ua.cinema.model;

import ua.cinema.util.TicketUtils;

import java.util.Objects;

public class Ticket {
    private Screening screening;
    private int seatNumber;
    private double price;
    private TicketStatus status;

    public Ticket() {}

    public Ticket(Screening screening, int seatNumber, double price, TicketStatus status) {
        this.screening = screening;
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
        if (!TicketUtils.isValidPrice(price) || !TicketUtils.isValidSeat(this)) {
            throw new IllegalArgumentException("Invalid ticket parameters (seat or price)");
        }
    }

    public static Ticket createTicket(Screening screening, int seatNumber, double price, TicketStatus status) {
        try {
            return new Ticket(screening, seatNumber, price, status);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public Screening getScreening() { return screening; }
    public void setScreening(Screening screening) { this.screening = screening; }

    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
        if (!TicketUtils.isValidSeat(this)) throw new IllegalArgumentException("Invalid seat number");
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (!TicketUtils.isValidPrice(price)) throw new IllegalArgumentException("Invalid price");
        this.price = price;
    }

    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }

    @Override
    public String toString() {
        return TicketUtils.formatTicket(this) + " [Status: " + status + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return seatNumber == ticket.seatNumber &&
                Double.compare(ticket.price, price) == 0 &&
                Objects.equals(screening, ticket.screening) &&
                status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(screening, seatNumber, price, status);
    }
}