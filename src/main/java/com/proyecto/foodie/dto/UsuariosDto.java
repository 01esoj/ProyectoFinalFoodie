package com.proyecto.foodie.dto;

import java.util.List;

public class UsuariosDto {
	
	private String dni_usuario;
	private String nombre_usuario;
	private String apellidos_usuario;
	private int telefono_usuario;
	private String correo_electronico;
	private String contrasena;
	private String tipo_usuario;
	private List<PlatosDto> listaPlatos;
	
	public UsuariosDto() {}

	public String getDni_usuario() {
		return dni_usuario;
	}

	public void setDni_usuario(String dni_usuario) {
		this.dni_usuario = dni_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getApellidos_usuario() {
		return apellidos_usuario;
	}

	public void setApellidos_usuario(String apellidos_usuario) {
		this.apellidos_usuario = apellidos_usuario;
	}

	public int getTelefono_usuario() {
		return telefono_usuario;
	}

	public void setTelefono_usuario(int telefono_usuario) {
		this.telefono_usuario = telefono_usuario;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
	public List<PlatosDto> getListaPlatos() {
		return listaPlatos;
	}

	public void setListaPlatos(List<PlatosDto> listaPlatos) {
		this.listaPlatos = listaPlatos;
	}
}
