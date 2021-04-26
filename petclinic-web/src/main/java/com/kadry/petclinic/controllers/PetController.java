package com.kadry.petclinic.controllers;

import com.kadry.petclinic.model.Owner;
import com.kadry.petclinic.model.Pet;
import com.kadry.petclinic.model.PetType;
import com.kadry.petclinic.services.OwnerService;
import com.kadry.petclinic.services.PetService;
import com.kadry.petclinic.services.PetTypeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    public static final String VIEW_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder){dataBinder.setDisallowedFields("id");}

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") long ownerId){
        return ownerService.findById(ownerId);
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model){
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, Pet pet, BindingResult result, ModelMap model){

       if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
           result.rejectValue("name", "duplicate", "already exits");
       }
       owner.getPets().add(pet);
       if(result.hasErrors()){
           model.put("pet", pet);
           return VIEW_PETS_CREATE_OR_UPDATE_FORM;
       }else{
           petService.save(pet);
           return "redirect:/owners/" + owner.getId();
       }
    }

    @GetMapping("/pets/{petId}//edit")
    public String initUpdateForm(@PathVariable long petId,Model model){
        model.addAttribute("pet", petService.findById(petId));
        return  VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Owner owner, Pet pet, BindingResult result, Model model){
        if(result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEW_PETS_CREATE_OR_UPDATE_FORM;
        } else{
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/"+owner.getId();
        }
    }



}
