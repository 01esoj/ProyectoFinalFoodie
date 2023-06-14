package com.proyecto.foodie.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InventarioForm {

	@NotNull
	private int cantidad;
	@NotNull
	private double precioCompra;
	@NotNull
	private String proveedor;
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	
	
	
	
}
