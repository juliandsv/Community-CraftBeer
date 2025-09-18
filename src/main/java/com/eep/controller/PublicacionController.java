package com.eep.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Base64;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eep.entity.Publicacion;
import com.eep.entity.Usuario;
import com.eep.repositories.PublicacionRepository;
import com.eep.repositories.UsuarioRepository;
import com.eep.service.PublicacionService;



@Controller
public class PublicacionController {

    @Autowired
    private PublicacionRepository publicacionRepository;
    @Autowired
    private UsuarioRepository  usuarioRepository;
    @Autowired
    private PublicacionService publicacionService;

    // Ruta para mostrar el formulario
    @GetMapping("/publicacion/crear")
    public String mostrarFormularioCrearPublicacion(Model model) {
        model.addAttribute("publicacion", new Publicacion());
        return "publicacion"; // El nombre del archivo HTML creado anteriormente
    }

    @PostMapping("/publicacion/crear")
    public String crearPublicacion(
            @ModelAttribute Publicacion publicacion,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo,
            Authentication authentication, // Para obtener el usuario autenticado
            RedirectAttributes redirectAttributes) throws IOException {

        // Verificar que el título no esté vacío
        if (publicacion.getDescripcion() == null || publicacion.getDescripcion().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "la descripcion de la publicación es obligatorio.");
            return "redirect:/publicacion/crear"; // Redirige al formulario si el título está vacío
        }

        // Verificar que el archivo no es nulo y guardarlo en base64
        if (archivo != null && !archivo.isEmpty()) {
            String base64 = Base64.getEncoder().encodeToString(archivo.getBytes());
            publicacion.setFoto(base64);
        } else {
            publicacion.setFoto(null); // o un valor por defecto si no se sube foto
        }

        // Obtener el email del usuario autenticado
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email);

        // Asociar el usuario a la publicación
        publicacion.setUsuario(usuario);
        
       

        // Establecer la fecha de la publicación
        publicacion.setFechaPublicacion(LocalDateTime.now());

        // Guardar la publicación en la base de datos
        publicacionRepository.save(publicacion);

        // Agregar un mensaje de éxito para mostrar en la vista
        redirectAttributes.addFlashAttribute("mensaje", "¡Publicación creada correctamente!");

        // Redirigir al perfil del usuario después de la creación
        return "redirect:/perfil"; // Redirige al perfil
    }
    @PostMapping("/eliminar/{id}")
    public String eliminarPublicacion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Llamamos al servicio para eliminar la publicación
        String mensaje = publicacionService.eliminarPublicacion(id);

        // Añadimos el mensaje a los atributos flash para mostrarlo después de redirigir
        redirectAttributes.addFlashAttribute("mensaje", mensaje);

        // Redirigimos a la página del perfil (o la página que necesites)
        return "redirect:/perfil";
    }
    
}


