package com.proyecto.foodie.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.micrometer.common.lang.NonNull;

public class EmpleadoForm {
	
	private double sueldo;
	
	@NonNull
	private String tipo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaContratacion;

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	
}
