package tp03v1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recepcionista extends Persona implements Serializable {

    private StockProductos sp;
    private StockTurnos st;
    private StockUsuarios su;

    public Recepcionista(String usuario, String pass) {
        super(usuario, pass);
        sp = StockProductos.getInstance();
        st = StockTurnos.getInstance();
        su = StockUsuarios.getInstace();
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
        int opProductos = 0;
        int opVet = 0;
        do {
            op = Entrada.LeerInt("Recepcionista...\n"
                    + "1. Cargar Turnos.\n"
                    + "2. Vender Productos.\n"
                    + "3. Salir.");
            switch (op) {
                case 1:
                    int turno = 0;
                    boolean sePudo = false;
                    Animales a = null;
                    if (st.getStockTurnos().isEmpty()) {
                        Entrada.Informacion("No hay ningun turno asignado.");
                        do {
                            turno = Entrada.LeerInt(st.muestraTurnos() + "En que horario asignara el turno?");
                            if (turno - 1 == 6 || turno - 1 == 7 || turno - 1 == 8) {
                                Entrada.Informacion("Estos horarios estan reservados para el Descanso.\nNo se puede asignar el turno.");
                            } else if (turno - 1 == 19) {
                                Entrada.Informacion("Horario de Salida.\nNo se puede asignar el turno.");
                            } else {
                                int t = Entrada.LeerInt("Que tipo de animal es el paciente: \n"
                                        + "1. Perro.\n"
                                        + "2. Gato.\n"
                                        + "3. Tortuga.\n"
                                        + "4. Canario.\n");
                                switch (t) {
                                    case 1:
                                        a = new Perro("", "", "", 0);
                                        a.setTipo("Perro");
                                        break;
                                    case 2:
                                        a = new Gato("", "", "", 0);
                                        a.setTipo("Gato");
                                        break;
                                    case 3:
                                        a = new Tortuga("", "", "", 0);
                                        a.setTipo("Tortuga");
                                        break;
                                    case 4:
                                        a = new Canario("", "", "", 0);
                                        a.setTipo("Canario");
                                        break;
                                    default:
                                        Entrada.Informacion("No es una opcion valida.");
                                        break;
                                }
                                boolean asignable = false;
                                String vete = "";
                                do {
                                    opVet = Entrada.LeerInt("Asignar Veterinario: " + su.muestraVeterinarios());
                                    Veterinario v = (Veterinario) su.getStockUsuarios().get(opVet - 1);
                                    for (int i = 0; i < v.getEspecialidad().size(); i++) {
                                        if (v.getEspecialidad().get(i).equals(a.getTipo())) {
                                            asignable = true;
                                            st.getVeterinario().set(turno - 1, v);
                                            vete = v.getUsuario();
                                        }
                                    }
                                } while (!asignable);
                                st.getHorarios().set(turno - 1, st.getHorarios().get(turno - 1).concat(" - " + a.getTipo() + " " + vete));
                                a.setNombreMascota(Entrada.LeerString("Nombre del paciente: "));
                                a.setNombreDueno(Entrada.LeerString("Nombre del dueño: "));
                                a.setNumeroDeContacto(Entrada.LeerInt("Numero de contacto: "));
                                st.getStockTurnos().add(turno - 1, a);

                                try {
                                    ObjectOutputStream salidaTurnos = new ObjectOutputStream(new FileOutputStream("turnos.obj"));
                                    salidaTurnos.writeObject(st.getStockTurnos());
                                    salidaTurnos.writeObject(st.getHorarios());
                                    salidaTurnos.writeObject(st.getVeterinario());
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                sePudo = true;
                            }
                        } while (!sePudo);

                    } else {
                        sePudo = false;

                        do {
                            turno = Entrada.LeerInt(st.muestraTurnos() + "Que turno desea Asignar?:");
                            if (turno - 1 == 6 || turno - 1 == 7 || turno - 1 == 8) {
                                Entrada.Informacion("Estos horarios estan reservados para el Descanso.\nNo se puede asignar el turno.");
                            } else if (turno - 1 == 19) {
                                Entrada.Informacion("Horario de Salida.\nNo se puede asignar el turno.");
                            } else if (st.getHorarios().get(turno - 1).contains("-")) {
                                Entrada.Informacion("No se puede asignar este horario, ya se encuentra ocupado.");
                            } else {
                                int t = Entrada.LeerInt("Que tipo de animal es el paciente: \n"
                                        + "1. Perro.\n"
                                        + "2. Gato.\n"
                                        + "3. Tortuga.\n"
                                        + "4. Canario.\n");
                                switch (t) {
                                    case 1:
                                        a = new Perro("", "", "", 0);
                                        a.setTipo("Perro");
                                        break;
                                    case 2:
                                        a = new Gato("", "", "", 0);
                                        a.setTipo("Gato");
                                        break;
                                    case 3:
                                        a = new Tortuga("", "", "", 0);
                                        a.setTipo("Tortuga");
                                        break;
                                    case 4:
                                        a = new Canario("", "", "", 0);
                                        a.setTipo("Canario");
                                        break;
                                    default:
                                        Entrada.Informacion("No es una opcion valida.");
                                        break;
                                }
                                boolean asignable = false;
                                String vete = "";
                                do {
                                    opVet = Entrada.LeerInt("Asignar Veterinario: \n" + su.muestraVeterinarios());
                                    Veterinario v = (Veterinario) su.getStockUsuarios().get(opVet - 1);
                                    for (int i = 0; i < v.getEspecialidad().size(); i++) {
                                        if (v.getEspecialidad().get(i).equals(a.getTipo())) {
                                            asignable = true;
                                            st.getVeterinario().set(turno - 1, v);
                                            vete = v.getUsuario();
                                        }
                                    }
                                    if (!asignable) {
                                        Entrada.Informacion("Este Veterinario no es asignable.\nEl paciente no esta dentro de sus especialidades.");
                                    }
                                } while (!asignable);
                                st.getHorarios().set(turno - 1, st.getHorarios().get(turno - 1).concat("- " + a.getTipo() + " " + vete));
                                a.setNombreMascota(Entrada.LeerString("Nombre del paciente: "));
                                a.setNombreDueno(Entrada.LeerString("Nombre del dueño: "));
                                a.setNumeroDeContacto(Entrada.LeerInt("Numero de contacto: "));
                                st.getStockTurnos().add(turno - 1, a);

                                try {
                                    ObjectOutputStream salidaTurnos = new ObjectOutputStream(new FileOutputStream("turnos.obj"));
                                    salidaTurnos.writeObject(st.getStockTurnos());
                                    salidaTurnos.writeObject(st.getHorarios());
                                    salidaTurnos.writeObject(st.getVeterinario());
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                sePudo = true;
                            }

                        } while (!sePudo);
                    }
                    break;
                case 2:
                    int opVenta = 0;
                    boolean salirVenta = false;
                    do {
                        opProductos = Entrada.LeerInt("Venta de Productos.\n"
                                + "Stock: \n" + sp.muestraRegulares() + (sp.getStockProductos().size() + 1) + " Salir.\n"
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
                                        ObjectOutputStream salidaProducto = new ObjectOutputStream(new FileOutputStream("productos.obj"));
                                        salidaProducto.writeObject(sp.getStockProductos());
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Recepcionista.class.getName()).log(Level.SEVERE, null, ex);
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
                case 3:
                    salir = true;
                    break;
                default:
                    Entrada.Informacion("No es una opcion valida.");
                    break;
            }

        } while (!salir);
    }

}
