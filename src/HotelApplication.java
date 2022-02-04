import java.text.ParseException;

public class HotelApplication {
    public static void main(String[] args) throws ParseException {
        System.out.println("Welcome to hotel");
        MainMenu mainMenu = new MainMenu();
        mainMenu.showOptions();
    }
}
