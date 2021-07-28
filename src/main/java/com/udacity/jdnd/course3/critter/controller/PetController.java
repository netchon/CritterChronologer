package com.udacity.jdnd.course3.critter.controller;


import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping()
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDtoToEntity(petDTO);
        Pet petSaved = petService.savePet(pet,petDTO.getOwnerId());
        return convertPetEntityToDto(petSaved);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return convertPetEntityToDto(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> petDTOS = petService.getPets().stream()
                .map(p -> convertPetEntityToDto(p))
                .collect(Collectors.toList());
      return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petDTOS = petService.getPetsByOwner(ownerId).stream()
                .map(p -> convertPetEntityToDto(p))
                .collect(Collectors.toList());

      return petDTOS;
    }


    private PetDTO convertPetEntityToDto(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    private Pet convertPetDtoToEntity(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO,pet);
        return pet;
    }
}
