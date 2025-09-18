package com.eep.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Publicacion {
	
	public String getFechaPublicacionFormatted() {
		return fechaPublicacionFormatted;
	}

	public void setFechaPublicacionFormatted(String fechaPublicacionFormatted) {
		this.fechaPublicacionFormatted = fechaPublicacionFormatted;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    
    @Lob
    private String foto;

    private LocalDateTime fechaPublicacion;
    
    @Transient
    private String fechaPublicacionFormatted;

    @ManyToOne
    @JoinColumn(name = "usuario_email")
    private Usuario usuario;

    // Constructor vacío
    public Publicacion() {}

    // Constructor con todos los atributos
    public Publicacion(String descripcion, String foto, LocalDateTime fechaPublicacion,String fechaPublicacionFormatted,  Usuario usuario) {
        this.descripcion = descripcion;
        this.foto = foto;
        this.fechaPublicacion = fechaPublicacion;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
