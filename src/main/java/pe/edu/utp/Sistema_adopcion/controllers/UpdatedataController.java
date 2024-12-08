package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.utp.Sistema_adopcion.models.Usuarios;
import pe.edu.utp.Sistema_adopcion.services.UsuarioService;

@Controller
@RequestMapping("/admin")
public class UpdatedataController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Muestra el formulario de actualización de datos.
     */
    @GetMapping("/updatedata")
    public String mostrarActualizarDatos(Model model) {
        model.addAttribute("usuario", new Usuarios()); // Objeto vacío para el formulario
        return "Admin/updatedata"; // Vista del formulario
    }

    /**
     * Maneja la actualización de datos del usuario.
     */
    @PostMapping("/updatedata")
    public String actualizarDatos(@ModelAttribute("usuario") Usuarios usuario, Model model) {
        // Busca el usuario por su correo electrónico
        Usuarios usuarioExistente = usuarioService.findByEmail(usuario.getEmail());

        if (usuarioExistente != null) {
            // Actualiza la contraseña si es proporcionada
            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                usuarioExistente.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }

            // Actualiza el nombre del usuario
            usuarioExistente.getPersona().setNombre(usuario.getPersona().getNombre());

            // Guarda los cambios
            usuarioService.save(usuarioExistente);

            model.addAttribute("success", "Datos actualizados correctamente.");
        } else {
            model.addAttribute("error", "El usuario no fue encontrado.");
        }

        return "Admin/updatedata"; // Devuelve la vista con un mensaje de éxito o error
    }
}
