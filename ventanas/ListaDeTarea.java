/*Melody
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.DefaultRowSorter;
import javax.swing.*;
import java.util.Set;
import java.util.HashSet;
import javax.swing.table.*;
import java.awt.*;
import java.io.FileWriter;
/**
 *
 * @author LENOVO
 */
public class ListaDeTarea extends javax.swing.JFrame {
    
    private static ListaDeTarea instance;
    private TableRowSorter<DefaultTableModel> sorter;
    /**
     * Creates new form ListaDeTarea
     */
    public ListaDeTarea() {
        initComponents();
        cargarTareas();
        cargarUsuarios();
        this.setLocationRelativeTo(null);
    }

    /**
     * Método estático para obtener la única instancia de ListaDeTarea.
     */
    public static ListaDeTarea getInstance() {
        if (instance == null) {
            instance = new ListaDeTarea();
        }
        return instance;
    }

    /**
     * Carga las tareas desde un archivo específico y las muestra en la tabla.
     */
    private void cargarTareas() {
        String archivoTareas = "tareas_" + variablesGlobales.adminUsername + ".txt";
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar la tabla

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoTareas))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 7) {
                    model.addRow(new Object[]{
                        datos[0], // Usuario asignado
                        datos[1], // Nombre de la tarea
                        datos[2], // Descripción
                        datos[3], // Prioridad
                        datos[4], // Estado
                        datos[5], // Fecha de inicio
                        datos[6]  // Fecha de entrega
                    });
                } else {
                    System.err.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Configurar el sorter
        sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        // Aplicar renderizador para colorear filas según prioridad
        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String prioridad = (String) table.getValueAt(row, 3);

                if ("Alta".equals(prioridad)) {
                    c.setBackground(new Color(255, 99, 71)); // Coral Rojo
                } else if ("Media".equals(prioridad)) {
                    c.setBackground(new Color(173, 216, 230)); // Celeste
                } else if ("Baja".equals(prioridad)) {
                    c.setBackground(new Color(169, 169, 169)); // Gris
                } else {
                    c.setBackground(Color.WHITE); // Color por defecto
                }

                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                }

                return c;
            }
        });
    }

    /**
     * Aplica filtros dinámicos a la tabla según los valores ingresados y seleccionados en los componentes.
     */
    private void aplicarFiltro() {
        String busqueda = busquedaTextFile.getText().toLowerCase();
        String prioridadSeleccionada = (String) prioridadComboBox1.getSelectedItem();
        String estadoSeleccionado = (String) estadoComboBox2.getSelectedItem();
        String usuarioSeleccionado = (String) asignacionComboBox2.getSelectedItem();

        RowFilter<DefaultTableModel, Object> rf = new RowFilter<DefaultTableModel, Object>() {
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String tarea = entry.getStringValue(1).toLowerCase();
                String descripcion = entry.getStringValue(2).toLowerCase();
                String prioridad = entry.getStringValue(3);
                String estado = entry.getStringValue(4);
                String usuario = entry.getStringValue(0);

                boolean cumpleBusqueda = tarea.contains(busqueda) || descripcion.contains(busqueda);
                boolean cumplePrioridad = prioridad.equals(prioridadSeleccionada) || prioridadSeleccionada.equals("Todos");
                boolean cumpleEstado = estado.equals(estadoSeleccionado) || estadoSeleccionado.equals("Todos");
                boolean cumpleUsuario = usuario.equals(usuarioSeleccionado) || usuarioSeleccionado.equals("Todos");

                return cumpleBusqueda && cumplePrioridad && cumpleEstado && cumpleUsuario;
            }
        };

        sorter.setRowFilter(rf);
    }

    /**
     * Carga los usuarios desde el archivo y los agrega al ComboBox de asignaciones.
     */
    private void cargarUsuarios() {
        try (Scanner scanner = new Scanner(new java.io.File("tareas_" + variablesGlobales.adminUsername + ".txt"))) {
            asignacionComboBox2.addItem("Todos");
            Set<String> usuarios = new HashSet<>();

            while (scanner.hasNextLine()) {
                String[] datos = scanner.nextLine().split(",");
                if (datos.length > 0) {
                    usuarios.add(datos[0]);
                }
            }

            for (String usuario : usuarios) {
                asignacionComboBox2.addItem(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza el estado de una tarea específica en el archivo y en la tabla.
     */
    public void actualizarEstadoTarea(String nombreTarea, String nuevoEstado) {
    String archivoTareas = "tareas_" + variablesGlobales.adminUsername + ".txt";
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    try {
        BufferedReader reader = new BufferedReader(new FileReader(archivoTareas));
        StringBuilder sb = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length == 7 && datos[1].equals(nombreTarea)) {
                System.out.println("Tarea encontrada: " + nombreTarea);
                datos[4] = nuevoEstado; // Actualizando el estado
                System.out.println("Nuevo estado: " + nuevoEstado);
            }
            sb.append(String.join(",", datos)).append("\n");
        }

        reader.close();

        // Escribir los cambios en el archivo
        FileWriter writer = new FileWriter(archivoTareas);
        writer.write(sb.toString());
        writer.close();
        System.out.println("Contenido del archivo después de escribir:");
        System.out.println(sb.toString());
        // Limpiar el filtro y recargar la tabla
        sorter.setRowFilter(null);
        cargarTareas(); // Recargar las tareas para reflejar el cambio
    } catch (IOException e) {
        System.err.println("Error al actualizar el estado de la tarea: " + e.getMessage());
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        busquedaTextFile = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        editarTareaButton = new javax.swing.JButton();
        eliminarTareaButton = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        asignacionComboBox2 = new javax.swing.JComboBox<>();
        jTextField15 = new javax.swing.JTextField();
        prioridadComboBox1 = new javax.swing.JComboBox<>();
        estadoComboBox2 = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jTextField16 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Asignado a:", "Tarea", "Descripcion", "Prioridad", "Estado", "Fecha Inicio", "Fecha de Entrega"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 780, 250));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Footlight MT Light", 1, 48)); // NOI18N
        jTextField1.setText("Lista de Tareas");
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 50));

        busquedaTextFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                busquedaTextFileMousePressed(evt);
            }
        });
        busquedaTextFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaTextFileActionPerformed(evt);
            }
        });
        jPanel2.add(busquedaTextFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 440, 30));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(38, 128, 112));
        jTextField4.setFont(new java.awt.Font("Footlight MT Light", 1, 24)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setText("Tareas Asignadas");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 780, 30));

        editarTareaButton.setBackground(new java.awt.Color(0, 102, 51));
        editarTareaButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editarTareaButton.setForeground(new java.awt.Color(255, 255, 255));
        editarTareaButton.setText("Editar Tarea");
        editarTareaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarTareaButtonActionPerformed(evt);
            }
        });
        jPanel2.add(editarTareaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 170, 40));

        eliminarTareaButton.setBackground(new java.awt.Color(0, 102, 51));
        eliminarTareaButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        eliminarTareaButton.setForeground(new java.awt.Color(255, 255, 255));
        eliminarTareaButton.setText("Eliminar Tarea");
        eliminarTareaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarTareaButtonActionPerformed(evt);
            }
        });
        jPanel2.add(eliminarTareaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 450, 170, 40));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_Home/search.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, -1, 30));

        jTextField14.setEditable(false);
        jTextField14.setBackground(new java.awt.Color(255, 255, 255));
        jTextField14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField14.setText("Por asignacion");
        jTextField14.setBorder(null);
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 110, -1));

        asignacionComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asignacionComboBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(asignacionComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 100, 30));

        jTextField15.setEditable(false);
        jTextField15.setBackground(new java.awt.Color(255, 255, 255));
        jTextField15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField15.setText("Por prioridad");
        jTextField15.setBorder(null);
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 110, -1));

        prioridadComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        prioridadComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Alta", "Media", "Baja", "Comunicado" }));
        prioridadComboBox1.setMinimumSize(new java.awt.Dimension(72, 22));
        prioridadComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prioridadComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(prioridadComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 100, 30));

        estadoComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        estadoComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Pendiente", "En Proceso", "Finalizada" }));
        estadoComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoComboBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(estadoComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, -1, 30));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images_Home/add.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 40, 40));

        jTextField16.setEditable(false);
        jTextField16.setBackground(new java.awt.Color(255, 255, 255));
        jTextField16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField16.setText("Por estado");
        jTextField16.setBorder(null);
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 110, -1));

        jTextField13.setEditable(false);
        jTextField13.setBackground(new java.awt.Color(255, 255, 255));
        jTextField13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField13.setText("Añadir Tarea");
        jTextField13.setBorder(null);
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 100, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 810, 510));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 220, 40));

        jButton2.setText("Usuarios");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 220, 40));

        jButton3.setText("Crear Tarea");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 220, 40));

        jButton4.setText("Lista de Tareas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 220, 40));

        jButton5.setText("Historial");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 220, 40));

        jButton7.setText("Lista de Usuarios");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 220, 40));

        jButton10.setText("Salir");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventanas/Screenshot 2024-11-26 202151.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-650, 0, 1080, 550));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventanas/Screenshot 2024-11-26 202151.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-340, 70, 990, 490));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ventanas/Screenshot 2024-11-26 202151.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1230, 550));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Usuarios usuariosVentana = new Usuarios(); // Crea una instancia de la nueva ventana
        usuariosVentana.setVisible(true); // Muestra la ventana
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Historial historialVentana = new Historial();
        historialVentana.setVisible(true);
        this.dispose(); // Opcional
    }//GEN-LAST:event_jButton5ActionPerformed

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
 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void busquedaTextFileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_busquedaTextFileMousePressed

    }//GEN-LAST:event_busquedaTextFileMousePressed

    private void busquedaTextFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaTextFileActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_busquedaTextFileActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void editarTareaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarTareaButtonActionPerformed
       // Obtener el índice de la fila seleccionada
        int filaSeleccionada = jTable1.getSelectedRow();

        if (filaSeleccionada != -1) { // Verifica que haya una fila seleccionada
            // Obtener los datos actuales de la tarea seleccionada
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            String usuarioActual = (String) model.getValueAt(filaSeleccionada, 0);
            String tareaActual = (String) model.getValueAt(filaSeleccionada, 1);
            String descripcionActual = (String) model.getValueAt(filaSeleccionada, 2);
            String prioridadActual = (String) model.getValueAt(filaSeleccionada, 3);
            String estadoActual = (String) model.getValueAt(filaSeleccionada, 4);
            String fechaInicio = (String) model.getValueAt(filaSeleccionada, 5);
            String fechaEntrega = (String) model.getValueAt(filaSeleccionada, 6);

            // Mostrar cuadro de diálogo para editar el nombre de la tarea
            String nuevaTarea = javax.swing.JOptionPane.showInputDialog(this,
                    "Editar nombre de la tarea:", tareaActual);
            if (nuevaTarea != null && !nuevaTarea.trim().isEmpty()) {
                // Mostrar cuadro de diálogo para editar la descripción
                String nuevaDescripcion = javax.swing.JOptionPane.showInputDialog(this,
                        "Editar descripción:", descripcionActual);
                if (nuevaDescripcion != null && !nuevaDescripcion.trim().isEmpty()) {
                    // Mostrar cuadro de selección para la prioridad
                    String[] opcionesPrioridad = {"Alta", "Media", "Baja", "Comunicado"};
                    String nuevaPrioridad = (String) javax.swing.JOptionPane.showInputDialog(
                            this,
                            "Selecciona la prioridad:",
                            "Editar Prioridad",
                            javax.swing.JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcionesPrioridad,
                            prioridadActual);

                    if (nuevaPrioridad != null) { // Si se selecciona una prioridad válida
                    // Mostrar cuadro de selección para el estado usando JComboBox
                    String[] opcionesEstado = {"Pendiente", "En proceso", "Finalizada"};
                    String nuevoEstado = (String) javax.swing.JOptionPane.showInputDialog(
                            this,
                            "Selecciona el estado:",
                            "Editar Estado",
                            javax.swing.JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcionesEstado,
                            estadoActual);

                    if (nuevoEstado != null){ 

                   
                        // Mostrar cuadro de diálogo para editar la fecha de inicio
                        String nuevaFechaInicio = javax.swing.JOptionPane.showInputDialog(this,
                                "Editar fecha de inicio (formato dd-mm-yyyy):", fechaInicio);

                        // Mostrar cuadro de diálogo para editar la fecha de entrega
                        String nuevaFechaEntrega = javax.swing.JOptionPane.showInputDialog(this,
                                "Editar fecha de entrega (formato dd-mm-yyyy):", fechaEntrega);

                        // Actualizar los datos en la tabla
                        model.setValueAt(nuevaTarea, filaSeleccionada, 1);
                        model.setValueAt(nuevaDescripcion, filaSeleccionada, 2);
                        model.setValueAt(nuevaPrioridad, filaSeleccionada, 3);
                        model.setValueAt(nuevoEstado, filaSeleccionada, 4);
                        model.setValueAt(nuevaFechaInicio, filaSeleccionada, 5);
                        model.setValueAt(nuevaFechaEntrega, filaSeleccionada, 6);

                        // Guardar los cambios en el archivo
                        guardarCambiosEnArchivo(model);

                        javax.swing.JOptionPane.showMessageDialog(this, "Tarea actualizada con éxito.");
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this,
                                "Debes seleccionar una prioridad.",
                                "Error",
                                javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this,
                            "La descripción no puede estar vacía.",
                            "Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "El nombre de la tarea no puede estar vacío.",
                        "Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Por favor, selecciona una tarea para editar.",
                    "Advertencia",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        }
    }//GEN-LAST:event_editarTareaButtonActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        ListaDeUsuarios listaUsuarios = new ListaDeUsuarios();
        listaUsuarios.setVisible(true);
        this.dispose(); // Opcional
    }//GEN-LAST:event_jButton7ActionPerformed

    private void eliminarTareaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTareaButtonActionPerformed

        int filaSeleccionada = jTable1.getSelectedRow();

        if (filaSeleccionada != -1) { // Verifica que haya una fila seleccionada
            // Confirmar eliminación
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas eliminar esta tarea?",
                "Confirmar Eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                // Eliminar la fila del modelo de la tabla
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.removeRow(filaSeleccionada);

                // Guardar los cambios en el archivo
                guardarCambiosEnArchivo(model);

                javax.swing.JOptionPane.showMessageDialog(this, "Tarea eliminada con éxito.");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Por favor, selecciona una tarea para eliminar.",
                "Advertencia",
                javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_eliminarTareaButtonActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        // Obtener el término de búsqueda desde el JTextField
        String busqueda = busquedaTextFile.getText().toLowerCase(); // Convierte el texto a minúsculas

        // Crear el filtro para buscar solo en las columnas de "Tarea" (índice 1) y "Descripción" (índice 2)
    RowFilter<DefaultTableModel, Object> rf = new RowFilter<DefaultTableModel, Object>() {
        public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
            // Obtener los valores de las columnas 1 (Tarea) y 2 (Descripción)
            String tarea = entry.getStringValue(1).toLowerCase(); // Columna de Tarea
            String descripcion = entry.getStringValue(2).toLowerCase(); // Columna de Descripción
            
            // Verificar si la búsqueda aparece en alguna de esas dos columnas
            return tarea.contains(busqueda) || descripcion.contains(busqueda);
        }
    };
        // Aplicar el filtro
        sorter.setRowFilter(rf);
}
// Método para limpiar la búsqueda
    private void limpiarBusqueda() {
        busquedaTextFile.setText(""); // Limpiar el campo de búsqueda
        sorter.setRowFilter(null); // Remover el filtro y mostrar todas las filas

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void asignacionComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignacionComboBox2ActionPerformed
        // TODO add your handling code here:
        aplicarFiltro();
    }//GEN-LAST:event_asignacionComboBox2ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void prioridadComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prioridadComboBox1ActionPerformed
        // TODO add your handling code here:
        aplicarFiltro();
    }//GEN-LAST:event_prioridadComboBox1ActionPerformed

    private void estadoComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoComboBox2ActionPerformed
        // TODO add your handling code here:
        aplicarFiltro();
    }//GEN-LAST:event_estadoComboBox2ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        CrearTarea crearTarea = new CrearTarea();
        crearTarea.setVisible(true);
        this.dispose(); // Opcional
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        Login loginVentana = new Login();
        loginVentana.setVisible(true); // Muestra la ventana de inicio de sesión
        this.dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_jButton10ActionPerformed

    private void guardarCambiosEnArchivo(DefaultTableModel model) {
        String archivoTareas = "tareas_" + variablesGlobales.adminUsername + ".txt";
    try (java.io.FileWriter fw = new java.io.FileWriter(archivoTareas);
         java.io.BufferedWriter bw = new java.io.BufferedWriter(fw)) {
        
        // Iterar sobre las filas de la tabla y escribirlas en el archivo
        for (int i = 0; i < model.getRowCount(); i++) {
            StringBuilder linea = new StringBuilder();
            for (int j = 0; j < model.getColumnCount(); j++) {
                linea.append(model.getValueAt(i, j));
                if (j < model.getColumnCount() - 1) {
                    linea.append(","); // Separador
                }
            }
            bw.write(linea.toString());
            bw.newLine();
        }
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
            java.util.logging.Logger.getLogger(ListaDeTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaDeTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaDeTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaDeTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaDeTarea().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> asignacionComboBox2;
    private javax.swing.JTextField busquedaTextFile;
    private javax.swing.JButton editarTareaButton;
    private javax.swing.JButton eliminarTareaButton;
    private javax.swing.JComboBox<String> estadoComboBox2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JComboBox<String> prioridadComboBox1;
    // End of variables declaration//GEN-END:variables
}
