package com.perfulandia.notificacioneservice.service;

import com.perfulandia.notificacioneservice.model.Notificacion;
import com.perfulandia.notificacioneservice.repository.NotificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceImplTest {

    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionServiceImpl service;

    private Notificacion notificacion;

    @BeforeEach
    void setUp() {
        notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setTitulo("Nuevo Pedido");
        notificacion.setMensaje("Su pedido #123 ha sido procesado");
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(notificacion));
        List<Notificacion> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindByIdExistente() {
        when(repository.findById(1L)).thenReturn(Optional.of(notificacion));
        Optional<Notificacion> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Nuevo Pedido", result.get().getTitulo());
    }

    @Test
    void testFindByIdNoExistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        Optional<Notificacion> result = service.findById(99L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSave() {
        when(repository.save(any(Notificacion.class))).thenReturn(notificacion);
        Notificacion saved = service.save(notificacion);
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }

    @Test
    void testDeleteById() {
        doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}