package com.eep.controller;


import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eep.entity.Cervezas;
import com.eep.entity.Favoritas;
import com.eep.entity.Publicacion;
import com.eep.entity.Usuario;
import com.eep.service.FavoritasService;
import com.eep.service.PublicacionService;
import com.eep.service.UsuarioService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    
    private final UsuarioService usuarioService;
    private final PublicacionService publicacionService;
    private final FavoritasService favoritasService;  // Servicio para obtener cervezas favoritas

    @Autowired
    public PerfilController(UsuarioService usuarioService, PublicacionService publicacionService, FavoritasService FavoritasService) {
        this.usuarioService = usuarioService;
        this.publicacionService = publicacionService;
        this.favoritasService = FavoritasService;
    }

    // Mostrar perfil de usuario logueado
    @GetMapping
    public String mostrarPerfil(Model model) {
    	String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.obtenerUsuario(emailUsuario);

        if (usuario == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "error";
        }

        
        List<Favoritas> favoritas = favoritasService.listarFavoritas(emailUsuario);

        List<Publicacion> publicaciones = publicacionService.obtenerPublicacionesPorUsuario(emailUsuario);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Publicacion publicacion : publicaciones) {
            if (publicacion.getFechaPublicacion() != null) {
                String fechaFormateada = publicacion.getFechaPublicacion().format(formatter);
                publicacion.setFechaPublicacionFormatted(fechaFormateada);
            }
        }

        model.addAttribute("favoritas", favoritas);
        model.addAttribute("publicaciones", publicaciones);
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

    // Mostrar perfil de otro usuario
    @GetMapping("/{email}")
    public String mostrarPerfilDeOtroUsuario(@PathVariable String email, Model model) {
        Usuario usuario = usuarioService.obtenerUsuario(email);
        
        if (usuario == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "error";
        }

        List<Publicacion> publicaciones = publicacionService.obtenerPublicacionesPorUsuario(email);
        List<Favoritas> favoritas = favoritasService.listarFavoritas(email);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Publicacion publicacion : publicaciones) {
            if (publicacion.getFechaPublicacion() != null) {
                String fechaFormateada = publicacion.getFechaPublicacion().format(formatter);
                publicacion.setFechaPublicacionFormatted(fechaFormateada);
            }
        }
        

        model.addAttribute("publicaciones", publicaciones);
        model.addAttribute("usuario", usuario);
        model.addAttribute("favoritas", favoritas);  // Pasar cervezas favoritas al modelo

        return "perfil";  // Usar la misma vista para el perfil, pero con datos de otro usuario
    }
    @PostMapping("/favoritas/eliminar/{id}")
	 public String eliminarFavorita(@PathVariable Long id) {
    	favoritasService.eliminarDeFavoritas(id);
	     return "redirect:/perfil"; // O a donde quieras volver
	 }
}