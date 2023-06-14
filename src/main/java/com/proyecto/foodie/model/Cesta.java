package com.proyecto.foodie.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proyecto.foodie.repository.PlatosRepository;

@Component
public class Cesta {
	
	private List<Platos> platos = new ArrayList<>();
	
	@Autowired
	private PlatosRepository platosRepository;

	public void addProduct(int id) {
		Platos plato = platosRepository.findById(id).orElse(null);
		platos.add(plato);
	}

	public void removeProduct(int id) {
		Platos platoToRemove = null;
	    for (Platos plato : platos) {
	        if (plato.getIdPlato() == id) {
	        	platoToRemove = plato;
	            break;
	        }
	    }
	    if (platoToRemove != null) {
	    	platos.remove(platoToRemove);
	    }
	}
	public void clearProducts() {
		platos.clear();
	}
	
	public List<Platos> getPlatos() {
		return platos;
	}

	public void setPlatos(List<Platos> platos) {
		this.platos = platos;
	}

	public double getTotalPrice() {
        double totalPrice = 0 ;
        for (Platos plato : platos) {
            totalPrice += plato.getPrecioPlato();
        }
        return totalPrice;
    }

	@Override
	public String toString() {
		return "Cesta [platos=" + platos + "]";
	}
	    
}