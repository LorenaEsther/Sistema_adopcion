package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.Sistema_adopcion.models.Personas;
import pe.edu.utp.Sistema_adopcion.services.UsuariosService;

@Controller
public class AuthController {

    @Autowired
    private UsuariosService usuariosService;

    // Procesa el formulario de registro
    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute Personas persona, Model model) {
        try {
            // Llama al servicio para registrar al usuario
            usuariosService.registrarUsuario(persona);
            // Añadir un mensaje de éxito al modelo
            model.addAttribute("success", "Registro exitoso. ¡Ahora puedes iniciar sesión!");
            return "login"; // Redirige al formulario de login tras el registro exitoso
        } catch (Exception e) {
            // Manejo de errores (por ejemplo, email ya existe)
            model.addAttribute("error", "Error al registrar el usuario: " + e.getMessage());
            return "login"; // Vuelve a la vista de login si hay error
        }
    }

    // Muestra el formulario de inicio de sesión
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        return "login"; // Retorna la vista 'login.html'
    }

    // Procesa el formulario de inicio de sesión
    @PostMapping("/login")
    public String autenticarUsuario(@RequestParam String email,
            @RequestParam String password,
            Model model) {
        Personas persona = usuariosService.autenticarUsuario(email, password);
        if (persona != null) {
            // Autenticación exitosa, redirigir a la página principal
            return "redirect:/home";
        } else {
            // Error de autenticación, mostrar mensaje de error
            model.addAttribute("error", "Correo electrónico o contraseña incorrectos");
            return "login"; // Vuelve a la vista de login si hay error
        }
    }
}
