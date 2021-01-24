package tp03v1;

import java.io.Serializable;

abstract class Animales implements Serializable {

    private String tipo;
    private String nombreMascota;
    private String nombreDueno;
    private int numeroDeContacto;

    public Animales(String tipo, String nombreMascota, String nombreDueno, int numeroDeContacto) {
        this.tipo = tipo;
        this.nombreMascota = nombreMascota;
        this.nombreDueno = nombreDueno;
        this.numeroDeContacto = numeroDeContacto;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the nombreMascota
     */
    public String getNombreMascota() {
        return nombreMascota;
    }

    /**
     * @param nombreMascota the nombreMascota to set
     */
    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    /**
     * @return the nombreDueno
     */
    public String getNombreDueno() {
        return nombreDueno;
    }

    /**
     * @param nombreDueno the nombreDueno to set
     */
    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    /**
     * @return the numeroDeContacto
     */
    public int getNumeroDeContacto() {
        return numeroDeContacto;
    }

    /**
     * @param numeroDeContacto the numeroDeContacto to set
     */
    public void setNumeroDeContacto(int numeroDeContacto) {
        this.numeroDeContacto = numeroDeContacto;
    }

    @Override
    public String toString() {
        return "Animales{" + "tipo=" + tipo + ", nombreMascota=" + nombreMascota + ", nombreDueno=" + nombreDueno + ", numeroDeContacto=" + numeroDeContacto + '}';
    }

}
