package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.edu.utp.Sistema_adopcion.models.Usuarios;
import pe.edu.utp.Sistema_adopcion.services.UsuarioService;

import java.util.List;

@Controller
public class TestDBController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para mostrar información de la base de datos
    @GetMapping("/test-db")
    public String testDatabaseConnection(Model model) {
        try {
            List<Usuarios> usuariosList = usuarioService.findAll(); // Método para obtener todos los usuarios
            model.addAttribute("usuarios", usuariosList); // Agregar la lista de usuarios al modelo
            return "test-db"; // Redirigir a la vista test-db.html
        } catch (Exception e) {
            model.addAttribute("error", "Error al conectar con la base de datos: " + e.getMessage());
            return "error"; // Opcionalmente redirigir a una vista de error
        }
    }
}
