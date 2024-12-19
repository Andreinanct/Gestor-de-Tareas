/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class ListaDeUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form ListaDeUsuarios
     */
    public ListaDeUsuarios() {
        initComponents();
        cargarUsuariosDesdeArchivo(); // Cargar los usuarios al abrir la ventana
        this.setLocationRelativeTo(null);
    }

    private void cargarUsuariosDesdeArchivo() {
     DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Limpiar la tabla antes de cargar datos

    try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("usuarios.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(","); // Separar por comas
            if (datos.length == 3) { // Asegurarse de que la línea tenga tres columnas
                // Agregar solo el primer (nombre) y tercer (rol) dato
                model.addRow(new Object[]{datos[0], datos[2]});
            }
        }
    } catch (java.io.IOException e) {
        javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al cargar el archivo de usuarios: " + e.getMessage(), 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        UsuarioEnt = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        eliminarUsuarioButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 210, 40));

        jButton2.setText("Usuarios");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 210, 40));

        jButton3.setText("Crear Tarea");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 210, 40));

        jButton4.setText("Lista de Tareas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 210, 40));

        jButton7.setText("Lista de Usuarios");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 210, 40));

        jButton5.setText("Historial");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 210, 40));

        jButton8.setText("Salir");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_Home/search.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, -1, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Rol de Usuario"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 770, 250));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Footlight MT Light", 1, 48)); // NOI18N
        jTextField1.setText("Lista de Usuarios");
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 420, 50));

        UsuarioEnt.setText("Buscar");
        UsuarioEnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UsuarioEntMousePressed(evt);
            }
        });
        UsuarioEnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioEntActionPerformed(evt);
            }
        });
        jPanel2.add(UsuarioEnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 440, 30));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(38, 128, 112));
        jTextField4.setFont(new java.awt.Font("Footlight MT Light", 1, 24)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 780, 30));

        eliminarUsuarioButton.setBackground(new java.awt.Color(0, 102, 51));
        eliminarUsuarioButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        eliminarUsuarioButton.setForeground(new java.awt.Color(255, 255, 255));
        eliminarUsuarioButton.setText("Eliminar Usuario");
        eliminarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarUsuarioButtonActionPerformed(evt);
            }
        });
        jPanel2.add(eliminarUsuarioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 170, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 810, 510));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventanas/Screenshot 2024-11-26 202151.png"))); // NOI18N
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-640, 0, 1090, 550));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventanas/Screenshot 2024-11-26 202151.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-200, 70, 890, 480));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventanas/Screenshot 2024-11-26 202151.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        VentanaAdmin homeVentana = new VentanaAdmin(variablesGlobales.getAdminUsername());
        homeVentana.setVisible(true);
        this.dispose(); // Opcional
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        CrearTarea crearTarea = new CrearTarea();
        crearTarea.setVisible(true);
        this.dispose(); // Opcional
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ListaDeTarea listaTarea = new ListaDeTarea();
        listaTarea.setVisible(true);
        this.dispose(); // Opcional
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Historial historialVentana = new Historial();
        historialVentana.setVisible(true);
        this.dispose(); // Opcional
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Usuarios usuariosVentana = new Usuarios(); // Crea una instancia de la nueva ventana
        usuariosVentana.setVisible(true); // Muestra la ventana
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void UsuarioEntMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsuarioEntMousePressed

    }//GEN-LAST:event_UsuarioEntMousePressed

    private void UsuarioEntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioEntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioEntActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void eliminarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarUsuarioButtonActionPerformed
     
        int filaSeleccionada = jTable1.getSelectedRow();
    
    if (filaSeleccionada != -1) { // Verifica que haya una fila seleccionada
        // Confirmar eliminación
        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que deseas eliminar este usuario?", 
                "Confirmar Eliminación", 
                javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
            // Obtener el nombre y el rol del usuario a eliminar
            String nombre = (String) jTable1.getValueAt(filaSeleccionada, 0);
            String rol = (String) jTable1.getValueAt(filaSeleccionada, 1);
            
            // Eliminar la fila del modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.removeRow(filaSeleccionada);
            
            // Eliminar el usuario del archivo
            eliminarUsuarioDelArchivo(nombre, rol);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.");
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, 
                "Por favor, selecciona un usuario para eliminar.", 
                "Advertencia", 
                javax.swing.JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_eliminarUsuarioButtonActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Login loginVentana = new Login();
        loginVentana.setVisible(true); // Muestra la ventana de inicio de sesión
        this.dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
    private void eliminarUsuarioDelArchivo(String nombre, String rol) {
    try {
        // Leer todos los usuarios del archivo
        java.io.File archivo = new java.io.File("usuarios.txt");
        java.util.List<String> lineas = java.nio.file.Files.readAllLines(archivo.toPath());
        
        // Eliminar la línea que coincide con el nombre y rol
        lineas.removeIf(linea -> {
            String[] datos = linea.split(",");
            return datos.length == 3 && datos[0].equals(nombre) && datos[2].equals(rol);
        });
        
        // Escribir de nuevo las líneas actualizadas al archivo
        java.nio.file.Files.write(archivo.toPath(), lineas);
    } catch (java.io.IOException e) {
        javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al guardar los cambios en el archivo: " + e.getMessage(), 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }
  
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
            java.util.logging.Logger.getLogger(ListaDeUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaDeUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaDeUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaDeUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaDeUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField UsuarioEnt;
    private javax.swing.JButton eliminarUsuarioButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
