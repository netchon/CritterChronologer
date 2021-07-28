package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.commons.RecordNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetServiceImpl(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }


    @Transactional
    @Override
    public Pet savePet(Pet pet, long ownerId) {
        Customer customer = customerRepository.findById(ownerId)
                .orElseThrow(RecordNotFoundException::new);

        pet.setCustomer(customer);

        Pet petSaved = petRepository.save(pet);

        if(customer.getPets() != null)
            customer.getPets().add(petSaved);
        else{
            List<Pet> pets = new ArrayList<>();
            pets.add(petSaved);
            customer.setPets(pets);
        }

        customerRepository.save(customer);

        return petSaved;
    }

    @Override
    public Pet getPet(long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        return pet;
    }

    @Override
    public List<Pet> getPetsByOwner(long ownerId) {
        Customer customer = customerRepository.findById(ownerId)
                .orElseThrow(RecordNotFoundException::new);
        return customer.getPets();
    }

    @Override
    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> findPetByIdIn(List<Long> petIds) {
        return petRepository.findPetByIdIn(petIds);
    }
}
