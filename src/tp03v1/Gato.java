package tp03v1;

import java.io.Serializable;

public class Gato extends Animales implements Serializable{
    
    public Gato(String tipo, String nombreMascota, String nombreDueno, int numeroDeContacto) {
        super(tipo, nombreMascota, nombreDueno, numeroDeContacto);
    }
    
}
