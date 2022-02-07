import api.HotelResource;
import model.IRoom;
import model.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
        System.out.println("Please select a number from menu option");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 1:
                findAndReserveARoom();
                showOptions();
                break;
            case 2:
                seeMyReservations();
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
//            case 6:
//                invalidInput();
//                break;
            default:
                System.out.println("Please provide a valid Input\n");
                showOptions();

        }

    }

    private void seeMyReservations()  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your email address(format: name@domain.com)");
        String email = sc.nextLine();
        List<Reservation> myRservationList = (List<Reservation>) resource.getCustomerReservations(email);
        if (myRservationList.isEmpty()) {
            System.out.println("No reservation found for : " + email);
        } else for (Reservation reservation : myRservationList) {
            System.out.println(reservation);

        }
    }


    void handleAdminOption() {
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.showOptions();
    }

    void handleCreateAccount() throws ParseException {
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

    void findAndReserveARoom()  throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
        String inDate = sc.next();
        Date checkInDate = null;
        try {
            checkInDate = simpleDateFormat.parse(inDate);
        } catch (ParseException e) {
            System.out.println("Please enter a valid check in date");
            findAndReserveARoom();
        }

        System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2020");
        String outDate = sc.next();
        Date checkOutDate;
        try {
            checkOutDate = simpleDateFormat.parse(outDate);
        } catch (ParseException e) {
            //todo check this
            System.out.println("Please enter a valid check out date");
            findAndReserveARoom();

        }

        checkOutDate = simpleDateFormat.parse(outDate);
        if (checkInDate != null & checkOutDate != null) {
            Collection<IRoom> availableRooms = resource.findARoom(checkInDate, checkOutDate);
            if (!availableRooms.isEmpty()) {
                System.out.println("Available rooms are: \n");
                System.out.println(availableRooms);

                System.out.println("Do you want to book a room? y/n");
                String bookRoom = sc.next();
                if (bookRoom.equalsIgnoreCase("y")) {
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
                            System.out.println("Your reservation has been done successfully ");
                            System.out.println(reservation);
                            showOptions();
                        }
                    } else System.out.println("You do not have an account with us . First Create an account with us!");
                    handleCreateAccount();

                } else if (bookRoom.equalsIgnoreCase("n")) {
                    showOptions();
                }
            } else {

                List<IRoom> alternativeRoomList = resource.checkForAlternativeRooms(checkInDate, checkOutDate);
                if (alternativeRoomList.isEmpty()) {
                    System.out.println("No rooms available for bookings");
                    showOptions();
                } else {
                    System.out.println("These are the some recommended rooms you can book");
                    for (IRoom room : alternativeRoomList) {
                        System.out.println(room);
                    }
                }
                System.out.println("Currently no rooms available");
                showOptions();
            }
        }
    }


}
