/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aimagenes;

/**
 *
 * @author Trucos Jen <Jen at jenjen172009@gmail.com>
 */
public class Main {
    public static conexion conexion = new conexion();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Conectado: "+conexion.crearConexion());
        if(conexion.crearConexion()){
            Form f = new Form();
            f.setVisible(true);
        }
    }
}
