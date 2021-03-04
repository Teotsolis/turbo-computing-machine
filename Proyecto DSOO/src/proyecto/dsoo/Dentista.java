/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dsoo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Dentista {
    private String Id;
    private String Nombre;
    private String Apellido;
    private String Especialidad;
    private int Telefono;
    private int celular;
    private String Dias;

    public Dentista() {
    }

    public Dentista(String Id, String Nombre, String Apellido, String Especialidad, int Telefono, int celular, String Dias) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Especialidad = Especialidad;
        this.Telefono = Telefono;
        this.celular = celular;
        this.Dias = Dias;
    }

    public String getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public int getTelefono() {
        return Telefono;
    }

    public int getCelular() {
        return celular;
    }

    public String getDias() {
        return Dias;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public void setDias(String Dias) {
        this.Dias = Dias;
    }
    
    public boolean agregar(){
        Connection con = null;
	Statement stmt = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        boolean resultado = false;
        
        try{
            con = Connect.getConexion();
            String sql = "INSERT INTO dentista(Nombre, Apellidos,Especialidad, Telefono, Celular, Dias) values('"
                    + Nombre +"','"+Apellido +"','"+Especialidad+ "', " + Telefono +"," +celular + ",'" + Dias +"')";
            stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            if(res == 1){
                resultado = true;
            }
            con.close();
        }catch(SQLException ex) {
            Logger.getLogger(ProyectoDSOO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
   public boolean eliminar(){
        boolean resultado = false;
        Connection con = null;
        
        con = Connect.getConexion();
        PreparedStatement pstm ;
        
        try {
            pstm = con.prepareStatement("delete FROM dentista where ID = " +  Id );
            int res = pstm.executeUpdate();
            if(res == 1)
                resultado = true;
            con.close();
            System.out.println("proyecto.dsoo.Dentista.eliminar()");
        } catch (SQLException ex) {
            //setMensaje("Error:" +ex.getMessage());
            System.out.println("ERROR" + ex.getMessage());
        }
        
        return resultado;
    }
   public boolean modificar(){
       Connection con = null;
	Statement stmt = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        boolean resultado = false;
        
        try{
            con = Connect.getConexion();
            String sql = "UPDATE dentista SET Nombre = '" + Nombre + "', Apellidos = '" + Apellido + "', Especialidad = '" + Especialidad + "', Telefono = "
                    + Telefono + ", Celular = " + celular + ", Dias = '" + Dias + "' Where ID = '" + Id + "'";
            stmt = con.createStatement();
            int res = stmt.executeUpdate(sql);
            if(res == 1){
                resultado = true;
            }
            con.close();
        }catch(SQLException ex) {
            Logger.getLogger(ProyectoDSOO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
   }
    public ArrayList<Dentista> getDentistas(){
        ArrayList<Dentista> grupo = new ArrayList<Dentista>();
        Dentista Dent;
        Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
        try {
             con =   Connect.getConexion();
            stmt = con.createStatement();
          
            rs = stmt.executeQuery("select * from dentista");
            while(rs.next()){
                 String Tempid = rs.getString("Id");
                 String TempNom = rs.getString("Nombre");
                 String Tempapell = rs.getString("Apellidos");
                 String TempEspec = rs.getString("Especialidad");
                 int Temptel = rs.getInt("Telefono");
                 int Tempcel = rs.getInt("Celular");
                 String TempDias = rs.getString("Dias");
                         
                 Dent = new Dentista(Tempid, TempNom, Tempapell, TempEspec, Temptel, Tempcel, TempDias);
                 grupo.add(Dent);
            }
            
            con.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDSOO.class.getName()).log(Level.SEVERE, null, ex);
                
    } 
        return grupo;
  }
    
    
    
    public Vector<String> RowDent(Dentista temp){
        Vector<String> Dent = new Vector<String>();
        Dent.add(temp.getId());
        Dent.add(temp.getNombre());
        Dent.add(temp.getApellido());
        Dent.add(temp.getEspecialidad());
        Dent.add(Integer.toString(temp.getTelefono()));
        Dent.add(Integer.toString(temp.getCelular()));
        Dent.add(temp.getDias());
        return Dent;
    }
}