/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubarrio;
import Conexion.conexion;
import Vista.Menu;
import Vista.Registar;

/**
 *
 * @author HUGO
 */
public class TuBarrio {
public static conexion conexion = new conexion();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                System.out.println("Conectado: "+ conexion.crearConexion());
        if(conexion.crearConexion()){
            Menu f = new Menu();
            f.setVisible(true);
        }
    }
    
}
