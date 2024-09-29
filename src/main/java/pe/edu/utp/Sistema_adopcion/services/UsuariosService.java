package pe.edu.utp.Sistema_adopcion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.utp.Sistema_adopcion.models.Personas;
import pe.edu.utp.Sistema_adopcion.models.Usuarios;
import pe.edu.utp.Sistema_adopcion.repositories.PersonasRepository;
import pe.edu.utp.Sistema_adopcion.repositories.UsuariosRepository;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class UsuariosService {

    private static final Logger logger = Logger.getLogger(UsuariosService.class.getName());

    @Autowired
    private PersonasRepository personasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para registrar un usuario
    public void registrarUsuario(Personas persona) throws Exception {
        try {
            logger.info("Iniciando el proceso de registro...");

            // Verificar si el email ya existe
            if (personasRepository.findByEmail(persona.getEmail()) != null) {
                throw new Exception("El correo electrónico ya está registrado.");
            }

            // Establecer la fecha de registro
            persona.setFechaRegistro(LocalDateTime.now());

            // Encriptar la contraseña antes de guardarla
            persona.setPassword(passwordEncoder.encode(persona.getPassword()));
            logger.info("Contraseña encriptada.");

            // Guardar la persona en la tabla Personas
            persona = personasRepository.save(persona);
            logger.info("Persona guardada en la base de datos con ID: " + persona.getId());

            // Crear el usuario y relacionarlo con la persona
            Usuarios usuario = new Usuarios();
            usuario.setPersona(persona); // Relaciona la persona con el usuario
            usuario.setRolId(2); // Asignar el rol 'usuario' por defecto (ID 2)

            // Guardar el usuario en la tabla Usuarios
            usuariosRepository.save(usuario);
            logger.info("Usuario relacionado guardado en la base de datos con persona ID: " + persona.getId());

        } catch (Exception e) {
            logger.severe("Error al registrar el usuario: " + e.getMessage());
            throw e; // Lanza la excepción para que el controlador la maneje
        }
    }

    // Método para autenticar al usuario
    public Personas autenticarUsuario(String email, String password) {
        Personas persona = personasRepository.findByEmail(email);
        if (persona != null && passwordEncoder.matches(password, persona.getPassword())) {
            return persona;
        }
        return null;
    }
}
