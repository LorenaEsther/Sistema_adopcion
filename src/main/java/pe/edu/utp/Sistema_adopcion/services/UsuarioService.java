package pe.edu.utp.Sistema_adopcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.Usuarios;
import pe.edu.utp.Sistema_adopcion.repositories.UsuariosRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public void save(Usuarios usuario) {
        usuariosRepository.save(usuario);
    }

    public long count() {
        return usuariosRepository.count();
    }

    public Usuarios findByEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }
    
}
