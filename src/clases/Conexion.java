package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {

//    private final String bd = "bd_dsi";
//    private final String user = "root";
//    private final String password = "";
//    private final String url = "jdbc:mysql://localhost/" + bd;
    private static Connection con;

    public static Connection conectar() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:src\\databases\\bd_dsi.db");
            System.out.println("Se conecto");
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion con la base de datos");
            return null;
        }
    }

    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo desconectar");
        }
    }
    //conexion a MySQL
//    public Connection conectar() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
//            System.out.println("Se conecto");
//        } catch (SQLException e) {
//            System.err.println("No se conecto a la BD " + e);
//            JOptionPane.showMessageDialog(null, "Error en la conexion con la base de datos");
//        } catch (ClassNotFoundException ex) { 
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return con;
//        
//    }
//    

}
