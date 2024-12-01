package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/uploads")
public class ImagenController {

    private final String rutaImagenes = "src/main/resources/static/uploads/";

    @PostMapping("/uploads")
    public String subirImagen(@RequestParam("archivo") MultipartFile archivo) {
        if (archivo.isEmpty()) {
            return "Por favor, seleccione una imagen.";
        }

        try {
            // Obtener el nombre del archivo
            String nombreArchivo = archivo.getOriginalFilename();
            // Crear la ruta completa donde se guardará la imagen
            File rutaCompleta = new File(rutaImagenes + nombreArchivo);
            // Guardar el archivo en la ruta especificada
            archivo.transferTo(rutaCompleta);
            return "Imagen subida correctamente: " + nombreArchivo;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir la imagen: " + e.getMessage();
        }
    }

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:src/main/resources/static/uploads/");
    }
}
}


/*import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {

    @GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = Paths.get("D:/Repositorios/Sistema_Adopcion/src/main/resources/uploads").resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}*/
