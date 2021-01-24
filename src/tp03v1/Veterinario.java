package tp03v1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Veterinario extends Persona implements Serializable {

    private List<String> especialidad;
    private List<String> rangoDias;

    private StockProductos sp;
    private StockTurnos st;

    public Veterinario(String usuario, String pass) {
        super(usuario, pass);
        especialidad = new ArrayList<>();
        rangoDias = new ArrayList<>();
        sp = StockProductos.getInstance();
        st = StockTurnos.getInstance();
    }

    /**
     * @return the especialidad
     */
    public List<String> getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(List<String> especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @return the sp
     */
    public StockProductos getSp() {
        return sp;
    }

    /**
     * @param sp the sp to set
     */
    public void setSp(StockProductos sp) {
        this.sp = sp;
    }

    /**
     * @return the st
     */
    public StockTurnos getSt() {
        return st;
    }

    /**
     * @param st the st to set
     */
    public void setSt(StockTurnos st) {
        this.st = st;
    }

    @Override
    public void ejecutar() {
        boolean salir = false;
        int op = 0;
        do {
            op = Entrada.LeerInt("Veterinario... \n"
                    + "¿Que desea hacer?\n"
                    + "1. Ver Turnos Asignados.\n"
                    + "2. Atender Turnos.\n"
                    + "3. Vender Productos.\n"
                    + "4. Salir.");
            switch (op) {
                case 1:
                    Entrada.Informacion(st.muestraTurnos());
                    break;
                case 2:
                    boolean termino = false;
                    int opTurno = 0;
                    do {
                        opTurno = Entrada.LeerInt(st.muestraTurnos() + (st.getHorarios().size() + 1) + " Salir.\n" + "Atender a: ");
                        if (opTurno == st.getHorarios().size() + 1) {
                            termino = true;
                        } else if (st.getHorarios().get(opTurno - 1).contains(" - ")) {
                            Entrada.Informacion("Ese turno ya fue atendido. ");
                            termino = true;

                        } else if (!st.getHorarios().get(opTurno - 1).contains(this.getUsuario())) {
                            Entrada.Informacion("El veterinario no puede atender este turno. Esta asignado a otro colega.");
                            termino = true;
                        } else {
                            Entrada.Informacion("Paciente atendido: " + st.getHorarios().get(opTurno - 1));
                            st.getStockTurnos().remove(opTurno - 1);
                            st.getVeterinario().remove(opTurno - 1);
                            st.getHorarios().set(opTurno - 1, "Atendido");

                            try {
                                ObjectOutputStream salidaTurnos = new ObjectOutputStream(new FileOutputStream("turnos.obj"));
                                salidaTurnos.writeObject(st.getStockTurnos());
                                salidaTurnos.writeObject(st.getHorarios());
                                salidaTurnos.writeObject(st.getVeterinario());
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Veterinario.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Veterinario.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            termino = true;

                        }
                    } while (!termino);
                    break;
                case 3:
                    int opVenta = 0;
                    int opProductos = 0;
                    boolean salirVenta = false;
                    do {
                        opProductos = Entrada.LeerInt("Venta de Productos.\n"
                                + "Stock: \n" + sp.muestraProductos() + (sp.getStockProductos().size() + 1) + " Salir.\n"
                                + "Producto: ");
                        if (opProductos != sp.getStockProductos().size() + 1) {
                            opVenta = Entrada.LeerInt(sp.getStockProductos().get(opProductos - 1)
                                    + "Vender?:\n"
                                    + "1. Si.\n"
                                    + "2. No.");
                            switch (opVenta) {
                                case 1:
                                    Entrada.Informacion(sp.getStockProductos().get(opProductos - 1).toString() + "Vendido.");
                                    sp.getStockProductos().remove(opProductos - 1);

                                    try {
                                        ObjectOutputStream salidaProductos = new ObjectOutputStream(new FileOutputStream("productos.obj"));
                                        salidaProductos.writeObject(sp.getStockProductos());
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(Veterinario.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Veterinario.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    break;

                                case 2:
                                    salirVenta = true;
                                    break;
                                default:
                                    Entrada.Informacion("No es una opcion valida.");
                                    break;
                            }
                        } else {
                            salirVenta = true;
                        }

                    } while (!salirVenta);
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    Entrada.Informacion("No es una opcion valida.");
                    break;
            }

        } while (!salir);
    }

    /**
     * @return the rangoDias
     */
    public List<String> getRangoDias() {
        return rangoDias;
    }

    /**
     * @param rangoDias the rangoDias to set
     */
    public void setRangoDias(List<String> rangoDias) {
        this.rangoDias = rangoDias;
    }

    public String verEspecialidad() {
        String salida = "";
        for (int i = 0; i < especialidad.size(); i++) {
            salida = salida + " " + especialidad.get(i);
        }
        return salida;
    }

}
