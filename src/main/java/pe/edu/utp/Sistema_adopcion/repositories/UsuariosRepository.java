package pe.edu.utp.Sistema_adopcion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.Sistema_adopcion.models.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    // Método personalizado para encontrar un usuario por su correo electrónico
    Usuarios findByEmail(String email);
}
