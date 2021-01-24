package tp03v1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockUsuarios implements Serializable{

    private static StockUsuarios instance = null;

    private List<Persona> stockUsuarios;

    private StockUsuarios() {
        stockUsuarios = new ArrayList<>();
        
        try {
            ObjectInputStream entradaUsuarios = new ObjectInputStream(new FileInputStream("usuarios.obj"));
            stockUsuarios = (List<Persona>)entradaUsuarios.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StockUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(StockUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @return the stockUsuarios
     */
    public List<Persona> getStockUsuarios() {
        return stockUsuarios;
    }

    /**
     * @param aStockUsuarios the stockUsuarios to set
     */
    public void setStockUsuarios(List<Persona> aStockUsuarios) {
        stockUsuarios = aStockUsuarios;
    }

    public static StockUsuarios getInstace() {
        if (instance == null) {
            instance = new StockUsuarios();
            return instance;
        } else {
            return instance;
        }

    }
    public String muestraVeterinarios()
    {
        String salida = "";
        for(int i = 0; i < getStockUsuarios().size(); i++)
        {
            if (getStockUsuarios().get(i) instanceof Veterinario) {
                salida = salida + (i + 1) + " - " + getStockUsuarios().get(i).getUsuario() + "\n";
                
            }
        }
        
        return salida;
    }
}
