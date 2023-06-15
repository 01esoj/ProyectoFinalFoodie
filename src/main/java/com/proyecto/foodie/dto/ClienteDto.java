package com.proyecto.foodie.dto;

import java.util.List;

public class ClienteDto {
	
private String dni_cliente;
	
	private String nombre_cliente;
	private String apellidos_cliente;
	private int telefono_cliente;
	private String correo_electronico;
	private String contrasena;
	private int tarjeta_credito;
	private List<PedidosDto> listaPedidos;
	
	public ClienteDto() {}

	public String getDni_cliente() {
		return dni_cliente;
	}

	public void setDni_cliente(String dni_cliente) {
		this.dni_cliente = dni_cliente;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getApellidos_cliente() {
		return apellidos_cliente;
	}

	public void setApellidos_cliente(String apellidos_cliente) {
		this.apellidos_cliente = apellidos_cliente;
	}

	public int getTelefono_cliente() {
		return telefono_cliente;
	}

	public void setTelefono_cliente(int telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
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

	public int getTarjeta_credito() {
		return tarjeta_credito;
	}

	public void setTarjeta_credito(int tarjeta_credito) {
		this.tarjeta_credito = tarjeta_credito;
	}
	
	
	public List<PedidosDto> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<PedidosDto> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
}
