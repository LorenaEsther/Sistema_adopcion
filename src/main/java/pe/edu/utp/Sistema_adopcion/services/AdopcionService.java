package pe.edu.utp.Sistema_adopcion.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public long contarTotalAdopciones() {
        return adopcionRepository.count();
    }
    
    public Map<String, Long> contarPreferenciasPorColor() {
        List<Object[]> resultados = adopcionRepository.contarAdopcionesPorColor();
        Map<String, Long> preferenciasPorColor = new HashMap<>();

        for (Object[] resultado : resultados) {
            String color = (String) resultado[0];
            Long cantidad = (Long) resultado[1];
            preferenciasPorColor.put(color, cantidad);
        }

        return preferenciasPorColor;
    }
}
