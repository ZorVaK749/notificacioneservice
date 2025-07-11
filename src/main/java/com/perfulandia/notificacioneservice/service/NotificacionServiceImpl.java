package com.perfulandia.notificacioneservice.service;

import com.perfulandia.notificacioneservice.model.Notificacion;
import com.perfulandia.notificacioneservice.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {
    private final NotificacionRepository repository;
    
    @Override
    public List<Notificacion> findAll() {
        return repository.findAll();
    }
    
    @Override
    public Optional<Notificacion> findById(Long id) {
        return repository.findById(id);
    }
    
    @Override
    public Notificacion save(Notificacion notificacion) {
        return repository.save(notificacion);
    }
    
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}