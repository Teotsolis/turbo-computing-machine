/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dsoo;

import Utilitys.ProyectoPOO1;
import Utilitys.TXTS1;
import java.sql.Connection;
import java.text.ParseException;
import milib.Alumno;
import milib.Circulo1;
import milib.Conexion;
import milib.Pantalla;

/**
 *
 * @author ASUS
 */
public class Prueba {
    public static void main(String[] args) throws ParseException {
        //Pantalla p1 = new Pantalla();
        //p1.setVisible(true);
        
        /*Circulo1 c1 = new Circulo1(25.5);
        System.out.println("El area es: " + c1.getArea());
        
        Alumno A1 = new Alumno("0122010338", "Isaac Solis", "Ingeneria en sistemas", 20);
        System.out.println(A1.getNombre() + " ' " + A1.getMatricula());
        
        Connection con = Conexion.getConexion();
        con.createStatement();
*/
        TXTS1 txt = new TXTS1();
        txt.setSize(300, 200);
        txt.setVisible(true);
        
    }
}
