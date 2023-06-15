package com.proyecto.foodie.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ingredientes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idIngrediente;
	
	@NotNull
	private String nombreIngrediente;
	@NotNull
	private double precioUnitario;
	
	@ManyToOne
	@JoinColumn(name="idInventario", insertable = false, updatable = false)
	private Inventario inventario;
	
	@ManyToMany(mappedBy="listaIngredientes", cascade = CascadeType.PERSIST)
	private List<Platos> listaPlatos;
	
	public Ingredientes() {}

	public Ingredientes(String nombreIngrediente, double precioUnitario) {
		this.nombreIngrediente = nombreIngrediente;
		this.precioUnitario = precioUnitario;
	}

	public Integer getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Integer idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNombreIngrediente() {
		return nombreIngrediente;
	}

	public void setNombreIngrediente(String nombreIngrediente) {
		this.nombreIngrediente = nombreIngrediente;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public List<Platos> getListaPlatos() {
		return listaPlatos;
	}

	public void setListaPlatos(List<Platos> listaPlatos) {
		this.listaPlatos = listaPlatos;
	}

	@Override
	public String toString() {
		return "Ingredientes [idIngrediente=" + idIngrediente + ", nombreIngrediente=" + nombreIngrediente
				+ ", precioUnitario=" + precioUnitario + "]";
	}
}
