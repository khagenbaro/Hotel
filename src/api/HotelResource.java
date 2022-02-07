package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

import static service.ReservationService.reservationList;

public class HotelResource {

    CustomerService customerService = CustomerService.getCustomerService();
    static ReservationService reservationService = ReservationService.getReservationService();

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String firstName, String lastName, String email) {
        CustomerService.getCustomerService().addCustomer(firstName, lastName, email);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String email) {
        return reservationService.getCustomerReservation(getCustomer(email));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);

    }

    public List<IRoom> checkForAlternativeRooms(Date checkInDate, Date checkOutDate) {
        return reservationService.checkForAlternativeRooms(checkInDate, checkOutDate);
    }

    public boolean checkRoomNumber(String roomNumber) {
        return ReservationService.checkRoomNumberExistance(roomNumber);
    }
}

