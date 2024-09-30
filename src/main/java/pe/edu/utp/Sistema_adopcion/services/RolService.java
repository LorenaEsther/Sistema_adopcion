package pe.edu.utp.Sistema_adopcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.Rol;
import pe.edu.utp.Sistema_adopcion.repositories.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol findById(int id) {
        return rolRepository.findById(id).orElse(null); // Agregar manejo de errores si es necesario
    }
}
