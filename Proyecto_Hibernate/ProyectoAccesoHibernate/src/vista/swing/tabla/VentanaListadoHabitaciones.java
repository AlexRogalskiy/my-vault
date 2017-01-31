/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.swing.tabla;

import aplicacion.facade.Facade;
import dto.Habitacion;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;

/**
 * @author Mario Codes Sánchez
 * @since 10/11/2016
 */
public class VentanaListadoHabitaciones extends javax.swing.JFrame {
    private DefaultTableModel model;
    private String tipo_habitacion = null;
    
    public VentanaListadoHabitaciones() {
        initComponents();
        
        this.setTitle("");
        this.setVisible(true);
        this.setResizable(false);
        
        this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        rellenoTablaDatos();
    }
    
    /**
     * Constructor por defecto que utilizare yo.
     * @param tipo_habitacion 
     */
    public VentanaListadoHabitaciones(String tipo_habitacion) {
        initComponents(); //Ini necesario de los componentes intrinsecos de la ventana.

        this.tipo_habitacion = tipo_habitacion;
        this.setVisible(true);
        this.setResizable(false);
        
        rellenoTablaDatos(tipo_habitacion);
    }
    
    /**
     * Metodo que se encarga de actualizar la tabla con el RestultSet que paso como parametro por el constructor.
     * Montado por mi cuenta googleando, explicado dentro.
     */
    private void rellenoTablaDatos() {
        if(model != null) model.setRowCount(0);
        
        model = (DefaultTableModel) tabla.getModel(); //Hacemos un get del DefaultModel con el que creamos la tabla desde Swing.

        Session s = Facade.abrirSessionHibernate();
        List lista = s.createCriteria(Habitacion.class).list();
        
        ListIterator li = lista.listIterator();
        
        while(li.hasNext()) {
            Habitacion habitacion = (Habitacion) li.next();
            
            try {
                Object[] row = new Object[8];
                row[0] = habitacion.getIdHabitacion();
                row[1] = habitacion.getAlojamientoIdAlojamiento();
                row[2] = habitacion.getReservaIdReserva();
                row[3] = habitacion.getExtrasHabitacion();
                row[4] = habitacion.getPrecio();
                row[5] = habitacion.getCuartoBanio();
                row[6] = habitacion.getTipoHabitacion();
                row[7] = habitacion.getResenias();
                model.addRow(row);
            }catch(NullPointerException ex) {
                System.out.println("Error especifico: " +ex.getLocalizedMessage());
                JOptionPane.showMessageDialog(this, "ERROR. NullPointerException.");
            }
        }
    }
    
    private void rellenoTablaDatos(String tipo_habitacion) {
        if(model != null) model.setRowCount(0);
        
        model = (DefaultTableModel) tabla.getModel(); //Hacemos un get del DefaultModel con el que creamos la tabla desde Swing.

        Session s = Facade.abrirSessionHibernate();
        List lista = s.createCriteria(Habitacion.class).list();
        
        ListIterator li = lista.listIterator();
        
        while(li.hasNext()) {
            Habitacion habitacion = (Habitacion) li.next();
            
            if(habitacion.getTipoHabitacion().matches(tipo_habitacion)) {
                try {
                    Object[] row = new Object[8];
                    row[0] = habitacion.getIdHabitacion();
                    row[1] = habitacion.getAlojamientoIdAlojamiento();
                    row[2] = habitacion.getReservaIdReserva();
                    row[3] = habitacion.getExtrasHabitacion();
                    row[4] = habitacion.getPrecio();
                    row[5] = habitacion.getCuartoBanio();
                    row[6] = habitacion.getTipoHabitacion();
                    row[7] = habitacion.getResenias();
                    model.addRow(row);
                }catch(NullPointerException ex) {
                    System.out.println("Error especifico: " +ex.getLocalizedMessage());
                    JOptionPane.showMessageDialog(this, "ERROR. NullPointerException.");
                }
            }
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

        labelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        botonCerrar = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 0));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelTitulo.setText("Habitaciones Ordenadas por Precio");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Habitacion", "ID_Alojamiento", "ID_Reserva", "Extras", "Precio", "# Cuarto(s) Baño", "Tipo Habitacion", "Reseñas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setRowHeight(18);
        tabla.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tabla);

        botonCerrar.setText("Cerrar");
        botonCerrar.setMaximumSize(new java.awt.Dimension(140, 38));
        botonCerrar.setMinimumSize(new java.awt.Dimension(140, 38));
        botonCerrar.setPreferredSize(new java.awt.Dimension(140, 38));
        botonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarActionPerformed(evt);
            }
        });

        botonActualizar.setText("Actualizar");
        botonActualizar.setMaximumSize(new java.awt.Dimension(140, 38));
        botonActualizar.setMinimumSize(new java.awt.Dimension(140, 38));
        botonActualizar.setPreferredSize(new java.awt.Dimension(140, 38));
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(labelTitulo)
                .addGap(0, 205, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonCerrarActionPerformed

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        if(this.tipo_habitacion == null) rellenoTablaDatos();
        else rellenoTablaDatos(tipo_habitacion);
        JOptionPane.showMessageDialog(this, "Tabla Actualizada.");
    }//GEN-LAST:event_botonActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

}
