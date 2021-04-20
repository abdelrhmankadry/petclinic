package com.kadry.petclinic.controllers;

import com.kadry.petclinic.model.Owner;
import com.kadry.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.lang.Long.parseLong;

@RequestMapping("/owners")
@Controller
public class OwnersController {

    public static final String VIEWS_OWNERS_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnersController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/index", "/index.html"})
    public String index(Model model){

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping({"/find", "/find.html",})
    public String findOwners(Model model){
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }
    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        if(results.isEmpty()){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1){
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else{
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return  mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner", new Owner());
        return VIEWS_OWNERS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm( Owner owner, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_OWNERS_CREATE_OR_UPDATE_FORM;
        } else{
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable String ownerId,  Model model){
        Owner owner = ownerService.findById(parseLong(ownerId));
        model.addAttribute("owner", owner);
        return VIEWS_OWNERS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(Owner owner, BindingResult result, @PathVariable String ownerId){
        if(result.hasErrors()){
            return VIEWS_OWNERS_CREATE_OR_UPDATE_FORM;
        } else{
            owner.setId(parseLong(ownerId));
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

}
