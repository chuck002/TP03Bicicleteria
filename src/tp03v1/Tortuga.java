package tp03v1;

import java.io.Serializable;

public class Tortuga extends Animales implements Serializable{
    
    public Tortuga(String tipo, String nombreMascota, String nombreDueno, int numeroDeContacto) {
        super(tipo, nombreMascota, nombreDueno, numeroDeContacto);
    }
    
}
