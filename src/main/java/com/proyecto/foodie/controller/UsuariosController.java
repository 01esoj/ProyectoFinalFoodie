package com.proyecto.foodie.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.foodie.form.ClienteForm;
import com.proyecto.foodie.form.EmpleadoForm;
import com.proyecto.foodie.form.IngredientesForm;
import com.proyecto.foodie.form.InventarioForm;
import com.proyecto.foodie.form.PlatoForm;
import com.proyecto.foodie.form.UsuarioForm;
import com.proyecto.foodie.model.Cliente;
import com.proyecto.foodie.model.Empleado;
import com.proyecto.foodie.model.Ingredientes;
import com.proyecto.foodie.model.Inventario;
import com.proyecto.foodie.model.Platos;
import com.proyecto.foodie.model.Usuarios;
import com.proyecto.foodie.repository.ClienteRepository;
import com.proyecto.foodie.repository.EmpleadoRepository;
import com.proyecto.foodie.repository.IngredientesRepository;
import com.proyecto.foodie.repository.InventarioRepository;
import com.proyecto.foodie.repository.PlatosRepository;
import com.proyecto.foodie.repository.UsuariosRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class UsuariosController {
	
	
	@Qualifier("usuariosRepository")
	@Autowired
	private UsuariosRepository usuariosRepositorio;
	
	@Autowired
	private InventarioRepository inventarioRepositorio;
	
	@Autowired
	private IngredientesRepository ingredienteRepositorio;
	
	@Autowired
	private  PlatosRepository platosRepositorio;
	
	@Autowired
	private  EmpleadoRepository empleadoRepositorio;
	
	@Autowired
	private  ClienteRepository clienteRepositorio;
	
	/*-------------admin EMPLEADO-------------------*/
	
	@GetMapping("/admin/indexAdmin")
	public String empPlantillaAdmin() {
		return "admin/indexAdmin";
	}
	@GetMapping("admin/indexEmpleado")
	public String empPlantillaEmpleado() {
		return "admin/indexEmpleado";
	}
	@GetMapping("admin/indexInventario")
	public String empPlantillaInventario(Model modelo) {
		modelo.addAttribute("listaIngredientes", ingredienteRepositorio.findAll());
		return "admin/indexInventario";
	}
	@GetMapping("admin/indexCliente")
	public String empPlantillaCliente() {
		return "admin/indexCliente";
	}
	
	
	/*-------------admin ALTA EMPLEADO  -------------------*/
	@GetMapping(path="admin/altaUsuario")
	public String showUsuarioAdmin(UsuarioForm usuarioForm,Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaUsuarios", usuariosRepositorio.findAll());
		return"admin/altaUsuario";
	}
	@PostMapping(path="admin/altaUsuario")
	public String creaUsuarioAdmin(@Valid UsuarioForm usuarioForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		session=request.getSession();
		int telefono = 0;
		telefono = (int)usuarioForm.getTelefonoUsuario();
		
		if((usuariosRepositorio.findByTelefono(telefono)==null) && 
				(usuariosRepositorio.findByCorreoElectronico(usuarioForm.getCorreoElectronico())==null)
				&&(usuariosRepositorio.findByDniUsuario(usuarioForm.getDniUsuario())==null)) {
			
			Empleado empleadoSeleccionado = new Empleado();
			empleadoSeleccionado.setNombreUsuario(usuarioForm.getNombreUsuario());
			empleadoSeleccionado.setApellidosUsuario(usuarioForm.getApellidosUsuario());
			empleadoSeleccionado.setDniUsuario(usuarioForm.getDniUsuario());
			empleadoSeleccionado.setTelefonoUsuario(telefono);
			empleadoSeleccionado.setCorreoElectronico(usuarioForm.getCorreoElectronico());
			empleadoRepositorio.save(empleadoSeleccionado);
			modelo.addAttribute("mensaje","Usuario "+ usuarioForm.getNombreUsuario() +" " + usuarioForm.getApellidosUsuario()+" dado de alta correctamente");
		
			return"admin/indexEmpleado";
		}
		else {
			session.setAttribute("error", "usuario / DNI / Correo en uso");
			return"admin/altaUsuario";
		}
					
	}
	
	/*------------- admin LISTA EMPLEADO -------------------*/
	
	@GetMapping(path="admin/listaUsuario")
	public String showListUsuarioAdmin(Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaUsuarios", usuariosRepositorio.findAll());
		return"admin/listaUsuario";
	}
	
	/*------------- admin CONFIG EMPLEADO -------------------*/
	
	@GetMapping(path="admin/listaUsuarioEdit")
	public String showConfigUsuarioAdmin(Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaUsuarios", usuariosRepositorio.findAll());
		return"admin/listaUsuarioEdit";
	}
	
	@GetMapping(path="admin/configUsuario")
	public String configUsuarioAdmin(EmpleadoForm empleadoForm,Model modelo,HttpServletRequest request,HttpSession session) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		modelo.addAttribute("listaUsuarios", usuariosRepositorio.findByIdUsuario(id));
		session.setAttribute("id", id);
		return"admin/configUsuario";
	}
	@PostMapping(path="admin/configUsuario")
	public String configUsuarioAdmin(@Valid EmpleadoForm empleadoForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session
			) {
		session=request.getSession();

		Date fechaContratacion;
		fechaContratacion = empleadoForm.getFechaContratacion();

		if (bindingResult.hasErrors()) {
			return"/error";
		}
		 Usuarios usuarioSeleccionado = usuariosRepositorio.findByIdUsuario((Integer)session.getAttribute("id"));

		Empleado empleadoSeleccionado =  (Empleado) empleadoRepositorio.findByIdUsuario(usuarioSeleccionado.getIdUsuario());
		 
		empleadoSeleccionado.setIdUsuario(usuarioSeleccionado.getIdUsuario());
		empleadoSeleccionado.setTipoUsuario(empleadoForm.getTipo());
		empleadoSeleccionado.setDniUsuario(usuarioSeleccionado.getDniUsuario());
		empleadoSeleccionado.setNombreUsuario(usuarioSeleccionado.getNombreUsuario());
		empleadoSeleccionado.setApellidosUsuario(usuarioSeleccionado.getApellidosUsuario());
		empleadoSeleccionado.setCorreoElectronico(usuarioSeleccionado.getCorreoElectronico());

		 empleadoSeleccionado.setFechacontratacion(fechaContratacion);
		 empleadoSeleccionado.setSueldo(empleadoForm.getSueldo());
		 
	
		 modelo.addAttribute("mensaje","Usuario "+ empleadoSeleccionado.getNombreUsuario() +" " + empleadoSeleccionado.getApellidosUsuario()+" modificado correctamente");
			
		 empleadoRepositorio.save(empleadoSeleccionado);

		 return"admin/indexEmpleado";
		 
	}
	
	/*------------- admin ELIMINAR EMPLEADO -------------------*/
	
	@GetMapping(path="admin/delUsuario")
	public String jugadoresDelAdmin(Model modelo, HttpSession session, HttpServletRequest request) {

		Integer id = Integer.parseInt(request.getParameter("id"));
		modelo.addAttribute("listaUsuarios", usuariosRepositorio.findByIdUsuario(id));
		Usuarios usuarioSeleccionado = usuariosRepositorio.findByIdUsuario((Integer)session.getAttribute("id"));
		 session.setAttribute("id", id);

		 
		 modelo.addAttribute("mensaje","Usuario "+ usuarioSeleccionado.getNombreUsuario() +" " + usuarioSeleccionado.getApellidosUsuario()+" eliminado correctamente");

		 
		 usuariosRepositorio.delete(usuarioSeleccionado);
	
		 return"admin/indexEmpleado";
	} 

	/*------------- admin CREAR PLATO -------------------*/
	
	
	@GetMapping(path="admin/indexPlato")
	public String showFormPlatoAdmin(PlatoForm platoForm,Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaPlato", platosRepositorio.findAll());
		return"admin/indexPlato";
	}
	@PostMapping(path="admin/indexPlato")
	public String creaUsuarioAdmin(@Valid PlatoForm platoForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		session=request.getSession();
		double plat = 0;
		plat = platoForm.getPrecioPlato();
		Platos platoNuevo = new Platos();
		
		platoNuevo.setNombrePlato(platoForm.getNombrePlato());
		platoNuevo.setPrecioPlato(plat);
		platoNuevo.setCategoria(platoForm.getDescripcion());
		platoNuevo.setDescripcion(platoForm.getDescripcion());
		
		
		List<Ingredientes> listaIn = new ArrayList<>();

		listaIn = ingredienteRepositorio.findFirstThreeIngredients();
		
		platoNuevo.setListaIngredientes(listaIn);
		
		 modelo.addAttribute("mensaje","Plato "+ platoNuevo.getNombrePlato() +" añadido correctamente");

		
		platosRepositorio.save(platoNuevo);
		return"admin/indexPlato";
	}
	/*------------- admin CREAR PLATO JSON -------------------*/
	@PostMapping("/cargarjsonplat2")
	public String cargarDatosJsonPlat(@RequestParam("archivo") MultipartFile archivo,Model modelo) throws IOException {

		if (!archivo.isEmpty()) {

			byte[] bytes = archivo.getBytes();

			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode rootNode = objectMapper.readTree(bytes);

			JsonNode platosNode = rootNode.get("platos");

			List<Platos> platos = objectMapper.readValue(platosNode.toString(), new TypeReference<List<Platos>>() {
			});

			// Agrega el vehículo como un nuevo registro en la base de datos

			 modelo.addAttribute("mensaje","Plato "+ "en JSON añadido correctamente");
			
			platosRepositorio.saveAll(platos);

			return "redirect:/admin/indexPlato";

		} else {

			return "redirect:/admin/indexPlato";

		}

	}
	/*------------- admin EDITAR PLATO  -------------------*/
	
	@GetMapping(path="admin/editPlatoAd")
	public String editPlatoAdmin(PlatoForm platoForm,Model modelo,HttpServletRequest request,HttpSession session) {
		int id = Integer.parseInt(request.getParameter("id"));

		modelo.addAttribute("listaPlato", platosRepositorio.findByIdPlato(id));
		session.setAttribute("id", id);
		return "admin/editPlatoAd";
	}
	@PostMapping(value = "admin/editPlatoAd")
    public String bPlatoAdmin(@Valid PlatoForm platoForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		 if (bindingResult.hasErrors()) {
			return"/error";
		}
		 double plat = 0;
		 plat = platoForm.getPrecioPlato();
		 
		 Platos platoSeleccionado = platosRepositorio.findByIdPlato((int)session.getAttribute("id"));
		 
		 platoSeleccionado.setNombrePlato(platoForm.getNombrePlato());
		 platoSeleccionado.setPrecioPlato(plat);
		 platoSeleccionado.setCategoria(platoForm.getCategoria());
		 platoSeleccionado.setDescripcion(platoForm.getDescripcion());
		 
		 modelo.addAttribute("mensaje","Plato "+ platoSeleccionado.getNombrePlato() +" editado correctamente");

		 
		 platosRepositorio.save(platoSeleccionado);
		 
        
        return "admin/indexPlato";
    }
	
	/*------------- admin ELIMINAR PLATO  -------------------*/
	
	@GetMapping(path="admin/eliminarPlato")
	public String platoDelAdmin(PlatoForm platoForm,Model modelo, HttpSession session, HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("PLATO ID --> "+id);
		Platos platoSeleccionado = new Platos();
		platoSeleccionado = platosRepositorio.findByIdPlato(id);

		if(platoSeleccionado==null) {
			session.setAttribute("error", "Error inesperado");
			return"redirect:/admin/indexPlato";
		}
		 
		 modelo.addAttribute("mensaje","Plato "+ platoSeleccionado.getNombrePlato() +" eliminado correctamente");

		 platosRepositorio.delete(platoSeleccionado);
		 
		 modelo.addAttribute("listaPlato", platosRepositorio.findAll());

		return "redirect:/admin/indexPlato";
	} 
	
	/*------------- admin CREAR INVENTARIO  -------------------*/
	
	@GetMapping(path="admin/altaInventario")
	public String showFormInventarioAdmin(InventarioForm inventarioForm,Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaInventario", inventarioRepositorio.findAll());
		return"admin/altaInventario";
	}
	@PostMapping(path="admin/altaInventario")
	public String creaInventarioAdmin(@Valid InventarioForm inventarioForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
	
		session=request.getSession();
		
		int cantidad = 0;
		cantidad= (int) inventarioForm.getCantidad();
		double precio = 0;
		precio = (double)inventarioForm.getPrecioCompra();
		
		System.out.println(inventarioForm.getCantidad()+" " + inventarioForm.getPrecioCompra() +" " +inventarioForm.getProveedor());
		
		
		Inventario invent = new Inventario();
		invent.setCantidad(cantidad);
		invent.setPrecioCompra(precio);
		invent.setProveedor(inventarioForm.getProveedor());
		
		 modelo.addAttribute("mensaje","Producto "+ invent.getProveedor() +" insertado correctamente");

		inventarioRepositorio.save(invent);
		
		return "admin/indexInventario";
	}
	
	/*------------- admin CREAR JSON INVENTARIO  -------------------*/
	
	@PostMapping("/cargarjsoninve2")
	public String cargarDatosJsonInvAdmin(@RequestParam("archivo") MultipartFile archivo, Model modelo) throws IOException {

		if (!archivo.isEmpty()) {
			byte[] bytes = archivo.getBytes();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(bytes);
			JsonNode inventarioNode = rootNode.get("inventario");
			List<Inventario> inventarios = objectMapper.readValue(inventarioNode.toString(), new TypeReference<List<Inventario>>() {
			
			});
			// Agrega el vehículo como un nuevo registro en la base de datos
			 modelo.addAttribute("mensaje","Producto "+ " insertado correctamente");

			inventarioRepositorio.saveAll(inventarios);
			return "redirect:/admin/indexInventario";

		} else {
			 modelo.addAttribute("mensaje","Producto "+"No se ha insertado correctamente");

			return "redirect:/admin/indexInventario";

		}

	}
	
	/*------------- admin LISTA INVENTARIO  -------------------*/
	
	@GetMapping(path="admin/listaInventario")
	public String showListInventarioAdmin(Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaInventario", inventarioRepositorio.findAll());
		return"admin/listaInventario";
	}
	
	/*------------- admin EDITAR INVENTARIO  -------------------*/
	
	@GetMapping(path="admin/editInventario")
	public String aInventarioAdmin(InventarioForm inventarioForm,Model modelo,HttpServletRequest request,HttpSession session) {
		int id_ingrediente = Integer.parseInt(request.getParameter("id"));
		modelo.addAttribute("listaInventario", inventarioRepositorio.findByIdInventario(id_ingrediente));
		session.setAttribute("id", id_ingrediente);
		return "admin/editInventario";
	} 
	@PostMapping(value = "admin/editInventario")
    public String aInventarioAdmin(@Valid InventarioForm inventarioForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		 if (bindingResult.hasErrors()) {
			return"/error";
		}
		 	int cantidad = 0;
		 	cantidad= (int) inventarioForm.getCantidad();
			double precio = 0;
			precio = inventarioForm.getPrecioCompra();
			
			Inventario inventarioSeleccionado = inventarioRepositorio.findByIdInventario((int)session.getAttribute("id"));

			inventarioSeleccionado.setCantidad(cantidad);
			inventarioSeleccionado.setPrecioCompra(precio);
			inventarioSeleccionado.setProveedor(inventarioForm.getProveedor());
			
			modelo.addAttribute("mensaje","Producto "+ inventarioSeleccionado.getProveedor() +" insertado correctamente");
			
			inventarioRepositorio.save(inventarioSeleccionado);
			
        return "admin/indexInventario";
    }
	
	/*------------- admin ELIMINAR INVENTARIO  -------------------*/
	
	@GetMapping(path="admin/delInventario")
	public String inventarioDelAdmin(Model modelo, HttpSession session, HttpServletRequest request) {
		int id_ingrediente = Integer.parseInt(request.getParameter("id"));
		//System.out.println("ID INVENTARIO ---> "+session.getAttribute("id"));
		System.out.println("ID INVENTARIO ---> "+id_ingrediente);
		
		Inventario inventarioSeleccionado = inventarioRepositorio.findByIdInventario(id_ingrediente);
		Ingredientes ingredientesSeleccionado = ingredienteRepositorio.findByIdIngrediente(id_ingrediente);
		 session.setAttribute("id", id_ingrediente);

		 if(ingredientesSeleccionado!=null) {
			  ingredienteRepositorio.delete(ingredientesSeleccionado);
		 }
		 modelo.addAttribute("mensaje","Producto "+ inventarioSeleccionado.getProveedor() +" eliminado correctamente");
			
		 inventarioRepositorio.delete(inventarioSeleccionado);
		 modelo.addAttribute("listaInventario", inventarioRepositorio.findAll());

		return "/admin/indexInventario";
	} 
	/*------------- admin ALTA INGREDIENTES  -------------------*/
	
	@GetMapping(path="/admin/altaIngrediente")
	public String showFormIngredienteAdmin(IngredientesForm ingredientesForm,Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaIngredientes", ingredienteRepositorio.findAll());
		return"/admin/altaIngrediente";
	}
	
	 public static Integer encontrarPrimerNumeroNoRepetido(List<Integer> lista1, List<Integer> lista2) {
		   // Crear un mapa para contar la frecuencia de los números en la lista1
	        Map<Integer, Integer> frecuencia = new LinkedHashMap<>();

	        // Recorrer la lista1 y aumentar la frecuencia de cada número
	        for (Integer num : lista1) {
	            frecuencia.put(num, frecuencia.getOrDefault(num, 0) + 1);
	        }

	        // Encontrar el primer número que tiene frecuencia 1 en la lista1
	        for (Integer num : lista1) {
	            if (frecuencia.get(num) == 1) {
	                // Verificar si el número también está en la lista2
	                if (lista2.contains(num)) {
	                    continue; // Si está en la lista2, continuar con el siguiente número
	                } else {
	                    return num; // Si no está en la lista2, es el primer número no repetido
	                }
	            }
	        }

	        // Si la lista2 está vacía, devolver el primer número de la lista1
	        if (lista2.isEmpty()) {
	            return lista1.get(0);
	        }

	        return null; // Si no se encuentra ningún número no repetido
	    }
	 
	@PostMapping(path="/admin/altaIngrediente")
	public String creaIngredienteAdmin(@Valid IngredientesForm ingredienteForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		session=request.getSession();
		
		List<Integer> listaIdsInvent = new ArrayList<>();
		listaIdsInvent = inventarioRepositorio.findAllByIdInventario();
		System.out.println(listaIdsInvent);
		
		List<Integer> listaIdsIngred = new ArrayList<>();
		listaIdsIngred = ingredienteRepositorio.findAllByIdIngrediente();
		System.out.println(listaIdsIngred);
		
		int numeroId = encontrarPrimerNumeroNoRepetido(listaIdsInvent,listaIdsIngred);
		System.out.println(numeroId);
		
		double precio = 0;
		precio = ingredienteForm.getPrecioUnitario();
		//telefono = (int)usuarioForm.getTelefono_usuario();
		Ingredientes ingred = new Ingredientes();
		ingred.setIdIngrediente(numeroId);
		ingred.setNombreIngrediente(ingredienteForm.getNombreIngrediente());
		ingred.setPrecioUnitario(precio);
		
		
		ingredienteRepositorio.save(ingred);
		
		return"/admin/indexInventario";
	}
	
	/*------------- admin ELIMINAR INGREDIENTES  -------------------*/
	
	@GetMapping(path="admin/delIngrediente")
	public String ingredienteDelAdmin(Model modelo, HttpSession session, HttpServletRequest request) {

		Integer id = Integer.parseInt(request.getParameter("id"));
		modelo.addAttribute("listaIngrediente", ingredienteRepositorio.findByIdIngrediente(id));
		Ingredientes ingredienteSeleccionado = ingredienteRepositorio.findByIdIngrediente(id);
		 session.setAttribute("id", id);

		 
		 modelo.addAttribute("mensaje","Ingrediente "+ ingredienteSeleccionado.getNombreIngrediente() +" eliminado correctamente");

		 
		 ingredienteRepositorio.delete(ingredienteSeleccionado);
	
		 return"admin/indexInventario";
	} 
	
	/*------------- admin CREAR CLIENTE  -------------------*/
	
	@GetMapping(path="admin/altaCliente")
	public String showFormClienteAdmin(ClienteForm clienteForm,Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaClientes", clienteRepositorio.findAll());
		return"admin/altaCliente";
	}
	@PostMapping(path="admin/altaCliente")
	public String creaClienteAdmin(@Valid ClienteForm clienteForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		session=request.getSession();
		int telefono = 0;
		telefono = (int)clienteForm.getTelefonoCliente();
		Cliente clienteSeleccionado = new Cliente();
		clienteSeleccionado.setNombreCliente(clienteForm.getNombreCliente());
		clienteSeleccionado.setApellidosCliente(clienteForm.getApellidosCliente());
		clienteSeleccionado.setDniCliente(clienteForm.getDniCliente());
		clienteSeleccionado.setTelefonoCliente(telefono);
		clienteSeleccionado.setCorreoElectronico(clienteForm.getCorreoElectronico());
		clienteRepositorio.save(clienteSeleccionado);
		return"/admin/indexCliente";
	}
	

	/*-------------INSERTAR EXCEL-------------------*/
	
	 @Autowired
	 private UserServices service;
	     
	     
	    @GetMapping("/admin/excel")
	    public void exportToExcel(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=usuarios_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Usuarios> listUsers = service.listAll();
	         
	        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
	         
	        excelExporter.export(response);    
	    }  
	/*-------------INSERTAR USUSARIO JSON-------------------*/
	
	@GetMapping("/usuarios.json")
	@ResponseBody
	public List<Usuarios> exportAllUsuarios() {
	    return (List<Usuarios>) usuariosRepositorio.findAll();
	}
	
	@GetMapping("/platos.json")
	@ResponseBody
	public List<Platos> exportAllPlatos() {
	    return (List<Platos>) platosRepositorio.findAll();
	}
	
	
	@GetMapping("/inventario.json")
	@ResponseBody
	public List<Inventario> exportAllInventario() {
	    return (List<Inventario>) inventarioRepositorio.findAll();
	}
	
/*-------------CARGAR PLATO-------------------*/
	
	@PostMapping("/cargarjsonplat")
	public String cargarDatosJsonVeh(@RequestParam("archivo") MultipartFile archivo) throws IOException {

		if (!archivo.isEmpty()) {

			byte[] bytes = archivo.getBytes();

			ObjectMapper objectMapper = new ObjectMapper();

			JsonNode rootNode = objectMapper.readTree(bytes);

			JsonNode platosNode = rootNode.get("platos");

			List<Platos> platos = objectMapper.readValue(platosNode.toString(), new TypeReference<List<Platos>>() {
			});

			// Agrega el vehículo como un nuevo registro en la base de datos

			platosRepositorio.saveAll(platos);

			return "redirect:/admin/indexAdmin";

		} else {

			return "redirect:/admin/indexAdmin";

		}

	}
/*-------------CARGAR INVENTARIO-------------------*/
	
	@PostMapping("/cargarjsoninve")
	public String cargarDatosJsonInv(@RequestParam("archivo") MultipartFile archivo) throws IOException {

		if (!archivo.isEmpty()) {
			byte[] bytes = archivo.getBytes();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(bytes);
			JsonNode inventarioNode = rootNode.get("inventario");
			List<Inventario> inventarios = objectMapper.readValue(inventarioNode.toString(), new TypeReference<List<Inventario>>() {
			
			});
			// Agrega el vehículo como un nuevo registro en la base de datos
			inventarioRepositorio.saveAll(inventarios);
			return "redirect:/admin/indexAdmin";

		} else {

			return "redirect:/admin/indexAdmin";

		}

	}

}
