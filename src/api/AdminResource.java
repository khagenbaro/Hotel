package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.List;

public class AdminResource {
    public Customer getCustomer(String email) {
        return CustomerService.getCustomerService().getCustomer(email);
    }

    public void addRoom(List<IRoom> roomList) {
        for (IRoom room : roomList) {
            ReservationService.getReservationService().addRoom(room);
        }
        System.out.println(ReservationService.roomList);
    }

    public List<IRoom> getAllRooms() {
        return ReservationService.roomList;
    }

    public void displayAllReservations() {

    }

    public void getAllCustomers() {
        System.out.println(CustomerService.customerList);
    }
}