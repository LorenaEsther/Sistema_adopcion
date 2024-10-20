package pe.edu.utp.Sistema_adopcion.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Voluntarios")
public class Voluntarios {

    @Id
    private int personaId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "persona_id")
    private Personas persona;

    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    // Constructors
    public Voluntarios() {
        this.fechaRegistro = new Date(); // Default to current date
    }

    public Voluntarios(Personas persona) {
        this.persona = persona;
        this.fechaRegistro = new Date(); // Default to current date
    }

    // Getters and Setters
    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
