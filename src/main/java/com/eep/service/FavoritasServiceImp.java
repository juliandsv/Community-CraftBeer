package com.eep.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eep.entity.Cervezas;
import com.eep.entity.Favoritas;
import com.eep.entity.Usuario;
import com.eep.repositories.CervezaRepository;
import com.eep.repositories.UsuarioRepository;
import com.eep.repositories.FavoritasRepository;


@Service
public class FavoritasServiceImp implements FavoritasService {
	
	 






	@Autowired
	    private  FavoritasRepository favoritasRepository;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    @Autowired
	    private CervezaRepository cervezaRepository;
	
	   
	    public void agregarAFavoritas(String email, Long cervezaId) {
	    	  // Buscar al usuario por email
	        Usuario usuario = usuarioRepository.findByEmail(email);
	        if (usuario == null) {
	            throw new RuntimeException("Usuario no encontrado");
	        }

	        // Buscar la cerveza por id
	        Cervezas cerveza = cervezaRepository.findById(cervezaId).orElseThrow(() -> new RuntimeException("Cerveza no encontrada"));

	        // Verificar si la cerveza ya está en las favoritas
	        Favoritas existente = favoritasRepository.findByUsuarioAndCerveza(usuario, cerveza);
	        if (existente != null) {
	            throw new RuntimeException("La cerveza ya está en tus favoritas");
	        }

	        // Crear la nueva relación y guardarla
	        Favoritas favorita = new Favoritas();
	        favorita.setUsuario(usuario);
	        favorita.setCerveza(cerveza);
	        favoritasRepository.save(favorita);
	    }

	   

	
	    
	   
	    
	    public List<Favoritas> listarFavoritas(String email) {
	    	  Usuario usuario = usuarioRepository.findByEmail(email);
	    	    
	    	    if (usuario != null) {
	    	        return favoritasRepository.findByUsuario(usuario); // devuelve la lista completa con puntuación
	    	    } else {
	    	        return List.of();
	    	    }
	    }






        
	    public void eliminarDeFavoritas(Long id) {
	        // Lógica para eliminar una favorita usando su id
	        Favoritas favorita = favoritasRepository.findById(id).orElse(null);
	        if (favorita != null) {
	            favoritasRepository.delete(favorita);
	        }
	    }
	    
	    

	

}
