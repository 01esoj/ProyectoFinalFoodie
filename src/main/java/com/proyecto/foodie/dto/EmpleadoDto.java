package com.proyecto.foodie.dto;

import java.util.Date;

public class EmpleadoDto extends UsuariosDto{
	
	private double sueldo;
	private Date fecha_contratacion;
	
	public EmpleadoDto() {
		super();
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public Date getFecha_contratacion() {
		return fecha_contratacion;
	}

	public void setFecha_contratacion(Date fecha_contratacion) {
		this.fecha_contratacion = fecha_contratacion;
	}
}
