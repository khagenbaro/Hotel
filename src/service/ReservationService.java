package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = new ReservationService();
    public static List<Reservation> reservationList = new ArrayList<>();
    static Map<IRoom, Reservation> roomReservationMap = new HashMap<>();
    public static List<IRoom> roomList = new ArrayList<>();

    private ReservationService() {

    }

    public static ReservationService getReservationService() {
        return reservationService;
    }

    public static ArrayList<Reservation> getReservtionList() {
        return (ArrayList<Reservation>) reservationList;
    }

    public static boolean isRoomNumberExists(String roomNumber) {
        for (IRoom room : roomList) {
            if (room.getRoomNumber().contains(roomNumber)) {
                return true;
            }
        }
        return false;

    }

    public void addRoom(IRoom room) {
        roomList.add(room);
    }

    public IRoom getARoom(String roomNumber) {
        for (IRoom room : roomList) {
            if (room.getRoomNumber().equals(roomNumber)) {
                return room;
            }
        }
        return null;
    }


    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation =Reservation.getReservation();
        reservationList.add(reservation);
        roomReservationMap.put(room, reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkIn, Date checkOut) {
        List<IRoom> roomList = ReservationService.roomList;
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
        for (Reservation reservation : reservationList) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservationList.add(reservation);
            }
        }
        return customerReservationList;
    }

    public void printAllReservation() {
        if (reservationList.isEmpty()) {
            System.out.println("No reservation found");
        } else {
            System.out.println(reservationList);
        }
    }

    public List<IRoom> checkForAlternativeRooms(Date checkInDate, Date checkOutDate) {
        return (List<IRoom>) findRooms(recomendedRoomSearch(checkInDate), recomendedRoomSearch(checkOutDate));
    }

    private Date recomendedRoomSearch(Date recomdedDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(recomdedDays);
        calendar.add(Calendar.DATE, 7);
        return calendar.getTime();
    }

    public static boolean checkRoomNumberExistance(String roomNumber) {
        for (IRoom roomNo : roomList) {
            if (roomNo.getRoomNumber().equals(roomNumber)) {
                System.out.println("This room number is already exists please enter another number");
                return true;
            }
        }
        return false;
    }

}
