package pe.edu.utp.Sistema_adopcion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.Sistema_adopcion.models.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    // Aquí puedes definir métodos de consulta personalizados si es necesario
}
