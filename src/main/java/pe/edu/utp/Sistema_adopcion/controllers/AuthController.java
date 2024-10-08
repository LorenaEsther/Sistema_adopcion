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

    @GetMapping("/pruebaregistrate")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "pruebaregistrate";
    }

    @PostMapping("/pruebaregistrate")
    public String registerUser(@ModelAttribute("usuario") Usuarios usuario) {
        // Obtener la persona del usuario
        Personas persona = usuario.getPersona();

        // Verificar si la persona ya tiene un ID (lo que indica que ya está guardada en la base de datos)
        if (persona.getId() == 0) {
            // Si la persona no tiene un ID, significa que es una nueva entidad y se guarda
            persona.setFechaRegistro(java.sql.Date.valueOf(LocalDate.now()));
            persona = personaService.save(persona);
        }

        // Asigna la persona guardada al usuario
        usuario.setPersona(persona);

        // Obtener el rol 'usuario' desde la base de datos y asignarlo
        Rol usuarioRol = rolService.findById(2); // Asegúrate de que este método existe en RolService
        usuario.setRol(usuarioRol);

        // Guarda el usuario
        usuarioService.save(usuario);

        // Redirige al login después de registrar el usuario
        return "redirect:/pruebalogin?registered=true";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        // Si hay usuarios en la base de datos, mostrar la página de login.
        if (usuarioService.count() > 0) {
            return "login";
        }
        // Si no hay usuarios, redirigir al registro.
        return "redirect:/pruebaregistrate";
    }

    @GetMapping("/pruebalogin")
    public String showTestLoginForm() {
        // Si hay usuarios en la base de datos, mostrar la página de login.
        if (usuarioService.count() > 0) {
            return "pruebalogin";
        }
        // Si no hay usuarios, redirigir al registro.
        return "redirect:/pruebaregistrate";
    }

    @PostMapping("/pruebalogin")
    public String loginUser() {
        // Implementar lógica de autenticación
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("email") String email,
            @ModelAttribute("password") String password,
            Model model) {

        // Mensajes de depuración
        System.out.println("Email recibido: " + email);
        System.out.println("Contraseña recibida: " + password);

        // Busca al usuario en la base de datos por su email
        Usuarios usuario = usuarioService.findByEmail(email);

        // Verifica si el usuario existe
        if (usuario != null) {
            System.out.println("Usuario encontrado: " + usuario.getEmail());
            System.out.println("Contraseña almacenada: " + usuario.getPassword());

            // Verifica si la contraseña es correcta
            if (usuario.getPassword().equals(password)) {
                System.out.println("Autenticación exitosa");
                return "redirect:/test-db"; // Redirigir al home o a donde sea necesario
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else {
            System.out.println("Usuario no encontrado");
        }

        // Autenticación fallida
        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login"; // Mantener en la página de login con el mensaje de error
    }
}
