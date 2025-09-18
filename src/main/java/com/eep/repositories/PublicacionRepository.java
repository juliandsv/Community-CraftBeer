package com.eep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eep.entity.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

	Publicacion save(Publicacion publicacion);
	List<Publicacion> findByUsuarioEmail(String email);
	

	
	

}
