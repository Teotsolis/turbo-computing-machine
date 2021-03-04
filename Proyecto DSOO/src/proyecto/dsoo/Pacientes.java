/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.dsoo;

/**
 *
 * @author ASUS
 */
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
public class Pacientes {
    private String Id;
    private String Nombre;
    private String Apellidos;
    private String Edad;

    public Pacientes() {
    }

    public Pacientes(String Id, String Nombre, String Apellidos, String Edad){
        this.Id = Id;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Edad = Edad;
    }

    public String getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getEdad() {
        return Edad;
    }


    public void setId(String Id) {
        this.Id = Id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public void setEdad(String Edad) {
        this.Edad = Edad;
    }

    
    public boolean agregar(){
        Connection con = null;
	Statement stmt = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        boolean resultado = false;
        
        try{
            con = Connect.getConexion();
            String sql = "INSERT INTO paciente(Nombre, Apellidos, Edad) values('"
                    + Nombre +"','"+Apellidos +"','"+Edad+ "')";
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
            pstm = con.prepareStatement("delete FROM paciente where ID = " +  Id );
            int res = pstm.executeUpdate();
            if(res == 1)
                resultado = true;
            con.close();
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
            String sql = "UPDATE paciente SET Nombre = '" + Nombre + "', Apellidos = '" + Apellidos + "', Edad = '" + Edad + "'Where ID = " + Id;
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
    public ArrayList<Pacientes> getPacientes(){
        ArrayList<Pacientes> grupo = new ArrayList<Pacientes>();
        Pacientes Pac;
        Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
        try {
             con =   Connect.getConexion();
            stmt = con.createStatement();
          
            rs = stmt.executeQuery("select * from paciente");
            while(rs.next()){
                 String Tempid = rs.getString("Id");
                 String TempNom = rs.getString("Nombre");
                 String Tempapell = rs.getString("Apellido");
                 String TempEd = rs.getString("Edad");
                         
                 Pac = new Pacientes(Tempid, TempNom, Tempapell, TempEd);
                 grupo.add(Pac);
            }
            
            con.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDSOO.class.getName()).log(Level.SEVERE, null, ex);
                
    } 
        return grupo;
  }
    
    
    
    
    public Vector<String> RowDent(Pacientes temp){
        Vector<String> Pac = new Vector<String>();
        Pac.add(temp.getId());
        Pac.add(temp.getNombre());
        Pac.add(temp.getApellidos());
        Pac.add(temp.getEdad());
        return Pac;
    }
}