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
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.utp.Sistema_adopcion.services.FileStorageService;

@Controller
@RequestMapping("/admin")
public class RegistroGatitoController {

    @Autowired
    private GatitoService gatitoService;

    @Autowired
    private FotoGatitoService fotoGatitoService;

    @Autowired
    private HistorialMedicoService historialMedicoService;

    @Autowired
    private FileStorageService fileStorageService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/registergatito")
    public String mostrarRegistroGatito(Model model) {
        model.addAttribute("titulo", "Registrar Gatito");
        model.addAttribute("activePage", "registergatito");

        // Inicializa un nuevo objeto Gatito para el formulario
        model.addAttribute("gatito", new Gatito());

        // Recuperar todos los gatos y añadirlos al modelo
        List<Gatito> listaGatitos = gatitoService.findAll();
        model.addAttribute("listaGatitos", listaGatitos);

        return "Admin/registergatito";
    }

    @PostMapping("/registergatito")
    public String registrarGatito(@ModelAttribute Gatito gatito,
            @ModelAttribute HistorialMedico historialMedico,
            @RequestParam("fotoGatito") MultipartFile[] fotoGatitosFiles,
            Model model) {

        if (fotoGatitosFiles == null || fotoGatitosFiles.length == 0) {
            System.out.println("No se han recibido archivos.");
        } else {
            System.out.println("Archivos recibidos correctamente.");
        }

        // Imprimir el número de archivos recibidos para verificar
        System.out.println("Número de archivos recibidos: " + fotoGatitosFiles.length);

        // Guardar el gatito primero
        Gatito nuevoGatito = gatitoService.save(gatito);

        // Ruta donde se guardarán las imágenes
        String uploadDir = "D:/Repositorios/Sistema_Adopcion/src/main/resources/uploads";

        // Verificar que el directorio exista o crearlo
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs(); // Crear el directorio si no existe
        }

        // Guardar las fotos del gatito si están presentes
        if (fotoGatitosFiles != null && fotoGatitosFiles.length > 0) {
            for (MultipartFile fotoGatitoFile : fotoGatitosFiles) {
                if (!fotoGatitoFile.isEmpty()) {
                    try {
                        // Almacenar archivo
                        String storedFilename = fileStorageService.storeFile(fotoGatitoFile, uploadDir);

                        // Crear y guardar la entidad FotoGatito
                        FotoGatito fotoGatito = new FotoGatito();
                        fotoGatito.setGatito(nuevoGatito);  // Asociar la foto con el gatito
                        fotoGatito.setUrlFoto(storedFilename);  // Guardar el nombre del archivo generado
                        fotoGatitoService.save(fotoGatito);
                    } catch (IOException e) {
                        e.printStackTrace();
                        model.addAttribute("errorMessage", "Error al subir la foto: " + e.getMessage());
                        return "error";
                    }
                }
            }
        }

        // Guardar el historial médico
        try {
            historialMedico.setGatito(nuevoGatito);
            historialMedicoService.save(historialMedico);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error al guardar el historial médico: " + e.getMessage());
            return "error";
        }

        // Redirigir a la página de registro o mostrar mensaje de éxito
        return "redirect:/admin/registergatito";
    }

    @PostMapping("/admin/eliminar/{id}")
    public String eliminarGatito(@PathVariable int id, Model model) {
        try {
            gatitoService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error al eliminar el gatito.");
            return "error";
        }
        return "redirect:/admin/registergatito";
    }

}
