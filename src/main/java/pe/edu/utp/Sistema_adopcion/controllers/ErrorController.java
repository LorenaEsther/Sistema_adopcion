package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, Model model) {
        // Agregar información sobre la excepción al modelo
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("errorType", e.getClass().getSimpleName());

        // También puedes agregar más detalles si es necesario
        System.out.println("Error ocurrido: " + e.getMessage());

        // Retorna el nombre de la vista de error
        return "error"; // Cambia esto según el nombre de tu página de error
    }

    @RequestMapping("/error")
    public String handleError() {
        // Este método maneja las solicitudes a /error
        return "error"; // Cambia esto según el nombre de tu página de error
    }
}
