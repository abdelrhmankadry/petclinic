package com.kadry.petclinic.controllers;

import com.kadry.petclinic.model.Pet;
import com.kadry.petclinic.model.Visit;
import com.kadry.petclinic.services.PetService;
import com.kadry.petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    public static final String CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    MockMvc mockMvc;
    @Mock
    VisitService visitService;
    @Mock
    PetService petService;
    @InjectMocks
    VisitController visitController;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController)
                .build();
        Pet pet = new Pet();
        pet.setId(1L);
        when(petService.findById(anyLong())).thenReturn(pet);
    }

    @Test
    void initNewVisitFormTest() throws Exception {

        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitFormTest() throws Exception {
        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    void initUpdateVisitFormTest() throws Exception {
        mockVisitServiceFindByID();
        mockMvc.perform(get("/owners/1/pets/1/visits/1/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("visit"))
                .andExpect(view().name(CREATE_OR_UPDATE_VISIT_FORM));
    }


    private void mockVisitServiceFindByID() {
        Visit visit = new Visit();
        visit.setId(1L);
        Pet pet = new Pet();
        pet.setId(1L);
        visit.setPet(pet);
        when(visitService.findById(anyLong())).thenReturn(visit);
    }
}