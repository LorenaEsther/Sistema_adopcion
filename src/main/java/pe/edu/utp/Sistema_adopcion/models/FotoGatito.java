package pe.edu.utp.Sistema_adopcion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Fotos_gatitos")
public class FotoGatito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "gatito_id", nullable = false)
    private Gatito gatito;

    private String urlFoto;

    // Constructors, Getters, and Setters

    public FotoGatito(int id, Gatito gatito, String urlFoto) {
        this.id = id;
        this.gatito = gatito;
        this.urlFoto = urlFoto;
    }

    public FotoGatito() {
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

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    
}
