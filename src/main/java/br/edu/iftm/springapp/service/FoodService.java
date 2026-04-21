package br.edu.iftm.springapp.service;

import java.util.List;

import br.edu.iftm.springapp.model.Food;

public interface FoodService{
    List<Food> getAllFoods();
    void saveFood(Food food);
    Food getFoodById(long id);
    void deleteFoodById(long id);
}