package com.perfulandia.notificacioneservice.assembler;

import com.perfulandia.notificacioneservice.controller.NotificacionController;
import com.perfulandia.notificacioneservice.model.Notificacion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class NotificacionModelAssembler implements RepresentationModelAssembler<Notificacion, EntityModel<Notificacion>> {
    @Override
    public EntityModel<Notificacion> toModel(Notificacion notificacion) {
        return EntityModel.of(notificacion,
            linkTo(methodOn(NotificacionController.class).getNotificacion(notificacion.getId())).withSelfRel(),
            linkTo(methodOn(NotificacionController.class).getAll()).withRel("notificaciones"));
    }
}