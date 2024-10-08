package pe.edu.utp.Sistema_adopcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.HistorialMedico;
import pe.edu.utp.Sistema_adopcion.repositories.HistorialMedicoRepository;

import java.util.List;

@Service
public class HistorialMedicoService {

    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    public List<HistorialMedico> findAll() {
        return historialMedicoRepository.findAll();
    }

    public HistorialMedico save(HistorialMedico historialMedico) {
        return historialMedicoRepository.save(historialMedico);
    }

    public void deleteById(int id) {
        historialMedicoRepository.deleteById(id);
    }

    // Additional methods as needed
}
