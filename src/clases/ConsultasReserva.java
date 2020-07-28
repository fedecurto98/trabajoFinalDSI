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

public class ConsultasReserva {

    Conexion conexion;

    public ConsultasReserva() {
        conexion = new Conexion();
    }

    public boolean agregarReserva(Reserva reserva) {
        try {
            String sql = "INSERT INTO reservas (fechaDesde,fechaHasta,dia,horaInicio,horaFin,idRecurso,idUsuario,observaciones) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ps.setDate(1, reserva.getFechaDesde());
            ps.setDate(2, reserva.getFechaHasta());
            ps.setString(3, reserva.getDia());
            ps.setString(4, reserva.getHoraInicio());
            ps.setString(5, reserva.getHoraFin());
            ps.setInt(6, reserva.getIdRecurso());
            ps.setInt(7, reserva.getIdUsuario());
            ps.setString(8, reserva.getObservaciones());

            ps.execute();
            ps.close();
            conexion.desconectar();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se inserto registro de reserva en la BD " + ex, "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

    public int obtenerIdUltimaReserva() {

        int idReserva = 0;

        try {
            String sql = "select max(idReserva) from reservas";
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

//                reserva.setIdReserva(rs.getInt(sql));
//                
//                usuario.setIdUsuario(rs.getInt("idUsuario"));
            idReserva = rs.getInt("max(idReserva)");

            ps.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo metodo read reserva" + ex, "Error", JOptionPane.ERROR_MESSAGE);

        }
        return idReserva;
    }
    

    public ArrayList<Reserva> consultarTablaReservas() {

        ArrayList<Reserva> listaReservas = new ArrayList<>();
        int idReserva = 0;
        try {
            String sql = "SELECT * FROM reservas";
            String sentenciaFechaDesde = "SELECT date((select fechaDesde from reservas where idReserva=?)/1000, 'unixepoch') as fechaDesde";
            String sentenciaFechaHasta = "SELECT date((select fechaHasta from reservas where idReserva=?)/1000, 'unixepoch') as fechaHasta";

            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            PreparedStatement ps1 = conexion.conectar().prepareStatement(sentenciaFechaDesde);
            PreparedStatement ps2 = conexion.conectar().prepareStatement(sentenciaFechaHasta);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Reserva reserva = new Reserva();

                idReserva = rs.getInt("idReserva");
                reserva.setIdReserva(idReserva);

                ps1.setInt(1, idReserva);
                ResultSet rs1 = ps1.executeQuery();
                java.util.Date fechaDesde = new SimpleDateFormat("yyyy-MM-dd").parse(rs1.getString("fechaDesde"));
                Date fechaDesdeFormatoSQL = new Date(fechaDesde.getTime());
                reserva.setFechaDesde(fechaDesdeFormatoSQL);

                ps2.setInt(1, idReserva);
                ResultSet rs2 = ps2.executeQuery();
                java.util.Date fechaHasta = new SimpleDateFormat("yyyy-MM-dd").parse(rs2.getString("fechaHasta"));
                Date fechaHastaFormatoSQL = new Date(fechaHasta.getTime());
                reserva.setFechaHasta(fechaHastaFormatoSQL);

                reserva.setDia(rs.getString("dia"));
                reserva.setHoraInicio(rs.getString("horaInicio"));
                reserva.setHoraFin(rs.getString("horaFin"));
                reserva.setIdRecurso(rs.getInt("idRecurso"));
                reserva.setIdUsuario(rs.getInt("idUsuario"));
                reserva.setObservaciones(rs.getString("observaciones"));

                listaReservas.add(reserva);
            }

            ps.close();
            ps1.close();
            ps2.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FFallo metodo read tabla reservas " + ex, "Error", JOptionPane.ERROR_MESSAGE);

        } catch (ParseException ex) {
            Logger.getLogger(ConsultasReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaReservas;
    }

}
