import api.AdminResource;
import api.HotelResource;
import model.FreeRoom;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static service.ReservationService.getReservationService;

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
                printAllReservation();
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

    private void printAllReservation() {
        getReservationService().printAllReservation();
    }

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
        IRoom room;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter room number");
        String roomNumber = sc.nextLine();
        // checking for room number already exists or not
        if(HotelResource.checkRoomNumber(roomNumber)){
            System.out.println("This room number already exists ");
        }

        System.out.println("Enter room price");
        Double price = sc.nextDouble();
        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
        String roomTypeInput = sc.next();
        RoomType roomType = null;
        if(roomTypeInput.equals("1")){
            roomType = RoomType.SINGLE;
        }
        else if(roomTypeInput.equals("2")){
            roomType = RoomType.DOUBLE;
        }
        if(price==0.0){
            room = new FreeRoom(roomNumber,roomType);
        }
        else
        {
            room = new Room(roomNumber,price,roomType);
        }
        System.out.println("Room added Successfully !");
        roomList.add(room);
        AdminResource resource = new AdminResource();
        resource.addRoom(roomList);
        System.out.println("Do you want to add another room to the list ?"+"Y/N");
        String doRoomAdd =  sc.next();
        if(doRoomAdd.equalsIgnoreCase("y")){
            addRoom();
        }
        else if(doRoomAdd.equalsIgnoreCase("n"))
        {
            showOptions();
        }
        else
        {
            System.out.println("please enter a valid input ");
            System.out.println("Going back to admin menu");
            showOptions();
        }


    }

}
