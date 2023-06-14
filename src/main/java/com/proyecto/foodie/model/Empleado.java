package com.proyecto.foodie.model;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@DiscriminatorValue("EMPLEADO")
public class Empleado extends Usuarios{
	
	private double sueldo;
	
	@Temporal(TemporalType.DATE)
	private Date fechaContratacion;
	
	@OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;
	
	public Empleado() {
		super();
	}

	public Empleado(double sueldo, Date fechaContratacion) {
		super();
		this.sueldo = sueldo;
		this.fechaContratacion = fechaContratacion;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public Date getFechacontratacion() {
		return fechaContratacion;
	}

	public void setFechacontratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	@Override
	public String toString() {
		return "Empleado [dniusuario=" + getDniUsuario() + ", sueldo=" + sueldo + ", fechaContratacion="
				+ fechaContratacion + "]";
	}
}
