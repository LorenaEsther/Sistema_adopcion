package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // Método para mostrar la página principal de administrador
    @GetMapping("/adminprueba")
    public String admin(Model model) {
        model.addAttribute("titulo", "Admin");
        // Cambia la vista para usar la plantilla adminTemplate
        return "Admin/adminTemplate";
    }

    @GetMapping("/registergatito")
    public String mostrarRegistroGatito(Model model) {
        model.addAttribute("titulo", "Registrar Gatito");
        System.out.println("Accediendo al método mostrarRegistroGatito");
        return "Admin/registergatito"; 
    }

}
