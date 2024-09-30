package pe.edu.utp.Sistema_adopcion.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Donaciones")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Personas persona;

    private double monto;

    private LocalDateTime fecha = LocalDateTime.now();

    // Constructors, Getters, and Setters
    public Donacion(int id, Personas persona, double monto) {
        this.id = id;
        this.persona = persona;
        this.monto = monto;
    }

    public Donacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
