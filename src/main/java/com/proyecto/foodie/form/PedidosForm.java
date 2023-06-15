package com.proyecto.foodie.form;

import java.util.List;

import com.proyecto.foodie.model.Cliente;
import com.proyecto.foodie.model.Platos;

import jakarta.validation.constraints.NotNull;

public class PedidosForm {
	
	@NotNull
	private String direccionEnvio;
	@NotNull
	private double precioTotal;
	@NotNull
	private String metodoPago;
	private Cliente cliente;
	private List<Platos> listaPlatos;
	
	public PedidosForm() {
		super();
	}

	public PedidosForm(String direccionEnvio, double precioTotal, String metodoPago,
			Cliente cliente, List<Platos> listaPlatos) {
		super();
		this.direccionEnvio = direccionEnvio;
		this.precioTotal = precioTotal;
		this.metodoPago = metodoPago;
		this.cliente = cliente;
		this.listaPlatos = listaPlatos;
	}
	
	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Platos> getListaPlatos() {
		return listaPlatos;
	}

	public void setListaPlatos(List<Platos> listaPlatos) {
		this.listaPlatos = listaPlatos;
	}
}
