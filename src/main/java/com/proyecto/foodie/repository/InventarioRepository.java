package com.proyecto.foodie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.foodie.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Integer>{
	
	@Query("SELECT u FROM Inventario u WHERE u.idInventario = :id")
	Inventario findByIdInventario(@Param("id") Integer id);
	
	@Query("SELECT u.idInventario FROM Inventario u")
	List<Integer>findAllByIdInventario();
}
