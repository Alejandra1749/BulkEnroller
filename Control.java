/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aduanal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;


public class Control {
    private AduanaF vista;
    private Modelo modelo;
    private int intentos = 0;

    public Control(AduanaF vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        // Boton ingresar _pantalla de inicio
        vista.ingresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarLogin();
            }
        });

        // Boton registarse_pantalla de inicio
        vista.Registrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRegistro();
                
            }
        });
         vista.registrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                
            }
        });
         vista.casa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarInicio();

            }
        });

        // salir
        vista.Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // boton ingreser desde formulario login
        vista.ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // boton de registro desde el formulario de registro
        vista.registrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registro();
            }
        });

        // regresar desde login
        vista.regresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarInicio();
                
            }
        });

        // regresar desde registro
        vista.regresar2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarInicio();
            }
        });
        
        // términos y condiciones
        vista.TI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 String mensaje = "Términos y Condiciones:\n\n"
                           + "1. Al usar esta aplicación, aceptas cumplir con los términos aquí descritos.\n"
                           + "2. No compartimos tu información personal sin tu consentimiento.\n"
                           + "3. El uso indebido del sistema puede resultar en el bloqueo de tu cuenta.\n\n"
                           + "Por favor, respeta las normas y condiciones de uso.\n\n"
                           + "© 2025 BulkEnroller - Todos los derechos reservados.";

                JOptionPane.showMessageDialog(vista.TI, mensaje, "Términos y Condiciones", JOptionPane.INFORMATION_MESSAGE); 
                }
            });

            // Cambiar cursor a mano al pasar por encima
             vista.TI.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void mostrarInicio() {
        // Muestra pantalla de inicio
        vista.registro.setVisible(false);
        vista.fondoi.setVisible(true);
        vista.ingreso.setVisible(false);
        vista.menu.setVisible(false);
        vista.fondoi.setVisible(true);
        vista.PanelT.setVisible(true);
        vista.ingresa.setVisible(true);
        vista.Registrarse.setVisible(true);
        vista.Salir.setVisible(true);
        vista.icon.setVisible(true);
        vista.bienvenido.setVisible(true);
    }

    private void mostrarLogin() {
        vista.ingreso.setVisible(true);
        vista.ingresa.setVisible(false);
        vista.fondoi.setVisible(false);
        vista.Registrarse.setVisible(false);
        vista.Salir.setVisible(false);
        vista.icon.setVisible(false);
        vista.bienvenido.setVisible(false);
        vista.PanelT.setVisible(false);
        
    }

    private void mostrarRegistro() {
        vista.registro.setVisible(true);
        vista.ingresa.setVisible(false);
          vista.fondoi.setVisible(false);
        vista.Registrarse.setVisible(false);
        vista.Salir.setVisible(false);
        vista.icon.setVisible(false);
        vista.bienvenido.setVisible(false);
    }

    private void login() {
        String usuario = vista.useri.getText();
        String contraseña = new String(vista.contraseña.getPassword());


        if (intentos >= 3) {
            JOptionPane.showMessageDialog(null, "Demasiados intentos. Acceso bloqueado.");
            vista.ingresar.setEnabled(false);
            mostrarInicio();
            return;
        }

        if (modelo.autenticarUsuario(usuario, contraseña)) {
            modelo.actualizarUltimoLogin(usuario);
            vista.user.setText(usuario); 
            JOptionPane.showMessageDialog(null, "¡Ingreso exitoso!");
            vista.menu.setVisible(true);
            vista.ingreso.setVisible(false);
        } else {
            intentos++;
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
        }
    }
    
    

    private void registro() {
        String nombre = vista.nombre.getText();
        String email = vista.emailr.getText();
        String pass = new String(vista.contraseñar.getPassword());
        String contraseña1 = new String(vista.contraseñar.getPassword());
        String contraseña2 = new String(vista.contraseñac.getPassword());

        //verifica si son diferentes
        if (!contraseña1.equals(contraseña2)) {
            //muestra mensaje de error
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            vista.contraseñar.setText(""); // limpia campos 
            vista.contraseñac.setText("");

          
            return;

         }

        // valida formato de la contraseña
        if (contraseña1.length() < 8) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres.");
            vista.contraseñar.setText("");
            return;
        }

        if (!contraseña1.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos una letra mayúscula.");
            vista.contraseñar.setText("");
            return;
        }

        if (!contraseña1.matches(".*[a-z].*")) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos una letra minúscula.");
            vista.contraseñar.setText("");
            return;
        }

        if (!contraseña1.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos un número.");
            vista.contraseñar.setText("");
            return;
        }

        if (!contraseña1.matches(".*[@#$%&].*")) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos un símbolo: @, #, $, %, &");
            vista.contraseñar.setText("");
            return;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de correo no puede estar vacío.");
            vista.emailr.setText("");
            return;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa un correo electrónico válido.\nEjemplo: usuario@gmail.com");
            vista.emailr.setText("");
            return;
        }

        

        if (modelo.registrarUsuario(nombre, email, pass)) {
            JOptionPane.showMessageDialog(null, "¡Registrado exitosamente!");
             mostrarLogin();
             vista.registro.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "El usuario ya existe.");
        }
    }
    
}

