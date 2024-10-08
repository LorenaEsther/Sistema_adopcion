package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.utp.Sistema_adopcion.services.UsuarioService;

@RestController
public class TestDBController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para probar la conexión a la base de datos
    @GetMapping("/test-db")
    public String testDatabaseConnection() {
        try {
            long count = usuarioService.count(); // Conteo de usuarios en la base de datos
            return "Conexión exitosa. Número de usuarios en la base de datos: " + count;
        } catch (Exception e) {
            return "Error al conectar con la base de datos: " + e.getMessage();
        }
    }
}
