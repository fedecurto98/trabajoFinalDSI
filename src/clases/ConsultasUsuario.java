package clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConsultasUsuario {

    Conexion conexion;

    public ConsultasUsuario() {
        conexion = new Conexion();
    }

    public boolean agregarUsuario(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (nombre,apellido,legajo,telefono,estado) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getLegajo());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getEstado());

            ps.execute();
            ps.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se inserto registro de usuario en la BD " + ex,"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean eliminarUsuario(int idUsuario) {
        try {
            String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, idUsuario);

            ps.execute();
            ps.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en eliminar usuario de la BD" + ex,"Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

    public boolean modificarUsuario(Usuario usuario) {
        try {
            String sql = "UPDATE usuarios SET nombre=?,apellido=?,legajo=?,telefono=?,estado=? WHERE idUsuario = ?";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getLegajo());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getEstado());
            ps.setInt(6, usuario.getIdUsuario());

            ps.executeUpdate();
            ps.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se inserto actualizacion de usuario en la BD" + ex,"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Usuario consultarUsuario(int idUsuario) {
        Usuario usuario = new Usuario();

        try {
            String sql = "SELECT * FROM usuarios WHERE idUsuario =?";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setLegajo(rs.getString("legajo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setEstado(rs.getString("estado"));
            }

            ps.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Fallo metodo read usuario" + ex,"Error", JOptionPane.ERROR_MESSAGE);
        }
        return usuario;
    }

    public ArrayList<Usuario> consultarTablaUsuarios() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuarios";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setLegajo(rs.getString("legajo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setEstado(rs.getString("estado"));

                listaUsuarios.add(usuario);
            }

            ps.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Fallo metodo read tabla usuarios" + ex,"Error", JOptionPane.ERROR_MESSAGE);
        }
        return listaUsuarios;
    }
    
    public ArrayList<Integer> listaDeUsuariosQueTienenReservas() {

        ArrayList<Integer> listaUsuariosConReservas = new ArrayList<>();

        int nuevoId; 
        
        try {
            String sql = "SELECT idUsuario FROM reservas";

            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                nuevoId = rs.getInt("idUsuario");
                listaUsuariosConReservas.add(nuevoId);
            }

            ps.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FFallo metodo buscar usuarios con reservas " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } 
        
        return listaUsuariosConReservas;
    }
}
