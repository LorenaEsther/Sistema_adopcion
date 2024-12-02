package pe.edu.utp.Sistema_adopcion.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        // Hashear la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

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
        if (usuarioEncontrado != null && passwordEncoder.matches(usuario.getPassword(), usuarioEncontrado.getPassword())) {
            // Verificar si el usuario tiene el rol de administrador (rol_id = 1)
            if (usuarioEncontrado.getRol().getId() == 1 && usuario.getEmail().equals("administrador123@gmail.com")) {
                // Si el usuario es administrador, redirigir al panel de administración
                return "redirect:/admin/adminprueba";  // Asegúrate de redirigir a la ruta correcta
            } else {
                // Si el usuario no es administrador, redirigir a la página principal
                return "redirect:/";
            }
        } else {
            // Autenticación fallida
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login"; // Mantener en la página de login con el mensaje de error
        }
    }
    // Mostrar formulario de "Olvidé mi contraseña"

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password"; // Redirige a forgot-password.html
    }

// Manejar la verificación de correo
    @PostMapping("/forgot-password")
    public String verifyEmail(@ModelAttribute("email") String email, Model model) {
        Usuarios usuario = usuarioService.findByEmail(email);
        if (usuario != null) {
            model.addAttribute("email", email);
            return "reset-password"; // Redirige al formulario de restablecimiento de contraseña
        } else {
            model.addAttribute("error", "El correo no está registrado.");
            return "forgot-password"; // Mantiene en la página con el mensaje de error
        }
    }

// Mostrar formulario de restablecimiento de contraseña
    @GetMapping("/reset-password")
    public String showResetPasswordForm(@ModelAttribute("email") String email, Model model) {
        model.addAttribute("email", email);
        return "reset-password"; // Redirige a reset-password.html
    }

// Manejar el restablecimiento de contraseña
    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("email") String email, @ModelAttribute("newPassword") String newPassword, Model model) {
        Usuarios usuario = usuarioService.findByEmail(email);
        if (usuario != null) {
            usuario.setPassword(passwordEncoder.encode(newPassword));
            usuarioService.save(usuario);
            // Redirigir al login con el mensaje de éxito
            return "redirect:/login?passwordReset=true";
        } else {
            model.addAttribute("error", "Ocurrió un error al actualizar la contraseña.");
            return "reset-password"; // Mantiene en la página con el mensaje de error
        }
    }

}
