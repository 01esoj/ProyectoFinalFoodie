package com.proyecto.foodie.form;

public class LoginForm {
	
	private String correoElectronico;
	private String contrasena;
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public LoginForm() {
		super();
	}

	public LoginForm(String correoElectronico, String contrasena) {
		super();
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
	}
}
