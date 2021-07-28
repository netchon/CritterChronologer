package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

public interface PetService {
    Pet savePet(Pet pet, long ownerId);
    Pet getPet(long id);
    List<Pet> getPetsByOwner(long ownerId);
    List<Pet> getPets();
    List<Pet> findPetByIdIn(List<Long> petIds);
}
