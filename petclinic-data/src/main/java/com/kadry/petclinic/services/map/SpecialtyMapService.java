package com.kadry.petclinic.services.map;

import com.kadry.petclinic.model.Speciality;
import com.kadry.petclinic.services.SpecialtiesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialtyMapService extends AbstractMapService<Speciality, Long>
            implements SpecialtiesService {


    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }


    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }
}
