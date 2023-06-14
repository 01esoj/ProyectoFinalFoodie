package com.proyecto.foodie.dto;

import java.util.List;

public class PedidosDto {
	
private int numero_pedido;
	
	private String direccion_envio;
	private double precio_total;
	private String metodo_pago;
	private ClienteDto cliente;
	private List<PlatosDto> listaPlatos;
	
	public PedidosDto() {}

	public int getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(int numero_pedido) {
		this.numero_pedido = numero_pedido;
	}

	public String getDireccion_envio() {
		return direccion_envio;
	}

	public void setDireccion_envio(String direccion_envio) {
		this.direccion_envio = direccion_envio;
	}

	public double getPrecio_total() {
		return precio_total;
	}

	public void setPrecio_total(double precio_total) {
		this.precio_total = precio_total;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public List<PlatosDto> getListaPlatos() {
		return listaPlatos;
	}

	public void setListaPlatos(List<PlatosDto> listaPlatos) {
		this.listaPlatos = listaPlatos;
	}
}
