package com.proyecto.foodie.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioForm {

	private String dniUsuario;
	@NotNull
	@Size(min=3, max=15, message="El campo nombre debe tener entre 3 y 15 caracteres") 
	private String nombreUsuario;
	
	@NotNull
	@Size(min=3, max=15, message="El campo nombre debe tener entre 3 y 15 caracteres") 
	private String apellidosUsuario;
	
	@NotNull
	private int telefonoUsuario;
	
	private String correoElectronico;
	public String getDniUsuario() {
		return dniUsuario;
	}
	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApellidosUsuario() {
		return apellidosUsuario;
	}
	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}
	public int getTelefonoUsuario() {
		return telefonoUsuario;
	}
	public void setTelefonoUsuario(int telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	
}
