package ua.cinema.model;

import ua.cinema.util.HallUtils;

import java.util.Objects;

public class Hall {
    private int hallNumber;
    private int capacity;

    public Hall() {}

    public Hall(int hallNumber, int capacity) {
        this.hallNumber = HallUtils.isValidHallNumber(hallNumber) ? hallNumber : 0;
        this.capacity = HallUtils.isValidCapacity(capacity) ? capacity : 0;
    }

    public static Hall createHall(int hallNumber, int capacity) {
        Hall h = new Hall(hallNumber, capacity);
        if (h.hallNumber == 0 || h.capacity == 0) return null;
        return h;
    }

    public int getHallNumber() { return hallNumber; }
    public void setHallNumber(int hallNumber) {
        if (HallUtils.isValidHallNumber(hallNumber)) this.hallNumber = hallNumber;
    }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) {
        if (HallUtils.isValidCapacity(capacity)) this.capacity = capacity;
    }

    @Override
    public String toString() {
        return HallUtils.formatHallLabel(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;
        Hall hall = (Hall) o;
        return hallNumber == hall.hallNumber &&
                capacity == hall.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hallNumber, capacity);
    }
}
