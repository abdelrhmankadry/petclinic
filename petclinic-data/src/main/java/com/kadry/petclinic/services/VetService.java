package com.kadry.petclinic.services;

import com.kadry.petclinic.model.Pet;
import com.kadry.petclinic.model.Vet;

import java.util.Set;

interface VetService {

    Vet findById(Long id);

    Vet save(Vet owner);

    Set<Vet> findAll();
}
