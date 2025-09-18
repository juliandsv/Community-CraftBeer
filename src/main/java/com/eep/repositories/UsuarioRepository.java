package com.eep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eep.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	Usuario findByEmail(String email);
	
	
		
	
	
	

}
