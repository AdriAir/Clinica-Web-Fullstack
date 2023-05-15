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
            preparedStatement = connection.prepareStatement("select * from Clinic;");


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
            preparedStatement = connection.prepareStatement("insert into Clinic (cif,name,address,phoneNumber,email) values (?,?,?,?,?);");
            preparedStatement.setString(1, clinic.getCif());
            preparedStatement.setString(2, clinic.getName());
            preparedStatement.setString(3, clinic.getAddress());
            preparedStatement.setString(4, clinic.getPhoneNumber());
            preparedStatement.setString(5, clinic.getEmail());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Proveedor insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal connection la sentencia Insert Clinic</p>";
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
    public String selectClinic(String json) {
        String resultado = "<p>Error al insertar</p>";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Clinic where cif=? or name like %?% or address like %?% or phoneNumber like %?% or email like %?%;");
            preparedStatement.setString(1, clinic.getCif());
            preparedStatement.setString(2, clinic.getName());
            preparedStatement.setString(3, clinic.getAddress());
            preparedStatement.setString(4, clinic.getPhoneNumber());
            preparedStatement.setString(5, clinic.getEmail());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Proveedor insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal connection la sentencia Select Clinic</p>";
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
    public String deleteClinic(String json) {
        String resultado = "<p>Error al insertar</p>";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("delete from Clinic where cif=?;");
            preparedStatement.setString(1, clinic.getCif());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Proveedor insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal connection la sentencia Delete Clinic</p>";
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
            preparedStatement = connection.prepareStatement("select * from Patient;");


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
    public String insertPatient(String json) {
        String resultado = "<p>Error al insertar</p>";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("insert into Patient (dni,name,phoneNumber,email,bornDate,clinic) values (?,?,?,?,?,?);");
            preparedStatement.setString(1, clinic.getPatients()[0].getDni());
            preparedStatement.setString(2, clinic.getPatients()[0].getName());
            preparedStatement.setString(3, clinic.getPatients()[0].getPhoneNumber());
            preparedStatement.setString(4, clinic.getPatients()[0].getEmail());
            preparedStatement.setString(5, clinic.getPatients()[0].getBornDate());
            preparedStatement.setString(6, clinic.getCif());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Paciente insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal connection la sentencia Insert Patient</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Insert: " + e.getMessage() + "</p>";
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
    public String selectPatient(String json) {
        String resultado = "";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Patient where dni=? or name like %?% or phoneNumber like %?% or email like %?% or bornDate like %?% or clinic like %?%;");
            preparedStatement.setString(1, clinic.getPatients()[0].getDni());
            preparedStatement.setString(2, clinic.getPatients()[0].getName());
            preparedStatement.setString(3, clinic.getPatients()[0].getPhoneNumber());
            preparedStatement.setString(4, clinic.getPatients()[0].getEmail());
            preparedStatement.setString(5, clinic.getPatients()[0].getBornDate());
            preparedStatement.setString(6, clinic.getCif());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Proveedor insertado correctamente</p>";
                else resultado = "<p>Algo ha salido mal connection la sentencia Select Patient</p>";
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
    public String deletePatient(String json) {
        String resultado = "<p>Error al borrar</p>";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("delete from Patient where dni=?;");
            preparedStatement.setString(1, clinic.getPatients()[0].getDni());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Proveedor insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal connection la sentencia Delete Clinic</p>";
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
            preparedStatement = connection.prepareStatement("select * from Treatment;");


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
    public String insertTreatment(String json) {
        String resultado = "<p>Error al insertar</p>";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("insert into Treatment (code,description,date,price,isPaid,patient) values (?,?,?,?,?,?);");
            preparedStatement.setString(1, clinic.getPatients()[0].getTreatments()[0].getCode());
            preparedStatement.setString(2, clinic.getPatients()[0].getTreatments()[0].getDescription());
            preparedStatement.setString(3, clinic.getPatients()[0].getTreatments()[0].getDate());
            preparedStatement.setString(4, String.valueOf(clinic.getPatients()[0].getTreatments()[0].getPrice()));
            preparedStatement.setString(5, String.valueOf(clinic.getPatients()[0].getTreatments()[0].isPaid()));
            preparedStatement.setString(6, clinic.getPatients()[0].getDni());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Tratamiento insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal connection la sentencia Insert Treatment</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception e) {
            sLastError = sLastError + "<p>Error accediendo a la BBDD Insert: " + e.getMessage() + "</p>";
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
    public String selectTreatment(String json) {
        String resultado = "";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Treatment where code=? or description like %?% or date like %?% or price like %?% or isPaid like %?% or patient like %?%;");
            preparedStatement.setString(1, clinic.getPatients()[0].getTreatments()[0].getCode());
            preparedStatement.setString(2, clinic.getPatients()[0].getTreatments()[0].getDescription());
            preparedStatement.setString(3, clinic.getPatients()[0].getTreatments()[0].getDate());
            preparedStatement.setString(4, String.valueOf(clinic.getPatients()[0].getTreatments()[0].getPrice()));
            preparedStatement.setString(5, String.valueOf(clinic.getPatients()[0].getTreatments()[0].isPaid()));
            preparedStatement.setString(6, clinic.getPatients()[0].getDni());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Proveedor insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal connection la sentencia Select Patient</p>";
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
    public String deleteTreatment(String json) {
        String resultado = "<p>Error al borrar</p>";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("delete from Treatment where code=?;");
            preparedStatement.setString(1, clinic.getPatients()[0].getTreatments()[0].getCode());


            if (preparedStatement.executeUpdate() != 0)
                resultado = "<p>Proveedor insertado correctamente</p>";
            else resultado = "<p>Algo ha salido mal connection la sentencia Delete Clinic</p>";
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
}