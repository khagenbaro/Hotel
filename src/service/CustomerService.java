package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    private static CustomerService customerService = new CustomerService();
    public static List<Customer> customerList = new ArrayList<>();

   private  CustomerService(){

    }
    public static CustomerService getCustomerService() {
        return customerService;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        customerList.add(customer);
        System.out.println(customerList);
    }
    
    public Customer getCustomer(String customerEmail) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return customerList;
    }
}
