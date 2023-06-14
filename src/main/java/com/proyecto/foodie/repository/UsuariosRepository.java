package com.proyecto.foodie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.foodie.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{
	
	Usuarios findByCorreoElectronico(String paramCorreoElectronico);
	
	@Query("SELECT u FROM Usuarios u WHERE u.dniUsuario = :dniUsuario")
    Usuarios findByDniUsuario(@Param("dniUsuario") String dniUsuario);
}
