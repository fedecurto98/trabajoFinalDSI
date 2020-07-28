package clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConsultasRecurso {

    Conexion conexion;

    public ConsultasRecurso() {
        conexion = new Conexion();
    }

    public boolean agregarRecurso(Recurso recurso) {
        try {
            String sql = "INSERT INTO recursos (nombre,descripcion,estado,tipo) VALUES (?,?,?,?)";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, recurso.getNombre());
            ps.setString(2, recurso.getDescripcion());
            ps.setString(3, recurso.getEstado());
            ps.setString(4, recurso.getTipo());
            
            ps.execute();
            ps.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se inserto registro de recurso en la BD " + ex,"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean eliminarRecurso(int idRecurso) {
        try {
            String sql = "DELETE FROM recursos WHERE idRecurso = ?";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, idRecurso);
            ps.execute();
            ps.close();
            conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en eliminar recurso de la BD" + ex,"Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

    public boolean modificarRecurso(Recurso recurso) {
        try {
            String sql = "UPDATE recursos SET nombre=?,descripcion=?,estado=?,tipo=? WHERE idRecurso = ?";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, recurso.getNombre());
            ps.setString(2, recurso.getDescripcion());
            ps.setString(3, recurso.getEstado());
            ps.setString(4, recurso.getTipo());
            ps.setInt(5, recurso.getIdRecurso());

            ps.executeUpdate();
            ps.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se inserto actualizacion de recurso en la BD" + ex,"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Recurso consultarRecurso(int idRecurso) {
        Recurso recurso = new Recurso();
        try {
            String sql = "SELECT * FROM recursos WHERE idRecurso =?";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, idRecurso);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                recurso.setIdRecurso(rs.getInt("idRecurso"));
                recurso.setNombre(rs.getString("nombre"));
                recurso.setDescripcion(rs.getString("descripcion"));
                recurso.setEstado(rs.getString("estado"));
                recurso.setTipo(rs.getString("tipo"));
            }

            ps.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println("Fallo metodo read recurso " + ex);
        }
        return recurso;
    }

    public ArrayList<Recurso> consultarTablaRecursos() {
        ArrayList<Recurso> listaRecursos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM recursos";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setIdRecurso(rs.getInt("idRecurso"));
                recurso.setNombre(rs.getString("nombre"));
                recurso.setDescripcion(rs.getString("descripcion"));
                recurso.setEstado(rs.getString("estado"));
                recurso.setTipo(rs.getString("tipo"));

                listaRecursos.add(recurso);
            }
            ps.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Fallo metodo read tabla recursos " + ex,"Error", JOptionPane.ERROR_MESSAGE);
        }
        return listaRecursos;
    }
    
    public ArrayList<Integer> listaDeRecursosQueTienenReservas() {

        ArrayList<Integer> listaRecursosConReservas = new ArrayList<>();

        int nuevoId; 
        
        try {
            String sql = "SELECT idRecurso FROM reservas";

            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                nuevoId = rs.getInt("idRecurso");
                listaRecursosConReservas.add(nuevoId);
            }

            ps.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FFallo metodo buscar recursos con reservas " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } 
        
        return listaRecursosConReservas;
    }
}
