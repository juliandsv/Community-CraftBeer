package com.eep.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eep.entity.Cervezas;
import com.eep.repositories.CervezaRepository;
@Service
public class CervezaServiceImp implements CervezaService {
	
	@Autowired
	private CervezaRepository cervezaRepository;

	@Override
	public List<Cervezas> buscarPorNombreOEstilo(String query) {
		// TODO Auto-generated method stub
		   return cervezaRepository.findByNombreContainingOrEstiloContaining(query, query);
	}

	@Override
	public Cervezas guardarCerveza(Cervezas cerveza) {
		// TODO Auto-generated method stub
		return cervezaRepository.save(cerveza);
	}
	@Override
	public String saveImage(MultipartFile file) {
		  // Verificamos si el archivo no es nulo ni vacío
	    if (file != null && !file.isEmpty()) {
	        // Convertimos el archivo a un string base64
	        try {
				return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return null;  
	}


	

}
