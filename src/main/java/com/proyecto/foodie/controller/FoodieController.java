package com.proyecto.foodie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.foodie.form.LoginForm;
import com.proyecto.foodie.form.SignupForm;
import com.proyecto.foodie.model.Cesta;
import com.proyecto.foodie.model.Cliente;
import com.proyecto.foodie.model.Platos;
import com.proyecto.foodie.model.Usuarios;
import com.proyecto.foodie.repository.AdminRepository;
import com.proyecto.foodie.repository.ClienteRepository;
import com.proyecto.foodie.repository.EmpleadoRepository;
import com.proyecto.foodie.repository.PlatosRepository;
import com.proyecto.foodie.repository.UsuariosRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class FoodieController {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PlatosRepository platosRepository;
	
	@Autowired
	private Cesta cesta;
	
	@GetMapping("/error")
	public String handleError() {
		return "error";
	}
	
	@GetMapping("/")
	public String index(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Iterable<Platos> itPlatosPrincipales = platosRepository.findByCategoria("Principal");
	    List<Platos> listaPlatosPrincipales = new ArrayList<>();
	    itPlatosPrincipales.forEach(listaPlatosPrincipales::add);
	    model.addAttribute("listaPlatosPrincipales", listaPlatosPrincipales);
	    
	    Iterable<Platos> itPlatosEntrantes = platosRepository.findByCategoria("Entrante");
	    List<Platos> listaPlatosEntrantes = new ArrayList<>();
	    itPlatosEntrantes.forEach(listaPlatosEntrantes::add);
	    model.addAttribute("listaPlatosEntrantes", listaPlatosEntrantes);
	    
	    Iterable<Platos> itPlatosBebidas = platosRepository.findByCategoria("Bebida");
	    List<Platos> listaPlatosBebidas = new ArrayList<>();
	    itPlatosBebidas.forEach(listaPlatosBebidas::add);
	    model.addAttribute("listaPlatosBebidas", listaPlatosBebidas);
	    
	    Iterable<Platos> itPlatosPostres = platosRepository.findByCategoria("Postre");
	    List<Platos> listaPlatosPostres = new ArrayList<>();
	    itPlatosPostres.forEach(listaPlatosPostres::add);
	    model.addAttribute("listaPlatosPostres", listaPlatosPostres);
	    
	    int carrito = cesta.getPlatos().size();
	    session.setAttribute("carrito", carrito);
	    
		return "index";
	}
	
	@GetMapping("/login")
	public String login(LoginForm loginForm) {
		return "login";
	}
	
	@PostMapping("/login")
	public String checkLoginInfo(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Usuarios loginUser = usuariosRepository.findByCorreoElectronico(loginForm.getCorreoElectronico());
		Cliente loginCliente = clienteRepository.findByCorreoElectronico(loginForm.getCorreoElectronico());
		
		if(loginForm.getCorreoElectronico().equals("admin") || (loginForm.getContrasena().equals("admin"))) {
			session.setAttribute("usuario", "ADMIN");
			return "redirect:/admin/indexAdmin";
		}
		
		if (loginUser != null && loginUser.getContrasena().equals(loginForm.getContrasena())) {
			session.setAttribute("id", loginUser.getIdUsuario());
			session.setAttribute("nombre", loginUser.getNombreUsuario());
			return "redirect:/admin/indexAdmin";
		} else if (loginCliente != null && loginCliente.getContrasena().equals(loginForm.getContrasena())) {
			session.setAttribute("id", loginCliente.getIdCliente());
			session.setAttribute("nombre", loginCliente.getNombreCliente());
			return "redirect:/";
		} else {
			session.setAttribute("error", "Correo o contraseña incorrectos");
			return "redirect:/login";
		}
	}
	
	@GetMapping("/signup")
	public String signup(SignupForm signupForm) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String checkSignupInfo(@ModelAttribute("signupForm") @Valid SignupForm signupForm, BindingResult bindingResult, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		if(bindingResult.hasErrors()) {
	        return "signup";
	    }
		
		if (clienteRepository.existsByDniCliente(signupForm.getDniCliente())) {
            model.addAttribute("errordni", "Ese DNI ya existe");
            return "signup";
        }

        if (clienteRepository.existsByTelefonoCliente(signupForm.getTelefonoCliente())) {
        	model.addAttribute("errortelefono", "Ese teléfono ya existe");
        	return "signup";
        }

        if (clienteRepository.existsByCorreoElectronico(signupForm.getCorreoElectronico())) {
        	model.addAttribute("erroremail", "Ese email ya existe");
        	return "signup";
        }
        
        if (clienteRepository.existsByTarjetaCredito(signupForm.getTarjetaCredito())) {
        	model.addAttribute("errortarjeta", "Esa tarjeta de crédito ya existe");
        	return "signup";
        }
        
		Cliente cliente = new Cliente(signupForm.getDniCliente(), signupForm.getNombreCliente(), signupForm.getApellidosCliente(), Integer.parseInt(signupForm.getTelefonoCliente()), signupForm.getCorreoElectronico(), signupForm.getContrasena(), Long.parseLong(signupForm.getTarjetaCredito()));
		clienteRepository.save(cliente);
		
	    session.setAttribute("cuenta", "Cuenta creada éxitosamente");
	    return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    session.invalidate();
	    return "redirect:/";
	}
	
	@GetMapping("/promociones")
	public String promociones(Model model, HttpServletRequest request) {
		
		Iterable<Platos> itPlatos = platosRepository.findByCategoria("Promocion");
	    List<Platos> listaPlatos = new ArrayList<>();
	    itPlatos.forEach(listaPlatos::add);
	    model.addAttribute("listaPlatos", listaPlatos);
	    
		return "promociones";
	}
}
