package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.utp.Sistema_adopcion.models.Personas;
import pe.edu.utp.Sistema_adopcion.models.Voluntarios;
import pe.edu.utp.Sistema_adopcion.services.VoluntarioService;

@Controller
@RequestMapping("/admin")
public class RegistroVoluntariosController {

    @Autowired
    private VoluntarioService voluntarioService;

    @GetMapping("/registervolunteer")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("voluntario", new Voluntarios());
        model.addAttribute("voluntarios", voluntarioService.obtenerTodosLosVoluntarios());
        return "Admin/registervolunteer";
    }

    @PostMapping("/registervolunteer")
    public String registrarVoluntario(@ModelAttribute Voluntarios voluntario, Model model) {
        Personas persona = voluntario.getPersona();
        if (persona == null) {
            persona = new Personas();
            voluntario.setPersona(persona);
        }
        voluntarioService.guardarVoluntario(voluntario);
        model.addAttribute("voluntarios", voluntarioService.obtenerTodosLosVoluntarios());
        model.addAttribute("voluntario", new Voluntarios());
        return "Admin/registervolunteer";
    }

    @GetMapping("/deletevolunteer/{id}")
    public String eliminarVoluntario(@PathVariable int id, Model model) {
        voluntarioService.eliminarVoluntario(id); // Elimina el voluntario por su ID
        model.addAttribute("voluntarios", voluntarioService.obtenerTodosLosVoluntarios()); // Actualiza la lista
        model.addAttribute("voluntario", new Voluntarios()); // Restablece el formulario
        return "Admin/registervolunteer"; // Redirige a la misma página
    }
}
