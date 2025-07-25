
package aduana2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Savepoint;


import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DnDConstants;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.util.List;


public class ImportarTEST3 extends javax.swing.JFrame {
    public File archivoSeleccionado; ///Ruta de archivo
    public JTextField txtRenombrar;
    public JPanel indicador;
    public JLabel estado;


    ///Meter a BD///
   private void subirArchivoCSV(File archivo) {
    int registrosInsertados = 0;
    int errores = 0;

    String url = "jdbc:mysql://localhost:3306/aduana";
    String usuario = "root";
    String password = "mysql";

    try (
        Connection conn = DriverManager.getConnection(url, usuario, password);
        BufferedReader br = new BufferedReader(new FileReader(archivo));
    ) {
        conn.setAutoCommit(false);

        String sqlArchivo = "INSERT INTO imported_files (file_name_if) VALUES (?)";
        PreparedStatement psArchivo = conn.prepareStatement(sqlArchivo, Statement.RETURN_GENERATED_KEYS);
        psArchivo.setString(1, archivo.getName());
        psArchivo.executeUpdate();

        ResultSet rsArchivo = psArchivo.getGeneratedKeys();
        int idArchivo = 0;
        if (rsArchivo.next()) {
            idArchivo = rsArchivo.getInt(1);
        }
        rsArchivo.close();
        psArchivo.close();

        String linea;
        int lineaActual = 0;

        while ((linea = br.readLine()) != null) {
            if (lineaActual == 0) {
                lineaActual++;
                continue;
            }

            String[] datos = linea.split(",");

            if (datos.length < 8) {
                System.err.println("Línea incompleta en línea " + lineaActual + ": " + linea);
                errores++;
                lineaActual++;
                continue;
            }

            String fullName = datos[0].trim();
            String email = datos[1].trim();
            String phone = datos[2].trim();
            String birthDateStr = datos[3].trim();
            String ssn = datos[4].trim();
            String voterId = datos[5].trim();
            String address = datos[6].trim();
            String systemRole = datos[7].trim();

            Savepoint puntoGuardado = conn.setSavepoint();
            
            try {
                Date birthDate = Date.valueOf(birthDateStr);

                String sqlUser = "INSERT INTO migrated_users (full_name_mu, email_mu, phone_mu, birth_date_mu, id_file) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement psUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
                psUser.setString(1, fullName);
                psUser.setString(2, email);
                psUser.setString(3, phone);
                psUser.setDate(4, birthDate);
                psUser.setInt(5, idArchivo); 
                psUser.executeUpdate();

                ResultSet rs = psUser.getGeneratedKeys();
                int userId = 0;
                if (rs.next()) {
                    userId = rs.getInt(1);
                }
                rs.close();
                psUser.close();

                String sqlDetails = "INSERT INTO migrated_details (user_id_mu, ssn_md, voter_id_md, address_md, system_role_md) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement psDetails = conn.prepareStatement(sqlDetails);
                psDetails.setInt(1, userId);
                psDetails.setString(2, ssn);
                psDetails.setString(3, voterId);
                psDetails.setString(4, address);
                psDetails.setString(5, systemRole);
                psDetails.executeUpdate();
                psDetails.close();

                registrosInsertados++;

            } catch (Exception ex) {
                System.err.println("❌ Error al insertar línea " + lineaActual + ": " + ex.getMessage());
                conn.rollback(puntoGuardado);
                errores++;
            }

            lineaActual++;
        }

        conn.commit();

        if (registrosInsertados > 0) {
            estado.setText(" Registros insertados: " + registrosInsertados + " | Errores: " + errores);
            indicador.setBackground(new Color(0x00bf63));
        } else {
            estado.setText("WARNING: Ningún registro fue insertado.");
            indicador.setBackground(Color.RED);
        }

        indicador.setVisible(true);

    } catch (Exception ex) {
        System.err.println("Error general al importar: " + ex.getMessage());
        estado.setText(" Error al importar archivo.");
        indicador.setBackground(Color.RED);
        indicador.setVisible(true);
    }
}




    
    
    //LINEA PUNTEADAAA//
    private void punta(JComponent comp, Color color) {
    comp.setBorder(new Border() {
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 5, 5, 5);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color);
            float[] dash = {5f, 5f};
            g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash, 0));
            g2.drawRect(x, y, width - 1, height - 1);
            g2.dispose();
                    }
                });
            }
    private void aplicarBordeRedondeado(JComponent componente, int radio, Color color, int grosor) {
    componente.setBorder(new javax.swing.border.AbstractBorder() {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color);
            g2.setStroke(new BasicStroke(grosor));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawRoundRect(x + grosor / 2, y + grosor / 2, width - grosor, height - grosor, radio, radio);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 10, 5, 10);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    });
}
    
    
    public ImportarTEST3() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

            setSize(830,520);
            getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
                JPanel n = new JPanel();
                n.setBackground(Color.WHITE);
                n.setLayout(null);
                n.setBounds(0, 0, this.getWidth(), this.getHeight());
                add(n);

        //LOGO IZQ SUPERIOR//
        JLabel logo = new JLabel();
            logo.setIcon(new ImageIcon("C:\\Users\\Fifi\\Desktop\\UTA\\Diseño SW\\LOGOBulk.png"));
            logo.setBounds(6, 6, 37, 47);
        n.add(logo);
        JLabel autor = new JLabel("BulkEnroller");
                autor.setBounds(45,7,200,40);
                autor.setFont(new Font("Arial", Font.BOLD, 16));
        n.add(autor);
        
        //RENOMBRAR ARCHIVO//
        txtRenombrar = new JTextField("Renombrar Archivo...");
            txtRenombrar.setBackground(new Color(0xefefef));
            txtRenombrar.setForeground(Color.BLACK);
            txtRenombrar.setFont(new Font("Arial", Font.PLAIN, 12));
            txtRenombrar.setHorizontalAlignment(SwingConstants.LEFT);
            txtRenombrar.setBounds(73, 80, 240, 20);
            txtRenombrar.setOpaque(true);
            txtRenombrar.setFocusable(true);
            txtRenombrar.requestFocusInWindow();
            txtRenombrar.setEnabled(false);
            txtRenombrar.setEditable(true);

            aplicarBordeRedondeado(txtRenombrar, 15, Color.GRAY, 1);

            n.add(txtRenombrar);

        
        
       
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //VISTA PREVIA
                // para poder empalmar
        JPanel panelContenedor = new JPanel(new CardLayout());
        panelContenedor.setBounds(50, 110, 280, 272);
        panelContenedor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        n.add(panelContenedor);

        // Vista inicial: imagen
        JLabel previa = new JLabel();
        previa.setIcon(new ImageIcon("C:\\Users\\Fifi\\Desktop\\UTA\\Diseño SW\\VISTA.png"));
        panelContenedor.add(previa, "imagen");

        // CardLayout handler
        CardLayout cl = (CardLayout) panelContenedor.getLayout();
        
                JButton btnCambio = new JButton("Usuario");
        btnCambio.setBounds(80, 390, 105, 15);
        btnCambio.setBackground(Color.WHITE);
        btnCambio.setForeground(Color.BLACK); 
        btnCambio.setFont(new Font("Arial", Font.PLAIN, 9));
        btnCambio.setFocusPainted(false);
        btnCambio.setContentAreaFilled(true);
        btnCambio.setOpaque(true);
        aplicarBordeRedondeado(btnCambio, 15, new Color(0x4996FF), 1);
        n.add(btnCambio);

        btnCambio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("Nombre");
                modelo.addColumn("Correo");
                modelo.addColumn("Teléfono");
                modelo.addColumn("Nacimiento");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aduana", "root", "mysql")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_mu, full_name_mu, email_mu, phone_mu, birth_date_mu FROM migrated_users");

            while (rs.next()) {
                modelo.addRow(new Object[] {
                    rs.getInt("id_mu"),
                    rs.getString("full_name_mu"),
                    rs.getString("email_mu"),
                    rs.getString("phone_mu"),
                    rs.getDate("birth_date_mu")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener usuarios: " + ex.getMessage());
        }

        JTable tablaUsuarios = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaUsuarios);
        panelContenedor.add(scroll, "tablaUsuarios");
        cl.show(panelContenedor, "tablaUsuarios");
            }
        });
        
            JButton btnCambio1 = new JButton("Detalles");
        btnCambio1.setBounds(195, 390, 105, 15);
        btnCambio1.setBackground(Color.WHITE);
        btnCambio1.setForeground(Color.BLACK); 
        btnCambio1.setFont(new Font("Arial", Font.PLAIN, 9));
        btnCambio1.setFocusPainted(false);
        btnCambio1.setContentAreaFilled(true);
        btnCambio1.setOpaque(true);
        aplicarBordeRedondeado(btnCambio1, 15, new Color(0x4996FF), 1);
        n.add(btnCambio1);

        btnCambio1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("ID");
                modelo.addColumn("SSN");
                modelo.addColumn("Voter ID");
                modelo.addColumn("Dirección");
                modelo.addColumn("Rol");

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aduana", "root", "mysql")) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT id_md, ssn_md, voter_id_md, address_md, system_role_md FROM migrated_details");

                    while (rs.next()) {
                        modelo.addRow(new Object[] {
                            rs.getInt("id_md"),
                            rs.getString("ssn_md"),
                            rs.getString("voter_id_md"),
                            rs.getString("address_md"),
                            rs.getString("system_role_md")
                        });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al obtener detalles: " + ex.getMessage());
                }

                JTable tablaDetalles = new JTable(modelo);
                JScrollPane scroll = new JScrollPane(tablaDetalles);
                panelContenedor.add(scroll, "tablaDetalles");
                cl.show(panelContenedor, "tablaDetalles");
            }
        });



         
         
         
         
         //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         //SEPARADOR
         JPanel separador = new JPanel();
            separador.setBackground(new Color(0xd9d9d9)); 
            separador.setBounds(380, 40, 1,400); 
         n.add(separador);
         
         //TITULO DER SUP
         JLabel titulo = new JLabel("Importar CSV a Base de Datos");
            titulo.setFont(new Font("Arial", Font.BOLD, 24));
            titulo.setForeground(Color.BLACK); 
            titulo.setBounds(425, 55, 450, 30); 
         n.add(titulo);
         
         //Caja de texto puntedaaaaa
         JLabel txtArrastrar = new JLabel("<html><div style='text-align: center;'>Arrastra tu archivo aquí<br>ó</div></html>");
                txtArrastrar.setFont(new Font("Arial", Font.PLAIN, 13));
                txtArrastrar.setForeground(Color.GRAY);
                txtArrastrar.setHorizontalAlignment(SwingConstants.CENTER);
                txtArrastrar.setBounds(450, 110, 300, 130);
                punta(txtArrastrar, new Color(0xD9D9D9));
         n.add(txtArrastrar);
         
         
                new DropTarget(txtArrastrar, new DropTargetAdapter() {
                @Override
                public void drop(DropTargetDropEvent evt) {
                    try {
                        evt.acceptDrop(DnDConstants.ACTION_COPY);
                        List<File> archivos = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                        if (!archivos.isEmpty()) {
                            File archivo = archivos.get(0);

                            if (archivo.getName().toLowerCase().endsWith(".csv")) {
                                archivoSeleccionado = archivo;

                                txtRenombrar.setText(archivo.getName());
                                txtRenombrar.setEnabled(true);

                                System.out.println("Archivo arrastrado: " + archivoSeleccionado.getAbsolutePath());
                            } else {
                                estado.setText("WARNING: Solo se permiten archivos .csv");
                                indicador.setBackground(Color.RED);
                                indicador.setVisible(true);
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        estado.setText("Error al leer el archivo arrastrado.");
                        indicador.setBackground(Color.RED);
                        indicador.setVisible(true);
                    }
                }
            });




         
         /////////////////////////////////////////////////////////////////////////////         
         
         JButton btnSeleccionar = new JButton("Seleccionar archivo");
               btnSeleccionar.setBackground(Color.WHITE);
               btnSeleccionar.setForeground(Color.BLACK);
               btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 9));
               btnSeleccionar.setBounds(545, 195, 117, 20);
               btnSeleccionar.setFocusPainted(false);
               btnSeleccionar.setContentAreaFilled(true);
               btnSeleccionar.setOpaque(true);
               
               aplicarBordeRedondeado(btnSeleccionar, 15, new Color(0x4996FF), 1);
               
               btnSeleccionar.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            JFileChooser fc = new JFileChooser();
                                            FileNameExtensionFilter filtroCSV = new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv");
                                            fc.setFileFilter(filtroCSV);

                                            int seleccion = fc.showOpenDialog(null);
                                            if (seleccion == JFileChooser.APPROVE_OPTION) {
                                                archivoSeleccionado = fc.getSelectedFile();
                                                System.out.println("Archivo seleccionado: " + archivoSeleccionado.getAbsolutePath());
                                                
                                                        txtRenombrar.setText(archivoSeleccionado.getName()); //Sacar bombre
                                                        txtRenombrar.setEnabled(true);

                                            }
                                        }
                                    });
               
         n.add(btnSeleccionar);
         
         //////////////////////////////////////////////////////////////////////////////      
               JLabel avisoFormato = new JLabel("Solo archivos .csv");
                    avisoFormato.setFont(new Font("Arial", Font.PLAIN, 10));
                    avisoFormato.setForeground(new Color(0x666666));
                    avisoFormato.setBounds(660, 240, 200, 20); 
                    
               JLabel lblEspecificaciones = new JLabel("<html><u>Especificaciones</u></html>");
                        lblEspecificaciones.setFont(new Font("Arial", Font.PLAIN, 10));
                        lblEspecificaciones.setForeground(new Color (0x666666));
                        lblEspecificaciones.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
                        lblEspecificaciones.setBounds(453, 240, 200, 20);
                        
                        lblEspecificaciones.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                String mensaje = """
                                Especificaciones del archivo CSV:

                                • Debe tener extensión .csv
                                • Codificación recomendada: UTF-8
                                • Separador de campos: coma (,)
                                • No use espacio entre comas.              
                                • Columnas esperadas (en orden):
                                    full_name_mu, email_mu, phone_mu, birth_date_mu,
                                    ssn_md, voter_id_md, address_md, system_role_md
                                • No se permiten campos vacíos
                                • El formato de fecha debe ser: YYYY-MM-DD
                                • system_role_md debe ser uno de: 'guest', 'admin', 'user'
                                    'moderator'.
                                """;

                                JOptionPane.showMessageDialog(null, mensaje, "Especificaciones del CSV", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });


           JLabel avisoEstado = new JLabel("Estado");
                    avisoEstado.setFont(new Font("Arial", Font.PLAIN, 11));
                    avisoEstado.setForeground(new Color(0x666666));
                    avisoEstado.setBounds(450, 340, 200, 20);              
                        
              n.add(avisoEstado);          
              n.add(lblEspecificaciones);
              n.add(avisoFormato);

               
         //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
         //Boton subir
         JButton btnSubir = new JButton("Subir");
            btnSubir.setBackground(new Color(0x0e3a74)); 
            btnSubir.setForeground(Color.WHITE); 
            btnSubir.setFont(new Font("Arial", Font.BOLD, 14));
//            btnSubir.setHorizontalAlignment(SwingConstants.RIGHT);
            btnSubir.setBounds(680, 410, 80, 25); 
            btnSubir.setFocusPainted(false);      
            btnSubir.setContentAreaFilled(false);  
            btnSubir.setOpaque(true);   
            
         n.add(btnSubir);
         
         btnSubir.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
            if (archivoSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Primero selecciona un archivo CSV.");
                return;
            }

            String nuevoNombre = txtRenombrar.getText().trim();

            if (!nuevoNombre.isEmpty()) {
                if (!nuevoNombre.toLowerCase().endsWith(".csv")) {
                    nuevoNombre += ".csv";
                }

                String nombreActual = archivoSeleccionado.getName();

                // a ver si funciona el pop p
                if (!nombreActual.equals(nuevoNombre)) {
                    File nuevoArchivo = new File(archivoSeleccionado.getParentFile(), nuevoNombre);

                    boolean renombrado = archivoSeleccionado.renameTo(nuevoArchivo);
                    if (renombrado) {
                        archivoSeleccionado = nuevoArchivo;
                        JOptionPane.showMessageDialog(null, "Archivo renombrado con éxito a: " + nuevoNombre);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo renombrar el archivo. Puede estar en uso.");
                        return;
                    }
                }
            }
                    subirArchivoCSV(archivoSeleccionado);
                    }
                });


         
         
         
         //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         estado = new JLabel("Seleccione su archivo.   ", SwingConstants.CENTER);
            estado.setFont(new Font("Arial", Font.PLAIN, 11));
            estado.setForeground(Color.BLACK);
            estado.setBackground(new Color(0xf0f5ff));
            estado.setOpaque(true);
//            estado.setHorizontalAlignment(SwingConstants.RIGHT);
            estado.setBounds(450, 360, 210, 30);
     
                indicador = new JPanel();
                    indicador.setVisible(false);
                    indicador.setBounds(445, 360, 5, 30); 
                    indicador.setOpaque(true);
                n.add(indicador);
                n.add(estado); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ImportarTEST3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImportarTEST3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImportarTEST3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImportarTEST3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImportarTEST3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
