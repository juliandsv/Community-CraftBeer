package com.eep.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eep.entity.Cervezas;
import com.eep.entity.Usuario;
import com.eep.repositories.UsuarioRepository;
import com.eep.service.CervezaService;
import com.eep.service.UsuarioService;


@Controller
@RequestMapping
public class CervezaController {
	
	@Autowired 
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private  CervezaService cervezaService;
	
	
	 @GetMapping("/inicio")
	    public String inicio(Model model) {
	        // Puedes añadir atributos al modelo si es necesario
	        model.addAttribute("mensaje", "Bienvenido a la página de inicio");
	        return "inicio";  
	    }
	   
	    private Usuario obtenerUsuario() {
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        
	        if (principal instanceof UserDetails) {
	            String email = ((UserDetails) principal).getUsername();
	            // Ahora, puedes usar el email para obtener tu usuario de la base de datos
	            return usuarioRepository.findById(email).orElse(null);
	        } else {
	            // Maneja el caso en que el principal no sea una instancia de UserDetails
	            return null;
	        }
	    }

	    @GetMapping("/buscar")
	    public String buscar(Model model) {
	        Usuario usuario = obtenerUsuario();  // Obtén el usuario desde la base de datos o sesión
	        model.addAttribute("usuario", usuario);
	        return "buscar";
	    }
	    
	    @GetMapping("/cervezas")
	    public ResponseEntity<List<Cervezas>> buscarCervezas(@RequestParam("query") String query) {
	        List<Cervezas> cervezas = cervezaService.buscarPorNombreOEstilo(query);
	        
	        return ResponseEntity.ok(cervezas);
	    }
	    
	    @GetMapping("/nuevaCerveza")
	    public String nuevaCervezaForm(Model model) {
	        model.addAttribute("cerveza", new Cervezas());
	        return "nuevaCerveza";  // Este es el nombre de la vista, puedes ajustarlo según el nombre de tu archivo HTML
	    }
	    
	    @PostMapping("/nuevaCerveza")
	    public String agregarCerveza(@RequestParam("nombre") String nombre,
	                                 @RequestParam("cervecera") String cervecera,
	                                 @RequestParam("estilo") String estilo,
	                                 @RequestParam("alcohol") String alcohol,
	                                 @RequestParam("IBU") String IBU,
	                                 @RequestParam("descripcion") String descripcion,
	                                 @RequestParam("imagen") MultipartFile imagen,
	                                 Model model) throws IOException {

	        Cervezas cerveza = new Cervezas();
	        cerveza.setNombre(nombre);
	        cerveza.setCervecera(cervecera);
	        cerveza.setEstilo(estilo);
	        cerveza.setAlcohol(alcohol);
	        cerveza.setIBU(IBU);
	        cerveza.setDescripcion(descripcion);

	        if (imagen != null && !imagen.isEmpty()) {
	            // Usamos el método saveImage del servicio para convertir la imagen y añadir prefijo
	            String base64 = cervezaService.saveImage(imagen);
	            cerveza.setImagen(base64);
	        } else {
	            cerveza.setImagen(null); // O una imagen por defecto si quieres
	        }

	        cervezaService.guardarCerveza(cerveza);
	        model.addAttribute("mensaje", "Cerveza agregada correctamente");

	        return "nuevaCerveza";
	    }
	    
	    

}
