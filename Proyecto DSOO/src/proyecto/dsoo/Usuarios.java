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
public class Usuarios {
    private String Id;
    private String Nombre;
    private String Usuario;
    private String Contraseña;

    public Usuarios() {
    }

    public Usuarios(String Id, String Nombre, String Usuario, String Contraseña) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
    }

    public String getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }
    

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    
    
    public boolean agregar(){
        Connection con = null;
	Statement stmt = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        boolean resultado = false;
        
        try{
            con = Connect.getConexion();
            String sql = "INSERT INTO usuario(Nombre, Usuario, Contraseña) values('"
                    + Nombre +"','"+ Usuario +"','"+ Contraseña + "')";
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
            pstm = con.prepareStatement("delete FROM usuario where ID = " +  Id );
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
            String sql = "UPDATE usuario SET Nombre = '" + Nombre + "', Usuario = '" + Usuario + "', Contraseña = '" + Contraseña + "' Where ID = " + Id;
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
    public ArrayList<Usuarios> getUsuarios(){
        ArrayList<Usuarios> grupo = new ArrayList<Usuarios>();
        Usuarios User;
        Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
        try {
             con =   Connect.getConexion();
            stmt = con.createStatement();
          
            rs = stmt.executeQuery("select * from usuario");
            while(rs.next()){
                 String Tempid = rs.getString("Id");
                 String TempNom = rs.getString("Nombre");
                 String TempUser = rs.getString("Usuario");
                 String TempCont = rs.getString("Contraseña");
                         
                 User = new Usuarios(Tempid, TempNom, TempUser, TempCont);
                 grupo.add(User);
            }
            
            con.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDSOO.class.getName()).log(Level.SEVERE, null, ex);
                
    } 
        return grupo;
  }
    
    
    public String Login(String User, String Cont){
        Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
        boolean Usu = false;
        try {
             con =   Connect.getConexion();
            stmt = con.createStatement();
          
            rs = stmt.executeQuery("select Usuario, Contraseña  from usuario WHERE Usuario = '" + User + "'");
            
            while(rs.next()){
                 Usu = true;
                 String TempCont = rs.getString("Contraseña");
                         if(TempCont.equals(Cont)){
                             return "Exito";
                         }
            }
            
            con.close();
            if(Usu){
                return "Contraseña incorrecta";
            }else{
                return "Usuario inexistente";
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ProyectoDSOO.class.getName()).log(Level.SEVERE, null, ex);
                
    }   
    return "Error en Sql";
}
    
    
    public Vector<String> RowDent(Usuarios temp){
        Vector<String> User = new Vector<String>();
        User.add(temp.getId());
        User.add(temp.getNombre());
        User.add(temp.getUsuario());
        User.add(temp.getContraseña());
        return User;
    }
}
