package pe.edu.utp.Sistema_adopcion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    private int personaId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "persona_id")
    private Personas persona;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Constructors
    public Usuarios() {
    }

    public Usuarios(Personas persona, Rol rol, String email, String password) {
        this.persona = persona;
        this.rol = rol;
        this.email = email;
        this.password = password;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
