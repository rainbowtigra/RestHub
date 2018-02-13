package com.kh013j.controllers;

import com.kh013j.model.domain.Category;
import com.kh013j.model.domain.Role;
import com.kh013j.model.exception.CategoryNotFound;
import com.kh013j.model.exception.DishNotFound;
import com.kh013j.model.exception.RoleNotFound;
import com.kh013j.model.service.interfaces.CategoryService;
import com.kh013j.model.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;

@Controller
public class AdminRoleController {

  @Autowired
  private RoleService roleService;


    @RequestMapping(value = "/admin/role", method = RequestMethod.GET)
    public ModelAndView showRole(){
        return new ModelAndView("AdminRole", "role", roleService.findAll() );
    }

    @RequestMapping(value = "admin/role/new", method = RequestMethod.GET)
    public ModelAndView createRole(){
        return new ModelAndView("RoleAdd", "role", new Role())
                .addObject("role", new Role());
    }

    @RequestMapping(value = "/admin/role/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editCategory(@PathVariable(value = "id") long id) {
        return new ModelAndView("RoleEdit", "role", roleService.findById(id));
    }

    @RequestMapping(value = "/admin/role/delete/{id}", method = RequestMethod.POST)
    public String deleteRole(@PathVariable(value = "id") long id) {
        roleService.delete(id);
        return "redirect:/admin/role";
    }

    @RequestMapping(value = "/admin/role/save", method = RequestMethod.POST)
    public String  SaveNewRole(@Valid @ModelAttribute("role") Role role, BindingResult roleResult) {
        roleService.update(role);
        return "redirect:/admin/role";
    }
}