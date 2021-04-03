package com.kadry.petclinic.services;

import com.kadry.petclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);
    
    Pet save(Pet owner);

    Set<Pet> findAll();
}
