package pe.edu.utp.Sistema_adopcion.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.Adopcion;
import pe.edu.utp.Sistema_adopcion.repositories.AdopcionRepository;

@Service
public class AdopcionService{
    
    @Autowired
    private AdopcionRepository adopcionRepository;
    
    // Crear una nueva adopción
    public Adopcion crearAdopcion(Adopcion adopcion) {
        return adopcionRepository.save(adopcion);
    }

    // Leer todas las adopciones
    public List<Adopcion> obtenerTodasLasAdopciones() {
        return adopcionRepository.findAll();
    }

    // Leer una adopción específica por ID
    public Optional<Adopcion> obtenerAdopcionPorId(int id) {
        return adopcionRepository.findById(id);
    }

    // Actualizar una adopción existente
    public Adopcion actualizarAdopcion(Adopcion adopcion) {
        if (adopcionRepository.existsById(adopcion.getId())) {
            return adopcionRepository.save(adopcion);
        } else {
            throw new RuntimeException("Adopcion no encontrada");
        }
    }

    // Eliminar una adopción por ID
    public void eliminarAdopcion(int id) {
        if (adopcionRepository.existsById(id)) {
            adopcionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Adopcion no encontrada");
        }
    }
    
}
