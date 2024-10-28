package pe.edu.utp.Sistema_adopcion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.Sistema_adopcion.models.Adopcion;

public interface AdopcionRepository extends JpaRepository<Adopcion, Integer>{
    
}
