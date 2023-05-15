package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Clinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDAdaptor {
    private String sLastError;

    public BDAdaptor() {
        sLastError = "";
    }

    private Connection initDatabase() {
        Connection connection = null;
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://bbdd:3306/";
        // Database name to access
        String dbName = "ClinicaDentista";
        String dbUsername = "principal";
        String dbPassword = "1234";

        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error conectando a la BBDD: " + e.getMessage() + "</p>";
            e.printStackTrace();
        }
        return connection;
    }

    public String getClinics() {
        String resultado = "";
        String cif, name, address, phoneNumber, email;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        int iRows = 0;
        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Clinic");


            //ResultSet resultSet = statement.executeQuery("select * from proveedores");
            ResultSet resultSet = preparedStatement.executeQuery();

            // iteración sobre el resultset
            while (resultSet.next())  //Mientras tengamos rows de salida...
            {
                iRows++;
                cif = (resultSet.getString("cif"));
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                phoneNumber = resultSet.getString("phoneNumber");
                email = resultSet.getString("email");

                // save the results
                resultado += "<p>" + cif + ";" +
                        name + ";" +
                        address + ";" +
                        phoneNumber + ";" +
                        email + "</p>\n";
            }
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage() + "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage() + "</p>";
                e.printStackTrace();

            }
        }
        resultado += "\n<p>Rows recogidas: " + iRows + "</p>\n";
        if (sLastError.isEmpty()) return resultado;
        else return resultado + sLastError;
    }
    public String insertClinic(String json) {
        String resultado = "<p>Error al insertar</p>";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("insert into Clinic (cif,name,address,phoneNumber,email) values (?,?,?,?,?)");
            preparedStatement.setString(1, clinic.getCif());
            preparedStatement.setString(2, clinic.getName());
            preparedStatement.setString(3, clinic.getAddress());
            preparedStatement.setString(4, clinic.getPhoneNumber());
            preparedStatement.setString(5, clinic.getEmail());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Proveedor insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal connection la sentencia Insert Proveedores</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage() + "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage() + "</p>";
                e.printStackTrace();

            }
        }
        if (sLastError.isEmpty()) return resultado;
        else return resultado + sLastError;

    }
    public String getPatients() {
        String resultado = "";
        String dni, name, phoneNumber, email, bornDate, clinic;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        int iRows = 0;
        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Patient");


            //ResultSet resultSet = statement.executeQuery("select * from proveedores");
            ResultSet resultSet = preparedStatement.executeQuery();

            // iteración sobre el resultset
            while (resultSet.next())  //Mientras tengamos rows de salida...
            {
                iRows++;
                dni = (resultSet.getString("dni"));
                name = resultSet.getString("name");
                phoneNumber = resultSet.getString("phoneNumber");
                email = resultSet.getString("email");
                bornDate = resultSet.getString("bornDate");
                clinic = resultSet.getString("clinic");

                // save the results
                resultado += "<p>" + dni + ";" +
                        name + ";" +
                        phoneNumber + ";" +
                        email + ";" +
                        bornDate + ";" +
                        clinic + "</p>\n";
            }
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage() + "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage() + "</p>";
                e.printStackTrace();

            }
        }
        resultado += "\n<p>Rows recogidas: " + iRows + "</p>\n";
        if (sLastError.isEmpty()) return resultado;
        else return resultado + sLastError;
    }
    public String getTreatments() {
        String resultado = "";
        String code, description, date, price, isPaid, patient;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        int iRows = 0;
        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Treatment");


            //ResultSet resultSet = statement.executeQuery("select * from proveedores");
            ResultSet resultSet = preparedStatement.executeQuery();

            // iteración sobre el resultset
            while (resultSet.next())  //Mientras tengamos rows de salida...
            {
                iRows++;
                code = (resultSet.getString("code"));
                description = resultSet.getString("description");
                date = resultSet.getString("date");
                price = resultSet.getString("price");
                isPaid = resultSet.getString("isPaid");
                patient = resultSet.getString("patient");

                // save the results
                resultado += "<p>" + code + ";" +
                        description + ";" +
                        date + ";" +
                        price + ";" +
                        isPaid + ";" +
                        patient + "</p>\n";
            }
        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Select: " + e.getMessage() + "</p>";
            e.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                sLastError = sLastError + "<p>Error cerrando la BBDD: " + e.getMessage() + "</p>";
                e.printStackTrace();

            }
        }
        resultado += "\n<p>Rows recogidas: " + iRows + "</p>\n";
        if (sLastError.isEmpty()) return resultado;
        else return resultado + sLastError;
    }
}