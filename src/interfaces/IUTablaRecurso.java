package interfaces;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IUTablaRecurso extends javax.swing.JFrame {

    public DefaultTableModel modeloTablaRecursos = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            if (column == 60) {
                return true;
            } else {
                return false;
            }

        }
    };

    public IUTablaRecurso() {
        initComponents();
        setResizable(false);
        setLocation(250, 80);
        setSize(550, 350);
        setTitle("RECURSOS DISPONIBLES");

        tblRecursos = new JTable(modeloTablaRecursos);
        scrollTablaRecursos.setViewportView(tblRecursos);

        modeloTablaRecursos.addColumn("Id");
        modeloTablaRecursos.addColumn("Nombre");
        modeloTablaRecursos.addColumn("Descripcion");
        modeloTablaRecursos.addColumn("Estado");
        modeloTablaRecursos.addColumn("Tipo");

        ImageIcon wallpaper = new ImageIcon("src/images/fondoTrama.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
        lblFondo.setIcon(icono);
        this.repaint();

    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/UTNIcono.png"));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInformacion = new javax.swing.JLabel();
        jPanelRecursos = new javax.swing.JPanel();
        scrollTablaRecursos = new javax.swing.JScrollPane();
        tblRecursos = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInformacion.setForeground(new java.awt.Color(204, 204, 255));
        lblInformacion.setText("Recurso seleccionado:");
        getContentPane().add(lblInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 280, -1));

        jPanelRecursos.setBackground(new java.awt.Color(102, 102, 255));
        jPanelRecursos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Recursos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanelRecursos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblRecursos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRecursos.setRequestFocusEnabled(false);
        scrollTablaRecursos.setViewportView(tblRecursos);

        jPanelRecursos.add(scrollTablaRecursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 430, 200));

        getContentPane().add(jPanelRecursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 480, 260));

        btnCerrar.setText("Confirmar y cerrar");
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, -1, -1));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -6, 570, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCerrar;
    private javax.swing.JPanel jPanelRecursos;
    private javax.swing.JLabel lblFondo;
    public javax.swing.JLabel lblInformacion;
    public javax.swing.JScrollPane scrollTablaRecursos;
    public javax.swing.JTable tblRecursos;
    // End of variables declaration//GEN-END:variables
}
