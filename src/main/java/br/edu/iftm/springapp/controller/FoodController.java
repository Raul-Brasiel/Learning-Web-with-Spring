package br.edu.iftm.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import br.edu.iftm.springapp.service.FoodService;
import br.edu.iftm.springapp.model.Food;

@Controller
public class FoodController{
    
    @Autowired
    private FoodService foodService;

    @GetMapping("/food")
    public String index(Model model){
        model.addAttribute("foodsList", foodService.getAllFoods());
        return "food/index";
    }

    @GetMapping("/food/create")
    public String create(Model model){
        model.addAttribute("food", new Food());
        return "food/create";
    }

    @PostMapping("/food/save")
    public String save(@ModelAttribute @Valid Food food, BindingResult result, Model model) {

        System.out.println(food);
        if (result.hasErrors()) {
            model.addAttribute("food", food);
            return "food/form";
        }

        foodService.saveFood(food);
        return "redirect:/food";
    }

    @GetMapping("/food/delete/{id}")
    public String delete(@PathVariable Long id){
        foodService.deleteFoodById(id);
        return "redirect:/food";
    }

    @GetMapping("/food/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Food food = foodService.getFoodById(id);
        model.addAttribute("food", food);
        return "food/edit";
    }
}