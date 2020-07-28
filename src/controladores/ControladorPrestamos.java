package controladores;

import clases.ConsultasPrestamo;
import clases.Prestamo;
import clases.Reserva;
import interfaces.IUConsultarPrestamo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControladorPrestamos implements MouseListener, ActionListener {

    ConsultasPrestamo consultasPrestamo;
    IUConsultarPrestamo iuConsultarPrestamo;

    public ControladorPrestamos() {
        iuConsultarPrestamo = new IUConsultarPrestamo();
        consultasPrestamo = new ConsultasPrestamo();

        iuConsultarPrestamo.tblPrestamos.addMouseListener(this);
        iuConsultarPrestamo.btnModificar.addActionListener(this);

        refrescarTablaTodosLosPrestamos();
    }

    public void hacerInterfazVisible() {
        iuConsultarPrestamo.setVisible(true);
    }

    private void refrescarTablaTodosLosPrestamos() {

        while (iuConsultarPrestamo.modeloTablaPrestamos.getRowCount() > 0) {
            iuConsultarPrestamo.modeloTablaPrestamos.removeRow(0);
        }

        ArrayList<Prestamo> listaPrestamos = consultasPrestamo.consultarTablaDeTodosLosPrestamos();
        for (Prestamo pre : listaPrestamos) {
            Object item[] = new Object[4];
            item[0] = pre.getIdPrestamo();
            item[1] = pre.getIdReserva();
            item[2] = pre.getFechaInicio();
            item[3] = pre.getEstado();

            iuConsultarPrestamo.modeloTablaPrestamos.addRow(item);
        }
        iuConsultarPrestamo.tblPrestamos.setModel(iuConsultarPrestamo.modeloTablaPrestamos);
    }

    public void refrescarTablaPrestamosdeUnaReserva() {

        while (iuConsultarPrestamo.modeloTablaPrestamos.getRowCount() > 0) {
            iuConsultarPrestamo.modeloTablaPrestamos.removeRow(0);
        }

        ArrayList<Prestamo> listaPrestamos = consultasPrestamo.consultarTablaPrestamosdeUltimaReserva();
        for (Prestamo pre : listaPrestamos) {
            Object item[] = new Object[4];
            item[0] = pre.getIdPrestamo();
            item[1] = pre.getIdReserva();
            item[2] = pre.getFechaInicio();
            item[3] = pre.getEstado();

            iuConsultarPrestamo.modeloTablaPrestamos.addRow(item);
        }
        iuConsultarPrestamo.tblPrestamos.setModel(iuConsultarPrestamo.modeloTablaPrestamos);
    }

    public void mostrarDatosReserva(Reserva reserva) {
        iuConsultarPrestamo.txtFechaInicio.setText("" + reserva.getFechaDesde());
        iuConsultarPrestamo.txtFechaFin.setText("" + reserva.getFechaHasta());
        iuConsultarPrestamo.txtHoraInicio.setText(reserva.getHoraInicio());
        iuConsultarPrestamo.txtHoraFin.setText(reserva.getHoraFin());
        iuConsultarPrestamo.txtRecurso.setText("" + reserva.getIdRecurso());
        iuConsultarPrestamo.txtUsuario.setText("" + reserva.getIdUsuario());
        iuConsultarPrestamo.areaObservaciones.setText(reserva.getObservaciones());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == iuConsultarPrestamo.btnModificar) {//Boton para borrar un registro de la BD
            if (iuConsultarPrestamo.lblIdPrestamo.getText() != null) {
                Prestamo prestamo = consultasPrestamo.consultarPrestamo(Integer.parseInt(iuConsultarPrestamo.lblIdPrestamo.getText()));
                prestamo.setEstado(iuConsultarPrestamo.cboEstado.getSelectedItem().toString());

                if (consultasPrestamo.modificarEstadoPrestamo(prestamo)) {
                    JOptionPane.showMessageDialog(this.iuConsultarPrestamo, "Prestamo modificado");
                } else {
                    JOptionPane.showMessageDialog(this.iuConsultarPrestamo, "No se pudo modificar prestamo");
                }
                refrescarTablaTodosLosPrestamos();
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un prestamo a modificar");
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == iuConsultarPrestamo.tblPrestamos) {
            int fila = iuConsultarPrestamo.tblPrestamos.getSelectedRow();
            int id = Integer.parseInt(iuConsultarPrestamo.tblPrestamos.getValueAt(fila, 0).toString());
            Prestamo prestamo = consultasPrestamo.consultarPrestamo(id);

            iuConsultarPrestamo.lblIdPrestamo.setText("" + prestamo.getIdPrestamo());
            iuConsultarPrestamo.cboEstado.setSelectedItem(prestamo.getEstado());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
