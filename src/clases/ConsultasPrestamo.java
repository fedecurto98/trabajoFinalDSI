package clases;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConsultasPrestamo {

    Conexion conexion;

    public ConsultasPrestamo() {
        conexion = new Conexion();
    }

    public boolean agregarPrestamo(Prestamo prestamo) {
        try {
            String sql = "INSERT INTO prestamos (idReserva,fechaInicio,estado) VALUES (?,?,?)";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, prestamo.getIdReserva());
            ps.setDate(2, prestamo.getFechaInicio());
            ps.setString(3, prestamo.getEstado());

            ps.execute();
            ps.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se inserto registro de prestamo en la BD " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public ArrayList<Prestamo> consultarTablaPrestamosdeUltimaReserva() {
        ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
        int idPrestamo = 0;
        try {
            String sql = "SELECT * FROM prestamos WHERE idReserva=(SELECT max(idReserva)FROM prestamos)";
            String sentenciaTransformarFecha = "SELECT date((select fechaInicio from prestamos where idPrestamo=?)/1000, 'unixepoch') as fechaInicio";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            PreparedStatement ps1 = conexion.conectar().prepareStatement(sentenciaTransformarFecha);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Prestamo prestamo = new Prestamo();

                idPrestamo = rs.getInt("idPrestamo");
                prestamo.setIdPrestamo(idPrestamo);
                prestamo.setIdReserva(rs.getInt("idReserva"));

                ps1.setInt(1, idPrestamo);
                ResultSet rs1 = ps1.executeQuery();
                java.util.Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(rs1.getString("fechaInicio"));
                Date fechaInicioFormatoSQL = new Date(fechaInicio.getTime());

                prestamo.setFechaInicio(fechaInicioFormatoSQL);

                prestamo.setEstado(rs.getString("estado"));

                listaPrestamos.add(prestamo);
            }

            ps.close();
            ps1.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo metodo read tabla prestamos de una reserva" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPrestamos;
    }
    
    
    public ArrayList<Prestamo> consultarTablaDeTodosLosPrestamos() {
        ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
        int idPrestamo = 0;
        try {
            String sql = "SELECT * FROM prestamos";
            String sentenciaTransformarFecha = "SELECT date((select fechaInicio from prestamos where idPrestamo=?)/1000, 'unixepoch') as fechaInicio";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            PreparedStatement ps1 = conexion.conectar().prepareStatement(sentenciaTransformarFecha);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Prestamo prestamo = new Prestamo();

                idPrestamo = rs.getInt("idPrestamo");
                prestamo.setIdPrestamo(idPrestamo);
                prestamo.setIdReserva(rs.getInt("idReserva"));

                ps1.setInt(1, idPrestamo);
                ResultSet rs1 = ps1.executeQuery();
                java.util.Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(rs1.getString("fechaInicio"));
                Date fechaInicioFormatoSQL = new Date(fechaInicio.getTime());

                prestamo.setFechaInicio(fechaInicioFormatoSQL);

                prestamo.setEstado(rs.getString("estado"));

                listaPrestamos.add(prestamo);
            }

            ps.close();
            ps1.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "Fallo metodo read tabla todos los prestamos" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPrestamos;
    }
    
    public boolean modificarEstadoPrestamo(Prestamo prestamo) {
        try {
            String sql = "UPDATE prestamos SET estado=? WHERE idPrestamo = ?";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, prestamo.getEstado());
            ps.setInt(2, prestamo.getIdPrestamo());
            
            ps.executeUpdate();
            ps.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se inserto actualizacion de prestamo en la BD " + ex,"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Prestamo consultarPrestamo(int idPrestamo) {
        Prestamo prestamo = new Prestamo();
        try {
            String sql = "SELECT * FROM prestamos WHERE idPrestamo =?";
            String sentenciaTransformarFecha = "SELECT date((select fechaInicio from prestamos where idPrestamo=?)/1000, 'unixepoch') as fechaInicio";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            PreparedStatement ps1 = conexion.conectar().prepareStatement(sentenciaTransformarFecha);
            ps.setInt(1, idPrestamo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setIdReserva(rs.getInt("idReserva"));
                ps1.setInt(1, idPrestamo);
                ResultSet rs1 = ps1.executeQuery();
                java.util.Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(rs1.getString("fechaInicio"));
                Date fechaInicioFormatoSQL = new Date(fechaInicio.getTime());
                prestamo.setFechaInicio(fechaInicioFormatoSQL);
                prestamo.setEstado(rs.getString("estado"));
            }

            ps.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Fallo metodo read prestamo" + ex,"Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prestamo;
    }

}
