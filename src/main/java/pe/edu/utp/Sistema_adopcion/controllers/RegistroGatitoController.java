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

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class RegistroGatitoController {

    @Autowired
    private GatitoService gatitoService;

    @Autowired
    private FotoGatitoService fotoGatitoService;

    @Autowired
    private HistorialMedicoService historialMedicoService;

    @GetMapping("/registergatito")
    public String mostrarRegistroGatito(Model model) {
        model.addAttribute("titulo", "Registrar Gatito");
        model.addAttribute("activePage", "registergatito");

        // Inicializa un nuevo objeto Gatito para el formulario
        model.addAttribute("gatito", new Gatito());
        model.addAttribute("historialMedico", new HistorialMedico());

        // Recuperar todos los gatos y añadirlos al modelo
        List<Gatito> listaGatitos = gatitoService.findAll(); // Asegúrate de que este método existe en tu GatitoService

        // Agregar log para verificar cuántos gatitos se recuperaron
        System.out.println("Total de gatitos recuperados: " + listaGatitos.size());

        model.addAttribute("listaGatitos", listaGatitos);

        return "Admin/registergatito";
    }

    @PostMapping("/registergatito")
    public String registrarGatito(@ModelAttribute Gatito gatito,
            @RequestParam("fotoGatito") MultipartFile fotoGatitoFile,
            Model model) {

        // Guardar el gatito
        Gatito nuevoGatito = gatitoService.save(gatito);

        // Ruta donde se guardarán las imágenes
        String uploadDir = new File("src/main/resources/uploads").getAbsolutePath(); // Ruta absoluta

        // Asegúrate de que el directorio existe
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs(); // Crea el directorio si no existe
        }

        // Guardar la foto del gatito
        if (!fotoGatitoFile.isEmpty()) {
            try {
                // Guarda el archivo en la ruta especificada
                String filePath = uploadDir + "/" + fotoGatitoFile.getOriginalFilename();
                fotoGatitoFile.transferTo(new File(filePath)); // Guarda el archivo

                // Crear y guardar el objeto FotoGatito
                FotoGatito fotoGatito = new FotoGatito();
                fotoGatito.setGatito(nuevoGatito); // Asocia la foto al gatito
                fotoGatito.setUrlFoto(fotoGatitoFile.getOriginalFilename()); // Guarda el nombre del archivo
                fotoGatitoService.save(fotoGatito);
                System.out.println("Foto guardada en: " + filePath); // Logging de éxito
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Error al subir la foto: " + e.getMessage());
                return "error"; // Manejo de errores en caso de fallo en la subida de la foto
            }
        } else {
            System.out.println("No se subió ninguna foto."); // Logging si no hay archivo
        }

        // Guardar el historial médico
        try {
            HistorialMedico historialMed = new HistorialMedico();
            historialMed.setGatito(nuevoGatito); // Asocia el historial médico al gatito
            historialMedicoService.save(historialMed);
            System.out.println("Historial médico guardado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error al guardar el historial médico: " + e.getMessage());
            return "error"; // Manejo de errores en caso de fallo en la subida de la foto
        }

        // Redirigir a la página de registro o mostrar mensaje de éxito
        return "redirect:/admin/registergatito"; // Cambia esto según tu lógica
    }
       
    @GetMapping("/registergatito/historial")
    public String mostrarHistorialMedico(@PathVariable int id, Model model){        
        List<HistorialMedico> historial =  historialMedicoService.findAll();        
        model.addAttribute("mostrarLista", historial);
        model.addAttribute("historialID", historialMedicoService.obtenerHistorial(id));
        return "redirect:/admin/registergatito";
    }    
    
// ACTUALIZAR HISTORIAL DEL GATITO
    @PostMapping("/registergatito/historial/{id}")    
    public String actualizarHistorialMedico(@ModelAttribute("historialMedico") HistorialMedico historialMedico,
            @PathVariable("id") int id, Model model) {
        try {
            Gatito gatito = gatitoService.obtenerGatitoPorId(id); // Obtiene el objeto Gatito
            HistorialMedico historial = historialMedicoService.obtenerHistorial(id);
            
            historial.setId(id);
            historial.setDescripcion(historialMedico.getDescripcionHistorial());
            historial.setDosisVacunas(historialMedico.getDosisVacunas());
            historial.setNumeroVisitasVeterinario(historialMedico.getNumeroVisitasVeterinario());
            historial.setFecha(new Date());
            historialMedico.setGatito(gatito); // Asocia el historial médico al gatito
            historialMedicoService.save(historial);
            System.out.println("Historial médico guardado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error al guardar el historial médico: " + e.getMessage());
            return "error"; // Manejo de errores en caso de fallo en la subida del historial médico
        }

        // Redirigir a la página correspondiente o mostrar mensaje de éxito
        return "redirect:/admin/registergatito"; // Cambia esto según tu lógica
    }

// ELIMINAR GATITO
    @GetMapping("registergatito/{id}")
    public String eliminarGatito(@PathVariable int id, Model model) {
        historialMedicoService.deleteById(id);
        fotoGatitoService.deleteById(id);
        gatitoService.deleteById(id);
        return "redirect:/admin/registergatito";
    }

}
