package model;

import java.util.regex.Pattern;

public class Customer {
    //regular expression for email matching
    String emailRegex = "^(.+)@(.+).(.+)$";
    Pattern pattern = Pattern.compile(emailRegex);
    //Declaring Necessary name variables
    private final String firstName;
    private final  String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        this.isValidEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    //Checking email is valid or not!!!
    private void isValidEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    public String getEmail() {
        return this.email;
    }
    public  String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }

    @Override
    public String toString() {
        return "Customer Details: " +
                "firstName='" + this.firstName +
                ", lastName='" + this.lastName +
                ", email='" + this.email;
    }

}

