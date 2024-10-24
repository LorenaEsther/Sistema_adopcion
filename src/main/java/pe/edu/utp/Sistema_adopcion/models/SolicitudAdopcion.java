package pe.edu.utp.Sistema_adopcion.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SolicitudAdopcion")
public class SolicitudAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "gatito_id", nullable = false)
    private Gatito gatito;
    
    private String razonAdopcion;

    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pendiente', 'aprobada', 'rechazada')", nullable = false)
    private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

    public SolicitudAdopcion(int id, Usuarios usuario, Gatito gatito, String razonAdopcion) {
        this.id = id;
        this.usuario = usuario;
        this.gatito = gatito;
        this.razonAdopcion = razonAdopcion;
    }

    public SolicitudAdopcion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Gatito getGatito() {
        return gatito;
    }

    public void setGatito(Gatito gatito) {
        this.gatito = gatito;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public String getRazonAdopcion() {
        return razonAdopcion;
    }

    public void setRazonAdopcion(String razonAdopcion) {
        this.razonAdopcion = razonAdopcion;
    }
    
    

    public enum EstadoSolicitud {
        PENDIENTE, APROBADA, RECHAZADA
    }

}
