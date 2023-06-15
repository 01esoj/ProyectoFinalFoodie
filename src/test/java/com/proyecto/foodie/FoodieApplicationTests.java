package com.proyecto.foodie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto.foodie.model.Platos;
import com.proyecto.foodie.repository.PlatosRepository;

@SpringBootTest
class FoodieApplicationTests {
	
	private Platos plato;
	private List<Platos> listaPlatos;
	
	private PlatosRepository platosRepository = Mockito.mock(PlatosRepository.class);

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		listaPlatos = new ArrayList<>();
		plato = new Platos("Pizza Margarita", 9.99, "Principal", "Deliciosa pizza con "
				+ "salsa de tomate, queso mozzarella y albahaca.");
		listaPlatos.add(plato);
	}
	
	@Test
	void buscarPlatos() {
		when(platosRepository.findAll()).thenReturn(listaPlatos);
		assertEquals(listaPlatos, platosRepository.findAll());
	}
	
	@Test
	void buscarPlatosPorCategoria() {
		when(platosRepository.findByIdPlato(anyInt())).thenReturn(plato);
		assertEquals(plato, platosRepository.findByIdPlato(1));
	}
	
}
