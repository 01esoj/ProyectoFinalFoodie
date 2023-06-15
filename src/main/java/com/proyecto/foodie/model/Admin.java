package com.proyecto.foodie.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuarios{
	
	private String historial;
	
	@OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;
	
	public Admin() {
		super();
	}

	public Admin(String historial) {
		super();
		this.historial = historial;
	}

	public String getHistorial() {
		return historial;
	}

	public void setHistorial(String historial) {
		this.historial = historial;
	}

	@Override
	public String toString() {
		return "Admin [dniUsuario=" + getDniUsuario() + ", historial=" + historial + "]";
	}
}
