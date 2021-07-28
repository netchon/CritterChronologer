package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.commons.RecordNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getOwnerByPet(long petId) {
        Pet pet =  petRepository.findById(petId)
                .orElseThrow(RecordNotFoundException::new);

        Customer customer = customerRepository.findByPetsIs(pet).
                orElseThrow(RecordNotFoundException::new);

        return customer;
    }
}
