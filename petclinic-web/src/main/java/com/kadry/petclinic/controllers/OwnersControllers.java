package com.kadry.petclinic.controllers;

import com.kadry.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnersControllers {

    private final OwnerService ownerService;

    public OwnersControllers(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/","/index", "/index.html"})
    public String index(Model model){

        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @RequestMapping({"/find", "/find.html",})

    public String find(Model model){

        return "notimplemented";
    }
}
