package com.proyecto.foodie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.foodie.model.Platos;

public interface PlatosRepository extends JpaRepository<Platos, Integer>{
	
	List<Platos> findByCategoria(String paramCategoria);
	
	@Query("SELECT u FROM Platos u WHERE u.idPlato = :id")
	Platos findByIdPlato(@Param("id") Integer id);
}
