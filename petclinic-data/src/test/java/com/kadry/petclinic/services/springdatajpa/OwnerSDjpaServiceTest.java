package com.kadry.petclinic.services.springdatajpa;

import com.kadry.petclinic.model.Owner;
import com.kadry.petclinic.repositories.OwnerRepository;
import com.kadry.petclinic.repositories.PetRepository;
import com.kadry.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDjpaServiceTest {

    public static final String SMITH = "smith";
    public static final long ID = 1L;
    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDjpaService service;

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner();

        owner.setId(ID);


    }

    @Test
    void findAll() {
        //given
        Set<Owner> owners = new HashSet<>();
        owners.add(owner);
        when(ownerRepository.findAll()).thenReturn(owners);

        //when
        Set<Owner> retrievedOwner = service.findAll();

        //then
        assertEquals(1, retrievedOwner.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        //given
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

        //when
        Owner retrievedOwner = service.findById(ID);

        //then
        assertEquals(ID, retrievedOwner.getId());
        verify(ownerRepository).findById(any());
    }

    @Test
    void save() {
        //given
        when(ownerRepository.save(any())).thenReturn(owner);

        //when
        Owner returnedOwner = service.save(owner);

        //then
        assertEquals(ID, returnedOwner.getId());

    }

    @Test
    void delete() {
        //when
        service.delete(owner);

        //then
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        //when
        service.deleteById(ID);

        //then
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {

        //given
        Owner returnOwner = new Owner();
        returnOwner.setId(ID);
        returnOwner.setLastName(SMITH);
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        //when
        Owner smith = service.findByLastName(SMITH);

        //then
        assertEquals(SMITH, smith.getLastName());
    }
}