import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public void showOptions() {
        System.out.println("Admin Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");//todo not done
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("-------------------------------------------");
        System.out.println("Please select a number for Admin option");

        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        switch (option) {
            case 1:
                viewAllCustomers();
                showOptions();
                break;
            case 2:
                viewAllRooms();
                showOptions();
                break;
            case 3:
                //printAllReservation();
                showOptions();
                break;
            case 4:
                addRoom();
                showOptions();
                break;
            case 5:
                break;
        }
    }

//    private void printAllReservation() {
//        System.out.println(reservationList);
//
//    }

    private void viewAllRooms() {
        AdminResource resource = new AdminResource();
        System.out.println(resource.getAllRooms());

    }

    private void viewAllCustomers() {
        AdminResource resource = new AdminResource();
        resource.getAllCustomers();
    }

    private void addRoom() {
        List<IRoom> roomList = new ArrayList<>();
        int addAnotherRoomInput;
        do {
            System.out.println("Enter room number");
            Scanner sc = new Scanner(System.in);
            String roomNumber = sc.next();
            System.out.println("Enter room price");
            Double price = sc.nextDouble();
            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            int roomType = sc.nextInt();
            System.out.println("Room Added Successfully ");

            //TODO
            IRoom room = new Room(roomNumber, price, RoomType.SINGLE);
            roomList.add(room);
            System.out.println("Do you want to add another Room '1' for Yes '2' for No");
            addAnotherRoomInput = sc.nextInt();
        }
        while(addAnotherRoomInput == 1);

        AdminResource resource = new AdminResource();
        resource.addRoom(roomList);
    }
//khagen baro
}
