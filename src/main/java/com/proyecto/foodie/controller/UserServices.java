package com.proyecto.foodie.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proyecto.foodie.model.Usuarios;
import com.proyecto.foodie.repository.UsuariosRepository;

import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class UserServices {
	
	@Autowired
	@Qualifier("usuariosRepository")
    private UsuariosRepository usuariosRepository;
     
    public List<Usuarios> listAll() {
        return usuariosRepository.findAll(Sort.by("nombreUsuario").ascending());
    }
     
}
