package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerService {
    private static CustomerService customerService = new CustomerService();
    public static List<Customer> customerList = new ArrayList<>();

    public static CustomerService getCustomerService() {
        return customerService;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        customerList.add(customer);
        System.out.println(customerList);
    }

    /**
     * takes a email Id of a customer and returns Customer object
     * @param customerEmail
     * @return
     */
    public Customer getCustomer(String customerEmail) {
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
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