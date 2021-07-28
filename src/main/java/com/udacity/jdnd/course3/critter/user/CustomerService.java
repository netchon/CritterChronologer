package com.udacity.jdnd.course3.critter.user;


import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomer();
    Customer getOwnerByPet(long petId);
}
