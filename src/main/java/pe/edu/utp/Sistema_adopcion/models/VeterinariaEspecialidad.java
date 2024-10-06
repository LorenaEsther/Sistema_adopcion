package pe.edu.utp.Sistema_adopcion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Veterinaria_Especialidad")
public class VeterinariaEspecialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "veterinaria_id", nullable = false)
    private Veterinaria veterinaria;

    private String especialidad;

    @Lob
    private String descripcion;

    // Constructors, Getters, and Setters

    public VeterinariaEspecialidad(int id, Veterinaria veterinaria, String especialidad, String descripcion) {
        this.id = id;
        this.veterinaria = veterinaria;
        this.especialidad = especialidad;
        this.descripcion = descripcion;
    }

    public VeterinariaEspecialidad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Veterinaria getVeterinaria() {
        return veterinaria;
    }

    public void setVeterinaria(Veterinaria veterinaria) {
        this.veterinaria = veterinaria;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
