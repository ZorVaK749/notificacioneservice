package com.perfulandia.notificacioneservice.controller;

import com.perfulandia.notificacioneservice.assembler.NotificacionModelAssembler;
import com.perfulandia.notificacioneservice.model.Notificacion;
import com.perfulandia.notificacioneservice.service.NotificacionService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService service;
    private final NotificacionModelAssembler assembler;

    public NotificacionController(NotificacionService service, NotificacionModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Notificacion>> getAll() {
        List<EntityModel<Notificacion>> notificaciones = service.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(notificaciones,
            linkTo(methodOn(NotificacionController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Notificacion> getNotificacion(@PathVariable Long id) {
        Notificacion notificacion = service.findById(id)
            .orElseThrow(() -> new RuntimeException("Notificación no encontrada: " + id));
        return assembler.toModel(notificacion);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Notificacion>> create(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = service.save(notificacion);
        return ResponseEntity
            .created(linkTo(methodOn(NotificacionController.class).getNotificacion(nuevaNotificacion.getId())).toUri())
            .body(assembler.toModel(nuevaNotificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}