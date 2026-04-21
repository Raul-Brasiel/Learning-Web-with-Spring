package br.edu.iftm.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.springapp.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{
}