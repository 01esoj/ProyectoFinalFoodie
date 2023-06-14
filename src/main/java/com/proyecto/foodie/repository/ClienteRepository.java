package com.proyecto.foodie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.foodie.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Cliente findByCorreoElectronico(String paramCorreoElectronico);
	
	boolean existsByDniCliente(String dniCliente);

	boolean existsByTelefonoCliente(String telefonoCliente);

	boolean existsByCorreoElectronico(String correoElectronico);

	boolean existsByTarjetaCredito(Long tarjetaCredito);
}
