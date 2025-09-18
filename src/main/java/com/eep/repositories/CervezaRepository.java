package com.eep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eep.entity.Cervezas;
import com.eep.entity.Usuario;

@Repository
public interface CervezaRepository extends JpaRepository<Cervezas, Long> {
	
	 List<Cervezas> findByNombreContainingOrEstiloContaining(String nombre, String estilo);
	 

}
