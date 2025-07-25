/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aduanal;

/**
 *
 * @author pamel
 */
public class AduanaL {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        AduanaF vista = new AduanaF();
        Modelo modelo = new Modelo();
        Control controlador=new Control(vista,modelo);
        vista.setVisible(true);
    }
    
}
