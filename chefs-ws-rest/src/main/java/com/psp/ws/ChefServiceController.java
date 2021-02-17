package com.psp.ws;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psp.modelo.Chef;
import com.psp.modelo.Especialidad;
import com.psp.modelo.Plato;

/**
 * 
 * @author Paula
 * Controlador,cualquier peticion rest pasara por aqui para procesarse
 */


@RestController
public class ChefServiceController {
   private static Map<String, Chef> chefs = new HashMap<>();
   static {
	   
	  //CREACION LISTAS DE PLATOS DE LAS ESPECIALIDADES
	   HashMap<String,Plato> platos1esp1=new HashMap<String,Plato>();
	   HashMap<String,Plato> platos2esp2=new HashMap<String,Plato>();
	   HashMap<String,Plato> platos3esp3=new HashMap<String,Plato>();
	   
	   HashMap<String,Especialidad>especialidadeschef1=new HashMap<String,Especialidad>();
	   HashMap<String,Especialidad>especialidadeschef2=new HashMap<String,Especialidad>();
	   HashMap<String,Especialidad>especialidadeschef3=new HashMap<String,Especialidad>();

	 
	   
	  //CREACION DE PLATOS
	  Plato plato1=new Plato();
	  plato1.setId("p1");
	  plato1.setNombre("El costillar de cerdo a la brasa");
	  plato1.setDescripcion("sólo disponible los fines de semana, es uno de los platos estrella");
	  plato1.setPuntuacion(10);
	  platos1esp1.put(plato1.getId(),plato1);
	  
	  Plato plato2=new 	Plato();
	  plato2.setId("p2");
	  plato2.setNombre("Chuletas de Vacuno a la piedra");
	  plato2.setDescripcion("carnes rojas maduradas,marcadas a fuego vivo y atemperadas");
	  plato2.setPuntuacion(8);
	  platos1esp1.put(plato2.getId(),plato2);
	  
	  Plato plato3=new Plato();
	  plato3.setId("p3");
	  plato3.setNombre("Panna Cotta");
	  plato3.setDescripcion(" elaborado a partir de crema de leche, azúcar y gelificantes");
	  plato3.setPuntuacion(7);
	  platos3esp3.put(plato3.getId(),plato3);
	  
	  Plato plato4=new Plato();
	  plato4.setId("p4");
	  plato4.setNombre("Arroz frito tres delicias");
	  plato4.setDescripcion("arroz salteado con distintos ingredientes");
	  plato4.setPuntuacion(9);
	  platos2esp2.put(plato4.getId(),plato4);
	  
	  Plato plato5=new Plato();
	  plato5.setId("p5");
	  plato5.setNombre("POLLO GONG BAO O KUNG PAO");
	  plato5.setDescripcion("Los ingredientes principales son pollo en cubos, chile seco y cacahuates fritos. Se cocina con ajo, jengibre, aceite, frijoles, vino, fécula, salsa de soya y azúcar.");
	  plato5.setPuntuacion(9);
	  platos2esp2.put(plato5.getId(),plato5);
	  
	  //CREACION DE ESPECIALIDADES
      Especialidad especialidad1=new Especialidad();
      especialidad1.setNombre("carne a la brasa");
      especialidad1.setId("e0");
      especialidad1.setPlatos(platos1esp1);
    
      
      Especialidad especialidad2=new Especialidad();
      especialidad2.setNombre("comida china");
      especialidad2.setId("e0");
      especialidad2.setPlatos(platos2esp2);
      
      Especialidad especialidad3=new Especialidad();
      especialidad3.setNombre("Reposteria");
      especialidad3.setId("e1");
      especialidad3.setPlatos(platos3esp3);
    

      
	   //CREACION DE CHEFS
	  Chef chef = new Chef();  
      chef.setId("1");
      chef.setNombre("Ferran Adria");
      chef.setAnios_exp(12);
      especialidadeschef1.put(especialidad1.getId(),especialidad1);
      especialidadeschef1.put(especialidad3.getId(),especialidad3);
      chef.setEspecialidades(especialidadeschef1);
      chefs.put(chef.getId(), chef);
      
      Chef chef2 = new Chef();
      chef2.setId("2");
      chef2.setNombre("Elena Arzak");
      chef2.setAnios_exp(8);
      especialidadeschef2.put(especialidad2.getId(),especialidad2);
      chef2.setEspecialidades(especialidadeschef2);
      chefs.put(chef2.getId(), chef2);
      
   }
   //METODOS POST. CREAR
   /**
    * Crear chef
    * @param chef
    * @return mensaje chef creado
    */
   @PostMapping("/chefs")
    public ResponseEntity<Object> createChef(@RequestBody Chef chef) {
       chefs.put(chef.getId(), chef);
       return new ResponseEntity<>("Chef creado", HttpStatus.CREATED);
    }
   /**
    * 
    * @param id
    * @param especialidad
    * @return mensaje especialidad de chef creada
    */
   @PostMapping("/chefs/{id}/Especialidades")
   public ResponseEntity<Object> createEspecialidad(@PathVariable("id")String id,@RequestBody Especialidad especialidad) {
      chefs.get(id).getEspecialidades().put(especialidad.getId(), especialidad);
      return new ResponseEntity<>("Especialidad creada", HttpStatus.CREATED);
   }
   /**
    * 
    * @param id
    * @param idesp
    * @param plato
    * @return mensaje plato creado
    */
   
   @PostMapping("/chefs/{id}/Especialidades/{idesp}")
   public ResponseEntity<Object> createPlato(@PathVariable("id")String id,@PathVariable("idesp")String idesp,@RequestBody Plato plato) {
      chefs.get(id).getEspecialidades().get(idesp).getPlatos().put(plato.getId(), plato);
      return new ResponseEntity<>("Plato creado", HttpStatus.CREATED);
   }
    
  //METODOS GETS
   /**
    * Ver todos los chefs
    * @return Todos los chefs
    */
    @GetMapping("/chefs")
     public ResponseEntity<Object> getChef() {
        return new ResponseEntity<>(chefs.values(), HttpStatus.OK);
     }
   
   /**
    * Ver chef concreto
    * @param id
    * @return chef/id
    */
   @GetMapping("/chefs/{id}")
   public ResponseEntity<Object> getChefById(@PathVariable("id")String id){
 	  return new ResponseEntity<>(chefs.get(id),HttpStatus.OK);
   }
   /**
    * Ver especialidades de un chef concreto
    * @param id
    * @return Especialidades de un chef
    */
   @GetMapping("/chefs/{id}/Especialidades")
   public ResponseEntity<Object> getEspecialidadesDeChef(@PathVariable("id")String id){
 	  return new ResponseEntity<>(chefs.get(id).getEspecialidades(),HttpStatus.OK);
   }
   /**
    * Ver especialidad concreta de un chef
    * @param id
    * @return
    */
   @GetMapping("/chefs/{id}/Especialidades/{idesp}")
   public ResponseEntity<Object> getEspecialidadDeChef(@PathVariable("id")String id,@PathVariable("idesp")String idesp){
 	  return new ResponseEntity<>(chefs.get(id).getEspecialidades().get(idesp),HttpStatus.OK);
   }
   /**
    * Ver los platos dentro de una especialidad de un chef
    * @param id
    * @param idesp
    * @return Platos de la especialidad de un chef
    */
   @GetMapping("/chefs/{id}/Especialidades/{idesp}/Platos")
   public ResponseEntity<Object> getPlatosDeEspecialidadDeChef(@PathVariable("id")String id,@PathVariable("idesp")String idesp){
 	  return new ResponseEntity<>(chefs.get(id).getEspecialidades().get(idesp).getPlatos(),HttpStatus.OK);
 	 
   }
   //METODOS PUT. ACTUALIZACIÓN
   /**
    * Actualizar chef
    * @param id
    * @param chef
    * @return mensaje chef actualizado
    */
  @PutMapping("/chefs/{id}")
   public ResponseEntity<Object> updateChef(@PathVariable("id") String id, @RequestBody Chef chef) { 
      chefs.remove(id);
      chef.setId(id);
      chefs.put(id, chef);
      return new ResponseEntity<>("Chef actualizado", HttpStatus.OK);
   }
  
  /**
   * Actualizacion de una especialidad de un chef
   * @param id
   * @param idesp
   * @param especialidad
   * @return mensaje especialidad actualizada
   */
  @PutMapping("/chefs/{id}/Especialidades/{idesp}")
  public ResponseEntity<Object> updateEspecialidad(@PathVariable("id") String id,@PathVariable("idesp")String idesp, @RequestBody Especialidad especialidad) { 
     chefs.get(id).getEspecialidades().remove(idesp);
     chefs.get(id).getEspecialidades().put(especialidad.getId(),especialidad);
     return new ResponseEntity<>("Especialidad de chef actualizada", HttpStatus.OK);
  }
  
  /**
   * Actualizacion de un plato de una especialidad de un chef
   * @param id
   * @param idesp
   * @param idplato
   * @param plato
   * @return mensaje plato actualizado correctamente
   */
  @PutMapping("/chefs/{id}/Especialidades/{idesp}/Platos/{idplato}")
  public ResponseEntity<Object>updatePlatosDeEspecialidad(@PathVariable("id")String id,@PathVariable("idesp")String idesp,@PathVariable("idplato")String idplato, @RequestBody Plato plato){
	  chefs.get(id).getEspecialidades().get(idesp).getPlatos().remove(idplato);
	  chefs.get(id).getEspecialidades().get(idesp).getPlatos().put(plato.getId(), plato);
	  return new ResponseEntity<>("Plato de especialidad actualizado", HttpStatus.OK);
	 
  }
   
   /**
    * Borrar un chef concreto completo
    * @param id
    * @return mensaje chef borrado
    */
   @DeleteMapping("/chefs/{id}")
   public ResponseEntity<Object> deleteChef(@PathVariable("id") String id) { 
      chefs.remove(id);
      return new ResponseEntity<>("chef borrado", HttpStatus.OK);
   }

   /**
    * Borrar Todas las especialidades de un chef
    * @param id
    * @return Mensaje especialidades eliminadas
    */
   @DeleteMapping("/chefs/{id}/Especialidades")
   public ResponseEntity<Object>deleteEspecialidadDeChef(@PathVariable("id")String id){
	   chefs.get(id).getEspecialidades().clear();
	   return new ResponseEntity<>("Especialidades de chef eliminadas", HttpStatus.OK);
   }
   /**
    * Borrar especialidad concreta de un chef
    * @param id
    * @param idesp
    * @return Especialidad borrada
    */
   @DeleteMapping("/chefs/{id}/Especialidades/{idesp}")
   public ResponseEntity<Object> deleteDeEspecialidadDeChef(@PathVariable("id")String id,@PathVariable("idesp")String idesp){
 	  chefs.get(id).getEspecialidades().remove(idesp);
 	   return new ResponseEntity<>(" Especialidad eliminada", HttpStatus.OK); 	 
   } 
   
   @DeleteMapping("/chefs/{id}/Especialidades/{idesp}/Platos/{idplato}")
   public ResponseEntity<Object> deletePlatosDeEspecialidadDeChef(@PathVariable("id")String id,@PathVariable("idesp")String idesp,@PathVariable("idplato")String idplato){
 	  chefs.get(id).getEspecialidades().get(idesp).getPlatos().remove(idplato);
 	   return new ResponseEntity<>("Plato de especialidad eliminado", HttpStatus.OK); 	 
   } 
   
 
  
   


  
 
  
  
 
}