package pe.edu.utp.Sistema_adopcion.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.utp.Sistema_adopcion.models.SolicitudAdopcion;
import pe.edu.utp.Sistema_adopcion.services.AdopcionService;
import pe.edu.utp.Sistema_adopcion.services.GatitoService;
import pe.edu.utp.Sistema_adopcion.services.SolicitudAdopcionService;
import pe.edu.utp.Sistema_adopcion.services.UsuarioService;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @Autowired
    private GatitoService gatitoService;

    @Autowired
    private SolicitudAdopcionService solicitudAdopcionService;

    @Autowired
    private AdopcionService adopcionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {
        long gatosDisponibles = gatitoService.contarGatosDisponibles();
        long solicitudesPendientes = solicitudAdopcionService.contarSolicitudesPendientes();
        long totalAdopciones = adopcionService.contarTotalAdopciones();
        long usuariosRegistrados = usuarioService.contarUsuariosRegistrados();

        Map<String, Long> preferenciasPorColor = adopcionService.contarPreferenciasPorColor();
        Map<String, Long> solicitudesPorEstado = solicitudAdopcionService.contarSolicitudesPorEstado();

        // Añade logs para verificar los datos
        System.out.println("Preferencias por color: " + preferenciasPorColor);
        System.out.println("Solicitudes por estado: " + solicitudesPorEstado);

        model.addAttribute("gatosDisponibles", gatosDisponibles);
        model.addAttribute("solicitudesPendientes", solicitudesPendientes);
        model.addAttribute("totalAdopciones", totalAdopciones);
        model.addAttribute("usuariosRegistrados", usuariosRegistrados);
        model.addAttribute("preferenciasPorColor", preferenciasPorColor);
        model.addAttribute("solicitudesPorEstado", solicitudesPorEstado);

        return "Admin/dashboard";
    }

}
