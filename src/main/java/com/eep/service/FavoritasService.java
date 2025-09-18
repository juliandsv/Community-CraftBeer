package com.eep.service;

import java.util.List;

import com.eep.entity.Cervezas;
import com.eep.entity.Favoritas;

public interface FavoritasService {
	
	void agregarAFavoritas(String email, Long cervezaId);
	
	List<Favoritas> listarFavoritas(String email);
	
	void eliminarDeFavoritas(Long id);

}
