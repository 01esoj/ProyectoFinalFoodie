package com.proyecto.foodie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.foodie.model.Ingredientes;

public interface IngredientesRepository extends JpaRepository<Ingredientes, Integer>{
	
	@Query("SELECT u FROM Ingredientes u WHERE u.idIngrediente = :id")
	Ingredientes findByIdIngrediente(@Param("id") Integer id);
	
	@Query("SELECT u.idIngrediente FROM Ingredientes u")
	List<Integer>findAllByIdIngrediente();
	
	@Query(value = "SELECT * FROM ingredientes ORDER BY id_ingrediente ASC LIMIT 3", nativeQuery = true)
    List<Ingredientes> findFirstThreeIngredients();
}
