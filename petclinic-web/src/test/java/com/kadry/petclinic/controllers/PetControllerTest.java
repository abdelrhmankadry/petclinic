package com.kadry.petclinic.controllers;

import com.kadry.petclinic.model.Owner;
import com.kadry.petclinic.model.Pet;
import com.kadry.petclinic.model.PetType;
import com.kadry.petclinic.services.OwnerService;
import com.kadry.petclinic.services.PetService;
import com.kadry.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    OwnerService ownerService;
    @Mock
    PetService petService;
    @Mock
    PetTypeService petTypeService;
    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController)
                .build();
    }

    @Test
    void initCreationFormTest() throws Exception {
        mockOwnerServiceFindById();
        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
        verifyNoInteractions(petService);
    }

    @Test
    void processCreationFormTest() throws Exception {
        mockOwnerServiceFindById();
        mockPetTypeServiceFindAll();
        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }



    @Test
    void initUpdateFormTest() throws Exception {

        Pet pet = mockPetServiceFindById();

        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pet", pet))
                .andExpect(view().name("pets/createOrUpdatePetForm"));
        verify(petService).findById(anyLong());
    }



    @Test
    void processUpdateFormTest() throws Exception {
        mockOwnerServiceFindById();
        mockPetTypeServiceFindAll();

        mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        verify(petService).save(any());
    }

    private Pet mockPetServiceFindById() {
        Pet pet = new Pet();
        pet.setId(1L);
        when(petService.findById(anyLong())).thenReturn(pet);
        return pet;
    }



    private void mockOwnerServiceFindById() {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.findById(anyLong())).thenReturn(owner);

    }

    private void mockPetTypeServiceFindAll() {
        PetType petType1 = new PetType();
        petType1.setId(1L);
        PetType petType2 = new PetType();
        petType2.setId(1L);
        HashSet<PetType> petTypes = new HashSet<>();
        petTypes.add(petType1);
        petTypes.add(petType2);
        when(petTypeService.findAll()).thenReturn(petTypes);
    }
}