package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    public Customer getCustomer(String email) {
        return CustomerService.getCustomerService().getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            ReservationService.getReservationService().addRoom(room);
        }
        System.out.println(ReservationService.rooms);
    }

    public Collection<IRoom> getAllRooms() {
        return ReservationService.rooms;
    }

    public void displayAllReservations() {


    }

    public void getAllCustomers() {
        System.out.println(CustomerService.customerList);
    }
}

