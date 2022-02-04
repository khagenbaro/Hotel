package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    static ReservationService reservationService = new ReservationService();
    public static List<IRoom> rooms = new ArrayList<>();
    public static List<Reservation> reservationList = new ArrayList<>();
    public static Map<IRoom, Reservation> roomReservationMap = new HashMap<>();

    public ReservationService() {

    }

    public static ReservationService getReservationService() {
        return reservationService;
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String roomNumber) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }


    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(reservation);
        roomReservationMap.put(room, reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkIn, Date checkOut) {
        List<IRoom> roomList = ReservationService.rooms;
        List<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : roomList) {
            if (roomReservationMap.containsKey(room)) {
                Reservation reservation = roomReservationMap.get(room);
                if (reservation.getCheckOutDate().before(checkIn)) {
                    availableRooms.add(room);
                }
            } else {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> customerReservationList = new ArrayList<>();
        for( Reservation reservation : reservationList){
            if(reservation.getCustomer().equals(customer)){
                customerReservationList.add(reservation);
            }
        }
        return customerReservationList;
    }

    public void printAllReservation() {
        System.out.println(reservationList);
    }
}
