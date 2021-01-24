package tp03v1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Administrador extends Persona implements Serializable {

    private StockUsuarios su;
    private StockProductos sp;

    public Administrador(String usuario, String pass) {
        super(usuario, pass);
        su = StockUsuarios.getInstace();
        sp = StockProductos.getInstance();
    }

    /**
     * @return the su
     */
    public StockUsuarios getSu() {
        return su;
    }

    /**
     * @param su the su to set
     */
    public void setSu(StockUsuarios su) {
        this.su = su;
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

    @Override
    public void ejecutar() {
        boolean salir = false;
        int op = 0;
        int opUsuarios = 0;
        int opProductos = 0;

        do {
            op = Entrada.LeerInt("Bienvenido Administrador...\n"
                    + "1. Cargar Usuarios.\n"
                    + "2. Cargar Productos.\n"
                    + "3. Salir del Usuario.\n"
                    + "4. Salir del Sistema.");
            switch (op) {
                case 1:
                    boolean salirUsuarios = false;
                    do {
                        opUsuarios = Entrada.LeerInt("Cargar Nuevo Empleado...\n"
                                + "1. Cargar Veterinario.\n"
                                + "2. Cargar Recepcionista.\n"
                                + "3. Volver Atras.");
                        switch (opUsuarios) {
                            case 1:
                                Veterinario v = new Veterinario(Entrada.LeerString("Usuario: "), Entrada.LeerString("Contraseña: "));
                                int especialidad = 0;
                                int diasLaborales = 0;
                                boolean especi = false;
                                boolean dias = false;
                                do {
                                    especialidad = Entrada.LeerInt("¿En que se especializa?\n"
                                            + "1. Perros.\n"
                                            + "2. Gatos.\n"
                                            + "3. Tortugas.\n"
                                            + "4. Canarios.\n"
                                            + "5. Terminar.");
                                    switch (especialidad) {
                                        case 1:
                                            boolean cargadaPerro = false;
                                            for (int i = 0; i < v.getEspecialidad().size(); i++) {
                                                if (v.getEspecialidad().get(i).equals("Perro")) {
                                                    Entrada.Informacion("Ya se encuentra cargada esa especialidad.");
                                                    cargadaPerro = true;
                                                }
                                            }
                                            if (!cargadaPerro) {
                                                v.getEspecialidad().add("Perro");
                                            }
                                            break;

                                        case 2:
                                            boolean cargadaGato = false;
                                            for (int i = 0; i < v.getEspecialidad().size(); i++) {
                                                if (v.getEspecialidad().get(i).equals("Gato")) {
                                                    Entrada.Informacion("Ya se encuentra cargada esa especialidad.");
                                                    cargadaGato = true;
                                                }
                                            }
                                            if (!cargadaGato) {
                                                v.getEspecialidad().add("Gato");
                                            }
                                            break;

                                        case 3:
                                            boolean cargadaTortuga = false;
                                            for (int i = 0; i < v.getEspecialidad().size(); i++) {
                                                if (v.getEspecialidad().get(i).equals("Tortuga")) {
                                                    Entrada.Informacion("Ya se encuentra cargada esa especialidad.");
                                                    cargadaTortuga = true;
                                                }
                                            }
                                            if (!cargadaTortuga) {
                                                v.getEspecialidad().add("Tortuga");
                                            }
                                            break;

                                        case 4:
                                            boolean cargadaCanario = false;
                                            for (int i = 0; i < v.getEspecialidad().size(); i++) {
                                                if (v.getEspecialidad().get(i).equals("Canario")) {
                                                    Entrada.Informacion("Ya se encuentra cargada esa especialidad.");
                                                    cargadaCanario = true;
                                                }
                                            }
                                            if (!cargadaCanario) {
                                                v.getEspecialidad().add("Canario");
                                            }
                                            break;

                                        case 5:
                                            especi = true;
                                            break;
                                        default:
                                            Entrada.Informacion("No es una opcion valida.");
                                            break;
                                    }
                                } while (!especi && v.getEspecialidad().size() < 4);
                                do {
                                    diasLaborales = Entrada.LeerInt("Dias que trabaja: \n"
                                            + "1. Lunes.\n"
                                            + "2. Martes.\n"
                                            + "3. Miercoles.\n"
                                            + "4. Jueves.\n"
                                            + "5. Viernes.\n"
                                            + "6. Sabado.\n");
                                    switch (diasLaborales) {
                                        case 1:
                                            v.getRangoDias().add("Lunes");
                                            break;
                                        case 2:
                                            v.getRangoDias().add("Martes");
                                            break;
                                        case 3:
                                            v.getRangoDias().add("Miercoles.");
                                            break;
                                        case 4:
                                            v.getRangoDias().add("Jueves");
                                            break;
                                        case 5:
                                            v.getRangoDias().add("Viernes");
                                            break;
                                        case 6:
                                            v.getRangoDias().add("Sabado");
                                            break;
                                        default:
                                            Entrada.Informacion("No es una opcion valida.");
                                            break;
                                    }

                                } while (v.getRangoDias().size() < 3);
                                su.getStockUsuarios().add(v);

                                try {
                                    ObjectOutputStream salidaUsuarios = new ObjectOutputStream(new FileOutputStream("usuarios.obj"));
                                    salidaUsuarios.writeObject(su.getStockUsuarios());
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                break;
                            case 2:
                                su.getStockUsuarios().add(new Recepcionista(Entrada.LeerString("Usuario: "), Entrada.LeerString("Contraseña: ")));

                                try {
                                    ObjectOutputStream salidaUsuarios = new ObjectOutputStream(new FileOutputStream("usuarios.obj"));
                                    salidaUsuarios.writeObject(su.getStockUsuarios());
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                break;
                            case 3:
                                salirUsuarios = true;
                                break;
                            default:
                                Entrada.Informacion("No es una opcion valida.");
                                break;
                        }
                    } while (!salirUsuarios);

                    break;
                case 2:
                    boolean salirProductos = false;
                    do {
                        opProductos = Entrada.LeerInt("Cargar nuevo Producto... \n"
                                + "1. Cargar Medicamento.\n"
                                + "2. Cargar Regulares.\n"
                                + "3. Volver Atras.");
                        switch (opProductos) {
                            case 1:
                                sp.getStockProductos().add(new Medicamentos(Entrada.LeerString("Descripcion del Medicamento: "), Entrada.LeerDouble("Precio: ")));

                                try {
                                    ObjectOutputStream salidaProductos = new ObjectOutputStream(new FileOutputStream("productos.obj"));
                                    salidaProductos.writeObject(sp.getStockProductos());
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                break;

                            case 2:
                                sp.getStockProductos().add(new Regulares(Entrada.LeerString("Descripcion del producto: "), Entrada.LeerDouble("Precio: ")));

                                try {
                                    ObjectOutputStream salidaProductos = new ObjectOutputStream(new FileOutputStream("productos.obj"));
                                    salidaProductos.writeObject(sp.getStockProductos());
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                break;
                            case 3:
                                salirProductos = true;
                                break;
                            default:
                                Entrada.Informacion("No es una opcion valida.");
                                break;
                        }
                    } while (!salirProductos);

                    break;
                case 3:
                    salir = true;
                    break;
                case 4:
                    Entrada.Informacion("Saliendo del sistema.");
                    System.exit(0);
                    break;
                default:
                    Entrada.Informacion("No es una opcion valida.");
                    break;
            }

        } while (!salir);
    }

}
