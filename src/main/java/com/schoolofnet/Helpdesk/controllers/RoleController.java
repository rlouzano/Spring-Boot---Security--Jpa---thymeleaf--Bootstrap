package com.schoolofnet.Helpdesk.controllers;

import com.schoolofnet.Helpdesk.models.Role;
import com.schoolofnet.Helpdesk.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RolesService rolesService;

    public RoleController(RolesService rolesService){
        this.rolesService = rolesService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list",this.rolesService.findAll());
        return "roles/index";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("role", new Role());
        return "roles/create";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){   // Verifica se existe algum erros nas validações que inserimos em nossa entidade
            return "roles/create";
            //"redirect:/roles/new"
        }
        Role  roleCreated = this.rolesService.create(role);
        return "redirect:/roles";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        this.rolesService.delete(id);
        return "redirect:/roles";
    }
}
