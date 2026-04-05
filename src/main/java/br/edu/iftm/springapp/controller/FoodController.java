@Controller
public class FoodController{
    
    @Autowired
    private FoodService foodService;

    @GetMapping("/food")
    public String index(Model model){
        model.addAttribute("foodsList", foodService.getAllFoods());
        return "food/index";
    }
}