package com.kadry.petclinic.services.springdatajpa;

import com.kadry.petclinic.model.Specialty;
import com.kadry.petclinic.repositories.SpecialtyRepository;
import com.kadry.petclinic.services.SpecialtyService;

import java.util.HashSet;
import java.util.Set;

public class SpecialtySDjpaService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtySDjpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
