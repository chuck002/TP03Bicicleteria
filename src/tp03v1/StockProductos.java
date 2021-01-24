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

public class StockProductos implements Serializable{

    private List<Productos> stockProductos;
    private static StockProductos instance = null;

    private StockProductos() {
        stockProductos = new ArrayList<>();
        
        try {
            ObjectInputStream entradaProductos = new ObjectInputStream(new FileInputStream("productos.obj"));
            stockProductos = (List<Productos>)entradaProductos.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StockProductos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(StockProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static StockProductos getInstance() {
        if (instance == null) {
            instance = new StockProductos();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * @return the stockProductos
     */
    public List<Productos> getStockProductos() {
        return stockProductos;
    }

    /**
     * @param stockProductos the stockProductos to set
     */
    public void setStockProductos(List<Productos> stockProductos) {
        this.stockProductos = stockProductos;
    }

    public String muestraRegulares() {
        String salida = "";

        for (int i = 0; i < stockProductos.size(); i++) {
            if (stockProductos.get(i) instanceof Regulares) {
                salida = salida + (i + 1) + " - " + stockProductos.get(i).toString() + "\n";
            }
        }
        return salida;
    }

    public String muestraProductos() {
        String salida = "";

        for (int i = 0; i < stockProductos.size(); i++) {
            salida = salida + (i + 1) + " - " + stockProductos.get(i).toString() + "\n";
        }
        return salida;
    }

}
