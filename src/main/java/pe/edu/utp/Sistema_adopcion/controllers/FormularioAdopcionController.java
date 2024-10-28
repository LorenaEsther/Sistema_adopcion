package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.utp.Sistema_adopcion.models.Gatito;
import pe.edu.utp.Sistema_adopcion.models.Personas;
import pe.edu.utp.Sistema_adopcion.models.SolicitudAdopcion;
import pe.edu.utp.Sistema_adopcion.models.Usuarios;
import pe.edu.utp.Sistema_adopcion.services.GatitoService;
import pe.edu.utp.Sistema_adopcion.services.PersonaService;
import pe.edu.utp.Sistema_adopcion.services.SolicitudAdopcionService;
import pe.edu.utp.Sistema_adopcion.services.UsuarioService;

@Controller
public class FormularioAdopcionController {

    @Autowired
    private SolicitudAdopcionService solicitudService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private GatitoService gatitoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/solicitud-adopcion/{id}")
    public String mostrarFormulario(@PathVariable int id, Model model) {
        Gatito gatitoSolicitud = gatitoService.obtenerGatitoPorId(id);
        SolicitudAdopcion solicitud = new SolicitudAdopcion();
        Personas persona = new Personas();

        model.addAttribute("titulo", "Solicitud para Adoptar a " + gatitoSolicitud.getNombre());
        model.addAttribute("gatito", gatitoSolicitud);
        model.addAttribute("solicitudAdopcion", solicitud);
        model.addAttribute("persona", persona);
        return "formulario-adopcion";
    }

    @PostMapping("/solicitud-adopcion/{id}")
    public String registrarSolicitud(@PathVariable int id,
            @ModelAttribute("solicitudAdopcion") SolicitudAdopcion solicitudAdopcion,
            RedirectAttributes redirectAttributes,
            Model model) {
        // Verifica si el usuario existe usando el email
        Usuarios usuarioExistente = usuarioService.findByEmail(solicitudAdopcion.getUsuario().getEmail());

        if (usuarioExistente != null) {
            // Si el usuario existe, actualiza los datos de la persona enlazada
            Personas persona = usuarioExistente.getPersona();
            persona.setNombre(solicitudAdopcion.getUsuario().getPersona().getNombre());
            persona.setApellidos(solicitudAdopcion.getUsuario().getPersona().getApellidos());
            persona.setTelefono(solicitudAdopcion.getUsuario().getPersona().getDireccion());
            persona.setDireccion(solicitudAdopcion.getUsuario().getPersona().getTelefono());
            personaService.save(persona);  // Guarda los cambios en la persona

            //Obtener Gatito
            Gatito gatito = gatitoService.obtenerGatitoPorId(id);

            // Aquí puedes continuar con el registro de la solicitud de adopción si es necesario
            solicitudAdopcion.setUsuario(usuarioExistente);
            solicitudAdopcion.setGatito(gatito);
            solicitudAdopcion.setRazonAdopcion(solicitudAdopcion.getRazonAdopcion());
            solicitudService.saveSolicitud(solicitudAdopcion);

        return "redirect:/adopta";

        } else {

            // Añadir mensaje de error al RedirectAttributes
            redirectAttributes.addFlashAttribute("error", "Debe estar registrado para poder realizar la solicitud.");

            return "redirect:/login";  // Redirigir al login, porque no tiene cuenta
        }
    }
    
}
