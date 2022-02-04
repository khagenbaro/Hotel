package model;

import java.util.Objects;

/** Declaring Room Method and declaring necessary variables */
public class Room implements IRoom {
    String roomNumber;
    Double price;
    RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;

    }

    @Override
    public String getRoomNumber() {

        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {

        return price;
    }

    @Override
    public RoomType getRoomType() {

        return roomType;
    }

    @Override
    public boolean isFree() {

        return this.price != null && this.price.equals(0.0);
    }

    @Override
    public String toString() {
        return "Room Details:" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Room)) {
            return false;
        }

        Room room = (Room) obj;
        return Objects.equals(this.roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
