
package interfaces;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class IUUsuarios extends javax.swing.JFrame {

    public DefaultTableModel modeloTablaUsuarios = new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            if (column == 60) {
                return true;
            }else{
                return false;
            }
 
        }
    };

    public IUUsuarios() {
        setTitle("Interfaz de Gestión de Usuarios - Área TIC");
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(550, 550);

        tblUsuarios = new JTable(modeloTablaUsuarios);
        scrollTabla.setViewportView(tblUsuarios);

        modeloTablaUsuarios.addColumn("Id");
        modeloTablaUsuarios.addColumn("Nombre");
        modeloTablaUsuarios.addColumn("Apellido");
        modeloTablaUsuarios.addColumn("Legajo");
        modeloTablaUsuarios.addColumn("Telefono");
        modeloTablaUsuarios.addColumn("Estado");

        ImageIcon wallpaper = new ImageIcon("src/images/fondoTrama.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
        lblFondo.setIcon(icono);
        this.repaint();

        setVisible(true);
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/UTNIcono.png"));
        return retValue;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblIdUsuario = new javax.swing.JLabel();
        scrollTabla = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtLegajo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        cboEstado = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregar.setText("Agregar");
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 130, -1));

        btnEliminar.setText("Eliminar");
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 130, -1));

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 130, -1));

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setActionCommand("Limpiar ");
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 130, -1));

        btnPDF.setText("Ver reporte PDF");
        getContentPane().add(btnPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 130, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 255));
        jLabel9.setText("Estado:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 255));
        jLabel8.setText("(*) CAMPO OBLIGATORIO");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setText("(*)Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 255));
        jLabel1.setText("ID Usuario:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 15, -1, -1));

        lblIdUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblIdUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblIdUsuario.setText("ID");
        getContentPane().add(lblIdUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 17, -1, -1));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollTabla.setViewportView(tblUsuarios);

        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 240, 500, 250));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 255));
        jLabel10.setText("(*)Apellido:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 255));
        jLabel11.setText("(*)Legajo:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 170, -1));

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 170, -1));

        txtLegajo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLegajoKeyTyped(evt);
            }
        });
        getContentPane().add(txtLegajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 170, -1));

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 170, -1));

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Habilitado", "Inhabilitado" }));
        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 255));
        jLabel12.setText("Telefono:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        btnCancelar.setText("Cancelar");
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 130, -1));

        btnNuevo.setText("Nuevo");
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 130, -1));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 670, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
       char c = evt.getKeyChar();
       if((c< 'a'|| c > 'z') && (c< 'A'|| c > 'Z') ) evt.consume();
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        char c = evt.getKeyChar();
       if((c< 'a'|| c > 'z') && (c< 'A'|| c > 'Z') ) evt.consume();
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtLegajoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLegajoKeyTyped
        char c = evt.getKeyChar();
       if(c< '0'|| c > '9')  evt.consume();
    }//GEN-LAST:event_txtLegajoKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
         char c = evt.getKeyChar();
       if(c< '0'|| c > '9')  evt.consume();
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JButton btnPDF;
    public javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblFondo;
    public javax.swing.JLabel lblIdUsuario;
    public javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tblUsuarios;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtLegajo;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
