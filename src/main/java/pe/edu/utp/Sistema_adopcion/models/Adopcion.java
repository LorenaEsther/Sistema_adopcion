
package pe.edu.utp.Sistema_adopcion.models;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Adopciones")
public class Adopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "gatito_id", nullable = false)
    private Gatito gatito;

    private LocalDateTime fechaAdopcion = LocalDateTime.now();

    // Constructors, Getters, and Setters

    public Adopcion(int id, Usuarios usuario, Gatito gatito) {
        this.id = id;
        this.usuario = usuario;
        this.gatito = gatito;
    }

    public Adopcion() {
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

    public LocalDateTime getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(LocalDateTime fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }
    
}
