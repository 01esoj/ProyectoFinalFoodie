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
	
	@GetMapping("/admin/indexAdmin")
	public String iniPg() {
		return "admin/indexAdmin";
	}
	
	@GetMapping("/admin/indexUser")
	public String empPg() {
		return "admin/indexUser";
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

	
	/*-------------CONFIGURAR USUSARIO-------------------*/
		@GetMapping(path="/admin/configUsuario")
		public String configUsuario(EmpleadoForm empleadoForm,Model modelo,HttpServletRequest request,HttpSession session) {
			String dni_usuario = request.getParameter("dni_usuario");
			modelo.addAttribute("listaUsuarios", usuariosRepositorio.findByDniUsuario(dni_usuario));
			session.setAttribute("dni_usuario", dni_usuario);
			return"admin/configUsuario";
		}
		@PostMapping(path="admin/configUsuario")
		public String configUsuario(@Valid EmpleadoForm empleadoForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session
				) {
			session=request.getSession();
			
			
			
			
			System.out.println("FECHADE ->"+empleadoForm.getFechaContratacion());
			Date fechaContratacion;
			fechaContratacion = empleadoForm.getFechaContratacion();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("FECHA FORMATO CAMBIADO ->"+sdf.format(fechaContratacion));
			System.out.println("FECHADEPuesta ->"+fechaContratacion);
			
			
			if (bindingResult.hasErrors()) {
				return"/error";
			}
			 Usuarios usuarioSeleccionado = usuariosRepositorio.findByDniUsuario((String)session.getAttribute("dni_usuario"));
			System.out.println("ID USUARIO SELECCIONADO ->"+ usuarioSeleccionado.getIdUsuario());
			 
			Empleado empleadoSeleccionado =  (Empleado) empleadoRepositorio.findByIdUsuario(usuarioSeleccionado.getIdUsuario());
			 
			empleadoSeleccionado.setIdUsuario(usuarioSeleccionado.getIdUsuario());
			empleadoSeleccionado.setTipoUsuario(empleadoForm.getTipo());
			empleadoSeleccionado.setDniUsuario(usuarioSeleccionado.getDniUsuario());
			empleadoSeleccionado.setNombreUsuario(usuarioSeleccionado.getNombreUsuario());
			empleadoSeleccionado.setApellidosUsuario(usuarioSeleccionado.getApellidosUsuario());
			empleadoSeleccionado.setCorreoElectronico(usuarioSeleccionado.getCorreoElectronico());

			 empleadoSeleccionado.setFechacontratacion(fechaContratacion);
			 empleadoSeleccionado.setSueldo(empleadoForm.getSueldo());
			 
			 System.out.println("ID EMPLEADO SELECCIONADO ->"+ empleadoSeleccionado.getIdUsuario());
			
			 empleadoRepositorio.save(empleadoSeleccionado);
			 //usuariosRepositorio.save(usuarioSeleccionado);
			 return"admin/indexAdmin";
			 
			 
			 
		}
		
	
	/*-------------INSERTAR USUSARIO-------------------*/
	
	
	@GetMapping(path="/admin/altaUsuario")
	public String showFormUsuario(UsuarioForm usuarioForm,Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaUsuarios", usuariosRepositorio.findAll());
		return"admin/altaUsuario";
	}
	@PostMapping(path="/admin/altaUsuario")
	public String creaUsuario(@Valid UsuarioForm usuarioForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		session=request.getSession();
		System.out.println(usuarioForm.getNombreUsuario()+" " + usuarioForm.getApellidosUsuario() );
		System.out.println(usuarioForm.getDniUsuario()+" " + usuarioForm.getTelefonoUsuario() );
		System.out.println(usuarioForm.getCorreoElectronico() );
		/*int telefono = 0;
		telefono = (int)usuarioForm.getTelefonoUsuario();
		Usuarios usu = new Usuarios();
		usu.setNombreUsuario(usuarioForm.getNombreUsuario());
		usu.setApellidosUsuario(usuarioForm.getApellidosUsuario());
		usu.setDniUsuario(usuarioForm.getDniUsuario());
		usu.setTelefonoUsuario(telefono);
		usu.setCorreoElectronico(usuarioForm.getCorreoElectronico());
		
		usuariosRepositorio.save(usu);
		*/
		int telefono = 0;
		telefono = (int)usuarioForm.getTelefonoUsuario();
		Empleado empleadoSeleccionado = new Empleado();
		empleadoSeleccionado.setNombreUsuario(usuarioForm.getNombreUsuario());
		empleadoSeleccionado.setApellidosUsuario(usuarioForm.getApellidosUsuario());
		empleadoSeleccionado.setDniUsuario(usuarioForm.getDniUsuario());
		empleadoSeleccionado.setTelefonoUsuario(telefono);
		empleadoSeleccionado.setCorreoElectronico(usuarioForm.getCorreoElectronico());
		//empleadoSeleccionado.setIdUsuario(usu.getIdUsuario());
		empleadoRepositorio.save(empleadoSeleccionado);
		//usu.setTipoUsuario("Empleado");
		
		
		//usuarioForm.getNombre_usuario();
		//modelo.addAttribute("listaUsuario", usuariosRepositorio.findAll());
		return"admin/indexAdmin";
	}
	/*----------------EDITAR USUSARIO-----------------*/
	
	@GetMapping(path="/admin/editUsuario")
	public String aJugadores(UsuarioForm usuarioForm,Model modelo,HttpServletRequest request,HttpSession session) {
		String dni_usuario = request.getParameter("dni_usuario");

		modelo.addAttribute("listaUsuarios", usuariosRepositorio.findByDniUsuario(dni_usuario));
		session.setAttribute("dni_usuario", dni_usuario);
		return "admin/editUsuario";
	} 
	@PostMapping(value = "/admin/editUsuario")
    public String bJugador(@Valid UsuarioForm usuarioForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		 if (bindingResult.hasErrors()) {
			return"/error";
		}
		 int telefono = 0;
		telefono = (int)usuarioForm.getTelefonoUsuario();
		 Usuarios usuarioSeleccionado = usuariosRepositorio.findByDniUsuario((String)session.getAttribute("dni_usuario"));
		
		 //p1.getEdad()>p2.getEdad()? 1:-1;
		 usuarioSeleccionado.setNombreUsuario(usuarioForm.getNombreUsuario());
		 usuarioSeleccionado.setApellidosUsuario(usuarioForm.getApellidosUsuario());
		 usuarioSeleccionado.setDniUsuario(usuarioSeleccionado.getDniUsuario());
		 usuarioSeleccionado.setTelefonoUsuario(telefono);
		 usuarioSeleccionado.setCorreoElectronico(usuarioSeleccionado.getCorreoElectronico());
		 	 	
		 usuariosRepositorio.save(usuarioSeleccionado);
        
        return "admin/altaUsuario";
    }
	/*----------------DELETE USUSARIO-----------------*/
	
	@GetMapping(path="/admin/delUsuario")
	public String jugadoresDel(Model modelo, HttpSession session, HttpServletRequest request) {
		 //int idJugador = Integer.parseInt(request.getParameter("id"));
		System.out.println("USUARIO ---> "+session.getAttribute("dni_usuario"));
		String dni_usuario = request.getParameter("dni_usuario");
		
		 Usuarios usuarioSeleccionado = usuariosRepositorio.findByDniUsuario((String)session.getAttribute("dni_usuario"));
		 session.setAttribute("dni_usuario", dni_usuario);

		 
		 usuariosRepositorio.delete(usuarioSeleccionado);
			modelo.addAttribute("listaUsuarios", usuariosRepositorio.findByDniUsuario(dni_usuario));

		return "/admin/indexAdmin";
	} 
	
	/*-------------INSERTAR INVENTARIO-------------------*/
	@GetMapping(path="/admin/altaInventario")
	public String showFormInventario(InventarioForm inventarioForm,Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaInventario", inventarioRepositorio.findAll());
		return"/admin/altaInventario";
	}
	@PostMapping(path="/admin/altaInventario")
	public String creaInventario(@Valid InventarioForm inventarioForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
	
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
		
		/*
		Ingredientes ingre = new Ingredientes();
		ingre.setIdIngrediente(invent.getIdInventario());
		System.out.println(invent.getIdInventario());*/
		
		inventarioRepositorio.save(invent);
		//ingredienteRepositorio.save(ingre);
		
		return "/admin/indexAdmin";
	}
	/*-------------EDITAR INVENTARIO-------------------*/
	@GetMapping(path="/admin/editInventario")
	public String aInventario(InventarioForm inventarioForm,Model modelo,HttpServletRequest request,HttpSession session) {
		int id_ingrediente = Integer.parseInt(request.getParameter("id"));
		//modelo.addAttribute("listaInventario", inventarioRepositorio.findAll());
		modelo.addAttribute("listaInventario", inventarioRepositorio.findByIdInventario(id_ingrediente));
		session.setAttribute("id", id_ingrediente);
		return "/admin/editInventario";
	} 
	@PostMapping(value = "/admin/editInventario")
    public String bJugador(@Valid InventarioForm inventarioForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
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
		
			inventarioRepositorio.save(inventarioSeleccionado);
			
        return "/admin/altaInventario";
    }
	/*-------------ELIMINAR INVENTARIO-------------------*/
	@GetMapping(path="/admin/delInventario")
	public String inventarioDel(Model modelo, HttpSession session, HttpServletRequest request) {
		int id_ingrediente = Integer.parseInt(request.getParameter("id"));
		//System.out.println("ID INVENTARIO ---> "+session.getAttribute("id"));
		System.out.println("ID INVENTARIO ---> "+id_ingrediente);
		
		Inventario inventarioSeleccionado = inventarioRepositorio.findByIdInventario(id_ingrediente);
		Ingredientes ingredientesSeleccionado = ingredienteRepositorio.findByIdIngrediente(id_ingrediente);
		 //Usuarios usuarioSeleccionado = usuariosRepositorio.findByDniUsuario((String)session.getAttribute("dni_usuario"));
		 session.setAttribute("id", id_ingrediente);

		 if(ingredientesSeleccionado!=null) {
			  ingredienteRepositorio.delete(ingredientesSeleccionado);
		 }
		
		 inventarioRepositorio.delete(inventarioSeleccionado);
		 modelo.addAttribute("listaInventario", inventarioRepositorio.findAll());

		return "/admin/indexAdmin";
	} 
	
	/*-------------INSERTAR INGREDIENTE-------------------*/
	
	@GetMapping(path="/admin/altaIngrediente")
	public String showFormIngrediente(IngredientesForm ingredientesForm,Model modelo,HttpServletRequest request,HttpSession session) {
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
	public String creaIngrediente(@Valid IngredientesForm ingredienteForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
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
		
		return"/admin/indexAdmin";
	}
	
	/*-------------MODIFICAR INGREDIENTE-------------------
	@GetMapping(path="/admin/editIngrediente")
	public String aIngrediente(IngredientesForm ingredienteForm,Model modelo,HttpServletRequest request,HttpSession session) {
		int id_ingrediente = Integer.parseInt(request.getParameter("id"));
		//modelo.addAttribute("listaInventario", inventarioRepositorio.findAll());
		modelo.addAttribute("listaIngredientes", ingredienteRepositorio.findByIdIngrediente(id_ingrediente));
		session.setAttribute("id", id_ingrediente);
		return "/admin/editIngrediente";
	} 
	@PostMapping(value = "/admin/editIngrediente")
    public String bJugador(@Valid IngredientesForm ingredienteForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		 if (bindingResult.hasErrors()) {
			return"/error";
		}
			double precio = 0;
			precio = ingredienteForm.getPrecioUnitario();
			
			Ingredientes ingredienteSeleccionado = ingredienteRepositorio.findByIdIngrediente((int)session.getAttribute("id"));

			ingredienteSeleccionado.setNombreIngrediente(null);
			ingredienteSeleccionado.setPrecioUnitario(precio);
			
			ingredienteRepositorio.save(ingredienteSeleccionado);
			
        return "/admin/indexAdmin";
    }*/
	
	/*-------------ELIMINAR INGREDIENTE-------------------*/
	
	
	
	
	
	/*-------------CREAR PLATO-------------------*/
	
	@GetMapping(path="/admin/altaPlato")
	public String showFormPlato(PlatoForm platoForm,Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaPlato", platosRepositorio.findAll());
		return"/admin/altaPlato";
	}
	@PostMapping(path="/admin/altaPlato")
	public String creaUsuario(@Valid PlatoForm platoForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
		session=request.getSession();
		double plat = 0;
		plat = platoForm.getPrecioPlato();
		Platos platoNuevo = new Platos();
		
		platoNuevo.setNombrePlato(platoForm.getNombrePlato());
		platoNuevo.setPrecioPlato(plat);
		platoNuevo.setCategoria(platoForm.getDescripcion());
		platoNuevo.setDescripcion(platoForm.getDescripcion());
		
		
		platosRepositorio.save(platoNuevo);
		//usuarioForm.getNombre_usuario();
		//modelo.addAttribute("listaUsuario", usuariosRepositorio.findAll());
		return"/admin/indexAdmin";
	}
	
	/*-------------EDITAR PLATO-------------------*/
	
	@GetMapping(path="/admin/editPlato")
	public String editPlato(PlatoForm platoForm,Model modelo,HttpServletRequest request,HttpSession session) {
		int id = Integer.parseInt(request.getParameter("id"));

		modelo.addAttribute("listaPlato", platosRepositorio.findByIdPlato(id));
		session.setAttribute("id", id);
		return "/admin/editPlato";
	} 
	@PostMapping(value = "/admin/editPlato")
    public String bPlato(@Valid PlatoForm platoForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
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
		 
		 platosRepositorio.save(platoSeleccionado);
		 
        
        return "/admin/indexAdmin";
    }
	
	/*-------------BORRAR PLATO-------------------*/
	
	
	
	@GetMapping(path="/admin/eliminarPlato")
	public String platoDel(Model modelo, HttpSession session, HttpServletRequest request) {
		 //int idJugador = Integer.parseInt(request.getParameter("id"));
		//String dni_usuario = request.getParameter("dni_usuario");
		int id = Integer.parseInt(request.getParameter("id"));

		Platos platoSeleccionado = platosRepositorio.findByIdPlato((int)session.getAttribute("id"));

		 //Usuarios usuarioSeleccionado = usuariosRepositorio.findByDniUsuario((String)session.getAttribute("dni_usuario"));
		 session.setAttribute("id", id);

		 
		 platosRepositorio.delete(platoSeleccionado);
		 modelo.addAttribute("listaPlato", platosRepositorio.findAll());

		return "/admin/indexAdmin";
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
	
/*-------------INSERTAR CLIENTE-------------------*/
	
	
	@GetMapping(path="/admin/altaCliente")
	public String showFormCliente(ClienteForm clienteForm,Model modelo,HttpServletRequest request,HttpSession session) {
		modelo.addAttribute("listaClientes", clienteRepositorio.findAll());
		return"/admin/altaCliente";
	}
	@PostMapping(path="/admin/altaCliente")
	public String creaCliente(@Valid ClienteForm clienteForm,Model modelo,BindingResult bindingResult, HttpServletRequest request,HttpSession session) {
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
		return"/admin/indexAdmin";
	}
	/*----------------EDITAR CLIENTE-----------------*/
	
	/*----------------ELIMINAR CLIENTE-----------------*/
}
