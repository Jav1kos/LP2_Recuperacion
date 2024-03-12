package com.cibertec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_ciudad")
public class ciudad {
	
	//Campos o Atributos
	@Id
	private String codigo_postal;
	private String nombre;
	
	//Propiedades de Lectura y Escritura
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "ciudad [codigo_postal=" + codigo_postal +
				", nombre=" + nombre + "]";
	}
}