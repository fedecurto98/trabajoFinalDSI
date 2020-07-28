
package interfaces;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class IUConsultarPrestamo extends javax.swing.JFrame {

    public DefaultTableModel modeloTablaPrestamos = new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            if (column == 60) {
                return true;
            } else {
                return false;
            }

        }
    };

    public IUConsultarPrestamo() {
        setTitle("Interfaz de Consultas de Préstamos - Área TIC");
        initComponents();
        setResizable(false);
        setLocation(250,20);
        setSize(500,700);

        tblPrestamos = new JTable(modeloTablaPrestamos);
        scrollTablaPrestamos.setViewportView(tblPrestamos);

        modeloTablaPrestamos.addColumn("Id");
        modeloTablaPrestamos.addColumn("Reserva");
        modeloTablaPrestamos.addColumn("Fecha");
        modeloTablaPrestamos.addColumn("Estado");

        ImageIcon wallpaper = new ImageIcon("src/images/fondoTrama.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
        lblFondo.setIcon(icono);
        this.repaint();

        txtFechaInicio.setEditable(false);
        txtFechaFin.setEditable(false);
        txtHoraInicio.setEditable(false);
        txtHoraFin.setEditable(false);
        txtRecurso.setEditable(false);
        txtUsuario.setEditable(false);
        areaObservaciones.setEditable(false);
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/UTNIcono.png"));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnModificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        lblIdPrestamo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanelPrestamos = new javax.swing.JPanel();
        scrollTablaPrestamos = new javax.swing.JScrollPane();
        tblPrestamos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        scrollAreaObservaciones = new javax.swing.JScrollPane();
        areaObservaciones = new javax.swing.JTextArea();
        txtFechaInicio = new javax.swing.JTextField();
        txtFechaFin = new javax.swing.JTextField();
        txtHoraFin = new javax.swing.JTextField();
        txtHoraInicio = new javax.swing.JTextField();
        txtRecurso = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        lblFondo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnModificar.setText("Modificar estado del préstamo");
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 620, 240, 40));

        jLabel1.setForeground(new java.awt.Color(204, 204, 255));
        jLabel1.setText("ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 80, 30));

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prestado", "Suspendido", "Pendiente", "Finalizado" }));
        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 100, 30));

        lblIdPrestamo.setForeground(new java.awt.Color(204, 204, 255));
        getContentPane().add(lblIdPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 50, 20));

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 255));
        jLabel3.setText("Reserva");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanelPrestamos.setBackground(new java.awt.Color(102, 102, 255));
        jPanelPrestamos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prestamos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelPrestamos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPrestamos.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollTablaPrestamos.setViewportView(tblPrestamos);

        jPanelPrestamos.add(scrollTablaPrestamos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 400, 230));

        getContentPane().add(jPanelPrestamos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 440, 270));
        jPanelPrestamos.getAccessibleContext().setAccessibleName("Prestamo");

        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 255));
        jLabel4.setText("Tabla de préstamos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 530, 10));

        jLabel5.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 255));
        jLabel5.setText("Préstamo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 530, 20));

        jLabel9.setForeground(new java.awt.Color(204, 204, 255));
        jLabel9.setText("Fecha inicio:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 20));

        jLabel7.setForeground(new java.awt.Color(204, 204, 255));
        jLabel7.setText("Fecha fin:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 70, 20));

        jLabel8.setForeground(new java.awt.Color(204, 204, 255));
        jLabel8.setText("Hora inicio:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 70, -1));

        jLabel6.setForeground(new java.awt.Color(204, 204, 255));
        jLabel6.setText("Hora fin:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, 20));

        jLabel10.setForeground(new java.awt.Color(204, 204, 255));
        jLabel10.setText("Recurso:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        jLabel11.setForeground(new java.awt.Color(204, 204, 255));
        jLabel11.setText("Usuario:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));

        jLabel12.setForeground(new java.awt.Color(204, 204, 255));
        jLabel12.setText("Observaciones:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        areaObservaciones.setColumns(20);
        areaObservaciones.setRows(5);
        scrollAreaObservaciones.setViewportView(areaObservaciones);

        getContentPane().add(scrollAreaObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 330, 30));

        txtFechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaInicioActionPerformed(evt);
            }
        });
        txtFechaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaInicioKeyTyped(evt);
            }
        });
        getContentPane().add(txtFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 130, -1));

        txtFechaFin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaFinKeyTyped(evt);
            }
        });
        getContentPane().add(txtFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 130, -1));

        txtHoraFin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHoraFinKeyTyped(evt);
            }
        });
        getContentPane().add(txtHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 130, -1));

        txtHoraInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHoraInicioKeyTyped(evt);
            }
        });
        getContentPane().add(txtHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 130, -1));

        txtRecurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRecursoKeyTyped(evt);
            }
        });
        getContentPane().add(txtRecurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 130, -1));

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 130, -1));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -10, 790, 860));

        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setText("Estado:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 80, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaInicioKeyTyped

    }//GEN-LAST:event_txtFechaInicioKeyTyped

    private void txtFechaFinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaFinKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFinKeyTyped

    private void txtHoraFinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraFinKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraFinKeyTyped

    private void txtHoraInicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraInicioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraInicioKeyTyped

    private void txtRecursoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecursoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRecursoKeyTyped

    private void txtFechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaInicioActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea areaObservaciones;
    public javax.swing.JButton btnModificar;
    public javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelPrestamos;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblFondo;
    public javax.swing.JLabel lblIdPrestamo;
    private javax.swing.JScrollPane scrollAreaObservaciones;
    private javax.swing.JScrollPane scrollTablaPrestamos;
    public javax.swing.JTable tblPrestamos;
    public javax.swing.JTextField txtFechaFin;
    public javax.swing.JTextField txtFechaInicio;
    public javax.swing.JTextField txtHoraFin;
    public javax.swing.JTextField txtHoraInicio;
    public javax.swing.JTextField txtRecurso;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
