package pe.edu.utp.Sistema_adopcion.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.Sistema_adopcion.models.Gatito;

public interface GatitoRepository extends JpaRepository<Gatito, Integer> {
    List<Gatito> findByEstado(Gatito.EstadoGatito estado);
}
