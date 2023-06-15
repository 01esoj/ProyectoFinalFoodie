package com.proyecto.foodie.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.foodie.model.Usuarios;

public interface EmpleadoRepository extends UsuariosRepository{
	
	@Query("SELECT u FROM Empleado u WHERE u.idUsuario = :id")
    Usuarios findByIdUsuario(@Param("id") Integer id);
}
