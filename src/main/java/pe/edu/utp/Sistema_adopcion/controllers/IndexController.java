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
        model.addAttribute("titulo", "Lista de felinos | Adopta Miu");
        return "adopta";
    }
    
    @GetMapping ("/login")
    public String login (Model model){
        model.addAttribute("titulo", "Logeo | Adopta Miu");
        return "login";
    }
    
    @GetMapping ("/consulta")
    public String consulta (Model model){
        model.addAttribute("titulo", "Preguntas Frecuentes | Adopta Miu");
        return "consulta";
    }
}