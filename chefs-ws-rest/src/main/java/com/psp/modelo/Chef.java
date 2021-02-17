package com.psp.modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Chef {
		String id;
		String nombre;
		int anios_exp;
		HashMap<String,Especialidad>especialidades;
		
		public Chef() {
			especialidades=new HashMap<String,Especialidad>();
		}
		
		public String getId() {
			return id;
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
		public int getAnios_exp() {
			return anios_exp;
		}
		public void setAnios_exp(int anios_exp) {
			this.anios_exp = anios_exp;
		}

		public HashMap<String, Especialidad> getEspecialidades() {
			return especialidades;
		}

		public void setEspecialidades(HashMap<String, Especialidad> especialidades) {
			this.especialidades = especialidades;
		}

		
		
}
