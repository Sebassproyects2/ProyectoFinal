package frames;

import GProyectos.GestNotificacion;
import gestor.controlador.Notificacion;
import gestor.controlador.Usuario;
import javax.swing.JOptionPane;

public class frmNotificacion extends javax.swing.JFrame {

    private Usuario usuario;

    /**
     * Creates new form frmNotificacion
     */
    public frmNotificacion(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        this.setLocationRelativeTo(null);
        cargarPreferencias();
    }

    private void cargarPreferencias() {
        GestNotificacion gestNotif = new GestNotificacion();

        //Busco las preferencias de notificación del usuario en la base de datos
        Notificacion notif = gestNotif.buscarPreferenciaPorUsuario(usuario.getNombre() + " (" + usuario.getEmail() + ")");

        if (notif != null) {
            //Cargo las preferencias de la BD
            int diasAntes = notif.getDiasAntes();
            switch (diasAntes) {
                case 5:
                    cmbDiasAntes.setSelectedIndex(1);
                    break;
                case 4:
                    cmbDiasAntes.setSelectedIndex(2);
                    break;
                case 3:
                    cmbDiasAntes.setSelectedIndex(3);
                    break;
                case 2:
                    cmbDiasAntes.setSelectedIndex(4);
                    break;
                case 1:
                    cmbDiasAntes.setSelectedIndex(5);
                    break;
                default:
                    cmbDiasAntes.setSelectedIndex(0); // Desactivado
                    break;
            }

            //Cargo las preferencias de intervalo en minutos
            int minutosIntervalo = notif.getMinutosIntervalo();
            switch (minutosIntervalo) {
                case 180:
                    cmbIntervalo.setSelectedIndex(1); //3 horas
                    break;
                case 120:
                    cmbIntervalo.setSelectedIndex(2); //2 horas
                    break;
                case 60:
                    cmbIntervalo.setSelectedIndex(3); //1 hora
                    break;
                case 30:
                    cmbIntervalo.setSelectedIndex(4); //30 minutos
                    break;
                case 10:
                    cmbIntervalo.setSelectedIndex(5); //10 minutos
                    break;
                case 2:
                    cmbIntervalo.setSelectedIndex(6); //2 minutos
                    break;
                default:
                    cmbIntervalo.setSelectedIndex(0); //Desactivado
                    break;
            }

            //Si las notificaciones están detenidas, selecciono "Desactivado"
            if (notif.isDetener()) {
                cmbDiasAntes.setSelectedIndex(0);
                cmbIntervalo.setSelectedIndex(0);
            }
        } else {
            //Si no hay preferencias, se establecen los valores predeterminados
            cmbDiasAntes.setSelectedIndex(1);
            cmbIntervalo.setSelectedIndex(1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbDiasAntes = new javax.swing.JComboBox<>();
        cmbIntervalo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 0));

        cmbDiasAntes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desactivado", "5 días", "4 días", "3 días", "2 días", "1 día" }));
        cmbDiasAntes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDiasAntesItemStateChanged(evt);
            }
        });

        cmbIntervalo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desactivado", "3 horas", "2 horas", "1 hora", "30 minutos", "10 minutos", "2 minutos" }));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleccione su preferencia de notificaciones:");

        jLabel2.setText("Días antes del vencimiento de la tarea para iniciar notificaciones:");

        jLabel3.setText("Frecuencia de las notificaciones:");

        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/volver-flecha.png"))); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/agregar-archivo.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDiasAntes, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver)
                        .addGap(92, 92, 92)
                        .addComponent(btnActualizar)
                        .addGap(155, 155, 155)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbDiasAntes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(cmbIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnActualizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        frmMenuPrincipal menuPrincipal = new frmMenuPrincipal(this.usuario);
        this.dispose();
        menuPrincipal.setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // Obtener los valores seleccionados
        String diasAntesStr = cmbDiasAntes.getSelectedItem().toString();
        String intervaloStr = cmbIntervalo.getSelectedItem().toString();

        int diasAntes = 0;
        int minutosIntervalo = 0;
        boolean detener = false;

        // Convertir el valor de días antes
        if (!diasAntesStr.equals("Desactivado")) {
            // Extraer el número de días porque el texto dice "x dias"
            diasAntes = Integer.parseInt(diasAntesStr.split(" ")[0]);
        } else {
            detener = true; // Si está desactivado, se detienen las notificaciones
        }

        // Convertir el valor del intervalo a minutos
        if (!intervaloStr.equals("Desactivado") && !detener) {
            if (intervaloStr.contains("hora")) {
                minutosIntervalo = Integer.parseInt(intervaloStr.split(" ")[0]) * 60;
            } else if (intervaloStr.contains("minuto")) {
                minutosIntervalo = Integer.parseInt(intervaloStr.split(" ")[0]);
            }
        } else {
            detener = true; // Si está desactivado, se detienen las notificaciones
        }

        // Crear una instancia de GestNotificacion para actualizar la preferencia
        GestNotificacion gestNotif = new GestNotificacion();
        Notificacion notif = gestNotif.buscarPreferenciaPorUsuario(this.usuario.getNombre() + " (" + this.usuario.getEmail() + ")");

        if (notif != null) {
            // Actualizar los valores en la notificación
            notif.setDiasAntes(diasAntes);
            notif.setMinutosIntervalo(minutosIntervalo);
            notif.setDetener(detener);

            // Actualizar la base de datos con los nuevos valores
            gestNotif.actualizarPreferencia(notif);

            JOptionPane.showMessageDialog(null, "Preferencias de notificación actualizadas con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: No se encontraron preferencias de notificación para este usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void cmbDiasAntesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDiasAntesItemStateChanged
        if (cmbDiasAntes.getSelectedItem().toString().equals("Desactivado")){
            cmbIntervalo.setSelectedIndex(0);
        }
        
    }//GEN-LAST:event_cmbDiasAntesItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

}
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmNotificacion.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNotificacion.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNotificacion.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNotificacion.class  

.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new frmNotificacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbDiasAntes;
    private javax.swing.JComboBox<String> cmbIntervalo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
