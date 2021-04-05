package com.kadry.petclinic.services.springdatajpa;

import com.kadry.petclinic.model.Pet;
import com.kadry.petclinic.repositories.PetRepository;
import com.kadry.petclinic.services.PetService;

import javax.print.attribute.HashAttributeSet;
import java.util.HashSet;
import java.util.Set;

public class PetSDjpaService implements PetService {

    private final PetRepository petRepository;

    public PetSDjpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<Pet>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
