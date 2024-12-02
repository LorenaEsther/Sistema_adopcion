package pe.edu.utp.Sistema_adopcion.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.edu.utp.Sistema_adopcion.models.SolicitudAdopcion;
import pe.edu.utp.Sistema_adopcion.repositories.SolicitudAdopcionRepository;
import java.util.Optional;

class SolicitudAdopcionServiceTest {

    @Mock
    private SolicitudAdopcionRepository solicitudAdopcionRepository;

    @InjectMocks
    private SolicitudAdopcionService solicitudAdopcionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSolicitudById_Success() {
        // Configuración
        int solicitudId = 1;
        SolicitudAdopcion solicitudMock = new SolicitudAdopcion();
        solicitudMock.setId(solicitudId);
        when(solicitudAdopcionRepository.findById(solicitudId)).thenReturn(Optional.of(solicitudMock));

        // Ejecución
        SolicitudAdopcion result = solicitudAdopcionService.getSolicitudById(solicitudId);

        // Verificación
        assertNotNull(result);
        assertEquals(solicitudId, result.getId());
        verify(solicitudAdopcionRepository, times(1)).findById(solicitudId);
    }

    @Test
    void testGetSolicitudById_NotFound() {
        // Configuración
        int solicitudId = 1;
        when(solicitudAdopcionRepository.findById(solicitudId)).thenReturn(Optional.empty());

        // Ejecución y Verificación
        assertThrows(RuntimeException.class, () -> {
            solicitudAdopcionService.getSolicitudById(solicitudId);
        });
        verify(solicitudAdopcionRepository, times(1)).findById(solicitudId);
    }
}
