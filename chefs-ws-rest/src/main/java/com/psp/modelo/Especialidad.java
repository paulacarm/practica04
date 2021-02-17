package com.psp.modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Especialidad {
	
	String id;
	String nombre;
	HashMap<String,Plato>platos;

	public Especialidad() {
		this.platos=new HashMap<String,Plato>();
	}

	public String getId() {
		return id;
	}
	public HashMap<String, Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(HashMap<String, Plato> platos) {
		this.platos = platos;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
