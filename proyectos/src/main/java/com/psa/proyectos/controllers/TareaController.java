package com.psa.proyectos.controllers;

import com.psa.proyectos.models.ProyectoModel;
import com.psa.proyectos.models.TareaModel;
import com.psa.proyectos.services.TareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Min;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping(value = "tareas")
@CrossOrigin("*")
public class TareaController {
    @Autowired
    TareaService tareaService;


    @Operation(summary = "Get Tasks",
            description = "Consulta para recuperar tareas", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = TareaModel.class)), responseCode = "200")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<TareaModel> obtenerTareas() {
        return tareaService.obtenerTareas();
    }

    @Operation(summary = "Post Tasks",
            description = "Grabar tarea", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = TareaModel.class)), responseCode = "201"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"

            )), responseCode = "404", description = "No se pudo grabar la tarea"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"
                    )),responseCode = "422", description = "El idProyecto debe existir"),
    })
    @PostMapping
    public  ResponseEntity<?>  guardarTarea(@RequestBody TareaModel tarea) throws URISyntaxException {
        Map<String,Object> response = new HashMap<>();
        try {
            TareaModel tareaServices = tareaService.guardarTarea(tarea);
            return ResponseEntity.created(new URI("/tareas/" + tareaServices.getIdTarea()))
                    .body(tareaServices);
        }catch (DataAccessException e) {

            response.put("message", "El idProyecto debe existir");
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("status",HttpStatus.UNPROCESSABLE_ENTITY.value());
            response.put("timestamp", new Date());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

    }

    //@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@Operation(summary = "Get Tasks by Id",
    //        description = "Consulta para recuperar tareas por Id", responses = {
    //        @ApiResponse(content = @Content(schema = @Schema(implementation = TareaModel.class)), responseCode = "200"),
    //        @ApiResponse(responseCode = "404", description = "No se pudo recuperar la tarea")})
    //@GetMapping (path = "/{id}")
    //pasa a metodo que sea por array
    public ResponseEntity<?> obtenerTareasPorID(@PathVariable("id") Long id){
        Map<String,Object> response = new HashMap<>();
        Optional<TareaModel> tareaServices;
        tareaServices = tareaService.obtenerPorId(id);

        if (tareaServices.isPresent()){
                response.put("message",tareaServices);
            }else{
                response.put("error", "No se pudo encontrar la tarea con id: "+id);
                // response.put("error:", e.getMostSpecificCause().getMessage());
                response.put("status",HttpStatus.NOT_FOUND.value());
                response.put("timestamp", new Date());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        return ResponseEntity.ok(tareaServices);

    }

    @Operation(summary = "Put Tasks",
            description = "Actualizar Tarea", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name="example",value = "{message: message description}"))
                            , responseCode = "200"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                        examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    ,responseCode = "404", description = "No se encontro la tarea"),
    })
    @PutMapping(path ="/{id}")
    public ResponseEntity<?> actualizarTareaPorId(@RequestBody TareaModel tarea,@PathVariable("id") Long id){

        Map<String,Object> response = new HashMap<>();
        try {

            boolean ok = this.tareaService.actualizarTareaPorId(tarea,id);
            if (ok) {
                response.put("message","Se actualizo la tarea con id: "+id);
                return ResponseEntity.ok().build();
            }else{
                response.put("timestamp", new Date());
                response.put("message", "No se pudo actualizar la tarea. No existe tarea con id: : "+id);
                response.put("status",HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        }catch (DataAccessException e) {
            response.put("timestamp", new Date());
            response.put("message", "Ocurrio un error al actualizar la tarea con id: " + id);
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @Operation(summary = "Delete Tasks",
            description = "Eliminar Tarea", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = TareaModel.class)), responseCode = "200"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    ,responseCode = "404", description = "No se encontro la tarea")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminarPorID(@PathVariable("id") Long id)
    {
        Map<String,Object> response = new HashMap<>();
        try {
            tareaService.eliminarTarea(id);
            response.put("message","Se elimino la tarea con id: "+id);

        }catch (DataAccessException e) {
            response.put("message", "No se pudo eliminar la tarea con id: " + id);
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("status",HttpStatus.NOT_FOUND.value());
            response.put("timestamp", new Date());
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok().build();


    }

    @GetMapping(path = "/query")
    //@RequestParam(value = "id_proyecto", required = false) Long id_proyecto,
    public ArrayList<TareaModel> obtenerPorParametros(@RequestParam(value = "nombre", required = false) String nombre,
                                                      @RequestParam(value = "descripcion", required = false) String descripcion)
    {
        return this.tareaService.obtenerPorNombreODescripcion(nombre,descripcion);

    }
    @Operation(summary = "Get Tasks by Project ID",
               description = "Consulta para encontrar las tareas asociadas a un Id Proyecto", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = TareaModel.class)), responseCode = "200"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                     examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    ,responseCode = "404", description = "No se encontraron tareas asociadas al Proyecto con id") ,
            @ApiResponse(content = @Content(mediaType = "application/json"
                     ,examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    ,responseCode = "500", description = "No se pudo recuperar el proyecto con id:")
    })
    @GetMapping(path = "/proyecto/{id_proyecto}")
    public ResponseEntity<?> obtenerTareasPorProyectos(@PathVariable("id_proyecto") Long id_proyecto)
    {
        Map<String, Object> response = new HashMap<>();
        List<TareaModel> tareaServices;
        try {
            tareaServices = tareaService.obtenerPorIdProyecto(id_proyecto);
            if (tareaServices.isEmpty() ) {
                response.put("error", "No se encontraron tareas asociadas al Proyecto con id: " + id_proyecto);
                response.put("status",HttpStatus.NOT_FOUND.value());
                response.put("timestamp", new Date());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(tareaServices);
        }catch (DataAccessException e){
            response.put("message", "No se pudo recuperar el proyecto con id: " + id_proyecto);
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("timestamp", new Date());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Get Tasks by Id",
            description = "Consulta para recuperar tareas por su Id", responses = {
            @ApiResponse(content = @Content(mediaType = "application/json",schema = @Schema(implementation = TareaModel.class)), responseCode = "200"),
            @ApiResponse(content = @Content(mediaType = "application/json",
                     examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    ,responseCode = "404", description = "No se encontro algunas de las tareas para los ids:"),
            @ApiResponse(content = @Content(mediaType = "application/json"
                    ,examples = @ExampleObject(name="example",value = "{message: message description, error: error description, status: status defined, timestamp: date}"))
                    ,responseCode = "500", description = "Ocurrio un error no definido")

    })
    @GetMapping(path = "/")
    public ResponseEntity<?> obtenerTareasPorIds(@RequestParam(value = "ids") Collection<Long> ids)
    {
        Map<String, Object> response = new HashMap<>();
        ArrayList<TareaModel> tareaServices;
        try {
            tareaServices = tareaService.obtenerTareasPorIds(ids);
            if (tareaServices.isEmpty() ) {
                response.put("error", "No se encontro algunas de las tareas para los ids: " + ids);
                response.put("status",HttpStatus.NOT_FOUND.value());
                response.put("timestamp", new Date());
                // response.put("error:", e.getMostSpecificCause().getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(tareaServices);
        }catch (DataAccessException e){

            response.put("message", "Ocurrio un error no definido para id: " + ids);
            response.put("error", e.getMostSpecificCause().getMessage());
            response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("timestamp", new Date());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }


    //@RequestMapping(value="/params", method = RequestMethod.GET)
    public ResponseEntity getParams(@RequestParam Map<String, String> params ) {

        System.out.println(params.keySet());
        System.out.println(params.values());
        Map<String, Object> response = new HashMap<>();
        response.put("keySet", params.keySet());
         response.put("values:",params.values());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
