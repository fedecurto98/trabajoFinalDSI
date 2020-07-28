package controladores;

import clases.ConsultasUsuario;
import clases.Usuario;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import interfaces.IUUsuarios;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControladorUsuarios implements ActionListener, MouseListener {

    IUUsuarios iuUsuarios;

    Usuario usuario;

    ConsultasUsuario consultasUsuario;

    int id = 0;

    public ControladorUsuarios() {

        iuUsuarios = new IUUsuarios();
        consultasUsuario = new ConsultasUsuario();
        iuUsuarios.btnAgregar.addActionListener(this);
        iuUsuarios.btnEliminar.addActionListener(this);
        iuUsuarios.btnModificar.addActionListener(this);
        iuUsuarios.btnLimpiar.addActionListener(this);
        iuUsuarios.btnPDF.addActionListener(this);
        iuUsuarios.tblUsuarios.addMouseListener(this);
        iuUsuarios.btnNuevo.addActionListener(this);
        iuUsuarios.btnCancelar.addActionListener(this);

        refrescarTabla();
        bloquear();
    }

    void bloquear() {
        iuUsuarios.btnAgregar.setEnabled(false);
        iuUsuarios.btnCancelar.setEnabled(false);
        iuUsuarios.btnLimpiar.setEnabled(false);
    }

    void desbloquear() {
        iuUsuarios.txtNombre.setEnabled(true);
        iuUsuarios.txtApellido.setEnabled(true);
        iuUsuarios.txtLegajo.setEnabled(true);
        iuUsuarios.txtTelefono.setEnabled(true);
        iuUsuarios.cboEstado.setEnabled(true);

    }

    void activar() {
        iuUsuarios.btnNuevo.setEnabled(true);
        iuUsuarios.btnEliminar.setEnabled(true);
        iuUsuarios.btnModificar.setEnabled(true);
        iuUsuarios.btnPDF.setEnabled(true);
    }

    @Override

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == iuUsuarios.btnNuevo) {

            desbloquear();
            iuUsuarios.btnAgregar.setEnabled(true);
            iuUsuarios.btnCancelar.setEnabled(true);
            iuUsuarios.btnEliminar.setEnabled(false);
            iuUsuarios.btnModificar.setEnabled(false);
            iuUsuarios.btnPDF.setEnabled(false);
            iuUsuarios.btnNuevo.setEnabled(false);

            limpiarCampos();
        }

        if (e.getSource() == iuUsuarios.btnModificar) {

            desbloquear();
            iuUsuarios.btnAgregar.setEnabled(false);
            iuUsuarios.btnNuevo.setEnabled(false);
            iuUsuarios.btnEliminar.setEnabled(true);
            iuUsuarios.btnModificar.setEnabled(true);
            iuUsuarios.btnPDF.setEnabled(true);
            iuUsuarios.btnCancelar.setEnabled(true);
        }

        if (e.getSource() == iuUsuarios.btnCancelar) {

            bloquear();
            iuUsuarios.btnNuevo.setEnabled(true);
            iuUsuarios.btnEliminar.setEnabled(true);
            iuUsuarios.btnModificar.setEnabled(true);
            iuUsuarios.btnPDF.setEnabled(true);
        }

        if (e.getSource() == iuUsuarios.btnAgregar) {//Boton para agregar un registro a la BD

            if (validarCampos()) {

                usuario = new Usuario();
                usuario.setNombre(iuUsuarios.txtNombre.getText());
                usuario.setApellido(iuUsuarios.txtApellido.getText());
                usuario.setLegajo(iuUsuarios.txtLegajo.getText());
                usuario.setTelefono(iuUsuarios.txtTelefono.getText());
                usuario.setEstado(iuUsuarios.cboEstado.getSelectedItem().toString());

                if (consultasUsuario.agregarUsuario(usuario)) {
                    JOptionPane.showMessageDialog(this.iuUsuarios, "Usuario agregado");
                } else {
                    JOptionPane.showMessageDialog(this.iuUsuarios, "No se pudo agregar usuario");
                }

                refrescarTabla();
                limpiarCampos();
                activar();
            }
        }

        if (e.getSource() == iuUsuarios.btnEliminar) {//Boton para borrar un registro de la BD
            if (validarCampos()) {
                int x = JOptionPane.showConfirmDialog(this.iuUsuarios, "Estas seguro de eliminar este registro?","Confirmar operacion", JOptionPane.YES_NO_OPTION);
                if (x == 0 && id > 0) {
                    if (!elUsuarioTieneReservasAsociados(id)) {
                        if (consultasUsuario.eliminarUsuario(id)) {
                            JOptionPane.showMessageDialog(this.iuUsuarios, "Usuario eliminado.");
                        } else {
                            JOptionPane.showMessageDialog(this.iuUsuarios, "No se pudo eliminar usuario.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar el usuario porque tiene prestamos asociados. Cambie el estado del usuario a 'No Disponible'");
                    }

                }
            }

            refrescarTabla();
            limpiarCampos();
            bloquear();
            activar();
        }

        if (e.getSource() == iuUsuarios.btnModificar) {//Boton para modificar un registro de la BD
            if (validarCampos()) {
                usuario.setNombre(iuUsuarios.txtNombre.getText());
                usuario.setApellido(iuUsuarios.txtApellido.getText());
                usuario.setLegajo(iuUsuarios.txtLegajo.getText());
                usuario.setTelefono(iuUsuarios.txtTelefono.getText());
                usuario.setEstado(iuUsuarios.cboEstado.getSelectedItem().toString());

                if (consultasUsuario.modificarUsuario(usuario)) {
                    JOptionPane.showMessageDialog(this.iuUsuarios, "Usuario modificado");
                } else {
                    JOptionPane.showMessageDialog(this.iuUsuarios, "No se pudo modificar usuario");
                }
                refrescarTabla();
                limpiarCampos();
                bloquear();
                activar();
            }
        }

        if (e.getSource() == iuUsuarios.btnLimpiar) {//Boton para limpiar campos
            limpiarCampos();
        }

        if (e.getSource() == iuUsuarios.btnPDF) {//Boton para generar PDF
            FileOutputStream archivo = null;
            try {
                File file = new File("src\\pdf\\reporteUsuarios.pdf");
                archivo = new FileOutputStream(file);
                Document doc = new Document();
                PdfWriter.getInstance(doc, archivo);
                doc.open();
                Image img = Image.getInstance("src\\images\\imgPDFUsuarios.png");
                img.setAlignment(Element.ALIGN_CENTER);
                img.scaleToFit(150, 150);
                doc.add(img);

                Paragraph p = new Paragraph(10);
                Font fuente = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                Font fuente1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
                p.add(Chunk.NEWLINE);
                p.setFont(fuente);
                p.add("CATÁLOGO DE USUARIOS PARA RECURSOS TIC - U.T.N.");
                p.add(Chunk.NEWLINE);
                p.add(Chunk.NEWLINE);
                p.add(Chunk.NEWLINE);
                p.setAlignment(Element.ALIGN_CENTER);

                doc.add(p);

                //Tabla de datos
                PdfPTable tabla = new PdfPTable(6);
                tabla.setWidthPercentage(100);
                PdfPCell c1 = new PdfPCell(new Phrase("ID", fuente1));
                PdfPCell c2 = new PdfPCell(new Phrase("NOMBRE", fuente1));
                PdfPCell c3 = new PdfPCell(new Phrase("APELLIDO", fuente1));
                PdfPCell c4 = new PdfPCell(new Phrase("LEGAJO", fuente1));
                PdfPCell c5 = new PdfPCell(new Phrase("TELEFONO", fuente1));
                PdfPCell c6 = new PdfPCell(new Phrase("ESTADO", fuente1));

                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                c6.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c6.setBackgroundColor(BaseColor.LIGHT_GRAY);

                tabla.addCell(c1);
                tabla.addCell(c2);
                tabla.addCell(c3);
                tabla.addCell(c4);
                tabla.addCell(c5);
                tabla.addCell(c6);

                float[] medidaCeldas = {0.20f, 0.60f, 0.60f, 0.40f, 0.8f, 0.60f};

                tabla.setWidths(medidaCeldas);

                //Agregar los registros
                ArrayList<Usuario> lista = consultasUsuario.consultarTablaUsuarios();

                for (Usuario user : lista) {
                    tabla.addCell("" + user.getIdUsuario());
                    tabla.addCell("" + user.getNombre());
                    tabla.addCell("" + user.getApellido());
                    tabla.addCell("" + user.getLegajo());
                    tabla.addCell("" + user.getTelefono());
                    tabla.addCell("" + user.getEstado());
                }

                doc.add(tabla);
                Paragraph p1 = new Paragraph(10);
                p1.add(Chunk.NEWLINE);
                p1.add("Número de registros: " + lista.size());
                p1.setAlignment(Element.ALIGN_RIGHT);
                doc.add(p1);
                doc.close();
                archivo.close();
                Desktop.getDesktop().open(file);

            } catch (DocumentException ex) {
                JOptionPane.showMessageDialog(this.iuUsuarios, "Error al crear archivo ");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this.iuUsuarios, "Error al crear I/O ");
            }
        }
    }

    public boolean elUsuarioTieneReservasAsociados(int idUsuarioABorrar) {
        ArrayList<Integer> listaUsuariosConReserva = consultasUsuario.listaDeUsuariosQueTienenReservas();
        
        for (int idUsuarioConReserva : listaUsuariosConReserva) {
            if(idUsuarioConReserva == idUsuarioABorrar){
                return true;
            } 
        }
        return false;
    }

    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == iuUsuarios.tblUsuarios) {
            int fila = iuUsuarios.tblUsuarios.getSelectedRow();
            id = Integer.parseInt(iuUsuarios.tblUsuarios.getValueAt(fila, 0).toString());
            usuario = consultasUsuario.consultarUsuario(id);
            iuUsuarios.lblIdUsuario.setText("" + usuario.getIdUsuario());
            iuUsuarios.txtNombre.setText(usuario.getNombre());
            iuUsuarios.txtApellido.setText(usuario.getApellido());
            iuUsuarios.txtLegajo.setText(usuario.getLegajo());
            iuUsuarios.txtTelefono.setText(usuario.getTelefono());
            iuUsuarios.cboEstado.setSelectedItem(usuario.getEstado());
        }
    }

    public void limpiarCampos() {

        iuUsuarios.lblIdUsuario.setText("");
        iuUsuarios.txtNombre.setText("");
        iuUsuarios.txtApellido.setText("");
        iuUsuarios.txtLegajo.setText("");
        iuUsuarios.txtTelefono.setText("");
        iuUsuarios.cboEstado.setSelectedIndex(0);
    }

    public void refrescarTabla() {

        while (iuUsuarios.modeloTablaUsuarios.getRowCount() > 0) {
            iuUsuarios.modeloTablaUsuarios.removeRow(0);
        }

        ArrayList<Usuario> lista = consultasUsuario.consultarTablaUsuarios();

        for (Usuario user : lista) {

            Object item[] = new Object[6];

            item[0] = user.getIdUsuario();

            item[1] = user.getNombre();

            item[2] = user.getApellido();

            item[3] = user.getLegajo();

            item[4] = user.getTelefono();

            item[5] = user.getEstado();

            iuUsuarios.modeloTablaUsuarios.addRow(item);

        }

        iuUsuarios.tblUsuarios.setModel(iuUsuarios.modeloTablaUsuarios);

    }

    public boolean validarCampos() {

        boolean nombreVacio = iuUsuarios.txtNombre.getText().isEmpty();

        boolean apellidoVacio = iuUsuarios.txtApellido.getText().isEmpty();

        boolean legajoVacio = iuUsuarios.txtLegajo.getText().isEmpty();

        if (nombreVacio || apellidoVacio || legajoVacio) {

            JOptionPane.showMessageDialog(this.iuUsuarios, "Los campos con (*) son OBLIGATORIOS");

            return false;

        } else {

            return true;

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
