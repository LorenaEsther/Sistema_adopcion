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

    @GetMapping("/adopta")
    public String adopta(Model model) {
        model.addAttribute("titulo", "Adopta un gatito");
        return "adopta";
    }


    @GetMapping("/donaciones")
    public String donaciones(Model model) {
        model.addAttribute("titulo", "Donaciones");
        return "donaciones";
    }

    @GetMapping("/miaus-en-casa")
    public String miausEnCasa(Model model) {
        model.addAttribute("titulo", "Miaus en Casa");
        return "miaus-en-casa";
    }

    @GetMapping("/consultas")
    public String consultas(Model model) {
        model.addAttribute("titulo", "Consultas");
        return "consultas";
    }

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("titulo", "Nosotros");
        return "nosotros";
    }

    @GetMapping("/miaushop")
    public String miaushop(Model model) {
        model.addAttribute("titulo", "MiauShop");
        return "miaushop";
    }

}
