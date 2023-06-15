package com.proyecto.foodie.dto;

public class AdminDto extends UsuariosDto{
	
	private String historial;

	public AdminDto() {
		super();
	}

	public String getHistorial() {
		return historial;
	}

	public void setHistorial(String historial) {
		this.historial = historial;
	}
}
