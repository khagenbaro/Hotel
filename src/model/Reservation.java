package model;

import java.util.Date;

/**
 * Declaring the Reservation Class
 */

public class Reservation {
    //Declaring the variables
    Customer customer;
    IRoom room;
    Date checkInDate, checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation details : " +
                this.customer.getFirstName()+
                " "+this.customer.getLastName()+
                this.customer.getEmail()+
                "Booked=" + this.room.getRoomNumber()+
                "with Price "+ this.room.getRoomPrice()+
                "Room type is " + this.room.getRoomType()+
                " his checkInDate is" + this.checkInDate +
                " and checkOutDate is " + this.checkOutDate;
    }
}
