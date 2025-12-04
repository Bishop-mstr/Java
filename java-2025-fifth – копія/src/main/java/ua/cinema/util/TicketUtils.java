package ua.cinema.util;

import ua.cinema.model.Ticket;
import ua.cinema.model.Hall;

public class TicketUtils {

    public static String formatTicket(Ticket ticket) {
        if (ticket == null) return "Ticket{null}";
        return "Movie: " + ticket.getScreening().getMovie().getTitle() +
                " | Hall: " + ticket.getScreening().getHall().hallNumber() +
                " | Seat: " + ticket.getSeatNumber() +
                " | Price: " + String.format("%.2f", ticket.getPrice());
    }

    public static boolean isValidSeat(Ticket ticket) {
        if (ticket == null || ticket.getScreening() == null || ticket.getScreening().getHall() == null)
            return false;
        Hall hall = ticket.getScreening().getHall();
        int seat = ticket.getSeatNumber();
        return ValidationHelper.isNumberBetween(seat, 1, hall.capacity());
    }

    public static boolean isValidPrice(double price) {
        return price >= 0.0;
    }
}