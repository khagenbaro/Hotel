package model;

//Declaring freeRoom method which extends class Room
public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);

    }

    @Override
    public String toString() {

        return "FreeRoom=>" + super.toString();
    }
}
