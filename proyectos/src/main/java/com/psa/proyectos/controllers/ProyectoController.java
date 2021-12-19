package com.psa.proyectos.controllers;


import com.psa.proyectos.models.ProyectoModel;
import com.psa.proyectos.models.TareaModel;
import com.psa.proyectos.services.ProyectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@RestController
@RequestMapping(value = "proyectos")
@CrossOrigin("*")

public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @Operation(summary = "Get Projects",
            description = "Consulta para recuperar proyectos", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProyectoModel.class)), responseCode = "200")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<ProyectoModel> obtenerProyectos(){
        return proyectoService.obtenerProyectos();
    }

    @Operation(summary = "Post Projects",
            description = "Grabar Proyecto", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProyectoModel.class)), responseCode = "201"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    , responseCode = "404", description = "No se pudo grabar el proyecto")
    })
    @PostMapping
    public ResponseEntity<?> guardarProyecto(@RequestBody ProyectoModel proyecto) throws URISyntaxException{
        Map<String,Object> response = new HashMap<>();

        try {
            ProyectoModel proyectoServices = proyectoService.guardarProyecto(proyecto);

           return ResponseEntity.created(new URI("/proyectos/" + proyectoServices.getIdProyecto()))
                    .body(proyectoServices);
        }catch (DataAccessException e) {
            response.put("message", "No se pudo guardar el proyecto");
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("status",HttpStatus.UNPROCESSABLE_ENTITY.value());
            response.put("timestamp", new Date());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
    }
    //@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Projects by Id",
            description = "Consulta para recuperar proyectos por Id", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProyectoModel.class)), responseCode = "200"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    ,responseCode = "404", description = "No se pudo recuperar el proyecto")})
    @GetMapping (path = "/{id}")
    public ResponseEntity <?> obtenerProyectosPorID(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        Optional<ProyectoModel> proyectoServices = proyectoService.obtenerPorId(id);

        if (!proyectoServices.isPresent()){
            response.put("message",proyectoServices);
        }else{
            response.put("message", "No se pudo encontrar el proyecto con id: "+id);
            response.put("error", "Ocurrio un error al actualizar el proyecto con id: " + id);
            response.put("status",HttpStatus.NOT_FOUND.value());
            response.put("timestamp", new Date());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(proyectoServices);

    }

    @Operation(summary = "Put Projects",
            description = "Actualizar Proyecto", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProyectoModel.class)), responseCode = "200"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    ,description = "No se encontro el proyecto")
    })
    @PutMapping(path ="/{id}")
    public ResponseEntity<?> actualizarProyecto(@RequestBody ProyectoModel proyecto, @PathVariable("id") Long id){
        Map<String,Object> response = new HashMap<>();
        try {
            boolean ok = this.proyectoService.actualizarProyectoPorId(proyecto,id);
            if (ok) {
                response.put( "Mensaje","Se actualizo el proyecto con id: "+id);
                return ResponseEntity.ok().build();
            }else{
                response.put("error", "No se pudo actualizar el proyecto. No existe proyecto con id: "+id);
                // response.put("Error:", e.getMostSpecificCause().getMessage());
                response.put("status",HttpStatus.NOT_FOUND.value());
                response.put("timestamp", new Date());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (DataAccessException e) {
            response.put("message", "Ocurrio un error al actualizar el proyecto con id: " + id);
            response.put("error:", e.getMostSpecificCause().getMessage());
            response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("timestamp", new Date());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @Operation(summary = "Delete Projects",
            description = "Eliminar Proyecto", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProyectoModel.class)), responseCode = "200"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    ,responseCode = "404", description = "No se encontro el proyecto")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminarPorID(@PathVariable("id") Long id)
    {
        Map<String,Object> response = new HashMap<>();
        try {
            proyectoService.eliminarProyecto(id);
            response.put("message","Se elimino el proyecto con id: "+id);

        }catch (DataAccessException e) {
            response.put("message", "No se pudo eliminar el proyecto con id: " + id);
            response.put("error:", e.getMostSpecificCause().getMessage());
            response.put("status",HttpStatus.NOT_FOUND.value());
            response.put("timestamp", new Date());
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/query")
    public ArrayList<ProyectoModel> obtenerProyectoPorNombre(@RequestParam("nombre") String nombre
                                                            ,@RequestParam("descripcion") String descripcion){
        return this.proyectoService.obtenerPorNombreODescripcion(nombre,descripcion);
    }

    //@GetMapping(path = "/{id_proyecto}/tareas")
    public ArrayList<TareaModel> obtenerPorParametros(@PathVariable("id_proyecto") Long id_proyecto)
    {
        return this.proyectoService.obtenerPorIdProyecto(id_proyecto);
    }

}
