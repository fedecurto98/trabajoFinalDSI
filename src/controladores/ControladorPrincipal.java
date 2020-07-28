package controladores;

import interfaces.IUPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorPrincipal implements ActionListener {

    IUPrincipal iuPrincipal;

    public static void main(String[] args) {
        ControladorPrincipal controlador = new ControladorPrincipal();
    }

    public ControladorPrincipal() {
        iuPrincipal = new IUPrincipal();
        iuPrincipal.menuRegistrarReserva.addActionListener(this);
        iuPrincipal.menuConsultarPrestamo.addActionListener(this);
        iuPrincipal.menuGestionarRecursos.addActionListener(this);
        iuPrincipal.menuGestionarUsuarios.addActionListener(this);
        iuPrincipal.menuDesarrollador.addActionListener(this);
        iuPrincipal.menuVersion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == iuPrincipal.menuRegistrarReserva) {
            ControladorReservas controladorReservas = new ControladorReservas();
        }
        if (e.getSource() == iuPrincipal.menuConsultarPrestamo) {
            ControladorPrestamos controladorPrestamos = new ControladorPrestamos();
            controladorPrestamos.hacerInterfazVisible();
        }

        if (e.getSource() == iuPrincipal.menuGestionarRecursos) {
            ControladorRecursos controladorRecursos = new ControladorRecursos();
        }

        if (e.getSource() == iuPrincipal.menuGestionarUsuarios) {
            ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
        }

        if (e.getSource() == iuPrincipal.menuDesarrollador) {
            JOptionPane.showMessageDialog(null, "Desarrollado por Federico Curto, Nicolás Mainardi y Ezequiel Crespo");
        }

        if (e.getSource() == iuPrincipal.menuVersion) {
            JOptionPane.showMessageDialog(null, "Versión 1.0");
        }
    }

}
