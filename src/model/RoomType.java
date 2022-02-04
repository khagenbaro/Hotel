package model;

//RoomType enumeration declaration
public enum RoomType {
    SINGLE(1),
    DOUBLE(2);

    int roomType;

    RoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getRoomType() {
        return this.roomType;
    }
}
