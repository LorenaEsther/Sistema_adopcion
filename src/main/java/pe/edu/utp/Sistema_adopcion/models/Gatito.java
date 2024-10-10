package pe.edu.utp.Sistema_adopcion.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Gatitos")
public class Gatito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descripcion;

    private String nombre;

    private int edadAproximada;

    private String color;

    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('disponible', 'en proceso', 'adoptado')", nullable = false)
    private EstadoGatito estado = EstadoGatito.DISPONIBLE;

    @OneToMany(mappedBy = "gatito", cascade = CascadeType.ALL) // Asegúrate de que esto esté aquí
    private List<FotoGatito> fotos; // Lista de fotos asociadas

    // Enumerated type for 'estado'
    public enum EstadoGatito {
        DISPONIBLE, EN_PROCESO, ADOPTADO
    }

    // Constructors
    public Gatito() {
        this.fechaIngreso = new Date(); // Default to current date
    }

    public Gatito(String nombre, int edadAproximada, String color) {
        this.nombre = nombre;
        this.edadAproximada = edadAproximada;
        this.color = color;
        this.fechaIngreso = new Date(); // Default to current date
        this.estado = EstadoGatito.DISPONIBLE;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdadAproximada() {
        return edadAproximada;
    }

    public void setEdadAproximada(int edadAproximada) {
        this.edadAproximada = edadAproximada;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public EstadoGatito getEstado() {
        return estado;
    }

    public void setEstado(EstadoGatito estado) {
        this.estado = estado;
    }

    public List<FotoGatito> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoGatito> fotos) {
        this.fotos = fotos;
    }
}
