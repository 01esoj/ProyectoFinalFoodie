package com.proyecto.foodie.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Inventario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idInventario;
	
	private int cantidad;
	private double precioCompra;
	private String proveedor;
	
	@OneToMany(mappedBy="inventario")
	private List<Ingredientes> listaIngredientes;
	
	public Inventario() {}

	public Inventario(int cantidad, double precioCompra, String proveedor) {
		this.cantidad = cantidad;
		this.precioCompra = precioCompra;
		this.proveedor = proveedor;
	}

	public Integer getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public List<Ingredientes> getListaIngredientes() {
		return listaIngredientes;
	}

	public void setListaIngredientes(List<Ingredientes> listaIngredientes) {
		this.listaIngredientes = listaIngredientes;
	}

	@Override
	public String toString() {
		return "Inventario [idInventario=" + idInventario + ", cantidad=" + cantidad + ", precioCompra="
				+ precioCompra + ", proveedor=" + proveedor + "]";
	}
}
