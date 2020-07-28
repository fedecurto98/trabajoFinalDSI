package controladores;

import clases.ConsultasPrestamo;
import clases.ConsultasRecurso;
import clases.ConsultasReserva;
import clases.ConsultasUsuario;
import clases.Prestamo;
import clases.Recurso;
import clases.Reserva;
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
import com.toedter.calendar.MinMaxDateEvaluator;
import interfaces.IUConsultarPrestamo;
import interfaces.IUReservas;
import interfaces.IUTablaRecurso;
import interfaces.IUTablaUsuario;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorReservas implements ActionListener, MouseListener {

    IUTablaRecurso iuTablaRecurso;
    IUTablaUsuario iuTablaUsuario;
    IUReservas iuReservas;
    ConsultasUsuario consultasUsuario;
    ConsultasRecurso consultasRecurso;
    ConsultasReserva consultasReserva;
    ConsultasPrestamo consultasPrestamo;
    Usuario usuario;
    Recurso recurso;
    Reserva reserva;
    Prestamo prestamo;
    ControladorPrestamos controladorPrestamos;

    int id = 0;

    public ControladorReservas() {
        iuReservas = new IUReservas();
        iuTablaRecurso = new IUTablaRecurso();
        iuTablaUsuario = new IUTablaUsuario();

        iuReservas.btnAgregarReserva.addActionListener(this);
        iuReservas.btnSeleccionarRecurso.addActionListener(this);
        iuReservas.btnSeleccionarUsuario.addActionListener(this);
        iuReservas.btnPDF.addActionListener(this);

        iuTablaRecurso.tblRecursos.addMouseListener(this);
        iuTablaRecurso.btnCerrar.addActionListener(this);

        iuTablaUsuario.tblUsuarios.addMouseListener(this);
        iuTablaUsuario.btnCerrar.addActionListener(this);

        consultasRecurso = new ConsultasRecurso();
        consultasUsuario = new ConsultasUsuario();
        consultasReserva = new ConsultasReserva();
        consultasPrestamo = new ConsultasPrestamo();

        controladorPrestamos = new ControladorPrestamos();

        refrescarTablaReserva();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == iuReservas.btnSeleccionarRecurso) {

            iuTablaRecurso.setVisible(true);
            refrescarTablaRecursos();
        }

        if (e.getSource() == iuReservas.btnSeleccionarUsuario) {

            iuTablaUsuario.setVisible(true);
            refrescarTablaUsuarios();
        }

        if (e.getSource() == iuTablaRecurso.btnCerrar) {
            iuTablaRecurso.dispose();
        }

        if (e.getSource() == iuTablaUsuario.btnCerrar) {
            iuTablaUsuario.dispose();
        }

        if (e.getSource() == iuReservas.btnAgregarReserva) {
            if (verificarDatosReservaIngresados()) {

                cargarDatosReserva();

                controladorPrestamos.hacerInterfazVisible();
                controladorPrestamos.refrescarTablaPrestamosdeUnaReserva();
                controladorPrestamos.mostrarDatosReserva(reserva);

                refrescarTablaReserva();
            }

        }

        if (e.getSource() == iuReservas.btnPDF) {//Boton para generar PDF
            FileOutputStream archivo = null;
            try {
                File file = new File("src\\pdf\\reporteReservas.pdf");
                archivo = new FileOutputStream(file);
                Document doc = new Document();
                PdfWriter.getInstance(doc, archivo);
                doc.open();
                Image img = Image.getInstance("src\\images\\UTN.png");
                img.setAlignment(Element.ALIGN_CENTER);
                img.scaleToFit(150, 150);
                doc.add(img);
                Paragraph p = new Paragraph(10);
                Font fuente = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                Font fuente1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

                p.add(Chunk.NEWLINE);
                p.setFont(fuente);
                p.add("LISTADO DE RESERVAS DE RECURSOS TIC - U.T.N.");
                p.add(Chunk.NEWLINE);
                p.add(Chunk.NEWLINE);
                p.add(Chunk.NEWLINE);
                p.setAlignment(Element.ALIGN_CENTER);
                doc.add(p);

                //Tabla de datos
                PdfPTable tabla = new PdfPTable(9);
                tabla.setWidthPercentage(100);
                PdfPCell c1 = new PdfPCell(new Phrase("ID", fuente1));
                PdfPCell c2 = new PdfPCell(new Phrase("FECHA INICIO", fuente1));
                PdfPCell c3 = new PdfPCell(new Phrase("FECHA FIN", fuente1));
                PdfPCell c4 = new PdfPCell(new Phrase("DIA", fuente1));
                PdfPCell c5 = new PdfPCell(new Phrase("HORA INICIO", fuente1));
                PdfPCell c6 = new PdfPCell(new Phrase("HORA FIN", fuente1));
                PdfPCell c7 = new PdfPCell(new Phrase("RECURSO", fuente1));
                PdfPCell c8 = new PdfPCell(new Phrase("USUARIO", fuente1));
                PdfPCell c9 = new PdfPCell(new Phrase("OBS.", fuente1));

                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                c6.setHorizontalAlignment(Element.ALIGN_CENTER);
                c7.setHorizontalAlignment(Element.ALIGN_CENTER);
                c8.setHorizontalAlignment(Element.ALIGN_CENTER);
                c9.setHorizontalAlignment(Element.ALIGN_CENTER);

                c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c7.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c8.setBackgroundColor(BaseColor.LIGHT_GRAY);
                c9.setBackgroundColor(BaseColor.LIGHT_GRAY);

                tabla.addCell(c1);
                tabla.addCell(c2);
                tabla.addCell(c3);
                tabla.addCell(c4);
                tabla.addCell(c5);
                tabla.addCell(c6);
                tabla.addCell(c7);
                tabla.addCell(c8);
                tabla.addCell(c9);

                float[] medidaCeldas = {0.15f, 0.40f, 0.40f, 0.20f, 0.25f, 0.25f, 0.35f, 0.35f, 0.3f};
                tabla.setWidths(medidaCeldas);

                //Agregar los registros
                ArrayList<Reserva> lista = consultasReserva.consultarTablaReservas();

                for (Reserva res : lista) {
                    tabla.addCell("" + res.getIdReserva());
                    tabla.addCell("" + res.getFechaDesde());
                    tabla.addCell("" + res.getFechaHasta());
                    tabla.addCell("" + res.getDia());
                    tabla.addCell("" + res.getHoraInicio());
                    tabla.addCell("" + res.getHoraFin());
                    tabla.addCell("" + res.getIdRecurso());
                    tabla.addCell("" + res.getIdUsuario());
                    tabla.addCell("" + res.getObservaciones());
                }
                doc.add(tabla);

                Paragraph p1 = new Paragraph(10);
                p1.add(Chunk.NEWLINE);
                p1.add("Número de registros: " + lista.size());
                p1.setAlignment(Element.ALIGN_RIGHT);

                Paragraph p2 = new Paragraph(10);
                p2.add(Chunk.NEWLINE);
                p2.add("Aclaración: Mon=Lunes, Tue=Martes, Wed=Miercoles, "
                        + "Thu=Jueves, Fri=Viernes, Sat=Sabado");
                p2.setAlignment(Element.ALIGN_LEFT);

                doc.add(p2);

                doc.close();
                archivo.close();
                Desktop.getDesktop().open(file);

            } catch (DocumentException ex) {
                JOptionPane.showMessageDialog(this.iuReservas, "Error al crear archivo ");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this.iuReservas, "Error al crear I/O ");
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == iuTablaRecurso.tblRecursos) {

            int fila = iuTablaRecurso.tblRecursos.getSelectedRow();
            id = Integer.parseInt(iuTablaRecurso.tblRecursos.getValueAt(fila, 0).toString());
            recurso = consultasRecurso.consultarRecurso(id);

            iuTablaRecurso.lblInformacion.setText("Recurso seleccionado: " + recurso.getNombre() + "   (Id:" + recurso.getIdRecurso() + ")");
            iuReservas.lblRecurso.setText("" + recurso.getNombre() + "   (Id:" + recurso.getIdRecurso() + ")");
        }

        if (e.getSource() == iuTablaUsuario.tblUsuarios) {

            int fila = iuTablaUsuario.tblUsuarios.getSelectedRow();
            id = Integer.parseInt(iuTablaUsuario.tblUsuarios.getValueAt(fila, 0).toString());
            usuario = consultasUsuario.consultarUsuario(id);

            iuTablaUsuario.lblInformacion.setText("Usuario seleccionado: " + usuario.getNombre() + " " + usuario.getApellido() + "   (Id:" + usuario.getIdUsuario() + ")");
            iuReservas.lblUsuario.setText("" + usuario.getNombre() + " " + usuario.getApellido() + "  (Id:" + usuario.getIdUsuario() + ")");
        }
    }

    private void refrescarTablaUsuarios() {

        while (iuTablaUsuario.modeloTablaUsuarios.getRowCount() > 0) {
            iuTablaUsuario.modeloTablaUsuarios.removeRow(0);
        }

        ArrayList<Usuario> listaUsuarios = consultasUsuario.consultarTablaUsuarios();
        for (Usuario user : listaUsuarios) {
            Object item[] = new Object[6];
            item[0] = user.getIdUsuario();
            item[1] = user.getNombre();
            item[2] = user.getApellido();
            item[3] = user.getLegajo();
            item[4] = user.getTelefono();
            item[5] = user.getEstado();

            iuTablaUsuario.modeloTablaUsuarios.addRow(item);
        }
        iuTablaUsuario.tblUsuarios.setModel(iuTablaUsuario.modeloTablaUsuarios);
    }

    private void refrescarTablaRecursos() {

        while (iuTablaRecurso.modeloTablaRecursos.getRowCount() > 0) {
            iuTablaRecurso.modeloTablaRecursos.removeRow(0);
        }

        ArrayList<Recurso> listaRecursos = consultasRecurso.consultarTablaRecursos();
        for (Recurso rec : listaRecursos) {
            Object item[] = new Object[5];
            item[0] = rec.getIdRecurso();
            item[1] = rec.getNombre();
            item[2] = rec.getDescripcion();
            item[3] = rec.getEstado();
            item[4] = rec.getTipo();

            iuTablaRecurso.modeloTablaRecursos.addRow(item);
        }
        iuTablaRecurso.tblRecursos.setModel(iuTablaRecurso.modeloTablaRecursos);
    }

    private void refrescarTablaReserva() {

        while (iuReservas.modeloTablaReserva.getRowCount() > 0) {
            iuReservas.modeloTablaReserva.removeRow(0);
        }

        ArrayList<Reserva> listaReservas = consultasReserva.consultarTablaReservas();

        for (Reserva res : listaReservas) {
            Object item[] = new Object[8];
            item[0] = res.getIdReserva();
            item[1] = res.getFechaDesde();
            item[2] = res.getFechaHasta();
            item[3] = res.getDia();
            item[4] = res.getHoraInicio();
            item[5] = res.getHoraFin();
            item[6] = res.getIdRecurso();
            item[7] = res.getIdUsuario();

            iuReservas.modeloTablaReserva.addRow(item);
        }
        iuReservas.tblReserva.setModel(iuReservas.modeloTablaReserva);
    }

    private boolean verificarDatosReservaIngresados() {
        Date fechaDesde = iuReservas.calendarFechaInicio.getDate();
        Date fechaHasta = iuReservas.calendarFechaFin.getDate();
        
        if (fechaDesde != null && verificarRangoValidoDeFecha(fechaDesde) && fechaHasta != null && verificarRangoValidoDeFecha(fechaHasta)) {
            if (fechaHasta.after(fechaDesde)) {
                if (validarHoraReserva()) {
                    if (validarSeleccionDeDia()) {
                        if (recurso != null && recurso.getEstado().equals("Disponible")) {
                            if (usuario != null && usuario.getEstado().equals("Habilitado")) {
                                
                                return true;
                                

                            } else {
                                JOptionPane.showMessageDialog(null, "Debes ingresar un usuario habilitado");
                                return false;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Debes ingresar un recurso disponible");
                            return false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes seleccionar un dia para los prestamos de la reserva");
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La hora de fin debe ser menor o igual que la de inicio");
                    return false;
                }

            } else {
                JOptionPane.showMessageDialog(null, "La fecha de fin debe ser mayor o igual que la de inicio");
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes ingresar una fecha y esta debe ser valida");
            return false;
        }
    }

    private void cargarDatosReserva() {
        reserva = new Reserva();

        java.sql.Date fechaInicioReservaFormatoSQL = new java.sql.Date(iuReservas.calendarFechaInicio.getDate().getTime());
        java.sql.Date fechaFinReservaFormatoSQL = new java.sql.Date(iuReservas.calendarFechaFin.getDate().getTime());

        reserva.setFechaDesde(fechaInicioReservaFormatoSQL);
        reserva.setFechaHasta(fechaFinReservaFormatoSQL);
        reserva.setDia(diasDelPrestamo());
        reserva.setHoraInicio(iuReservas.spnHoraInicio.getValue().toString());
        reserva.setHoraFin(iuReservas.spnHoraFin.getValue().toString());
        reserva.setIdRecurso(recurso.getIdRecurso());
        reserva.setIdUsuario(usuario.getIdUsuario());

        if (iuReservas.areaObservaciones.getText().equals("")) {
            reserva.setObservaciones("---");
        } else {
            reserva.setObservaciones(iuReservas.areaObservaciones.getText());
        }

        if (consultasReserva.agregarReserva(reserva)) {
            JOptionPane.showMessageDialog(this.iuReservas, "Reserva agregada");

        } else {
            JOptionPane.showMessageDialog(this.iuReservas, "No se pudo agregar reserva");
        }
        crearPrestamosAsociados(iuReservas.calendarFechaInicio.getDate(), iuReservas.calendarFechaFin.getDate(), diasDelPrestamo());

    }

    private void crearPrestamosAsociados(Date fechaInicio, Date fechaFin, String Dia) {

        String fechaInicial = fechaInicio.toString();
        String[] fechaInicialParticionada = fechaInicial.split(" ");

        int i = 0;
        boolean estaDentroDeRango = true;

        do {
            Date fecha = new Date(fechaInicio.getYear(), fechaInicio.getMonth(), Integer.parseInt(fechaInicialParticionada[2]) + i);
            String fechaEnFormatoCadena = fecha.toString();
            String[] fechaParticionada = fechaEnFormatoCadena.split(" ");

            java.sql.Date fechaPrestamoFormatoSQL = new java.sql.Date(fecha.getTime());

            int idReserva = consultasReserva.obtenerIdUltimaReserva();

            estaDentroDeRango = fecha.before(fechaFin);
            if (estaDentroDeRango == true) {

                if (Dia.equals(fechaParticionada[0])) {

                    //dentro de aca se deberia comparar la fecha con la de feriados
                    //if esta en la llista de feriados, pasa de largo ese solo prestamo
                    
                    if(laFechaEsValida(fecha)){
                        prestamo = new Prestamo();

                        prestamo.setIdReserva(idReserva);
                        prestamo.setFechaInicio(fechaPrestamoFormatoSQL);
                        prestamo.setEstado("Pendiente");
                        System.out.println(fecha);
                        if (consultasPrestamo.agregarPrestamo(prestamo)) {
                            System.out.println("Prestamo agregado");
                        } else {
                            JOptionPane.showMessageDialog(this.iuReservas, "No se pudo agregar prestamo");
                        }

                         System.out.println(fechaPrestamoFormatoSQL);

                    }else{
                        JOptionPane.showMessageDialog(null, "El prestamo del dia "+ fechaPrestamoFormatoSQL+" no pudo ser agregado debido a que ese dia hay examen o es feriado");
                     
                    }
                }
            }
            i++;
        } while (estaDentroDeRango);

    }

    public boolean laFechaEsValida(Date fechaPrueba) {

        ArrayList<Date> listaFechas = fechasEspeciales();

        for (Date fechaLista : listaFechas) {
            //System.out.println(fechaPrueba);
            //System.out.println(fechaPrueba.getDay());
            //System.out.println(fechaLista);
            //System.out.println(fechaLista.getDay());
            
            if(fechaPrueba.compareTo(fechaLista)==0){
                return false;
            }
            
            /*f (fechaPrueba.getDay() == fechaLista.getDay() && fechaPrueba.getMonth() == fechaLista.getMonth() && fechaPrueba.getYear() == fechaLista.getYear()) {
                System.out.println("false");
                return false;
            }*/
        }
        return true;
    }

    public ArrayList<Date> fechasEspeciales() {
        try {
            ArrayList<Date> fechas = new ArrayList<Date>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            //FERIADOS
            Date fecha1 = sdf.parse("2019-01-01");
            Date fecha2 = sdf.parse("2019-03-04");
            Date fecha3 = sdf.parse("2019-03-05");
            Date fecha4 = sdf.parse("2019-03-24");
            Date fecha5 = sdf.parse("2019-04-02");
            Date fecha6 = sdf.parse("2019-04-19");
            Date fecha7 = sdf.parse("2019-05-01");
            Date fecha8 = sdf.parse("2019-05-25");
            Date fecha9 = sdf.parse("2019-06-20");
            Date fecha10 = sdf.parse("2019-11-18");
            Date fecha11 = sdf.parse("2019-12-08");
            Date fecha12 = sdf.parse("2019-12-25");

            fechas.add(fecha1);
            fechas.add(fecha2);
            fechas.add(fecha3);
            fechas.add(fecha4);
            fechas.add(fecha5);
            fechas.add(fecha6);
            fechas.add(fecha7);
            fechas.add(fecha8);
            fechas.add(fecha9);
            fechas.add(fecha10);
            fechas.add(fecha11);
            fechas.add(fecha12);
            //MESA DE EXAMEN
            Date fecha13 = sdf.parse("2019-02-04");
            Date fecha14 = sdf.parse("2019-02-05");
            Date fecha15 = sdf.parse("2019-02-06");
            Date fecha16 = sdf.parse("2019-02-07");
            Date fecha17 = sdf.parse("2019-02-08");
            Date fecha18 = sdf.parse("2019-02-18");
            Date fecha19 = sdf.parse("2019-02-019");
            Date fecha20 = sdf.parse("2019-02-20");
            Date fecha21 = sdf.parse("2019-02-21");
            Date fecha22 = sdf.parse("2019-02-22");
            Date fecha23 = sdf.parse("2019-03-07");
            Date fecha24 = sdf.parse("2019-03-08");
            Date fecha25 = sdf.parse("2019-03-06");
            Date fecha26 = sdf.parse("2019-04-29");
            Date fecha27 = sdf.parse("2019-05-07");
            Date fecha28 = sdf.parse("2019-05-15");
            Date fecha29 = sdf.parse("2019-05-23");
            Date fecha30 = sdf.parse("2019-05-31");
            Date fecha31 = sdf.parse("2019-07-18");
            Date fecha32 = sdf.parse("2019-07-19");
            Date fecha33 = sdf.parse("2019-08-01");
            Date fecha34 = sdf.parse("2019-08-02");
            Date fecha35 = sdf.parse("2019-09-03");
            Date fecha36 = sdf.parse("2019-09-25");
            Date fecha37 = sdf.parse("2019-12-05");
            Date fecha38 = sdf.parse("2019-12-06");
            Date fecha39 = sdf.parse("2019-12-16");
            Date fecha40 = sdf.parse("2019-12-17");
            Date fecha41 = sdf.parse("2019-12-26");
            Date fecha42 = sdf.parse("2019-12-27");

            fechas.add(fecha13);
            fechas.add(fecha14);
            fechas.add(fecha15);
            fechas.add(fecha16);
            fechas.add(fecha17);
            fechas.add(fecha18);
            fechas.add(fecha19);
            fechas.add(fecha20);
            fechas.add(fecha21);
            fechas.add(fecha22);
            fechas.add(fecha23);
            fechas.add(fecha24);
            fechas.add(fecha25);
            fechas.add(fecha26);
            fechas.add(fecha27);
            fechas.add(fecha28);
            fechas.add(fecha29);
            fechas.add(fecha30);
            fechas.add(fecha31);
            fechas.add(fecha32);
            fechas.add(fecha33);
            fechas.add(fecha34);
            fechas.add(fecha35);
            fechas.add(fecha36);
            fechas.add(fecha37);
            fechas.add(fecha38);
            fechas.add(fecha39);
            fechas.add(fecha40);
            fechas.add(fecha41);
            fechas.add(fecha42);

            return fechas;

        } catch (ParseException ex) {
            Logger.getLogger(ControladorReservas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean verificarRangoValidoDeFecha(Date fechaInicio) {

        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        if (formatoFecha.format(fechaInicio).equals(formatoFecha.format(fechaActual))) {
            return true;
        }
        if ((fechaInicio.after(fechaActual))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validarHoraReserva() {
        String inicio = iuReservas.spnHoraInicio.getValue().toString();
        String fin = iuReservas.spnHoraFin.getValue().toString();

        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");

        Date horaInicial = formato.parse(inicio, new ParsePosition(0));
        Date horaFinal = formato.parse(fin, new ParsePosition(0));

        if (horaInicial.before(horaFinal)) {
            return true;
        } else {
            return false;
        }
    }

    private String diasDelPrestamo() {
        if (iuReservas.chkLunes.isSelected()) {
            return "Mon";
        } else if (iuReservas.chkMartes.isSelected()) {
            return "Tue";
        } else if (iuReservas.chkMiercoles.isSelected()) {
            return "Wed";
        } else if (iuReservas.chkJueves.isSelected()) {
            return "Thu";
        } else if (iuReservas.chkViernes.isSelected()) {
            return "Fri";
        } else if (iuReservas.chkSabado.isSelected()) {
            return "Sat";
        } else {
            return null;
        }
    }

    private boolean validarSeleccionDeDia() {
        if (iuReservas.chkLunes.isSelected() || iuReservas.chkMartes.isSelected()
                || iuReservas.chkMiercoles.isSelected() || iuReservas.chkJueves.isSelected()
                || iuReservas.chkViernes.isSelected() || iuReservas.chkSabado.isSelected()) {
            return true;
        } else {
            return false;
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
