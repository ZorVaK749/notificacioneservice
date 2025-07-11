package com.perfulandia.notificacioneservice.service;

import com.perfulandia.notificacioneservice.model.Notificacion;
import java.util.List;
import java.util.Optional;

public interface NotificacionService {
    List<Notificacion> findAll();
    Optional<Notificacion> findById(Long id);
    Notificacion save(Notificacion notificacion);
    void deleteById(Long id);
}