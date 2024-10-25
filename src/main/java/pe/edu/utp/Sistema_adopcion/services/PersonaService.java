
package pe.edu.utp.Sistema_adopcion.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.Personas;
import pe.edu.utp.Sistema_adopcion.repositories.PersonasRepository;


@Service
public class PersonaService {

    @Autowired
    private PersonasRepository personasRepository;

    public Personas save(Personas persona) {
        return personasRepository.save(persona);
    }
    
    public List<Personas> findAll(){
        return personasRepository.findAll();
    }
}

