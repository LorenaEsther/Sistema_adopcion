package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.utp.Sistema_adopcion.models.FotoGatito;
import pe.edu.utp.Sistema_adopcion.models.Gatito;
import pe.edu.utp.Sistema_adopcion.models.HistorialMedico;
import pe.edu.utp.Sistema_adopcion.services.FotoGatitoService;
import pe.edu.utp.Sistema_adopcion.services.GatitoService;
import pe.edu.utp.Sistema_adopcion.services.HistorialMedicoService;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class RegistroGatitoController {

    @Autowired
    private GatitoService gatitoService;

    @Autowired
    private FotoGatitoService fotoGatitoService;

    @Autowired
    private HistorialMedicoService historialMedicoService;

    @PostMapping("/registergatito")
    public String registrarGatito(@ModelAttribute Gatito gatito,
            @ModelAttribute HistorialMedico historialMedico,
            @ModelAttribute("fotoGatitoFile") MultipartFile fotoGatitoFile,
            Model model) {
        // Guardar el gatito
        Gatito nuevoGatito = gatitoService.save(gatito);

        // Guardar la foto del gatito
        if (!fotoGatitoFile.isEmpty()) {
            try {
                FotoGatito fotoGatito = new FotoGatito();
                fotoGatito.setGatito(nuevoGatito);
                fotoGatito.setUrlFoto(fotoGatitoFile.getOriginalFilename());
                // Aquí podrías guardar el archivo en un directorio específico si es necesario
                fotoGatitoService.save(fotoGatito);
            } catch (Exception e) {
                e.printStackTrace();
                return "error"; // Manejo de errores en caso de fallo en la subida de la foto
            }
        }

        // Guardar el historial médico
        historialMedico.setGatito(nuevoGatito);
        historialMedicoService.save(historialMedico);

        // Redirigir a la página de registro o mostrar mensaje de éxito
        return "redirect:/admin/registergatito";
    }
}
