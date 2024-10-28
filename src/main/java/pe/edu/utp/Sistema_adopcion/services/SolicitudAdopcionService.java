package pe.edu.utp.Sistema_adopcion.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.SolicitudAdopcion;
import pe.edu.utp.Sistema_adopcion.repositories.SolicitudAdopcionRepository;

@Service
public class SolicitudAdopcionService {

    @Autowired
    private SolicitudAdopcionRepository solicitudAdopcionRepository;

    // Crear una nueva solicitud de adopción
    public SolicitudAdopcion saveSolicitud(SolicitudAdopcion solicitud) {
        return solicitudAdopcionRepository.save(solicitud);
    }

    // Leer todas las solicitudes de adopción
    public List<SolicitudAdopcion> getAllSolicitudes() {
        return solicitudAdopcionRepository.findAll();
    }

    // Leer una solicitud por su ID
    public SolicitudAdopcion getSolicitudById(int id) {
        return solicitudAdopcionRepository.findById(id).get();
    }

    // Actualizar una solicitud de adopción
    public SolicitudAdopcion updateSolicitud(SolicitudAdopcion solicitud) {

        if (solicitudAdopcionRepository.existsById(solicitud.getId())) {
            return solicitudAdopcionRepository.save(solicitud);
        } else {
            throw new RuntimeException("SolicitudAdopcion no encontrado");
        }
    }

    public SolicitudAdopcion updateEstadoSolicitud(int id, SolicitudAdopcion.EstadoSolicitud nuevoEstado) {
        // Verifica si la solicitud existe
        if (solicitudAdopcionRepository.existsById(id)) {
            // Obtiene la solicitud actual
            SolicitudAdopcion solicitudExistente = solicitudAdopcionRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("SolicitudAdopcion no encontrada"));

            // Actualiza solo el campo 'estado'
            solicitudExistente.setEstado(nuevoEstado);

            // Guarda la solicitud actualizada y la retorna
            return solicitudAdopcionRepository.save(solicitudExistente);
        } else {
            throw new RuntimeException("SolicitudAdopcion no encontrada");
        }
    }

}
