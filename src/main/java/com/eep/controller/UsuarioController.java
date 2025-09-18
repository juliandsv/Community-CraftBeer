package com.eep.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eep.entity.Publicacion;
import com.eep.entity.Usuario;
import com.eep.service.PublicacionService;
import com.eep.service.UsuarioService;

@Controller
@RequestMapping
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PublicacionService publicacionService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LoginPage() {
		
		return "login";
		
	}
	 @PostMapping
	 public String loginPost(@RequestParam("email") String email, @RequestParam("password") String password) {
	        // Lógica para autenticar el usuario
	        return "inicio";  // Redirige a /buscar después del login
	    }
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public String RegistroPage() {
		
		return "registro";
		
	}
	
	
	@PostMapping(value="/registro")
	public String RegistroPage(@ModelAttribute Usuario usuario) {
		usuarioService.registrarUsuario(usuario);
		return "redirect:/login"; // Redirige al login después de registrar un nuevo usuario
	}
	
	
}
