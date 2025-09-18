package com.eep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eep.entity.Cervezas;
import com.eep.entity.Favoritas;
import com.eep.entity.Usuario;

public interface FavoritasRepository extends JpaRepository<Favoritas, Long> {
	
	List<Favoritas> findByUsuario(Usuario usuario);
    default Favoritas findByUsuarioAndCerveza(Usuario usuario, Cervezas cerveza) {
		// TODO Auto-generated method stub
		return null;
	}
    void deleteById(Long id);
	
	
    
}
