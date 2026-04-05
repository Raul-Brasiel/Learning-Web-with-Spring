public interface FoodService{
    List<Product> getAllFoods();
    void saveFood(Food food);
    Food getFoodById(long id);
    void deleteFoodById(long id);
}