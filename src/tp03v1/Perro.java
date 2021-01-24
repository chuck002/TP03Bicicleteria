package tp03v1;

import java.io.Serializable;

public class Perro extends Animales implements Serializable{
    
    public Perro(String tipo, String nombreMascota, String nombreDueno, int numeroDeContacto) {
        super(tipo, nombreMascota, nombreDueno, numeroDeContacto);
    }
    
}
