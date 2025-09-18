package com.eep.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eep.entity.Cervezas;
import com.eep.entity.Favoritas;
import com.eep.entity.Usuario;
import com.eep.repositories.CervezaRepository;
import com.eep.repositories.UsuarioRepository;
import com.eep.repositories.FavoritasRepository;
import com.eep.service.FavoritasService;

@Controller
@RequestMapping("/favoritas")
public class FavoritasController {
	
	 @Autowired
	  private FavoritasService favoritasService;
	 @Autowired
	  private FavoritasRepository favoritasRepository;
	 
	 @Autowired
	 private UsuarioRepository usuarioRepository;
	 
	 @Autowired
	 private CervezaRepository cervezaRepository;
	 
	 @PostMapping("/guardar")
	 public ResponseEntity<String> agregarAFavoritas(@RequestBody Favoritas favorita) {
	   
	     // Verificamos si la cerveza y el usuario existen
	     Usuario usuario = usuarioRepository.findByEmail(favorita.getUsuario().getEmail());
	     if (usuario == null) {
	         System.out.println("Usuario no encontrado: " + favorita.getUsuario().getEmail());
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
	     }

	     Cervezas cerveza = cervezaRepository.findById(favorita.getCerveza().getId()).orElse(null);
	     if (cerveza == null) {
	         System.out.println("Cerveza no encontrada con ID: " + favorita.getCerveza().getId());
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cerveza no encontrada.");
	     }

	     // Establecemos la relación entre la cerveza y el usuario
	     favorita.setUsuario(usuario);
	     favorita.setCerveza(cerveza);
	     
	     // Guardamos la favorita en la base de datos
	     favoritasRepository.save(favorita);

	    
	     return ResponseEntity.ok("Cerveza agregada a favoritas.");
	 }
	
	  
}