package com.proyecto.foodie.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class IngredientesForm {
	
	@NotNull
	private String nombreIngrediente ;
	@NotNull
	private double precioUnitario;
	
	public String getNombreIngrediente() {
		return nombreIngrediente;
	}
	public void setNombreIngrediente(String nombreIngrediente) {
		this.nombreIngrediente = nombreIngrediente;
	}
	public double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	
}
