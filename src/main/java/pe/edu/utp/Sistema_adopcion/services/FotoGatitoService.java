package pe.edu.utp.Sistema_adopcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.FotoGatito;
import pe.edu.utp.Sistema_adopcion.repositories.FotoGatitoRepository;

import java.util.List;

@Service
public class FotoGatitoService {

    @Autowired
    private FotoGatitoRepository fotoGatitoRepository;

    public List<FotoGatito> findAll() {
        return fotoGatitoRepository.findAll();
    }

    public FotoGatito save(FotoGatito fotoGatito) {
        return fotoGatitoRepository.save(fotoGatito);
    }
    
    public FotoGatito obtenerFotoPorId(int id) {
        return fotoGatitoRepository.findById(id).get();
    }

    public void deleteById(int id) {
        fotoGatitoRepository.deleteById(id);
    }


}
