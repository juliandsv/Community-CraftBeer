package com.eep.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Cervezas {
	
	
	

	

		public boolean isValidada() {
		return validada;
	}
	public void setValidada(boolean validada) {
		this.validada = validada;
	}

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;  
	    
	    @Column(name = "nombre")
	    private String nombre;
	    @Column(name = "cervecera")
	    private  String cervecera;
	    @Column(name = "estilo")
	    private String estilo;
	    @Column(name = "alcohol")
	    private String alcohol;
	    @Column(name = "IBU")
	    private String IBU;
	    @Column(name = "descripcion" , columnDefinition ="TEXT" )
	    private String descripcion;
	    
	    
	    @Column(name = "imagen", columnDefinition = "TEXT")
	    private String imagen;
	    
	    private boolean validada = false;
	    
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getCervecera() {
			return cervecera;
		}
		public void setCervecera(String cervecera) {
			this.cervecera = cervecera;
		}
		public String getEstilo() {
			return estilo;
		}
		public void setEstilo(String estilo) {
			this.estilo = estilo;
		}
		public String getAlcohol() {
			return alcohol;
		}
		public void setAlcohol(String alcohol) {
			this.alcohol = alcohol;
		}
		public String getIBU() {
			return IBU;
		}
		public void setIBU(String iBU) {
			IBU = iBU;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public String getImagen() {
			return imagen;
		}
		public void setImagen(String imagen) {
			this.imagen = imagen;
		}
		public Cervezas(Long id, String nombre, String cervecera, String estilo, String alcohol, String iBU,
				String descripcion, String imagen, Boolean validada) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.cervecera = cervecera;
			this.estilo = estilo;
			this.alcohol = alcohol;
			IBU = iBU;
			this.descripcion = descripcion;
			this.imagen = imagen;
			this.validada = validada;
		}
	    
		public Cervezas() {}
		
		@Override
		public String toString() {
			return "Cervezas [id=" + id + ", nombre=" + nombre + ", cervecera=" + cervecera + ", estilo=" + estilo
					+ ", alcohol=" + alcohol + ", IBU=" + IBU + ", descripcion=" + descripcion + "]";
		}
		
		
	    
	

}
