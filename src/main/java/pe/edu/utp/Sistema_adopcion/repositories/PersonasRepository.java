package pe.edu.utp.Sistema_adopcion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.Sistema_adopcion.models.Personas;

@Repository
public interface PersonasRepository extends JpaRepository<Personas, Long> {

    // Método personalizado para encontrar una persona por su correo electrónico
    Personas findByEmail(String email);
}
