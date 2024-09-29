package pe.edu.utp.Sistema_adopcion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    private Long personaId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "persona_id")
    private Personas persona;

    @Column(name = "rol_id", nullable = false)
    private int rolId;

    // Getters y Setters
    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }
}
