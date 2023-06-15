package com.proyecto.foodie.dto;

import java.util.List;

public class InventarioDto {
	
private int id_ingrediente;
	
	private double cantidad;
	private double preico_compra;
	private String proveedor;
	private List<IngredientesDto> listaIngredientes;
	
	public InventarioDto() {}
	
	public int getId_ingrediente() {
		return id_ingrediente;
	}

	public void setId_ingrediente(int id_ingrediente) {
		this.id_ingrediente = id_ingrediente;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPreico_compra() {
		return preico_compra;
	}

	public void setPreico_compra(double preico_compra) {
		this.preico_compra = preico_compra;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public List<IngredientesDto> getListaIngredientes() {
		return listaIngredientes;
	}

	public void setListaIngredientes(List<IngredientesDto> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}
}
