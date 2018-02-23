package com.kh013j.controllers.admin;

import com.kh013j.model.domain.Dish;
import com.kh013j.model.domain.Image;
import com.kh013j.model.exception.DishNotFound;
import com.kh013j.model.service.ImgurImageService;
import com.kh013j.model.service.interfaces.CategoryService;
import com.kh013j.model.service.interfaces.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminDishController {


    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    private Logger logger = LoggerFactory.getLogger(AdminDishController.class);

    @GetMapping(value = "/admin")
    public String admin(){
        return "Admin";
    }

    @GetMapping(value = "/admin/dish/all")
    public ModelAndView showDish(){
        return new ModelAndView("AdminDish", "dish", dishService.findAll() );
    }


    @GetMapping(value = "/admin/dish/new")
    public ModelAndView dishCreate(){
        return new ModelAndView("DishEditAdd", "dish", new Dish())
                .addObject("category",categoryService.findAll() );
    }


    @GetMapping(value = "/admin/dish/edit/{id}")
    public ModelAndView dishEdit(@PathVariable(value = "id") long id) {
        return new ModelAndView("DishEditAdd", "dish", dishService.findById(id))
                .addObject("category",categoryService.findAll() );
    }

    @PostMapping(value = "/admin/dish/delete/{id}")
    public String dishDelete(@PathVariable(value = "id") long id) {
        dishService.delete(id);
        return "redirect:/admin/dish/all";
    }

    @GetMapping(value = "/admin/dish/tweakAvail/{id}")
    public String tweakAvailability(@PathVariable(value = "id") long id) {
        dishService.tweakAvailability(id);
        return "redirect:/admin/dish/all";
    }

    @GetMapping(value = "/admin/dish/{id}/removeImage/{img_id}")
    public String removeImage(@PathVariable(value = "id") long id,
                              @PathVariable(value = "img_id") long imageId) throws DishNotFound {
        Dish dish = dishService.findById(id);
        dish.getImages().removeIf(img -> img.getId() == imageId);
        dishService.update(dish);
        return "redirect:/admin/dish/edit/" + id;
    }

    @PostMapping(value = "/admin/dish/save")
    public String dishSaveNew(@Valid @ModelAttribute("dish" )Dish dish, BindingResult dishResult,
                              @RequestParam("pic") List<MultipartFile> files, Model model) throws DishNotFound {
        if (dishResult.hasErrors()) {
            model.addAttribute("category",categoryService.findAll());
            return "DishEditAdd";
        }
        if (files != null && !files.isEmpty()) {

            if (dish.getImages() == null) {
                dish.setImages(new ArrayList<>());
            }

            files.forEach(file -> {
                Image image = new Image();
                try {
                    image.setUrl(ImgurImageService.uploadImage(file.getBytes()));
                    dish.getImages().add(image);
                } catch (IOException e) {
                    logger.error("Something wrong with file", e, file);
                }
            });
        }
        dishService.update(dish);
        return "redirect:/admin/dish/all";
    }
}