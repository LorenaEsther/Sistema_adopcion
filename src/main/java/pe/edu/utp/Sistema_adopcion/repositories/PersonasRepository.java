package pe.edu.utp.Sistema_adopcion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.Sistema_adopcion.models.Personas;

@Repository
public interface PersonasRepository extends JpaRepository<Personas, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario, pero no uno para email
}
