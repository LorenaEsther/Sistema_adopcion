package pe.edu.utp.Sistema_adopcion.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.utp.Sistema_adopcion.models.Personas;
import pe.edu.utp.Sistema_adopcion.models.Rol;
import pe.edu.utp.Sistema_adopcion.models.Usuarios;
import pe.edu.utp.Sistema_adopcion.services.UsuarioService;
import pe.edu.utp.Sistema_adopcion.services.PersonaService;
import pe.edu.utp.Sistema_adopcion.services.RolService;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private RolService rolService;

    // Mostrar el formulario de inicio de sesión y registro
    @GetMapping("/login")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "login"; // Redirige a login.html
    }

    // Manejar el registro de usuarios
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("usuario") Usuarios usuario, Model model) {
        // Verificar si el email ya está en uso
        if (usuarioService.findByEmail(usuario.getEmail()) != null) {
            model.addAttribute("error", "El correo electrónico ya está en uso.");
            return "login"; // Redirige de nuevo al formulario con un mensaje de error
        }

        // Crear y guardar la persona
        Personas persona = new Personas();
        persona.setNombre(usuario.getPersona().getNombre()); // Asumiendo que el objeto Persona está vinculado a Usuario
        persona.setFechaRegistro(java.sql.Date.valueOf(LocalDate.now()));
        persona = personaService.save(persona);

        // Asigna la persona guardada al usuario
        usuario.setPersona(persona);

        // Obtener el rol 'usuario' desde la base de datos y asignarlo
        Rol usuarioRol = rolService.findById(2); // Asegúrate de que este método existe en RolService
        usuario.setRol(usuarioRol);

        // Guarda el usuario
        usuarioService.save(usuario);

        // Redirige al login después de registrar el usuario
        return "redirect:/login?registered=true";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("usuario") Usuarios usuario, Model model) {
        // Busca al usuario en la base de datos por su email
        Usuarios usuarioEncontrado = usuarioService.findByEmail(usuario.getEmail());

        // Verifica si el usuario existe y si la contraseña es correcta
        if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(usuario.getPassword())) {
            // Autenticación exitosa
            return "redirect:/test-db"; // Redirigir al home o a donde sea necesario
        } else {
            // Autenticación fallida
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login"; // Mantener en la página de login con el mensaje de error
        }
    }
}
