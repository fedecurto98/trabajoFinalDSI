package controladores;

import clases.ConsultasRecurso;

import clases.Recurso;

import interfaces.IURecursos;

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

import java.awt.Desktop;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ControladorRecursos implements ActionListener, MouseListener {

    IURecursos iuRecursos;

    Recurso recurso;

    ConsultasRecurso consultasRecurso;

    int id = 0;

    public ControladorRecursos() {

        iuRecursos = new IURecursos();

        consultasRecurso = new ConsultasRecurso();

        iuRecursos.btnAgregar.addActionListener(this);

        iuRecursos.btnEliminar.addActionListener(this);

        iuRecursos.btnModificar.addActionListener(this);

        iuRecursos.btnLimpiar.addActionListener(this);

        iuRecursos.btnPDF.addActionListener(this);

        iuRecursos.tblRecursos.addMouseListener(this);

        iuRecursos.btnNuevo.addActionListener(this);

        iuRecursos.btnCancelar.addActionListener(this);

        refrescarTablaRecursos();

        bloquear();

    }

    void bloquear() {

        iuRecursos.btnAgregar.setEnabled(false);

        iuRecursos.btnCancelar.setEnabled(false);

        iuRecursos.btnLimpiar.setEnabled(false);

    }

    void desbloquear() {

        iuRecursos.txtNombre.setEnabled(true);

        iuRecursos.areaDescripcion.setEnabled(true);

        iuRecursos.cboEstado.setEnabled(true);

        iuRecursos.cboTipo.setEnabled(true);

    }

    void activar() {

        iuRecursos.btnNuevo.setEnabled(true);

        iuRecursos.btnEliminar.setEnabled(true);

        iuRecursos.btnModificar.setEnabled(true);

        iuRecursos.btnPDF.setEnabled(true);
    }

    @Override

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == iuRecursos.btnNuevo) {

            desbloquear();

            iuRecursos.btnAgregar.setEnabled(true);

            iuRecursos.btnCancelar.setEnabled(true);

            iuRecursos.btnEliminar.setEnabled(false);

            iuRecursos.btnModificar.setEnabled(false);

            iuRecursos.btnPDF.setEnabled(false);

            iuRecursos.btnNuevo.setEnabled(false);

            limpiarCampos();

        }

        if (e.getSource() == iuRecursos.btnModificar) {

            desbloquear();

            iuRecursos.btnAgregar.setEnabled(false);

            iuRecursos.btnNuevo.setEnabled(false);

            iuRecursos.btnEliminar.setEnabled(true);

            iuRecursos.btnModificar.setEnabled(true);

            iuRecursos.btnPDF.setEnabled(true);

            iuRecursos.btnCancelar.setEnabled(true);

        }

        if (e.getSource() == iuRecursos.btnCancelar) {

            bloquear();

            iuRecursos.btnNuevo.setEnabled(true);

            iuRecursos.btnEliminar.setEnabled(true);

            iuRecursos.btnModificar.setEnabled(true);

            iuRecursos.btnPDF.setEnabled(true);

        }

        if (e.getSource() == iuRecursos.btnAgregar) {//Boton para agregar un registro a la BD

            if (validarCampos()) {

                recurso = new Recurso();

                recurso.setNombre(iuRecursos.txtNombre.getText());

                recurso.setDescripcion(iuRecursos.areaDescripcion.getText());

                recurso.setEstado(iuRecursos.cboEstado.getSelectedItem().toString());

                recurso.setTipo(iuRecursos.cboTipo.getSelectedItem().toString());

                if (consultasRecurso.agregarRecurso(recurso)) {

                    JOptionPane.showMessageDialog(this.iuRecursos, "Recurso agregado");

                } else {

                    JOptionPane.showMessageDialog(this.iuRecursos, "No se pudo agregar recurso");

                }

                refrescarTablaRecursos();

                limpiarCampos();

                bloquear();

                activar();

            }

        }

        if (e.getSource() == iuRecursos.btnEliminar) {//Boton para borrar un registro de la BD

            if (validarCampos()) {

                int x = JOptionPane.showConfirmDialog(this.iuRecursos, "Estas seguro de eliminar este registro?","Confirmar operacion",JOptionPane.YES_NO_OPTION);

                if (x == 0 && id > 0) {

                    if (!elRecursoTieneReservasAsociados(id)) {
                        if (consultasRecurso.eliminarRecurso(id)) {

                            JOptionPane.showMessageDialog(this.iuRecursos, "Recurso eliminado");

                        } else {

                            JOptionPane.showMessageDialog(this.iuRecursos, "No se pudo eliminar recurso");

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar el recurso porque tiene prestamos asociados. Cambie el estado del recurso a 'No Disponible'");
                    }

                }

            }

            refrescarTablaRecursos();

            limpiarCampos();

            bloquear();

            activar();

        }

        if (e.getSource() == iuRecursos.btnModificar) {//Boton para modificar un registro de la BD

            if (validarCampos()) {

                recurso.setNombre(iuRecursos.txtNombre.getText());

                recurso.setDescripcion(iuRecursos.areaDescripcion.getText());

                recurso.setEstado(iuRecursos.cboEstado.getSelectedItem().toString());

                recurso.setTipo(iuRecursos.cboTipo.getSelectedItem().toString());

                if (consultasRecurso.modificarRecurso(recurso)) {

                    JOptionPane.showMessageDialog(this.iuRecursos, "Recurso modificado");

                } else {

                    JOptionPane.showMessageDialog(this.iuRecursos, "No se pudo modificar recurso");

                }

                refrescarTablaRecursos();

                limpiarCampos();

                activar();

            }

        }

        if (e.getSource() == iuRecursos.btnLimpiar) {//Boton para limpiar campos

            limpiarCampos();

        }

        if (e.getSource() == iuRecursos.btnPDF) {//Boton para generar PDF

            FileOutputStream archivo = null;

            try {

                File file = new File("src\\pdf\\reporteRecursos.pdf");

                archivo = new FileOutputStream(file);

                Document doc = new Document();

                PdfWriter.getInstance(doc, archivo);

                doc.open();

                Image img = Image.getInstance("src\\images\\imgPDFRecursos.png");

                img.setAlignment(Element.ALIGN_CENTER);

                img.scaleToFit(200, 200);

                doc.add(img);

                Paragraph p = new Paragraph(10);

                Font fuente = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);

                Font fuente1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

                p.add(Chunk.NEWLINE);

                p.setFont(fuente);

                p.add("CATÁLOGO DE RECURSOS TIC - U.T.N.");

                p.add(Chunk.NEWLINE);

                p.add(Chunk.NEWLINE);

                p.add(Chunk.NEWLINE);

                p.setAlignment(Element.ALIGN_CENTER);

                doc.add(p);

                //Tabla de datos
                PdfPTable tabla = new PdfPTable(5);

                tabla.setWidthPercentage(100);

                PdfPCell c1 = new PdfPCell(new Phrase("ID", fuente1));

                PdfPCell c2 = new PdfPCell(new Phrase("NOMBRE", fuente1));

                PdfPCell c3 = new PdfPCell(new Phrase("DESCRIPCION", fuente1));

                PdfPCell c4 = new PdfPCell(new Phrase("ESTADO", fuente1));

                PdfPCell c5 = new PdfPCell(new Phrase("TIPO", fuente1));

                c1.setHorizontalAlignment(Element.ALIGN_CENTER);

                c2.setHorizontalAlignment(Element.ALIGN_CENTER);

                c3.setHorizontalAlignment(Element.ALIGN_CENTER);

                c4.setHorizontalAlignment(Element.ALIGN_CENTER);

                c5.setHorizontalAlignment(Element.ALIGN_CENTER);

                c1.setBackgroundColor(BaseColor.LIGHT_GRAY);

                c2.setBackgroundColor(BaseColor.LIGHT_GRAY);

                c3.setBackgroundColor(BaseColor.LIGHT_GRAY);

                c4.setBackgroundColor(BaseColor.LIGHT_GRAY);

                c5.setBackgroundColor(BaseColor.LIGHT_GRAY);

                tabla.addCell(c1);

                tabla.addCell(c2);

                tabla.addCell(c3);

                tabla.addCell(c4);

                tabla.addCell(c5);

//                float[] medidaCeldas = {0.20f, 0.60f, 0.60f, 0.40f, 1.0f, 0.40f};
//                tabla.setWidths(medidaCeldas);
                //Agregar los registros
                ArrayList<Recurso> lista = consultasRecurso.consultarTablaRecursos();

                for (Recurso rec : lista) {

                    tabla.addCell("" + rec.getIdRecurso());

                    tabla.addCell("" + rec.getNombre());

                    tabla.addCell("" + rec.getDescripcion());

                    tabla.addCell("" + rec.getEstado());

                    tabla.addCell("" + rec.getTipo());

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

            } catch (FileNotFoundException ex) {

                JOptionPane.showMessageDialog(this.iuRecursos, "Error al crear archivo " + ex);

            } catch (DocumentException ex) {

                JOptionPane.showMessageDialog(this.iuRecursos, "Error al crear documento PDF " + ex);

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this.iuRecursos, "Error al crear I/O " + ex);

            }

        }

    }

    @Override

    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == iuRecursos.tblRecursos) {

            int fila = iuRecursos.tblRecursos.getSelectedRow();

            id = Integer.parseInt(iuRecursos.tblRecursos.getValueAt(fila, 0).toString());

            recurso = consultasRecurso.consultarRecurso(id);

            iuRecursos.lblIdRecurso.setText("" + recurso.getIdRecurso());

            iuRecursos.txtNombre.setText(recurso.getNombre());

            iuRecursos.areaDescripcion.setText(recurso.getDescripcion());

            iuRecursos.cboEstado.setSelectedItem(recurso.getEstado());

            iuRecursos.cboTipo.setSelectedItem(recurso.getTipo());

        }

    }

    public void refrescarTablaRecursos() {

        while (iuRecursos.modeloTablaRecursos.getRowCount() > 0) {

            iuRecursos.modeloTablaRecursos.removeRow(0);

        }

        ArrayList<Recurso> lista = consultasRecurso.consultarTablaRecursos();

        for (Recurso r : lista) {

            Object item[] = new Object[5];

            item[0] = r.getIdRecurso();

            item[1] = r.getNombre();

            item[2] = r.getDescripcion();

            item[3] = r.getEstado();

            item[4] = r.getTipo();

            iuRecursos.modeloTablaRecursos.addRow(item);

        }

        iuRecursos.tblRecursos.setModel(iuRecursos.modeloTablaRecursos);

    }

    public void limpiarCampos() {

        iuRecursos.lblIdRecurso.setText("");

        iuRecursos.txtNombre.setText("");

        iuRecursos.areaDescripcion.setText("");

        iuRecursos.cboEstado.setSelectedIndex(0);

        iuRecursos.cboEstado.setSelectedIndex(0);

    }

    public boolean validarCampos() {

        if ((!iuRecursos.txtNombre.getText().equals("")) && (!iuRecursos.areaDescripcion.getText().equals(""))) {

            return true;

        } else {

            JOptionPane.showMessageDialog(this.iuRecursos, "Debes llenar todos los campos.");

            return false;

        }

    }

    public boolean elRecursoTieneReservasAsociados(int idRecursoABorrar) {
        ArrayList<Integer> listaRecursosConReserva = consultasRecurso.listaDeRecursosQueTienenReservas();

        for (int idRecursoConReserva : listaRecursosConReserva) {
            if (idRecursoConReserva == idRecursoABorrar) {
                return true;
            }
        }
        return false;
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
