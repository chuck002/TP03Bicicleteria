package tp03v1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

class Sistema implements Serializable{

    private StockUsuarios su;

    public Sistema() {
        su = StockUsuarios.getInstace();
    }

    void arrancar() {
        boolean salir = false;
        boolean esta = false;
        do {
            if (su.getStockUsuarios().isEmpty()) {
                Entrada.Informacion("No hay Ningun usuario todavia cargado en el Sistema.");
                su.getStockUsuarios().add(new Administrador(Entrada.LeerString("Administrador nuevo: "), Entrada.LeerString("Contraseña nueva: ")));
                
                try {
                    ObjectOutputStream salidaUsuarios = new ObjectOutputStream(new FileOutputStream("usuarios.obj"));
                    salidaUsuarios.writeObject(su.getStockUsuarios());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
                String user;
                String pass;
                user = Entrada.LeerString("Usuario: ");
                if (user.equals(su.getStockUsuarios().get(0).getUsuario())) {
                    pass = Entrada.LeerString("Bienvenido Administrador.\nContraseña: ");
                    if (pass.equals(su.getStockUsuarios().get(0).getPass())) {
                        su.getStockUsuarios().get(0).ejecutar();
                    }
                } else {
                    if (su.getStockUsuarios().get(1).equals(null)) {
                        Entrada.Informacion("No hay Usuarios Cargados en el Sistema.");
                    } else {
                        for (Persona p : su.getStockUsuarios()) {
                                if(p.getUsuario().equals(user))
                                {
                                    esta = true;
                                    pass = Entrada.LeerString("Contraseña: ");
                                    if(p.getPass().equals(pass))
                                    {
                                        p.ejecutar();
                                    }
                                }
                         }
                        if(!esta)
                        {
                            Entrada.Informacion("No exite ese Usuario;");
                            esta = true;
                        }
                    }
                }
            }

        } while (!salir);
    }

}
