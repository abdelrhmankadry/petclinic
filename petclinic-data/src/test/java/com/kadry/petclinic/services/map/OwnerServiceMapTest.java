package com.kadry.petclinic.services.map;

import com.kadry.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    final Long id = 1L;
    final String lastName = "Smith";
    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        Owner owner = new Owner();
        owner.setId(id);
        owner.setLastName(lastName);
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(id, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(id);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(id));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        //given
        Owner owner2 = new Owner();
        long id = 2L;
        owner2.setId(id);
        //when
        Owner savedOwner = ownerServiceMap.save(owner2);
        //then
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner3 = new Owner();
        Owner savedOwner = ownerServiceMap.save(owner3);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(id);
        assertEquals(id, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner retrieveOwner = ownerServiceMap.findByLastName(lastName);
        assertEquals(id, retrieveOwner.getId());
    }
}