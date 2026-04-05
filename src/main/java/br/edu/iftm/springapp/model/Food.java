@Entity
@Table(name = "foods")
public class Food{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;

    @Column(name = "kcal")
    private Float kcal;

    @Column(name = "preparationTime") //em minutos
    private Integer preparationTime;

    //Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Float getKcal() {
        return kcal;
    }
    public void setKcal(Float kcal) {
        this.kcal = kcal;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }
    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }
}