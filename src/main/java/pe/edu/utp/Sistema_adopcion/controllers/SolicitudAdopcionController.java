package pe.edu.utp.Sistema_adopcion.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.utp.Sistema_adopcion.models.Adopcion;
import pe.edu.utp.Sistema_adopcion.models.Gatito;
import pe.edu.utp.Sistema_adopcion.models.SolicitudAdopcion;
import pe.edu.utp.Sistema_adopcion.services.AdopcionService;
import pe.edu.utp.Sistema_adopcion.services.GatitoService;
import pe.edu.utp.Sistema_adopcion.services.SolicitudAdopcionService;

@Controller
public class SolicitudAdopcionController {

    @Autowired
    private GatitoService gatitoService;

    @Autowired
    private SolicitudAdopcionService solicitudService;

    @Autowired
    private AdopcionService adopcionService;

    @GetMapping("/admin/solicitudesadopcion")
    public String mostrarSolicitudesAdopcion(Model model) {
        List<Gatito> listaGatitos = gatitoService.findAll();
        List<SolicitudAdopcion> listaSolicitudesAdopcion = solicitudService.getAllSolicitudes();

        model.addAttribute("listaGatitos", listaGatitos);
        model.addAttribute("listaSolicitudesAdopcion", listaSolicitudesAdopcion);

        return "Admin/solicitudes-adopcion";
    }

    @PostMapping("/admin/aceptar-solicitud/{id}")
    public String aceptarSolicitud(@PathVariable int id) {

        SolicitudAdopcion solicitudAdopcion = solicitudService.getSolicitudById(id);

        if (solicitudAdopcion == null) {
            throw new RuntimeException("SolicitudAdopcion no encontrada");
        }

        solicitudService.updateEstadoSolicitud(id, SolicitudAdopcion.EstadoSolicitud.aprobada);

        Gatito gatitoActualizar = solicitudAdopcion.getGatito();
        gatitoActualizar.setEstado(Gatito.EstadoGatito.ADOPTADO);
        
        gatitoService.updateGatito(gatitoActualizar);
        
        Adopcion nuevaAdopcion = new Adopcion();
        nuevaAdopcion.setUsuario(solicitudAdopcion.getUsuario());
        nuevaAdopcion.setGatito(solicitudAdopcion.getGatito());

        adopcionService.crearAdopcion(nuevaAdopcion);
        return "redirect:/admin/solicitudesadopcion";
    }

    @PostMapping("/admin/rechazar-solicitud/{id}")
    public String rechazarSolicitud(@PathVariable int id) {
        SolicitudAdopcion solicitudAdopcion = solicitudService.getSolicitudById(id);
        
        Gatito gatitoActualizar = solicitudAdopcion.getGatito();
        gatitoActualizar.setEstado(Gatito.EstadoGatito.DISPONIBLE);
        
        gatitoService.updateGatito(gatitoActualizar);
        solicitudService.updateEstadoSolicitud(id, SolicitudAdopcion.EstadoSolicitud.rechazada);
        return "redirect:/admin/solicitudesadopcion";
    }
}
