package tp03v1;

import java.io.Serializable;

public class Medicamentos extends Productos implements Serializable{
    
    public Medicamentos(String descripcion, double precio) {
        super(descripcion, precio);
    }
    
}
