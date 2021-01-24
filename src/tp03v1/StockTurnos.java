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

class StockTurnos implements Serializable{

    private List<Animales> stockTurnos;
    private static StockTurnos instance = null;
    private List<String> horarios;
    private List<Veterinario> veterinario;

    private StockTurnos() {

        stockTurnos = new ArrayList<>();
        veterinario = new ArrayList<>();
        horarios = new ArrayList<>();
        horarios.add("9:00");
        horarios.add("9:30");
        horarios.add("10:00");
        horarios.add("10:30");
        horarios.add("11:00");
        horarios.add("11:30");
        horarios.add("12:00 - Descanso.");
        horarios.add("12:30 - Descanso.");
        horarios.add("13:00 - Descanso.");
        horarios.add("13:30");
        horarios.add("14:00");
        horarios.add("14:30");
        horarios.add("15:00");
        horarios.add("15:30");
        horarios.add("16:00");
        horarios.add("16:30");
        horarios.add("17:00");
        horarios.add("17:30");
        horarios.add("18:00 - Salida.");

        setStockTurnos(stockTurnos);
        setVeterinario(veterinario);
        
        try {
            ObjectInputStream entradaTurnos = new ObjectInputStream(new FileInputStream("turnos.obj"));
            stockTurnos = (List<Animales>)entradaTurnos.readObject();
            horarios = (List<String>) entradaTurnos.readObject();
            veterinario = (List<Veterinario>)entradaTurnos.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StockTurnos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(StockTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static StockTurnos getInstance() {
        if (instance == null) {
            instance = new StockTurnos();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * @return the stockTurnos
     */
    public List<Animales> getStockTurnos() {
        return stockTurnos;
    }

    /**
     * @param stockTurnos the stockTurnos to set
     */
    public void setStockTurnos(List<Animales> stockTurnos) {
        Animales e = null;
        for (int i = 0; i < horarios.size(); i++) {
            stockTurnos.add(e);
        }
    }

    /**
     * @return the horarios
     */
    public List<String> getHorarios() {
        return horarios;
    }

    /**
     * @param horarios the horarios to set
     */
    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public String muestraTurnos() {
        String salida = "";
        for (int i = 0; i < horarios.size(); i++) {
            salida = salida + (i + 1) + " - " + horarios.get(i) + "\n";
        }
        return salida;
    }

    /**
     * @return the veterinario
     */
    public List<Veterinario> getVeterinario() {
        return veterinario;
    }

    /**
     * @param veterinario the veterinario to set
     */
    public void setVeterinario(List<Veterinario> veterinario) {
        Veterinario v = null;
        for(int i = 0;i < horarios.size(); i++ )
        {
            veterinario.add(v);
        }
    }

}
