package pe.edu.utp.Sistema_adopcion.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.utp.Sistema_adopcion.models.Gatito;
import pe.edu.utp.Sistema_adopcion.services.GatitoService;

@Controller
public class IndexController {

    @Autowired
    private GatitoService gatitoService;

    @GetMapping("/")
    public String index(Model model) {
        // Puedes pasar atributos al modelo si es necesario para la vista
        model.addAttribute("titulo", "Sistema de adopción de gatos");
        return "index";  // Retorna la plantilla index.html ubicada en /templates
    }

    @GetMapping("/adopta")
    public String adopta(Model model) {
        List<Gatito> gatitos = gatitoService.findAll();  // Obtener la lista de gatitos desde el servicio
        model.addAttribute("gatitos", gatitos);  // Pasar la lista de gatitos al modelo
        model.addAttribute("titulo", "Adopta un gatito");
        return "adopta";  // Thymeleaf buscará adopta.html en /templates
    }

    @GetMapping("/detalles-gato/{id}")
    public String detallesGato(@PathVariable("id") int id, Model model) {
        Optional<Gatito> gatitoOpt = gatitoService.obtenerGatitoPorId(id);

        if (gatitoOpt.isPresent()) {
            model.addAttribute("gatito", gatitoOpt.get());
            return "detalles-gato"; // Thymeleaf buscará un archivo detalles-gato.html
        } else {
            model.addAttribute("error", "Gatito no encontrado");
            return "error"; // O una vista de error que hayas definido
        }
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

    /*@GetMapping("/adminprueba")
    public String admin(Model model) {
        model.addAttribute("titulo", "Admin");
        return "Admin/index";
    }*/
}
