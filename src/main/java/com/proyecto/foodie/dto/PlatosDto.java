package com.proyecto.foodie.dto;

import java.util.List;

public class PlatosDto {
	
	private int id_plato;
	private String nombre_plato;
	private double precio_plato;
	private String categoria;
	private String descripcion;
	private List<PedidosDto> listaPedidos;
	private List<UsuariosDto> listaUsuarios;
	private List<IngredientesDto> listaIngredientes;
	
	public PlatosDto() {}

	public int getId_plato() {
		return id_plato;
	}

	public void setId_plato(int id_plato) {
		this.id_plato = id_plato;
	}

	public String getNombre_plato() {
		return nombre_plato;
	}

	public void setNombre_plato(String nombre_plato) {
		this.nombre_plato = nombre_plato;
	}

	public double getPrecio_plato() {
		return precio_plato;
	}

	public void setPrecio_plato(double precio_plato) {
		this.precio_plato = precio_plato;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<PedidosDto> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<PedidosDto> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public List<UsuariosDto> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuariosDto> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<IngredientesDto> getListaIngredientes() {
		return listaIngredientes;
	}

	public void setListaIngredientes(List<IngredientesDto> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}
}
