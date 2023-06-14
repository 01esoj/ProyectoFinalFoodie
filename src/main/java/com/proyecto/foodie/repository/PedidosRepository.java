package com.proyecto.foodie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.foodie.model.Cliente;
import com.proyecto.foodie.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Integer>{
	
	List<Pedidos> findByCliente(Cliente cliente);
}
