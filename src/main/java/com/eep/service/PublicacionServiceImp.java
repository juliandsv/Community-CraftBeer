package com.eep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eep.entity.Publicacion;

import com.eep.repositories.PublicacionRepository;

@Service
public class PublicacionServiceImp implements PublicacionService {
	
	@Autowired
    private PublicacionRepository publicacionRepository;

	
	
	@Override
	public List<Publicacion> obtenerPublicacionesPorUsuario(String email) {
		return publicacionRepository.findByUsuarioEmail(email);
	}



	@Override
	public Publicacion crearPublicacion(Publicacion publicaciones) {
		
		return publicacionRepository.save(publicaciones);
	}



	public String eliminarPublicacion(Long id) {
	    // Verificamos si existe una publicación con el ID proporcionado
	    Optional<Publicacion> publicacionOpt = publicacionRepository.findById(id);

	    if (publicacionOpt.isPresent()) {
	        // Si la publicación existe, la eliminamos
	        publicacionRepository.deleteById(id);
	        return "Publicación eliminada correctamente.";
	    } else {
	        // Si no existe la publicación, retornamos un mensaje de error
	        return "Publicación no encontrada.";
	    }
	}

	


	



	


	
}



	


