package com.eep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eep.entity.Usuario;

@Service
public interface UsuarioService {
	
	
    List<Usuario>findAll();
	
	public void registrarUsuario(Usuario usuario);
	
	  // Buscar un usuario por correo electrónico
	    
	boolean existsByEmail(String email);  // Verificar si un usuario existe por correo electrónico

	Usuario save(Usuario usuario);
	
	 Usuario obtenerUsuario(String email);
	 
	 

}
