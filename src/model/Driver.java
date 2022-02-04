package model;

//Driver Method to just check email validation is correct or not
public class Driver {
    public static void main(String[] args) {
        Customer customer = new Customer("first", "second", "email@.com");
        System.out.println(customer);
    }
}
