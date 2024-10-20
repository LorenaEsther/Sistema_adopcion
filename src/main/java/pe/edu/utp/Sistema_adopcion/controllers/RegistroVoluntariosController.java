package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.utp.Sistema_adopcion.models.Voluntarios;
import pe.edu.utp.Sistema_adopcion.services.VoluntarioService;

@Controller
@RequestMapping("/admin")
public class RegistroVoluntariosController {

    @GetMapping("/registervolunteer")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("voluntario", new Voluntarios()); // Agrega un objeto voluntario vacío al modelo
        return "Admin/registervolunteer"; // Asegúrate que el archivo se llame exactamente registervolunteer.html
    }
}

