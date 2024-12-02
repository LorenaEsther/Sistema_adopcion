package pe.edu.utp.Sistema_adopcion.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.utp.Sistema_adopcion.models.SolicitudAdopcion;

@Repository
public interface SolicitudAdopcionRepository extends JpaRepository<SolicitudAdopcion, Integer> {

    long countByEstado(SolicitudAdopcion.EstadoSolicitud estado);

    @Query("SELECT sa.estado, COUNT(sa) FROM SolicitudAdopcion sa GROUP BY sa.estado")
    List<Object[]> contarSolicitudesPorEstado();
}
