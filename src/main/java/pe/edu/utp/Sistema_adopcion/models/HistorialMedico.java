
package pe.edu.utp.Sistema_adopcion.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Historial_medico")
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "gatito_id", nullable = false)
    private Gatito gatito;

    @Lob
    private String descripcion;

    private String dosisVacunas;

    private int numeroVisitasVeterinario = 0;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    // Constructors, Getters, and Setters

    public HistorialMedico(int id, Gatito gatito, String descripcion, String dosisVacunas, Date fecha) {
        this.id = id;
        this.gatito = gatito;
        this.descripcion = descripcion;
        this.dosisVacunas = dosisVacunas;
        this.fecha = fecha;
    }

    public HistorialMedico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gatito getGatito() {
        return gatito;
    }

    public void setGatito(Gatito gatito) {
        this.gatito = gatito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDosisVacunas() {
        return dosisVacunas;
    }

    public void setDosisVacunas(String dosisVacunas) {
        this.dosisVacunas = dosisVacunas;
    }

    public int getNumeroVisitasVeterinario() {
        return numeroVisitasVeterinario;
    }

    public void setNumeroVisitasVeterinario(int numeroVisitasVeterinario) {
        this.numeroVisitasVeterinario = numeroVisitasVeterinario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}