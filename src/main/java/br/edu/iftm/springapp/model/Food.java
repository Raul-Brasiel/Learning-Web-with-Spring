package br.edu.iftm.springapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "foods")
public class Food{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max  = 50, message = "Nome deve conter pelo menos 3 caracteres")
    @NotBlank(message = "Nome e um campo obrigatorio")
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;

    @NotNull(message = "Informe uma quantidade de kcal valida")
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