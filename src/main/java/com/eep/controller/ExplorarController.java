package com.eep.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eep.entity.Usuario;
import com.eep.service.UsuarioService;

@Controller
@RequestMapping("/explorar")
public class ExplorarController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostrarUsuarios(Model model) {
        // Obtiene la lista de usuarios y la pasa al modelo para ser usada en la vista
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "explorar"; // Nombre de la vista explorar.html
    }
}