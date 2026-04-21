package br.edu.iftm.springapp.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import br.edu.iftm.springapp.model.Food;
import br.edu.iftm.springapp.repository.FoodRepository;
import br.edu.iftm.springapp.service.FoodService;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getAllFoods(){
        return foodRepository.findAll();
    }

    @Override
    public void saveFood(Food food){
        foodRepository.save(food);
    }

    @Override
    public Food getFoodById(long id) {
        Optional <Food> optional = foodRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Food not found with id: " + id);
        }
    }

    @Override
    public void deleteFoodById(long id) {
        this.foodRepository.deleteById(id);
    }
}