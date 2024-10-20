package pe.edu.utp.Sistema_adopcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.Voluntarios;
import pe.edu.utp.Sistema_adopcion.repositories.VoluntarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    // Guardar un voluntario en la base de datos
    public Voluntarios guardarVoluntario(Voluntarios voluntario) {
        return voluntarioRepository.save(voluntario);
    }

    // Obtener todos los voluntarios
    public List<Voluntarios> obtenerTodosLosVoluntarios() {
        return voluntarioRepository.findAll();
    }

    // Obtener un voluntario por su ID
    public Optional<Voluntarios> obtenerVoluntarioPorId(int id) {
        return voluntarioRepository.findById(id);
    }

    // Eliminar un voluntario por su ID
    public void eliminarVoluntario(int id) {
        voluntarioRepository.deleteById(id);
    }
}
