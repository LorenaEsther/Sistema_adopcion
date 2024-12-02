package pe.edu.utp.Sistema_adopcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.Gatito;
import pe.edu.utp.Sistema_adopcion.repositories.GatitoRepository;

import java.util.List;
import pe.edu.utp.Sistema_adopcion.models.FotoGatito;

@Service
public class GatitoService {

    @Autowired
    private GatitoRepository gatitoRepository;

    @Autowired
    private FotoGatitoService fotoGatitoService;

    public List<Gatito> findAll() {
        return gatitoRepository.findAll();
    }
    
    public List<Gatito> findAllDisponibles() {
        return gatitoRepository.findByEstado(Gatito.EstadoGatito.DISPONIBLE);
    }

    public Gatito save(Gatito gatito) {
        // Guardar el gatito primero
        Gatito savedGatito = gatitoRepository.save(gatito);

        // Si tiene fotos, guardarlas también
        if (gatito.getFotos() != null && !gatito.getFotos().isEmpty()) {
            for (FotoGatito foto : gatito.getFotos()) {
                foto.setGatito(savedGatito);  // Asociar cada foto con el gatito guardado
                fotoGatitoService.save(foto);  // Guardar la foto
            }
        }

        return savedGatito;
    }

    public void deleteById(int id) {
        gatitoRepository.deleteById(id);
    }

    public Gatito obtenerGatitoPorId(int id) {
        return gatitoRepository.findById(id).get();
    }

    // Método para actualizar el gatito
    public Gatito updateGatito(Gatito gatito) {
        if (gatitoRepository.existsById(gatito.getId())) {
            // Al ser el mismo método `save` de JPA, si el gatito ya existe, lo actualiza.
            return gatitoRepository.save(gatito);
        } else {
            throw new RuntimeException("Gatito no encontrado");
        }
    }
    
    public long contarGatosDisponibles() {
        return gatitoRepository.countByEstado(Gatito.EstadoGatito.DISPONIBLE);
    }
}
