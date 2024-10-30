package pe.edu.utp.Sistema_adopcion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.Sistema_adopcion.models.Voluntarios;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntarios, Integer> {
}