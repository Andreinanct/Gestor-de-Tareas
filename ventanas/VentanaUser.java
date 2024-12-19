/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VentanaUser extends javax.swing.JFrame {
    private String username;
    private TableRowSorter<DefaultTableModel> sorter;

    
    // Método para cargar las tareas del usuario
    public void cargarTareasUsuario() {
        // Modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de llenarla

        String archivoTareas = "tareasGeneral.txt"; // Archivo con todas las tareas

        try (Scanner scanner = new Scanner(new File(archivoTareas))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");

                // Validar datos antes de procesarlos
                if (datos.length < 8) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                } // Suponiendo formato: admin, usuarioAsignado, nombre, descripcion, prioridad, estado, fechaInicio, fechaEntrega

                String usuarioAsignado = datos[1];
                String estado = datos[5];

                if (usuarioAsignado.equalsIgnoreCase(username) && !estado.equalsIgnoreCase("Entregada")) {
                    modelo.addRow(new Object[] {
                        datos[0], // Nombre del administrador
                        datos[2], // Nombre de la tarea
                        datos[3], // Descripción
                        datos[4], // Prioridad
                        estado,   // Estado
                        datos[7]  // Fecha de entrega
                    });
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "No se encontró el archivo de tareas.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las tareas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        sorter = new TableRowSorter<>(modelo);
        jTable1.setRowSorter(sorter); // Asignar el sorter a la tabla
        
        // Aplicar el renderizador para cambiar el color de las filas según la prioridad
        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                                                           boolean hasFocus, int row, int column) {
                // Obtener el valor de la prioridad en la columna 3 (índice 3)
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String prioridad = (String) table.getValueAt(row, 3);
                
                // Cambiar el color si la prioridad es "Alta"
                if ("Alta".equals(prioridad)) {
                    c.setBackground(new Color(255, 99, 71)); // Coral Rojo 
                } else if ("Media".equals(prioridad)) {
                    c.setBackground(new Color(173, 216, 230)); // Celeste 
                } else if ("Baja".equals(prioridad)) {
                    c.setBackground(new Color(169, 169, 169)); // Plomo
                } else if ("Comunicado".equals(prioridad)) {
                    c.setBackground(Color.WHITE); // Blanco
                } else {
                    c.setBackground(Color.WHITE); // Color por defecto (blanco)
                }

                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                }

                return c;
            
            }
        });
  
    }
    
    // Método para actualizar el estado de la tarea seleccionada
    private void entregarTarea() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = jTable1.convertRowIndexToModel(selectedRow);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            String tareaNombre = (String) model.getValueAt(modelRow, 1);
            String tareaUsuario = (String) model.getValueAt(modelRow, 0);

            model.setValueAt("Entregado", modelRow, 4);

            String archivoTareas = "tareas_" + variablesGlobales.adminUsername + ".txt";
            try {
                File archivo = new File(archivoTareas);
                Scanner scanner = new Scanner(archivo);
                StringBuilder contenido = new StringBuilder();

                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] datos = linea.split(",");

                    if (datos[2].equals(tareaNombre) && datos[1].equals(username)) {
                        datos[5] = "Entregado"; // Cambiar estado a "Entregado"
                    }

                    contenido.append(String.join(",", datos)).append("\n");
                }

                PrintWriter writer = new PrintWriter(archivo);
                writer.write(contenido.toString());
                writer.close();
                JOptionPane.showMessageDialog(this, "La tarea fue entregada exitosamente.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar la tarea: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea para entregarla.");
        }
    }
    /**
     * Creates new form Home
     */
    
    
    public VentanaUser(String username) {
        this.username = username;
        initComponents();
        cargarTareasUsuario();
        jTextField2.setText("Hola, " + username + "!");
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        UsuarioEnt = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Footlight MT Light", 1, 48)); // NOI18N
        jTextField1.setText("Inicio");
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 50));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Footlight MT Light", 1, 36)); // NOI18N
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 420, 40));

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
        jPanel1.add(UsuarioEnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 440, 30));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_Home/search.png"))); // NOI18N
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(38, 128, 112));
        jTextField4.setFont(new java.awt.Font("Footlight MT Light", 1, 24)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setText("Menú");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 780, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Asignado por:", "Tarea", "Descripcion", "Prioridad", "Estado", "Fecha de Entrega"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 810, 290));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 820, 430));

        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 220, 40));

        jButton4.setText("Mis Tareas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 220, 40));

        jButton5.setText("Historial");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 220, 40));

        jButton8.setText("Salir");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, -1, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventanas/Screenshot 2024-11-26 202151.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-640, 0, 1090, 570));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventanas/Screenshot 2024-11-26 202151.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-200, 250, 1090, 300));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventanas/Screenshot 2024-11-26 202151.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 1080, 600));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        TareasUser tareasUser = new TareasUser();
        tareasUser.setVisible(true);
        this.dispose(); // Opcional
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        HistorialUser historialUser = new HistorialUser();
        historialUser.setVisible(true);
        this.dispose(); // Opcional
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Login loginVentana = new Login();
        loginVentana.setVisible(true); // Muestra la ventana de inicio de sesión
        this.dispose(); // Cierra la ventana actual

    }//GEN-LAST:event_jButton8ActionPerformed

    private void UsuarioEntMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsuarioEntMousePressed

    }//GEN-LAST:event_UsuarioEntMousePressed

    private void UsuarioEntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioEntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioEntActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        String username = "username";
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
            java.util.logging.Logger.getLogger(VentanaUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaUser(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField UsuarioEnt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
