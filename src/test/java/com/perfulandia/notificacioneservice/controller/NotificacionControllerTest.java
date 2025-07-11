package com.perfulandia.notificacioneservice.controller;

import com.perfulandia.notificacioneservice.assembler.NotificacionModelAssembler;
import com.perfulandia.notificacioneservice.model.Notificacion;
import com.perfulandia.notificacioneservice.service.NotificacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificacionController.class)
class NotificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private NotificacionService service;

    @SuppressWarnings("removal")
    @MockBean
    private NotificacionModelAssembler assembler;

    private final Notificacion notificacion = new Notificacion(1L, "Título", "Mensaje");

    @Test
    void getAllNotificaciones() throws Exception {
        EntityModel<Notificacion> entityModel = EntityModel.of(notificacion);
        when(service.findAll()).thenReturn(List.of(notificacion));
        when(assembler.toModel(any(Notificacion.class))).thenReturn(entityModel);
        
        mockMvc.perform(get("/api/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href").exists());
    }

    @Test
    void getNotificacionById() throws Exception {
        EntityModel<Notificacion> entityModel = EntityModel.of(notificacion);
        when(service.findById(1L)).thenReturn(Optional.of(notificacion));
        when(assembler.toModel(any(Notificacion.class))).thenReturn(entityModel);
        
        mockMvc.perform(get("/api/notificaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Título"));
    }

    @Test
    void createNotificacion() throws Exception {
        EntityModel<Notificacion> entityModel = EntityModel.of(notificacion);
        when(service.save(any(Notificacion.class))).thenReturn(notificacion);
        when(assembler.toModel(any(Notificacion.class))).thenReturn(entityModel);
        
        mockMvc.perform(post("/api/notificaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titulo\":\"Título\",\"mensaje\":\"Mensaje\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Título"));
    }

    @Test
    void deleteNotificacion() throws Exception {
        mockMvc.perform(delete("/api/notificaciones/1"))
                .andExpect(status().isNoContent());
    }
}