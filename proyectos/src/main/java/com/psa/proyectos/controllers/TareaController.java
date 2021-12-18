package com.psa.proyectos.controllers;

import com.psa.proyectos.models.ProyectoModel;
import com.psa.proyectos.models.TareaModel;
import com.psa.proyectos.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "tareas")
@CrossOrigin("*")
public class TareaController {
    @Autowired
    TareaService tareaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<TareaModel> obtenerTareas() {
        return tareaService.obtenerTareas();
    }

    @PostMapping
    public  ResponseEntity<?>  guardarTarea(@RequestBody TareaModel tarea){
        Map<String,Object> response = new HashMap<>();
        try {
            //tareaService.guardarTarea(tarea);
            //en el put esta devolviendo el json que retorna el servicio
        response.put("Mensaje",tareaService.guardarTarea(tarea));

        }catch (DataAccessException e) {
            response.put("Mensaje", "No se pudo guardar la tarea");
            response.put("Error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<?> obtenerTareasPorID(@PathVariable("id") Long id){
        //return this.tareaService.obtenerPorId(id);
        Map<String,Object> response = new HashMap<>();
        Optional<TareaModel> tareaServices;
        tareaServices = tareaService.obtenerPorId(id);

        if (tareaServices.isPresent()){
                response.put("Mensaje",tareaServices);
            }else{
                response.put("Error", "No se pudo encontrar la tarea con id: "+id);
                // response.put("Error:", e.getMostSpecificCause().getMessage());
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

    }

    @PutMapping(path ="/{id}")
    public ResponseEntity<?> actualizarTareaPorId(@RequestBody TareaModel tarea,@PathVariable("id") Long id){

        Map<String,Object> response = new HashMap<>();
        try {
            boolean ok = this.tareaService.actualizarTareaPorId(tarea,id);
            if (ok) {
                response.put("Mensaje","Se actualizo la tarea con id: "+id);
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
            }else{
                response.put("Mensaje", "No se pudo actualizar la tarea. No existe tarea con id: : "+id);
               // response.put("Error:", e.getMostSpecificCause().getMessage());
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e) {
            response.put("Mensaje", "Ocurrio un error al actualizar la tarea con id: " + id);
            response.put("Error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminarPorID(@PathVariable("id") Long id)
    {
        Map<String,Object> response = new HashMap<>();
        try {
            tareaService.eliminarTarea(id);
            response.put("Mensaje","Se elimino la tarea con id: "+id);

        }catch (DataAccessException e) {
            response.put("Mensaje", "No se pudo eliminar la tarea con id: " + id);
            response.put("Error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);


    }

   // @GetMapping(path = "/query")
   // public ArrayList<TareaModel> obtenerTareasPorNombre(@RequestParam("nombre") String nombre){

   //     return this.tareaService.obtenerPorNombre(nombre);
   // }

    @GetMapping(path = "/query")
    //@RequestParam(value = "id_proyecto", required = false) Long id_proyecto,
    public ArrayList<TareaModel> obtenerPorParametros(@RequestParam(value = "nombre", required = false) String nombre,
                                                      @RequestParam(value = "descripcion", required = false) String descripcion)
    {

        //return this.tareaService.obtenerPorIdProyecto(id_proyecto);
        return this.tareaService.obtenerPorNombreODescripcion(nombre,descripcion);

    }
    //@RequestParam(value = "id_proyecto", required = false) Long id_proyecto,
    //@GetMapping(path = "/proyecto/{id_proyecto}")
    public ResponseEntity<?> obtenerTareasPorProyectos(@PathVariable("id_proyecto") Long id_proyecto)
    {
        Map<String, Object> response = new HashMap<>();
        List<TareaModel> tareaServices;
        try {
            tareaServices = tareaService.obtenerPorIdProyecto(id_proyecto);
            if (!tareaServices.isEmpty() ) {
                response.put("Mensaje", tareaServices);
            } else {
                response.put("Error", "No se pudo recuperar las tarea para el Proyecto con id: " + id_proyecto);
                // response.put("Error:", e.getMostSpecificCause().getMessage());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){

            response.put("Mensaje", "No se pudo recuperar el proyecto con id: " + id_proyecto);
            response.put("Error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> obtenerTareasPorIds(@RequestParam(value = "ids") Collection<Long> ids)
    {
        Map<String, Object> response = new HashMap<>();
        ArrayList<TareaModel> tareaServices;
        try {
            tareaServices = tareaService.obtenerTareasPorIds(ids);
            if (!tareaServices.isEmpty() ) {
                response.put("Mensaje", tareaServices);
            } else {
                response.put("Error", "No se pudo recuperar algunas de las tareas para los ids: " + ids);
                // response.put("Error:", e.getMostSpecificCause().getMessage());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }catch (DataAccessException e){

            response.put("Mensaje", "No se pudo recuperar el proyecto con id: " + ids);
            response.put("Error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //@RequestMapping(value="/params", method = RequestMethod.GET)
    public ResponseEntity getParams(@RequestParam Map<String, String> params ) {

        System.out.println(params.keySet());
        System.out.println(params.values());

        Collection<String> texto  = params.values();
        return new ResponseEntity<String>((MultiValueMap<String, String>) texto, HttpStatus.OK);
    }

}
