import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;


public class MainMenu {
    HotelResource resource = new HotelResource();

    void showOptions() throws ParseException {
        System.out.println("Main Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1. Find and Reserve a room");
        System.out.println("2. See my Reservations");
        System.out.println("3. Create an account");//done
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("-------------------------------------------");
        System.out.println("Please select a number for menu option");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                findAndReserveARoom();
                showOptions();
                break;
            case 2:
                System.out.println('1');
                showOptions();
                break;
            case 3:
                handleCreateAccount();
                showOptions();
                break;
            case 4:
                handleAdminOption();
                showOptions();
                break;
            case 5:
                break;
            default:
                System.out.println("Please provide a valid Input\n");
                showOptions();
        }
    }


    void handleAdminOption() {
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.showOptions();
    }

    void handleCreateAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your First Name");
        String firstName = sc.next();
        System.out.println("Enter Your last Name");
        String lastName = sc.next();
        System.out.println("Enter Your email format:sample@domain.com");
        String email = sc.next();
        System.out.println("Account Created Successfully");
        HotelResource resource = new HotelResource();
        resource.createACustomer(firstName, lastName, email);
    }

    void findAndReserveARoom() throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
        String inDate = sc.next();
        Date checkInDate = null;
        try {
            checkInDate = simpleDateFormat.parse(inDate);
        } catch (ParseException e) {
            //todo check this
            System.out.println("Please enter a valid input");
            findAndReserveARoom();
        }

        System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2020");
        String outDate = sc.next();

        Date checkOutDate = null;
        try {
            checkOutDate = simpleDateFormat.parse(outDate);
        } catch (ParseException e) {
            System.out.println("Please enter a valid input ");
            findAndReserveARoom();
        }
        if (checkInDate != null & checkOutDate != null) {
            Collection<IRoom> availableRooms = resource.findARoom(checkInDate, checkOutDate);
            if (!availableRooms.isEmpty()) {
                System.out.println("Available rooms are: \n");
                System.out.println(availableRooms);

                System.out.println("Do you want to book a room? y/n");
                String bookRoom = sc.next();
                if (bookRoom.equals("y")) {
                    System.out.println("Do you have an account with us? y/n");
                    String haveAccount = sc.next();
                    if (haveAccount.equals("y")) {
                        System.out.println("Enter email address");
                        String email = sc.next();
                        if (resource.getCustomer(email) != null) {
                            System.out.println("Which room would you like to book? Enter room Number");
                            String roomNo = sc.next();
                            IRoom room = resource.getRoom(roomNo);
                            Reservation reservation = resource.bookARoom(email, room, checkInDate, checkOutDate);
                            System.out.println(reservation);
                        }
                    } else System.out.println("You do not have an account with us . First Create an account with us!");
                    handleCreateAccount();

                } else if (bookRoom.equals("n")) {
                    showOptions();
                }
            } else {
                System.out.println("Currently no rooms available");
                showOptions();
            }
        }
    }

}