package pe.edu.utp.Sistema_adopcion.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.utp.Sistema_adopcion.models.Adopcion;

public interface AdopcionRepository extends JpaRepository<Adopcion, Integer>{
    @Query("SELECT g.color, COUNT(a) FROM Adopcion a JOIN a.gatito g GROUP BY g.color")
    List<Object[]> contarAdopcionesPorColor();
}
