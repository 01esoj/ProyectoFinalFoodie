package com.proyecto.foodie.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCliente;
	
	@NotNull
	@Column(unique=true)
	private String dniCliente;
	
	@NotNull
	private String nombreCliente;
	private String apellidosCliente;
	@NotNull
	@Column(unique=true)
	private int telefonoCliente;
	@NotNull
	@Column(unique=true)
	private String correoElectronico;
	@NotNull
	private String contrasena;
	@Column(unique=true)
	private Long tarjetaCredito;
	
	@OneToMany(mappedBy="cliente")
	private List<Pedidos> listaPedidos;
	
	public Cliente() {}

	public Cliente(String dniCliente, String nombreCliente, String apellidosCliente, int telefonoCliente,
			String correoElectronico, String contrasena, Long tarjetaCredito) {
		this.dniCliente = dniCliente;
		this.nombreCliente = nombreCliente;
		this.apellidosCliente = apellidosCliente;
		this.telefonoCliente = telefonoCliente;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.tarjetaCredito = tarjetaCredito;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidosCliente() {
		return apellidosCliente;
	}

	public void setApellidosCliente(String apellidosCliente) {
		this.apellidosCliente = apellidosCliente;
	}

	public int getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(int telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

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

	public Long getTarjetaCredito() {
		return tarjetaCredito;
	}

	public void setTarjetaCredito(Long tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}
	
	
	public List<Pedidos> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedidos> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", dniCliente=" + dniCliente + ", nombreCliente=" + nombreCliente
				+ ", apellidosCliente=" + apellidosCliente + ", telefonoCliente=" + telefonoCliente
				+ ", correoElectronico=" + correoElectronico + ", contrasena=" + contrasena + ", tarjetaCredito="
				+ tarjetaCredito + "]";
	}
	
}
