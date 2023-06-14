package com.proyecto.foodie.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupForm {
	
	@NotNull
	@Size(min = 9, max = 9, message = "El dni debe tener 9 caracteres")
	private String dniCliente;
	@NotNull
	@Size(min = 3, max = 20, message = "El nombre de cliente debe tener entre 3 y 20 caracteres")
	private String nombreCliente;
	private String apellidosCliente;
	@NotNull
	@Pattern(regexp = "6\\d{8}", message = "El teléfono debe tener 9 dígitos y comenzar por 6")
	private String telefonoCliente;
	@NotNull
	@Email(message = "El correo no puede ser nulo")
	private String correoElectronico;
	@NotNull
	@Size(min = 4, max = 30, message = "La contraseña debe tener entre 4 y 30 caracteres")
	private String contrasena;
	private Long tarjetaCredito;
	
	public SignupForm() {}

	public SignupForm(String dniCliente, String nombreCliente, String apellidosCliente, String telefonoCliente,
			String correoElectronico, String contrasena, Long tarjetaCredito) {
		this.dniCliente = dniCliente;
		this.nombreCliente = nombreCliente;
		this.apellidosCliente = apellidosCliente;
		this.telefonoCliente = telefonoCliente;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.tarjetaCredito = tarjetaCredito;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidosCliente() {
		return apellidosCliente;
	}

	public void setApellidosCliente(String apellidosCliente) {
		this.apellidosCliente = apellidosCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Long getTarjetaCredito() {
		return tarjetaCredito;
	}

	public void setTarjetaCredito(Long tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}

	@Override
	public String toString() {
		return "SignupForm [dniCliente=" + dniCliente + ", nombreCliente=" + nombreCliente + ", apellidosCliente="
				+ apellidosCliente + ", telefonoCliente=" + telefonoCliente + ", correoElectronico=" + correoElectronico
				+ ", contrasena=" + contrasena + ", tarjetaCredito=" + tarjetaCredito + "]";
	}
	
	
}
