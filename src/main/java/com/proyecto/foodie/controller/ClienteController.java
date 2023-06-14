package com.proyecto.foodie.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.foodie.form.LoginForm;
import com.proyecto.foodie.form.PedidosForm;
import com.proyecto.foodie.form.SignupForm;
import com.proyecto.foodie.model.Cliente;
import com.proyecto.foodie.model.Pedidos;
import com.proyecto.foodie.model.Platos;
import com.proyecto.foodie.model.Cesta;
import com.proyecto.foodie.repository.ClienteRepository;
import com.proyecto.foodie.repository.PedidosRepository;
import com.proyecto.foodie.repository.PlatosRepository;
import com.proyecto.foodie.repository.UsuariosRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidosRepository pedidosRepository;
	
	@Autowired
	private PlatosRepository platosRepository;
	
	@Autowired
	private Cesta cesta;
	
	public ClienteController(Cesta cesta) {
		this.cesta = cesta;
	}
	
	@GetMapping("/perfil")
	public String perfil(SignupForm signupForm, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Cliente cliente = clienteRepository.findById((Integer) session.getAttribute("id")).orElse(null);
		
		model.addAttribute("cliente", cliente);
		return "perfil";
	}
	
	@PostMapping("/actualizarPerfil")
	public String actualizarPerfil(@ModelAttribute("signupForm") @Valid SignupForm signupForm, BindingResult bindingResult, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
        
		Cliente cliente = clienteRepository.findById((Integer) session.getAttribute("id")).orElse(null);
		
		if(bindingResult.hasErrors()) {
			return "redirect:/perfil";
	    }
		
		if (signupForm.getNombreCliente() != null && !signupForm.getNombreCliente().isEmpty()) {
		    cliente.setNombreCliente(signupForm.getNombreCliente());
		}

		if (signupForm.getApellidosCliente() != null && !signupForm.getApellidosCliente().isEmpty()) {
		    cliente.setApellidosCliente(signupForm.getApellidosCliente());
		}

		if (signupForm.getTelefonoCliente() != null && !signupForm.getTelefonoCliente().isEmpty()) {
		    cliente.setTelefonoCliente(Integer.parseInt(signupForm.getTelefonoCliente()));
		}

		if (signupForm.getCorreoElectronico() != null && !signupForm.getCorreoElectronico().isEmpty()) {
		    cliente.setCorreoElectronico(signupForm.getCorreoElectronico());
		}

		if (signupForm.getContrasena() != null && !signupForm.getContrasena().isEmpty()) {
		    cliente.setContrasena(signupForm.getContrasena());
		}

		if (signupForm.getTarjetaCredito() != null) {
		    cliente.setTarjetaCredito(signupForm.getTarjetaCredito());
		}
        
		clienteRepository.save(cliente);
		
		redirectAttributes.addFlashAttribute("mensaje", "Los cambios se guardaron correctamente.");
		model.addAttribute("signupForm", new SignupForm());
	    return "redirect:/perfil";
	}
	
	@GetMapping("/pedidos")
	public String pedidos(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") != null) {
			Cliente cliente = clienteRepository.findById((Integer) session.getAttribute("id")).orElse(null);
			List<Pedidos> listaPedidos = pedidosRepository.findByCliente(cliente);
			model.addAttribute("listaPedidos", listaPedidos);
		}
		
	    return "pedidos";
	}
	
	@PostMapping("/cesta/add")
    public ResponseEntity<Void> addToCart(@RequestParam int productId) {
    	cesta.addProduct(productId);
    	return ResponseEntity.ok().build();
    }
	
	@PostMapping("/cesta/remove")
    public String removeFromCart(@RequestParam int productId) {
		cesta.removeProduct(productId);
    	return "redirect:/cesta";
    }
	
	@GetMapping("/cesta")
	public String mostrarCesta(PedidosForm pedidosForm, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Platos> carrito = cesta.getPlatos();
		
		model.addAttribute("cesta", carrito);
		model.addAttribute("precio", cesta.getTotalPrice());
	    return "cesta";
	}
	
    @PostMapping("/altapedido")
    @Transactional
	public String checkPedidoInfo(@ModelAttribute("pedidosForm") @Valid PedidosForm pedidosForm, BindingResult bindingResult, Model model, HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	
    	if(bindingResult.hasErrors()) {
			return "cesta";
		}
    	
    	Integer clienteId = (Integer) session.getAttribute("id");
    	
    	if(clienteId != null) {
    		Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
    		if(cesta.getPlatos().size()>0) {
    			List<Platos> platos = new ArrayList<>();
    			for (Platos plato : cesta.getPlatos()) {
    			    Platos platoPersistido = platosRepository.findById(plato.getIdPlato()).orElse(null);
    			    if (platoPersistido == null) {
    			        platoPersistido = platosRepository.save(plato);
    			    }
    			    platos.add(platoPersistido);
    			}
	    		Pedidos pedidoNuevo = new Pedidos(pedidosForm.getDireccionEnvio(), cesta.getTotalPrice(), pedidosForm.getMetodoPago(), cliente, platos);
	    		System.out.println(pedidoNuevo.getListaPlatos());
	    		pedidoNuevo = pedidosRepository.save(pedidoNuevo); // Guardar pedido y obtener el objeto persistido

	            // Establecer la relación many-to-many entre los platos y el pedido
	            pedidoNuevo.setListaPlatos(platos);
	            pedidosRepository.save(pedidoNuevo);
	    		cesta.clearProducts();
    		} else {
    			model.addAttribute("carritovacio", "Debe tener como mínimo 1 producto para realizar el pedido.");
    			return "cesta";
    		}
    	} else {
    		if(cesta.getPlatos().size()>0) {
    			List<Platos> platos = new ArrayList<>();
    			for (Platos plato : cesta.getPlatos()) {
    			    Platos platoPersistido = platosRepository.findById(plato.getIdPlato()).orElse(null);
    			    if (platoPersistido == null) {
    			        platoPersistido = platosRepository.save(plato);
    			    }
    			    platos.add(platoPersistido);
    			}
	    		Pedidos pedidoNuevo = new Pedidos(pedidosForm.getDireccionEnvio(), cesta.getTotalPrice(), pedidosForm.getMetodoPago(), platos);
	    		
	    		pedidosRepository.save(pedidoNuevo);
	    		cesta.clearProducts();
    		} else {
    			model.addAttribute("carritovacio", "Debe tener como mínimo 1 producto para realizar el pedido.");
    			return "cesta";
    		}
    	}
		
		return "redirect:/";
	}
	
}
