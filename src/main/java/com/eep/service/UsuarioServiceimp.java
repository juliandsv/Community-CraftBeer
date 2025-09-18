package com.eep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eep.entity.Usuario;
import com.eep.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
@Service
public class UsuarioServiceimp implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Usuario> findAll() {
		
		return repositorio.findAll();
	}
     @Override
	 public Usuario obtenerUsuario(String email) {
	        return repositorio.findByEmail(email);
	    }

	@Override
	public boolean existsByEmail(String email) {
		
		return repositorio.existsById(email);
	}
    @Transactional
	@Override
	public void registrarUsuario(Usuario usuario) {
    	String encodedPassword = passwordEncoder.encode(usuario.getPassword()); // Encriptar la contraseña
		usuario.setPassword(encodedPassword); // Establecer la contraseña encriptada en el objeto usuario
		
		repositorio.save(usuario); // Guardar el usuario con la contraseña encriptada
		
	}
    @Override
	public Usuario save(Usuario usuario) {
    	
		
		return repositorio.save(usuario);
    }


    

}
