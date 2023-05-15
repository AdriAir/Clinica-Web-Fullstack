/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.TrabajoTallerServidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author andres
 */
public class BDAdaptador {

    private String sLastError;
    
    public BDAdaptador() {
        sLastError = "";
    }
 
    private Connection initDatabase()            
    {
            Connection con = null;
            // Initialize all the information regarding
            // Database Connection
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql://bbdd:3306/";
            // Database name to access
            String dbName = "taller_db";
            String dbUsername = "mecanico";
            String dbPassword = "2secret2know";

            try {
                Class.forName(dbDriver);
                con = DriverManager.getConnection(dbURL + dbName, dbUsername,dbPassword);
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error conectando a la BBDD: " + e.getMessage()+ "</p>";
                e.printStackTrace();
            }
            return con;
    }
    
    
    
    public String getProveedores() {
        String resultado = "";
        String id, nombre, fecha, direccion, telefono, email, contacto;
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        int iRows = 0;
        try {
            con = this.initDatabase();

            //st = con.createStatement();
            ps = con.prepareStatement("select * from proveedores");


            
            //ResultSet rs = st.executeQuery("select * from proveedores");
            ResultSet rs = ps.executeQuery();
            
             // iteración sobre el resultset
            while (rs.next())  //Mientras tengamos rows de salida...
            {
              iRows++;
              id = (rs.getString("id"));
              nombre = rs.getString("nombre");
              fecha = rs.getString("fechaAlta");         
              direccion = rs.getString("direccion");
              telefono = rs.getString("telefono");
              email = rs.getString("email");
              contacto = rs.getString("contacto");
              
              // save the results
              resultado += "<p>" + id + ";" +
                                       nombre + ";" +
                      fecha + ";" +
                      direccion + ";" +
                      telefono + ";" +
                      email + ";" +
                      contacto + "</p>\n";
            }
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage()+ "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (st!= null) st.close();
                if (con!=null) con.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage()+ "</p>";
                e.printStackTrace();
                     
            }
        }
        resultado += "\n<p>Rows recogidas: " + iRows + "</p>\n";
        if (sLastError.isEmpty()) return resultado;        
        else return resultado + sLastError;
    }
    
    
    //Método de inserción en la tabla de proveedores de un nuevo valor.
    public String insertProveedor(String sCSV) {
        String resultado = "<p>Error al insertar</p>";
        String id, nombre, fecha, direccion, telefono, email, contacto;
        Connection con = null;
       
        Proveedor miPr = new Proveedor(sCSV);
        
        PreparedStatement ps = null;
      
        try {
            con = this.initDatabase();

            //st = con.createStatement();
            ps = con.prepareStatement("insert into proveedores (id,nombre,fechaAlta,direccion,telefono,email,contacto) values (?,?,?,?,?,?,?)");
            ps.setString(1, String.valueOf((int)(Math.random()*100000)));
            ps.setString(2,miPr.nombreProveedor);
            ps.setString(3,miPr.fechaAlta);
            ps.setString(4,miPr.direccionPostal);
            ps.setString(5,miPr.numeroTelefono);
            ps.setString(6,miPr.email);
            ps.setString(7,miPr.personaDeContacto);

            
            
            if (ps.executeUpdate()!=0)
                 resultado = "<p>Proveedor insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal con la sentencia Insert Proveedores</p>";            
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí
            
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage()+ "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (ps!= null) ps.close();
                if (con!=null) con.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage()+ "</p>";
                e.printStackTrace();
                     
            }
        }
        if (sLastError.isEmpty()) return resultado;        
        else return resultado + sLastError;
        
    }
    
}

