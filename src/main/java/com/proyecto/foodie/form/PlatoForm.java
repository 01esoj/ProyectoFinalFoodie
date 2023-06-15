package com.proyecto.foodie.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PlatoForm {

	
	@NotNull
	private String nombrePlato;
	@NotNull
	private double precioPlato;
	@NotNull
	private String categoria;
	@NotNull
	private String descripcion;
	
	public String getNombrePlato() {
		return nombrePlato;
	}
	public void setNombrePlato(String nombrePlato) {
		this.nombrePlato = nombrePlato;
	}
	public double getPrecioPlato() {
		return precioPlato;
	}
	public void setPrecioPlato(double precioPlato) {
		this.precioPlato = precioPlato;
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
	
	
}
