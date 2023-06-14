package com.proyecto.foodie.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pedidos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer numeroPedido;
	
	@NotNull
	private String direccionEnvio;
	private double precioTotal;
	private String metodoPago;
	
	@ManyToOne
	@JoinColumn(name="dni_cliente")
	private Cliente cliente;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name="rel_platos_pedidos", 
			joinColumns = @JoinColumn(name="numeroPedido", nullable=false), 
			inverseJoinColumns = @JoinColumn(name="idPlato", nullable = false))
	private List<Platos> listaPedidos;
	
	public Pedidos() {}
	
	public Pedidos(String direccionEnvio, double precioTotal, String metodoPago, List<Platos> listaPlatos) {
		this.direccionEnvio = direccionEnvio;
		this.precioTotal = precioTotal;
		this.metodoPago = metodoPago;
		this.listaPedidos = listaPlatos;
	}
	
	public Pedidos(String direccionEnvio, double precioTotal, String metodoPago, Cliente cliente, List<Platos> listaPlatos) {
		this.direccionEnvio = direccionEnvio;
		this.precioTotal = precioTotal;
		this.metodoPago = metodoPago;
		this.cliente = cliente;
		this.listaPedidos = listaPlatos;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
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
		return listaPedidos;
	}

	public void setListaPlatos(List<Platos> listaPlatos) {
		this.listaPedidos = listaPlatos;
	}

	@Override
	public String toString() {
		return "Pedidos [numeroPedido=" + numeroPedido + ", direccionEnvio=" + direccionEnvio + ", precioTotal="
				+ precioTotal + ", metodoPago=" + metodoPago + ", cliente=" + cliente.getDniCliente() + "]";
	}
}
