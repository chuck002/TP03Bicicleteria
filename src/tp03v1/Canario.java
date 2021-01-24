package tp03v1;

import java.io.Serializable;

public class Canario extends Animales implements Serializable{
    
    public Canario(String tipo, String nombreMascota, String nombreDueno, int numeroDeContacto) {
        super(tipo, nombreMascota, nombreDueno, numeroDeContacto);
    }
    
}
