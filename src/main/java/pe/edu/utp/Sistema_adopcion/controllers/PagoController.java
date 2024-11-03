package pe.edu.utp.Sistema_adopcion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagoController {

    // Mostrar el formulario de pago con parámetros de plan y modalidad
    @GetMapping("/pago/{plan}/{modalidad}")
    public String showPagoForm(@PathVariable String plan, @PathVariable String modalidad, Model model) {
        model.addAttribute("plan", plan);
        model.addAttribute("modalidad", modalidad);
        return "pago"; // Redirige a pago.html
    }

    // Manejar el procesamiento del formulario de pago
    @PostMapping("/pago/procesar")
    public String procesarPago(@RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String tarjeta,
            @RequestParam String mes,
            @RequestParam String ano,
            @RequestParam String cvv,
            @RequestParam String plan,
            @RequestParam String modalidad,
            Model model) {
        // Lógica de procesamiento del pago (ejemplo: validación y almacenamiento)
        model.addAttribute("mensaje", "Pago procesado exitosamente para el plan " + plan + " en modalidad " + modalidad);
        return "confirmacion_pago"; // Redirige a una página de confirmación de pago
    }
}
