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
}