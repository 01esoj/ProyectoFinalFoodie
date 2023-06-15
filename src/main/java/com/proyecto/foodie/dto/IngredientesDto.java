package com.proyecto.foodie.dto;

import java.util.List;


public class IngredientesDto {
	
private int id_ingrediente;
	
	private String nombre_ingrediente;
	private double precio_unitario;
	private InventarioDto inventario;
	private List<PlatosDto> listaPlatos;
	
	public IngredientesDto() {}

	public int getId_ingrediente() {
		return id_ingrediente;
	}

	public void setId_ingrediente(int id_ingrediente) {
		this.id_ingrediente = id_ingrediente;
	}

	public String getNombre_ingrediente() {
		return nombre_ingrediente;
	}

	public void setNombre_ingrediente(String nombre_ingrediente) {
		this.nombre_ingrediente = nombre_ingrediente;
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	
	public InventarioDto getInventario() {
		return inventario;
	}

	public void setInventario(InventarioDto inventario) {
		this.inventario = inventario;
	}

	public List<PlatosDto> getListaPlatos() {
		return listaPlatos;
	}

	public void setListaPlatos(List<PlatosDto> listaPlatos) {
		this.listaPlatos = listaPlatos;
	}
}
