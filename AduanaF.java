/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aduanal;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 *
 * @author pamel
 */
public class AduanaF extends javax.swing.JFrame {
    
   //PANTALLA DE INICIO
        public JLabel icon;
        public JLabel bienvenido;
        public JButton ingresa;
        public JButton Registrarse;
        public JButton Salir;
        public JLabel regresar;
        public JLabel regresar2;
        public JLabel fondoi;
        public JPanel PanelT;
        public JLabel IN;
        public JLabel Logo;
        public JLabel nombre1;
        public JLabel TI;
        
        //MENÚ
        public JPanel menu;
        public JLabel user;
        public JPanel subir;
        public JPanel crear;
        public JPanel archivos;
        public JPanel requisitos;
        public JPanel barra;
        public JLabel casa;
        
        //iNGRESAR
       
        public JTextField useri;
        public JPanel ingreso ;
        public JPasswordField contraseña;
        public JButton ingresar;
        public JLabel fondo;
        public JLabel Bienvenido;
        public JLabel Ingresar;
        public JLabel gmail;
        public JLabel contra;
        public JLabel olvidar;
        public JCheckBox recordar;
        
        //registrarse
        public JTextField emailr;
        public JTextField nombre;
        public JPanel registro;
        public JPasswordField contraseñar;
        public JPasswordField contraseñac;
        public JButton registrarse;
        public JLabel fondor;
        public JLabel Bienvenidor;
        public JLabel registrarser;
        public JLabel gmailr;
        public JLabel contrar;
        public JLabel contraconf;
        public JLabel nombrer;
    /**
     * Creates new form AduanaF
     */
    public AduanaF() {
        // tooltips
        UIManager.put("ToolTip.background", new Color(25, 25, 130)); 
        UIManager.put("ToolTip.foreground", Color.WHITE); 
        UIManager.put("ToolTip.font", new Font("Segoe UI", Font.PLAIN, 12));
        initComponents();
           
        this.getContentPane().setBackground(new java.awt.Color(230,240,255));
        fondoi=new JLabel();
        fondoi.setIcon(new ImageIcon("imagenes/i111.png"));
        add(fondoi);
        //dispose();//cierra la ventana
     
        
        
        //SALIR
        
        //
        this.setLayout(null);
        this.setSize(830,520);
        fondoi.setSize(830,520);
        setResizable(false);
        setTitle("BulkEnroller");
        setIconImage(new ImageIcon("imagenes/u.png").getImage());
        // Supón que tu panel se llama "panelTransparente"Line
       
        PanelT = new RoundedPanel(30); 

        PanelT.setLayout(null); // ubicar botones manualmente
        PanelT.setBounds(270,90,270,330);
        PanelT.setOpaque(false); // No opaco
        PanelT.setBackground(new Color(255, 255, 255, 230)); // blanco con transparencia
        fondoi.add(PanelT);
        

        //inicio
        ingresa=new JButton("Ingresar");
        ingresa.setForeground(Color.white);
        ingresa.setBackground(new Color(0,0,255));
        ingresa.setFont(new Font("Segoe UI",Font.BOLD,15));
        ingresa.setBounds(35,120, 200, 45);
        ingresa.setVisible(true);
        ingresa.setFocusPainted(false);
        
        ingresa.setOpaque(true);
        PanelT.add(ingresa);
        
        
        Registrarse=new JButton("Registrarse");
        Registrarse.setForeground(Color.BLUE);
        Registrarse.setBackground(new Color(255,255,255));
        Registrarse.setFont(new Font("Segoe UI",Font.BOLD,15));
        Registrarse.setBounds(35,190, 200, 45);
        Registrarse.setVisible(true);
        PanelT.add(Registrarse);
        
        Salir=new JButton("Salir");
        Salir.setForeground(Color.white);
        Salir.setBackground(new Color(0,0,255));
        Salir.setFont(new Font("Segoe UI",Font.BOLD,15));
        Salir.setBounds(600,400, 150, 45);
        Salir.setVisible(true);
        fondoi.add(Salir);
       
        
        icon=new JLabel();
        icon.setIcon(new ImageIcon("imagenes/ico.png"));
        icon.setSize(100,100);
        icon.setLocation(330,10);
        icon.setVisible(true);
        fondoi.add(icon);
        
        bienvenido=new JLabel("INGRESO");
        bienvenido.setBackground(Color.black);
        bienvenido.setFont(new Font("Segoe UI",Font.BOLD,25));
        bienvenido.setBounds(80, 30, 310, 25);
        bienvenido.setVisible(true);
        PanelT.add(bienvenido);
        
        IN=new JLabel("Bienvenido");
        IN.setBackground(Color.GRAY);
        IN.setFont(new Font("Segoe UI",Font.PLAIN,17));
        IN.setBounds(90, 50, 310, 35);
        IN.setVisible(true);
        PanelT.add(IN);
        
        TI=new JLabel("Terminos y Condiciones");
        TI.setForeground(Color.GRAY);
        TI.setFont(new Font("Segoe UI",Font.PLAIN,12));
        TI.setBounds(70, 300, 130, 15);
        TI.setVisible(true);
        TI.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        PanelT.add(TI);
        
//        Logo=new JLabel();
//        fondoi.add(Logo);
//        Logo.setIcon(new ImageIcon("C:\\Users\\pamel\\Downloads\\u.png"));
//        Logo.setSize(30,40);
//        Logo.setLocation(5,5);
//        Logo.setVisible(true);//
        
        
        
        nombre1=new JLabel("BulkEnroller");
        nombre1.setFont(new Font("Segoe UI",Font.PLAIN,15));
        nombre1.setForeground(Color.darkGray);
        nombre1.setBounds(380,40,100,35);
        nombre1.setVisible(true);
        fondoi.add(nombre1);
        
     
        //panel ingreso
        ingreso=new JPanel();
        ingreso.setLayout(null);
        ingreso.setBackground(Color.white);
        ingreso.setSize(830,520);
        ingreso.setVisible(false);
        this.add(ingreso);
        fondo=new JLabel();
        ingreso.add(fondo);
        fondo.setIcon(new ImageIcon("imagenes/blue.png"));
        fondo.setSize(700,500);
        fondo.setLocation(350,15);
        fondo.setVisible(true);
        
        regresar=new JLabel();
        regresar.setIcon(new ImageIcon("imagenes/2.png"));
        regresar.setSize(50,50);
        regresar.setLocation(10,0);
        regresar.setVisible(true);
        ingreso.add(regresar);
        
        
        
    

        
        //icono 
        
        
        //etiquetas
        Bienvenido=new JLabel("Bienvenido!");
        Bienvenido.setForeground(Color.BLACK);
        Bienvenido.setFont(new Font("Segoe UI",Font.BOLD,25));
        Bienvenido.setBounds(30, 40, 300, 35);
        Bienvenido.setVisible(true);
        ingreso.add(Bienvenido);
        
        Ingresar=new JLabel("Ingresa los datos");
        Ingresar.setFont(new Font("Segoe UI",Font.PLAIN,12));
        Ingresar.setForeground(Color.gray);
        Ingresar.setBounds(30,75,300,25);
        Ingresar.setVisible(true);
        ingreso.add(Ingresar);
        
        gmail=new JLabel("Usuario");
        gmail.setForeground(Color.BLACK);
        gmail.setFont(new Font("Segoe UI",Font.BOLD,14));
        gmail.setBounds(30, 130, 300, 25);
        gmail.setVisible(true);
        ingreso.add(gmail);
        
        useri=new JTextField();
        useri.setForeground(Color.BLACK);
        useri.setFont(new Font("Segoe UI",Font.BOLD,14));
        useri.setBounds(30, 160, 300, 40);
        useri.setVisible(true);
        ingreso.add(useri);
        
        
        contra=new JLabel("Contraseña");
        contra.setForeground(Color.BLACK);
        contra.setFont(new Font("Segoe UI",Font.BOLD,14));
        contra.setBounds(30, 260, 300, 25);
        contra.setVisible(true);
        ingreso.add(contra);
        
        contraseña=new JPasswordField();
        contraseña.setForeground(Color.BLACK);
        contraseña.setFont(new Font("Segoe UI",Font.BOLD,14));
        contraseña.setBounds(30, 290, 300, 40);
        contraseña.setVisible(true);
        ingreso.add(contraseña);
        
//        olvidar=new JLabel("olvide mi contraseña");
//        olvidar.setForeground(Color.blue);
//        olvidar.setFont(new Font("Segoe UI",Font.BOLD,12));
//        olvidar.setBounds(200, 330, 120, 15);
//        olvidar.setVisible(true);
//        olvidar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
//        ingreso.add(olvidar);
//        
//        recordar=new JCheckBox("Recordar");
//        recordar.setForeground(Color.gray);
//        recordar.setFont(new Font("Segoe UI",Font.BOLD,12));
//        recordar.setBounds(30, 330, 150, 20);
//        recordar.setVisible(true);
//        ingreso.add(recordar);
        
        ingresar=new JButton("INGRESAR");
        ingresar.setForeground(Color.white);
        ingresar.setBackground(new Color(0,0,255));
        ingresar.setFont(new Font("Segoe UI",Font.BOLD,15));
        ingresar.setBounds(100,390, 160, 45);
        ingresar.setVisible(true);
        ingreso.add(ingresar);
        
        
       
        
        //panel registro
        
        registro=new JPanel();
        registro.setLayout(null);
        registro.setBackground(Color.white);
        registro.setSize(830,520);
        registro.setVisible(false);
        this.add(registro);
        fondor=new JLabel();
        registro.add(fondor);
        fondor.setIcon(new ImageIcon("imagenes/a.jpg"));
        fondor.setSize(700,500);
        fondor.setLocation(350,15);
        fondor.setVisible(true);
        
        Bienvenidor=new JLabel("Bienvenido!");
        Bienvenidor.setForeground(Color.BLACK);
        Bienvenidor.setFont(new Font("Segoe UI",Font.BOLD,25));
        Bienvenidor.setBounds(30, 20, 300, 35);
        Bienvenidor.setVisible(true);
        registro.add(Bienvenidor);
        
        registrarser=new JLabel("Registrarse");
        registrarser.setFont(new Font("Segoe UI",Font.PLAIN,14));
        registrarser.setForeground(Color.gray);
        registrarser.setBounds(30,50,300,25);
        registrarser.setVisible(true);
        registro.add(registrarser);
        
        nombrer=new JLabel("Nombre");
        nombrer.setForeground(Color.BLACK);
        nombrer.setFont(new Font("Segoe UI",Font.BOLD,14));
        nombrer.setBounds(30, 80, 300, 25);
        nombrer.setVisible(true);
        registro.add(nombrer);
        
        gmailr=new JLabel("Email");
        gmailr.setForeground(Color.BLACK);
        gmailr.setFont(new Font("Segoe UI",Font.BOLD,14));
        gmailr.setBounds(30, 165, 300, 25);
        gmailr.setVisible(true);
        registro.add(gmailr);
        
        contrar=new JLabel("Contraseña");
        contrar.setForeground(Color.BLACK);
        contrar.setFont(new Font("Segoe UI",Font.BOLD,14));
        contrar.setBounds(30, 255, 300, 25);
        contrar.setVisible(true);
        registro.add(contrar);
        
        contraconf=new JLabel("Confirmar contraseña");
        contraconf.setForeground(Color.BLACK);
        contraconf.setFont(new Font("Segoe UI",Font.BOLD,14));
        contraconf.setBounds(30, 345, 300, 25);
        contraconf.setVisible(true);
        registro.add(contraconf);
        
        nombre=new JTextField();
        nombre.setForeground(Color.BLACK);
        nombre.setFont(new Font("Segoe UI",Font.BOLD,14));
        nombre.setBounds(30, 105, 300, 40);
        nombre.setVisible(true);
        registro.add(nombre);
        
        emailr=new JTextField();
        emailr.setForeground(Color.BLACK);
        emailr.setFont(new Font("Segoe UI",Font.BOLD,14));
        emailr.setBounds(30, 195, 300, 40);
        emailr.setVisible(true);
        registro.add(emailr);
        emailr.setToolTipText("Ingrese un correo electrónico válido (ej. nombre@dominio.com)");
        
        
        contraseñar=new JPasswordField();
        contraseñar.setForeground(Color.BLACK);
        contraseñar.setFont(new Font("Segoe UI",Font.BOLD,14));
        contraseñar.setBounds(30, 285, 300, 40);
        contraseñar.setVisible(true);
        registro.add(contraseñar);
        // Tooltip para el primer campo de contraseña
       
        
        contraseñac=new JPasswordField();
        contraseñac.setForeground(Color.BLACK);
        contraseñac.setFont(new Font("Segoe UI",Font.BOLD,14));
        contraseñac.setBounds(30, 375, 300, 40);
        contraseñac.setVisible(true);
        registro.add(contraseñac);
        // Tooltip para el campo de confirmación de contraseña
        contraseñar.setToolTipText("Mínimo 8 caracteres con mayúscula, minúscula, número y símbolo (@, #, $, %, &)");

        contraseñac.setToolTipText("Confirme la contraseña");
        
        registrarse=new JButton("REGISTRARSE");
        registrarse.setForeground(Color.white);
        registrarse.setBackground(new Color(0,0,255));
        registrarse.setFont(new Font("Segoe UI",Font.BOLD,15));
        registrarse.setBounds(95,430, 160, 45);
        registrarse.setVisible(true);
        registro.add(registrarse);
         
        regresar2=new JLabel();
        regresar2.setIcon(new ImageIcon("imagenes/2.png"));
        regresar2.setSize(50,30);
        regresar2.setLocation(10,0);
        regresar2.setVisible(true);
        registro.add(regresar2);
       
        
        //menú
        menu=new JPanel();
        menu.setBackground(Color.white);
        menu.setVisible(false);
        menu.setSize(830,520);
        menu.setLayout(null);
        add(menu);
        
        barra=new JPanel();
        barra.setBackground(new Color(0,0,128));
        barra.setOpaque(true);
        barra.setLayout(null);
        barra.setBounds(0,0,850,50);
        barra.setVisible(true);
        menu.add(barra);
        
        user=new JLabel("nombre");
        user.setForeground(Color.WHITE);
        user.setBounds(720,5,100,30);
        user.setFont(new Font("Arial", Font.PLAIN, 14));
        barra.add(user);
        
        JLabel useri=new JLabel();
        useri.setBounds(680,5,50,35);
        useri.setIcon(new ImageIcon("imagenes/usuario1.png"));
        useri.setVisible(true);
        barra.add(useri);
        
        casa=new JLabel();
        casa.setBounds(10,5,50,35);
        casa.setIcon(new ImageIcon("imagenes/casa1.png"));
        casa.setVisible(true);
        barra.add(casa);
        
        
        
        
        subir=new JPanel();
        subir.setLayout(null);
        subir.setBounds(50,180,350,190);
        subir.setVisible(true);
        subir.setBackground(new Color(0,102,204));
        menu.add(subir);
        
        JLabel imagen=new JLabel();
        imagen.setIcon(new ImageIcon("imagenes/subir.png"));
        imagen.setSize(80,80);
        imagen.setLocation(140,10);
        
        subir.add(imagen);
        
        JLabel titulo = new JLabel("Importar CSV");
        titulo.setForeground(Color.white);
        titulo.setSize(200,25);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setVisible(true);
        titulo.setLocation(110,100);
        //titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subir.add(titulo);
        
        JLabel subtitulo = new JLabel("Carga tus archivos desde tu computadora");
        subtitulo.setForeground(Color.WHITE);
        //subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setLocation(48,120);
        subtitulo.setSize(300,25);
        subtitulo.setVisible(true);
        subir.add(subtitulo);

        crear=new JPanel();
        crear.setLayout(null);
        crear.setBounds(430,180,350,190);
        crear.setVisible(true);
        crear.setBackground(new Color(0,102,204));
        menu.add(crear);
        //subir.setLayout(new BoxLayout(subir, BoxLayout.Y_AXIS));
        
        JLabel imagen2=new JLabel();
        imagen2.setIcon(new ImageIcon("imagenes/csv1.png"));
        imagen2.setSize(80,80);
        imagen2.setLocation(140,10);
       
        crear.add(imagen2);
        
        JLabel titulo2 = new JLabel("Mis archivos");
        titulo2.setForeground(Color.white);
        titulo2.setSize(200,25);
        titulo2.setFont(new Font("Arial", Font.BOLD, 18));
        titulo2.setVisible(true);
        titulo2.setLocation(120,100);
        //titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        crear.add(titulo2);
        
        JLabel subtitulo2 = new JLabel("Verificar Archivos Cargados");
        subtitulo2.setForeground(Color.WHITE);
        //subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo2.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo2.setLocation(80,120);
        subtitulo2.setSize(300,25);
        subtitulo2.setVisible(true);
        crear.add(subtitulo2);
        
        
//        archivos=new JPanel();
//        archivos.setLayout(null);
//        archivos.setBounds(50,290,350,170);
//        archivos.setVisible(true);
//        archivos.setBackground(new Color(0,102,204));
//        menu.add(archivos);
//        //subir.setLayout(new BoxLayout(subir, BoxLayout.Y_AXIS));
//        
//        JLabel imagen3=new JLabel();
//        imagen3.setIcon(new ImageIcon("C:\\Users\\pamel\\Downloads\\doc.png"));
//        imagen3.setSize(80,80);
//        imagen3.setLocation(140,10);
//        //imagen.setHorizontalAlignment(JLabel.CENTER);
//        //imagen.setAlignmentX(Component.CENTER_ALIGNMENT);
//        archivos.add(imagen3);
//        
        JLabel titulo3 = new JLabel("Selecciona una opción");
        titulo3.setForeground(Color.BLACK);
        titulo3.setSize(250,25);
        titulo3.setFont(new Font("Arial", Font.BOLD, 20));
        titulo3.setVisible(true);
        titulo3.setLocation(300,100);
        //titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.add(titulo3);
//        
//        JLabel subtitulo3 = new JLabel("Carga tus archivos desde tu computadora");
//        subtitulo3.setForeground(Color.WHITE);
//        //subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
//        subtitulo3.setFont(new Font("Arial", Font.PLAIN, 14));
//        subtitulo3.setLocation(48,120);
//        subtitulo3.setSize(300,25);
//        subtitulo3.setVisible(true);
//        archivos.add(subtitulo3);
//        
//        
//        requisitos=new JPanel();
//        requisitos.setLayout(null);
//        requisitos.setBounds(430,290,350,170);
//        requisitos.setVisible(true);
//        requisitos.setBackground(new Color(0,102,204));
//        menu.add(requisitos);
//        //subir.setLayout(new BoxLayout(subir, BoxLayout.Y_AXIS));
//        
//        JLabel imagen4=new JLabel();
//        imagen4.setIcon(new ImageIcon("C:\\Users\\pamel\\Downloads\\al.png"));
//        imagen4.setSize(80,80);
//        imagen4.setLocation(140,10);
//        //imagen.setHorizontalAlignment(JLabel.CENTER);
//        //imagen.setAlignmentX(Component.CENTER_ALIGNMENT);
//        requisitos.add(imagen4);
//        
//        JLabel titulo4 = new JLabel("Subir Documento CSV");
//        titulo4.setForeground(Color.white);
//        titulo4.setSize(200,25);
//        titulo4.setFont(new Font("Arial", Font.BOLD, 18));
//        titulo4.setVisible(true);
//        titulo4.setLocation(85,100);
//        //titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
//        requisitos.add(titulo4);
//        
//        JLabel subtitulo4 = new JLabel("Carga tus archivos desde tu computadora");
//        subtitulo4.setForeground(Color.WHITE);
//        //subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
//        subtitulo4.setFont(new Font("Arial", Font.PLAIN, 14));
//        subtitulo4.setLocation(48,120);
//        subtitulo4.setSize(300,25);
//        subtitulo4.setVisible(true);
//        requisitos.add(subtitulo4);
//        
        
        
        

        
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
            java.util.logging.Logger.getLogger(AduanaF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AduanaF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AduanaF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AduanaF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AduanaF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
