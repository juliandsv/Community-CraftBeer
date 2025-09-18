package com.eep.service;

import java.util.List;




import com.eep.entity.Publicacion;


public interface PublicacionService {
	
	 Publicacion crearPublicacion(Publicacion publicaciones);

	    
	  
		List<Publicacion> obtenerPublicacionesPorUsuario(String email);
		
		 String  eliminarPublicacion(Long id);

}
