package com.politecnicomalaga.controller;

import com.politecnicomalaga.model.Clinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BDAdaptor {
    private String lastError;

    //CONSTRUCTOR
    public BDAdaptor() {
        lastError = "";
    }

    //Methods
    private Connection initDatabase() {
        Connection connection = null;
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://bbdd:3306/ClinicaDentista";
        // Database name to access
        String dbUsername = "principal";
        String dbPassword = "1234";

        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (Exception exception) {
            lastError = lastError + "<br><p>Error conectando a la BBDD: " + exception.getMessage() + "</p><br>";
            exception.printStackTrace();
        }
        return connection;
    }

    public String listPatients(String apellidos) {
        StringBuilder result = new StringBuilder();
        String dni, name, surname, phoneNumber, email, bornDate, clinic;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection = this.initDatabase();
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Patient where surname like ?;");
            preparedStatement.setString(1, "%" + apellidos + "%");

            //ResultSet = statement.executeQuery("select * from proveedores");
            ResultSet resultSet = preparedStatement.executeQuery();
            result = new StringBuilder();
            result.append("<br><table>" +
                    "<tr>" +
                    "<th>Fila</th>" +
                    "<th>DNI</th>" +
                    "<th>Nombre</th>" +
                    "<th>Apellidos</th>" +
                    "<th>Teléfono</th>" +
                    "<th>Email</th>" +
                    "<th>Fecha de Nacimiento</th>" +
                    "<th>Clínica (CIF)</th>" +
                    "</tr>");
            // iteración sobre el resultset
            while (resultSet.next())  //Mientras tengamos rows de salida...
            {
                rows++;
                dni = (resultSet.getString("dni"));
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                phoneNumber = resultSet.getString("phoneNumber");
                email = resultSet.getString("email");
                bornDate = resultSet.getString("bornDate");
                clinic = resultSet.getString("clinic");
                // save the results
                result.append("<tr>" + "<th>").
                        append(rows).
                        append("</th>").
                        append("<th>").
                        append(dni).
                        append("</th>").
                        append("<th>").
                        append(name).
                        append("</th>").
                        append("<th>").
                        append(surname).
                        append("</th>").
                        append("<th>").
                        append(phoneNumber).
                        append("</th>").
                        append("<th>").
                        append(email).
                        append("</th>").append("<th>").
                        append(bornDate).
                        append("</th>").append("<th>").
                        append(clinic).
                        append("</th>").
                        append("</tr>");
            }
            result.append("</table>");
        } catch (Exception exception) {
            lastError = lastError + "<br><p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p><br>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (connection != null) connection.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception exception) {
                lastError = lastError + "<br><p>Error cerrando la BBDD: " + exception.getMessage() + "</p><br>";
                exception.printStackTrace();
            }
        }
        result.append("\n<p>Filas recogidas: ").append(rows).append(" </p><br>");
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;
    }

    public String listTreatments(String dni) {
        StringBuilder result = new StringBuilder();
        String code, description, date, patientDni;
        float price;
        boolean isPaid;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Treatment where dni = ?;");
            preparedStatement.setString(1, dni);

            ResultSet resultSet = preparedStatement.executeQuery();
            result.append("<br><table>" +
                    "<tr>" +
                    "<th>Fila</th>" +
                    "<th>Código</th>" +
                    "<th>Descripción</th>" +
                    "<th>Fecha</th>" +
                    "<th>Precio</th>" +
                    "<th>Estado</th>" +
                    "<th>Paciente (DNI)</th>" +
                    "</tr>");
            // iteración sobre el resultset
            while (resultSet.next())  //Mientras tengamos rows de salida...
            {
                rows++;
                code = (resultSet.getString("code"));
                description = resultSet.getString("description");
                date = resultSet.getString("date");
                price = resultSet.getFloat("price");
                isPaid = resultSet.getBoolean("isPaid");
                patientDni = resultSet.getString("patient");
                // save the results
                result.append("<tr>" + "<th>").
                        append(rows).
                        append("</th>").
                        append("<th>").
                        append(code).
                        append("</th>").
                        append("<th>").
                        append(description).
                        append("</th>").
                        append("<th>").
                        append(date).
                        append("</th>").
                        append("<th>").
                        append(price).
                        append("</th>").
                        append("<th>").
                        append(isPaid).
                        append("</th>").
                        append("<th>").
                        append(patientDni).
                        append("</th>").
                        append("</tr>");
            }
            result.append("</table>");
        } catch (Exception exception) {
            lastError = lastError + "<br><p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p><br>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (connection != null) connection.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception exception) {
                lastError = lastError + "<br><p>Error cerrando la BBDD: " + exception.getMessage() + "</p><br>";
                exception.printStackTrace();

            }
        }
        result.append("\n<br><p>Rows recogidas: ").append(rows).append("</p><br>");
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;
    }

    public String insertPatient(String json) {
        String result = "";
        Connection connection = null;
        json = json.replaceAll("%22", "'");
        json = json.replaceAll("%20", " ");
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;
        String dni, name, surname, phoneNumber, email, bornDate, clinicCif;
        int rows = 0;
        try {
            connection = this.initDatabase();
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("insert into Patient (dni,name, surname,phoneNumber,email,bornDate,clinic) values (?,?,?,?,?,?,?);");
            preparedStatement.setString(1, clinic.getPatients()[0].getDni());
            preparedStatement.setString(2, clinic.getPatients()[0].getName());
            preparedStatement.setString(3, clinic.getPatients()[0].getSurname());
            preparedStatement.setString(4, clinic.getPatients()[0].getPhoneNumber());
            preparedStatement.setString(5, clinic.getPatients()[0].getEmail());
            preparedStatement.setString(6, clinic.getPatients()[0].getBornDate());
            preparedStatement.setString(7, clinic.getCif());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (preparedStatement.executeUpdate() != 0)
                result = "<br><p>Paciente insertada correctamente</p><br>";
            else result = "<br><p>Algo ha salido al insertar la clínica...</p><br>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<br><p>Error accediendo a la BBDD Insert: " + exception.getMessage() + "</p><br>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<br><p>Error cerrando la BBDD: " + exception.getMessage() + "</p><br>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;

    }

    public String insertTreatment(String json) {
        String result = "";
        Connection connection = null;
        json = json.replaceAll("%22", "'");
        json = json.replaceAll("%20", " ");
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
                result = "<br><p>Tratamiento insertado correctamente</p><br>";
            else result = "<br><p>Algo ha salido mal connection la sentencia Insert Treatment</p><br>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<br><p>Error accediendo a la BBDD Insert: " + exception.getMessage() + "</p><br>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<br><p>Error cerrando la BBDD: " + exception.getMessage() + "</p><br>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result;
        else return result + lastError;

    }

    public String chargeTreatment(String data) {
        String result = "";
        Connection connection = null;
        String code = data.split(";")[0];
        String dni = data.split(";")[1];
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("update Treatment set isPaid=true where patient=? and code=?;");
            preparedStatement.setString(1, dni);
            preparedStatement.setString(2, code);


            if (preparedStatement.executeUpdate() != 0)
                result = "<br><p>Tratamiento cobrado correctamente</p><br>";
            else result = "<br><p>Algo ha salido mal connection la sentencia Update Treatment</p><br>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<br><p>Error accediendo a la BBDD Insert: " + exception.getMessage() + "</p><br>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<br><p>Error cerrando la BBDD: " + exception.getMessage() + "</p><br>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result;
        else return result + lastError;

    }
}