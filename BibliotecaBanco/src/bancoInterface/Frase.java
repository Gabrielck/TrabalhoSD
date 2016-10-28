package bancoInterface;

/**
 *
 * @author gabrielkr
 */
public class Frase implements java.io.Serializable {
    private Integer id;
    private String frase;
    private Integer tipo;

    public Frase() {
    }

    public Frase(Integer id, String frase, Integer tipo) {
        this.id = id;
        this.frase = frase;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    
}
