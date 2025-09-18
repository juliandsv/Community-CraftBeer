package com.eep.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Favoritas {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "usuario_email", referencedColumnName = "email")
	    private Usuario usuario;
	    
	    @ManyToOne
	    @JoinColumn(name = "cerveza_id", referencedColumnName = "id")
	    private Cervezas cerveza;
	    
	    @Column(nullable = false)
	    private int valoracion;
	    
	    public Favoritas() {}

	    public Favoritas( Usuario usuario, Cervezas cerveza, int valoracion) {
	       
	        this.usuario = usuario;
	        this.cerveza = cerveza;
	        this.valoracion = valoracion;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Usuario getUsuario() {
	        return usuario;
	    }

	    public void setUsuario(Usuario usuario) {
	        this.usuario = usuario;
	    }

	    public Cervezas getCerveza() {
	        return cerveza;
	    }

	    public void setCerveza(Cervezas cerveza) {
	        this.cerveza = cerveza;
	    }
	    public int getValoracion() {
	        return valoracion;
	    }

	    public void setValoracion(int valoracion) {
	        this.valoracion = valoracion;
	    }

	    @Override
	    public String toString() {
	        return "Favoritas [id=" + id + ", usuario=" + usuario.getEmail() + ", cerveza=" + cerveza.getNombre() + "]";

}
}
