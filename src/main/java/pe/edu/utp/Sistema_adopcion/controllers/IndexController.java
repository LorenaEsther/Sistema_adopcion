package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        // Puedes pasar atributos al modelo si es necesario para la vista
        model.addAttribute("titulo", "Sistema de adopción de gatos");
        return "index";  // Retorna la plantilla index.html ubicada en /templates
    }
    
    @GetMapping ("/adopta")
    public String adopta (Model model){
        model.addAttribute("titulo", "Adopta un gatito");
        return "adopta";
    }
    
    @GetMapping ("/login")
    public String login (Model model){
        model.addAttribute("titulo", "login");
        return "login";
    }
    
}