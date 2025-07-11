package com.perfulandia.notificacioneservice.repository;

import com.perfulandia.notificacioneservice.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}