package br.edu.iftm.springapp.impl;

@FoodService
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
        Optional < Food > optional = foodRepository.findById(id);
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