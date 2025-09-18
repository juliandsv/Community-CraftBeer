package com.eep.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eep.entity.Cervezas;



@Service
public interface CervezaService {
	
	 List<Cervezas> buscarPorNombreOEstilo(String query);
	 
	 Cervezas guardarCerveza(Cervezas cerveza);
	 
	 String saveImage(MultipartFile file);
   

}
