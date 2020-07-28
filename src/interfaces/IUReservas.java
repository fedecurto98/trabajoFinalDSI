package interfaces;

import com.toedter.calendar.JCalendar;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IUReservas extends javax.swing.JFrame {

    public DefaultTableModel modeloTablaReserva = new DefaultTableModel() {

        public boolean isCellEditable(int row, int column) {
            if (column == 60) {
                return true;
            } else {
                return false;
            }

        }
    };

    public IUReservas() {

        setTitle("Interfaz de Gestión de Reservas - Área TIC");
        initComponents();
        setResizable(false);
        setLocation(250, 20);
        setSize(700, 730);
        lblRecurso.setText("Seleccione un recurso ");
        lblUsuario.setText("Seleccione un usuario ");

        chkLunes.setOpaque(false);
        chkMartes.setOpaque(false);
        chkMiercoles.setOpaque(false);
        chkJueves.setOpaque(false);
        chkViernes.setOpaque(false);
        chkSabado.setOpaque(false);
        btnSeleccionarUsuario.setOpaque(false);
        btnSeleccionarUsuario.setContentAreaFilled(false);
        btnSeleccionarUsuario.setBorderPainted(false);
        btnSeleccionarRecurso.setOpaque(false);
        btnSeleccionarRecurso.setContentAreaFilled(false);
        btnSeleccionarRecurso.setBorderPainted(false);
        btnAgregarReserva.setOpaque(false);
        btnAgregarReserva.setContentAreaFilled(false);
        btnAgregarReserva.setBorderPainted(false);

        tblReserva = new JTable(modeloTablaReserva);
        scrollTabla.setViewportView(tblReserva);

        modeloTablaReserva.addColumn("IdReserva");
        modeloTablaReserva.addColumn("Fecha Desde");
        modeloTablaReserva.addColumn("Fecha Hasta");
        modeloTablaReserva.addColumn("Dia");
        modeloTablaReserva.addColumn("Hora Inicio");
        modeloTablaReserva.addColumn("Hora Fin");
        modeloTablaReserva.addColumn("Usuario");
        modeloTablaReserva.addColumn("Recurso");

        ImageIcon wallpaper = new ImageIcon("src/images/fondoTrama.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
        lblFondo.setIcon(icono);
        this.repaint();

        JCalendar calendarioFechaInicio = calendarFechaInicio.getJCalendar();
        calendarioFechaInicio.setTodayButtonVisible(true);
        calendarioFechaInicio.setTodayButtonText("Fecha actual");
        calendarioFechaInicio.setWeekOfYearVisible(false);
//        calendarioFechaInicio.getDayChooser().addDateEvaluator(new FechasEspeciales());

        JCalendar calendarioFechaFin = calendarFechaFin.getJCalendar();
        calendarioFechaFin.setTodayButtonVisible(true);
        calendarioFechaFin.setTodayButtonText("Fecha actual");
        calendarioFechaFin.setWeekOfYearVisible(false);

        establecerRangoFecha();

        this.setVisible(true);

    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/UTNIcono.png"));
        return retValue;
    }

//    public static List<Calendar> fechasEspeciales() {
//        List<Calendar> fechas = new ArrayList<Calendar>();
//
//        Calendar calendar = new GregorianCalendar(2019, Calendar.DECEMBER, 10);
//        fechas.add(calendar);
//        calendar = new GregorianCalendar(2015, Calendar.DECEMBER, 12);
//        fechas.add(calendar);
//        calendar = new GregorianCalendar(2015, Calendar.DECEMBER, 18);
//        fechas.add(calendar);
//
//        return fechas;
//    }

    private void establecerRangoFecha() {
        Date fechaActual = new Date();
        calendarFechaInicio.getJCalendar().setMinSelectableDate(fechaActual);
        calendarFechaFin.getJCalendar().setMinSelectableDate(fechaActual);
//        Calendar calendar = Calendar.getInstance();
//        //esto hace que el dia maximo que se pueda elegir es 30 dias despues del dia actual
//        calendar.add(Calendar.DAY_OF_YEAR, 30);
//        calendarFechaInicio.getJCalendar().setMaxSelectableDate(calendar.getTime());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupDias = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        calendarFechaInicio = new com.toedter.calendar.JDateChooser();
        calendarFechaFin = new com.toedter.calendar.JDateChooser();
        scrollAreaObservaciones = new javax.swing.JScrollPane();
        areaObservaciones = new javax.swing.JTextArea();
        lblRecurso = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAgregarReserva = new javax.swing.JButton();
        btnSeleccionarRecurso = new javax.swing.JButton();
        btnSeleccionarUsuario = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        spnHoraInicio = new javax.swing.JSpinner();
        spnHoraFin = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        chkLunes = new javax.swing.JCheckBox();
        chkMartes = new javax.swing.JCheckBox();
        chkMiercoles = new javax.swing.JCheckBox();
        chkJueves = new javax.swing.JCheckBox();
        chkViernes = new javax.swing.JCheckBox();
        chkSabado = new javax.swing.JCheckBox();
        scrollTabla = new javax.swing.JScrollPane();
        tblReserva = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        btnPDF = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 255));
        jLabel1.setText("Tabla de Reservas");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setText("Hora fin:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel3.setForeground(new java.awt.Color(204, 204, 255));
        jLabel3.setText("Recurso:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        jLabel4.setForeground(new java.awt.Color(204, 204, 255));
        jLabel4.setText("Usuario:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jLabel6.setForeground(new java.awt.Color(204, 204, 255));
        jLabel6.setText("Observaciones:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        calendarFechaInicio.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(calendarFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 120, -1));

        calendarFechaFin.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(calendarFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 120, -1));

        areaObservaciones.setColumns(20);
        areaObservaciones.setRows(5);
        scrollAreaObservaciones.setViewportView(areaObservaciones);

        getContentPane().add(scrollAreaObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 280, 100));

        lblRecurso.setForeground(new java.awt.Color(255, 255, 255));
        lblRecurso.setText("Seleccionar Recurso");
        getContentPane().add(lblRecurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 170, -1));

        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Seleccionar usuario");
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 170, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 770, 10));

        btnAgregarReserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pngAgregarPrestamo (1).jpg"))); // NOI18N
        btnAgregarReserva.setText("Agregar Préstamo");
        getContentPane().add(btnAgregarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 100, 40));

        btnSeleccionarRecurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgReservaRecurso.jpg"))); // NOI18N
        btnSeleccionarRecurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarRecursoActionPerformed(evt);
            }
        });
        getContentPane().add(btnSeleccionarRecurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 30, 30));

        btnSeleccionarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgReservaUsuario.jpg"))); // NOI18N
        btnSeleccionarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnSeleccionarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 30, 30));

        jLabel5.setForeground(new java.awt.Color(204, 204, 255));
        jLabel5.setText("de una misma reserva");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 210, 20));

        spnHoraInicio.setModel(new javax.swing.SpinnerListModel(new String[] {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00"}));
        getContentPane().add(spnHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        spnHoraFin.setModel(new javax.swing.SpinnerListModel(new String[] {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00"}));
        getContentPane().add(spnHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        jLabel8.setForeground(new java.awt.Color(204, 204, 255));
        jLabel8.setText("Hora inicio:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        jLabel7.setForeground(new java.awt.Color(204, 204, 255));
        jLabel7.setText("Fecha fin:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        jLabel9.setForeground(new java.awt.Color(204, 204, 255));
        jLabel9.setText("Fecha inicio:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        btnGroupDias.add(chkLunes);
        chkLunes.setForeground(new java.awt.Color(255, 255, 255));
        chkLunes.setText("Lunes");
        getContentPane().add(chkLunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 80, 30));

        btnGroupDias.add(chkMartes);
        chkMartes.setForeground(new java.awt.Color(255, 255, 255));
        chkMartes.setText("Martes");
        getContentPane().add(chkMartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, -1, -1));

        btnGroupDias.add(chkMiercoles);
        chkMiercoles.setForeground(new java.awt.Color(255, 255, 255));
        chkMiercoles.setText("Miercoles");
        getContentPane().add(chkMiercoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, -1, -1));

        btnGroupDias.add(chkJueves);
        chkJueves.setForeground(new java.awt.Color(255, 255, 255));
        chkJueves.setText("Jueves");
        getContentPane().add(chkJueves, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, -1, -1));

        btnGroupDias.add(chkViernes);
        chkViernes.setForeground(new java.awt.Color(255, 255, 255));
        chkViernes.setText("Viernes");
        getContentPane().add(chkViernes, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, -1, -1));

        btnGroupDias.add(chkSabado);
        chkSabado.setForeground(new java.awt.Color(255, 255, 255));
        chkSabado.setText("Sabado");
        getContentPane().add(chkSabado, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, -1, -1));

        tblReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabla.setViewportView(tblReserva);

        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 620, 150));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 432, 780, 10));

        jLabel10.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 255));
        jLabel10.setText("Gestión de Reservas");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btnPDF.setText("Generar Reporte PDF de reservas");
        getContentPane().add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, -1, -1));

        jLabel11.setForeground(new java.awt.Color(204, 204, 255));
        jLabel11.setText("(*)Dia prestamos:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 120, 30));

        jLabel12.setForeground(new java.awt.Color(204, 204, 255));
        jLabel12.setText("(*) Representa el día de la semana ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 210, 20));

        jLabel13.setForeground(new java.awt.Color(204, 204, 255));
        jLabel13.setText("en que se generarán los préstamos");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 210, 20));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 10, 840, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarUsuarioActionPerformed

    private void btnSeleccionarRecursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarRecursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarRecursoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea areaObservaciones;
    public javax.swing.JButton btnAgregarReserva;
    private javax.swing.ButtonGroup btnGroupDias;
    public javax.swing.JButton btnPDF;
    public javax.swing.JButton btnSeleccionarRecurso;
    public javax.swing.JButton btnSeleccionarUsuario;
    public com.toedter.calendar.JDateChooser calendarFechaFin;
    public com.toedter.calendar.JDateChooser calendarFechaInicio;
    public javax.swing.JCheckBox chkJueves;
    public javax.swing.JCheckBox chkLunes;
    public javax.swing.JCheckBox chkMartes;
    public javax.swing.JCheckBox chkMiercoles;
    public javax.swing.JCheckBox chkSabado;
    public javax.swing.JCheckBox chkViernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblFondo;
    public javax.swing.JLabel lblRecurso;
    public javax.swing.JLabel lblUsuario;
    private javax.swing.JScrollPane scrollAreaObservaciones;
    public javax.swing.JScrollPane scrollTabla;
    public javax.swing.JSpinner spnHoraFin;
    public javax.swing.JSpinner spnHoraInicio;
    public javax.swing.JTable tblReserva;
    // End of variables declaration//GEN-END:variables
}
