package pe.edu.utp.Sistema_adopcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.Gatito;
import pe.edu.utp.Sistema_adopcion.repositories.GatitoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GatitoService {

    @Autowired
    private GatitoRepository gatitoRepository;

    public List<Gatito> findAll() {
        return gatitoRepository.findAll();
    }

    public Gatito save(Gatito gatito) {
        return gatitoRepository.save(gatito);
    }

    public void deleteById(int id) {
        gatitoRepository.deleteById(id);
    }

    public Optional<Gatito> obtenerGatitoPorId(int id) {
        return gatitoRepository.findById(id);
    }

    // Additional methods as needed
}
