package pe.edu.utp.Sistema_adopcion.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.utp.Sistema_adopcion.models.FotoGatito;
import pe.edu.utp.Sistema_adopcion.models.Gatito;
import pe.edu.utp.Sistema_adopcion.models.HistorialMedico;
import pe.edu.utp.Sistema_adopcion.services.FotoGatitoService;
import pe.edu.utp.Sistema_adopcion.services.GatitoService;
import pe.edu.utp.Sistema_adopcion.services.HistorialMedicoService;

@Controller
public class IndexController {

    @Autowired
    private GatitoService gatitoService;
    
    @Autowired
    private HistorialMedicoService historialMedicoService;

    @Autowired
    private FotoGatitoService fotoGatitoService;

    @GetMapping("/")
    public String index(Model model) {
        // Puedes pasar atributos al modelo si es necesario para la vista
        model.addAttribute("titulo", "Sistema de adopción de gatos");
        return "index";  // Retorna la plantilla index.html ubicada en /templates
    }

    @GetMapping("/adopta")
    public String adopta(Model model) {
        List<FotoGatito> fotoGatitosDisponibles = fotoGatitoService.findAll().stream()
                .filter(foto -> foto.getGatito().getEstado() == Gatito.EstadoGatito.DISPONIBLE)
                .collect(Collectors.toList());

        model.addAttribute("fotoGatitos", fotoGatitosDisponibles);
        model.addAttribute("titulo", "Adopta un gatito");
        return "adopta";  // Thymeleaf buscará adopta.html en /templates
    }

    @GetMapping("/detalles-gato/{id}")
    public String detallesGato(@PathVariable("id") int id, Model model) {
        /*Gatito gatitoOpt = gatitoService.obtenerGatitoPorId(id);
        model.addAttribute("gatito", gatitoOpt);*/
        FotoGatito fotoGatitos = fotoGatitoService.obtenerFotoPorId(id);
        model.addAttribute("fotoGatitos", fotoGatitos);
        HistorialMedico HistorialMedico = historialMedicoService.obtenerHistorial(id);
        model.addAttribute("historialGatitos", HistorialMedico);
        model.addAttribute("titulo","Detalles");
        return "detalles-gato"; // Thymeleaf buscará un archivo detalles-gato.html
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
