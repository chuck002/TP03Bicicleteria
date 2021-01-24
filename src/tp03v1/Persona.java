package tp03v1;

import java.io.Serializable;

public abstract class Persona implements Serializable{
    
    private String usuario;
    private String pass;

    public Persona(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Persona{" + "usuario=" + getUsuario() + ", pass=" + getPass() + '}';
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public abstract void ejecutar();
}
