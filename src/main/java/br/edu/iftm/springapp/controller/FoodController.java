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

    @GetMapping("/food/save")
    public String save(@ModelAttribute("food") Food food){
        foodService.saveFood(food);
        return "redirect:/food";
    }
}