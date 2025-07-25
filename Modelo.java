/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aduanal;

//import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {
    private Connection conexion;

    public Modelo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/aduana",
                "root",
                "4464"
            );
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public boolean autenticarUsuario(String usuario, String contraseña) {
        try {
            Statement comando = conexion.createStatement();
            String sql = "SELECT * FROM users_login WHERE username_ul='" + usuario + "' AND password_ul = SHA2('" + contraseña + "', 256)";

            ResultSet rs = comando.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean existeCorreo(String email) {
    try {
        Statement comando = conexion.createStatement();
        String sql = "SELECT * FROM users_login WHERE email_ul = '" + email + "'";
        ResultSet rs = comando.executeQuery(sql);
        return rs.next(); 
    } catch (SQLException e) {
        return true; 
    }
}


    public boolean registrarUsuario(String nombre, String email, String password) {
        try {
            Statement comando = conexion.createStatement();
            ResultSet rs = comando.executeQuery("SELECT * FROM users_login WHERE username_ul='" + nombre + "'");
            if (!rs.next()) {
                String sql = "INSERT INTO users_login (username_ul, email_ul, password_ul) VALUES ('" + nombre + "', '" + email + "', SHA2('" + password + "', 256))";

                comando.executeUpdate(sql);
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean actualizarUltimoLogin(String username) {
    try {
        Statement comando = conexion.createStatement();
        String sql = "UPDATE users_login SET last_login_ul = NOW() WHERE username_ul = '" + username + "'";
        int filasActualizadas = comando.executeUpdate(sql);
        return filasActualizadas > 0;
    } catch (SQLException e) {
        return false;
    }
}
}
