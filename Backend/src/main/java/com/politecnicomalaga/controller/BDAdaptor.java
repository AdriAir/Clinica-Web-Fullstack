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
        String dbURL = "jdbc:mysql://bbdd:3306/";
        // Database name to access
        String dbName = "ClinicaDentista";
        String dbUsername = "principal";
        String dbPassword = "1234";

        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
        } catch (Exception exception) {
            lastError = lastError + "<p>Error conectando a la BBDD: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        }
        return connection;
    }

    public String getClinics() {
        StringBuilder result = new StringBuilder();
        String cif, name, address, phoneNumber, email;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection = this.initDatabase();
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Clinic;");
            //ResultSet = statement.executeQuery("select * from proveedores");
            ResultSet resultSet = preparedStatement.executeQuery();
            result.append("<table>" +
                    "<tr>" +
                    "<th>Fila</th>" +
                    "<th>CIF</th>" +
                    "<th>Nombre</th>" +
                    "<th>Dirección</th>" +
                    "<th>Teléfono</th>" +
                    "<th>Email</th>" +
                    "</tr>");
            // iteración sobre el resultset
            while (resultSet.next())  //Mientras tengamos rows de salida...
            {
                rows++;
                cif = (resultSet.getString("cif"));
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                phoneNumber = resultSet.getString("phoneNumber");
                email = resultSet.getString("email");
                // save the results
                result.append("<tr>" + "<th>").
                        append(rows).
                        append("</th>").
                        append("<th>").
                        append(cif).
                        append("</th>").
                        append("<th>").
                        append(name).
                        append("</th>").
                        append("<th>").
                        append(address).
                        append("</th>").
                        append("<th>").
                        append(phoneNumber).
                        append("</th>").
                        append("<th>").
                        append(email).
                        append("</th>").
                        append("</tr>");
            }
            result.append("</table>");
        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();
            }
        }
        result.append("\n<p>Filas recogidas: ").append(rows).append("</p>\n");
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;
    }

    public String getPatients() {
        StringBuilder result = new StringBuilder();
        String dni, name, surname, phoneNumber, email, bornDate, clinic;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection = this.initDatabase();
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Patient;");
            //ResultSet = statement.executeQuery("select * from proveedores");
            ResultSet resultSet = preparedStatement.executeQuery();
            result = new StringBuilder();
            result.append("<table>" +
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
            lastError = lastError + "<p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();
            }
        }
        result.append("\n<p>Filas recogidas: ").append(rows).append("</p>\n");
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;
    }

    public String getTreatments() {
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
            preparedStatement = connection.prepareStatement("select * from Treatment;");
            ResultSet resultSet = preparedStatement.executeQuery();
            result.append("<table>" +
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
            lastError = lastError + "<p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        result.append("\n<p>Rows recogidas: ").append(rows).append("</p>\n");
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;
    }


    public String insertPatient(String json) {
        String result = "";
        Connection connection = null;
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
                result = "<p>Paciente insertada correctamente</p>";
            else result = "<p>Algo ha salido al insertar la clínica...</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Insert: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;

    }

    public String insertTreatment(String json) {
        String result = "";
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
                result = "<p>Tratamiento insertado correctamente</p>";
            else result = "<p>Algo ha salido mal connection la sentencia Insert Treatment</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Insert: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result;
        else return result + lastError;

    }

    public String selectClinic(String json) {
        StringBuilder result = new StringBuilder();
        String cif, name, address, phoneNumber, email;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        Clinic clinic = FileController.readJson(json);
        int rows = 0;
        try {
            connection = this.initDatabase();
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("select * from Clinic " +
                    "where cif=? " +
                    "or name like %?% " +
                    "or address like %?% " +
                    "or phoneNumber like %?% " +
                    "or email like %?%;");
            preparedStatement.setString(1, clinic.getCif());
            preparedStatement.setString(2, clinic.getName());
            preparedStatement.setString(3, clinic.getAddress());
            preparedStatement.setString(4, clinic.getPhoneNumber());
            preparedStatement.setString(5, clinic.getEmail());
            //ResultSet = statement.executeQuery("select * from proveedores");
            ResultSet resultSet = preparedStatement.executeQuery();
            result = new StringBuilder();
            result.append("<table>" +
                    "<tr>" +
                    "<th>Fila</th>" +
                    "<th>CIF</th>" +
                    "<th>Nombre</th>" +
                    "<th>Dirección</th>" +
                    "<th>Teléfono</th>" +
                    "<th>Email</th>" +
                    "</tr>");
            // iteración sobre el resultset
            while (resultSet.next())  //Mientras tengamos rows de salida...
            {
                rows++;
                cif = (resultSet.getString("cif"));
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                phoneNumber = resultSet.getString("phoneNumber");
                email = resultSet.getString("email");
                // save the results
                result.append("<tr>" + "<th>").
                        append(rows).
                        append("</th>").
                        append("<th>").
                        append(cif).
                        append("</th>").
                        append("<th>").
                        append(name).
                        append("</th>").
                        append("<th>").
                        append(address).
                        append("</th>").
                        append("<th>").
                        append(phoneNumber).
                        append("</th>").
                        append("<th>").
                        append(email).
                        append("</th>").
                        append("</tr>");
            }
            result.append("</table>");
        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) {
            return result.toString();
        } else {
            return result + lastError;
        }
    }

    public String selectPatient(String json) {
        StringBuilder result = new StringBuilder();
        Connection connection = null;
        String dni, name, surname, phoneNumber, email, bornDate, clinicCif;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection = this.initDatabase();

            preparedStatement = connection.prepareStatement("select * from Patient " +
                    "where dni=? " +
                    "or name like %?% " +
                    "or surname like %?% " +
                    "or phoneNumber like %?% " +
                    "or email like %?% " +
                    "or bornDate = ? " +
                    "or clinic = ?;");
            preparedStatement.setString(1, clinic.getPatients()[0].getDni());
            preparedStatement.setString(2, clinic.getPatients()[0].getName());
            preparedStatement.setString(3, clinic.getPatients()[0].getSurname());
            preparedStatement.setString(4, clinic.getPatients()[0].getPhoneNumber());
            preparedStatement.setString(5, clinic.getPatients()[0].getEmail());
            preparedStatement.setString(6, clinic.getPatients()[0].getBornDate());
            preparedStatement.setString(7, clinic.getCif());

            //ResultSet = statement.executeQuery("select * from proveedores");
            ResultSet resultSet = preparedStatement.executeQuery();
            result = new StringBuilder();
            result.append("<table>" +
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
                clinicCif = resultSet.getString("clinicCif");
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
                        append("</th>").
                        append("<th>").
                        append(bornDate).
                        append("</th>").
                        append("<th>").
                        append(clinicCif).
                        append("</th>").
                        append("</tr>");
            }
            result.append("</table>");

        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;

    }

    public String selectTreatment(String json) {
        StringBuilder result = new StringBuilder();
        Connection connection = null;
        String code, description, date, patientDni;
        boolean isPaid;
        float price;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection = this.initDatabase();

            preparedStatement = connection.prepareStatement("select * from Treatment " +
                    "where code=? " +
                    "or description like %?% " +
                    "or date = ? " +
                    "or price = ? " +
                    "or isPaid = ? " +
                    "or patient = ?;");
            preparedStatement.setString(1, clinic.getPatients()[0].
                    getTreatments()[0].getCode());
            preparedStatement.setString(2, clinic.getPatients()[0].
                    getTreatments()[0].getDescription());
            preparedStatement.setString(3, clinic.getPatients()[0].
                    getTreatments()[0].getDate());
            preparedStatement.setString(4, String.valueOf(clinic.getPatients()[0].
                    getTreatments()[0].getPrice()));
            preparedStatement.setString(5, String.valueOf(clinic.getPatients()[0].
                    getTreatments()[0].isPaid()));
            preparedStatement.setString(6, clinic.getPatients()[0].getDni());

            //ResultSet = statement.executeQuery("select * from proveedores");
            ResultSet resultSet = preparedStatement.executeQuery();
            result.append("<table>" +
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
            lastError = lastError + "<p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;
    }

    public String deletePatient(String json) {
        String result = "<p>Error al borrar</p>";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("delete from Patient where dni=?;");
            preparedStatement.setString(1, clinic.getPatients()[0].getDni());


            if (preparedStatement.executeUpdate() != 0)
                result = "<p>Paciente eliminado correctamente</p>";
            else result = "<p>Algo ha salido mal al eliminar un paciente</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result;
        else return result + lastError;

    }

    public String deleteTreatment(String json) {
        String result = "<p>Error al borrar</p>";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("delete from Treatment where code=?;");
            preparedStatement.setString(1, clinic.getPatients()[0].getTreatments()[0].getCode());


            if (preparedStatement.executeUpdate() != 0)
                result = "<p>Tratamiento eliminado correctamente</p>";
            else result = "<p>Algo ha salido mal al eliminar el tratamiento</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Select: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result;
        else return result + lastError;

    }

    public String updateClinic(String json) {
        String result = "";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;
        String newCif = "";

        try {
            connection = this.initDatabase();
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("update Clinic set name=?,address=?,phoneNumber=?,email=? where cif=?;");
            preparedStatement.setString(1, clinic.getCif());
            preparedStatement.setString(2, clinic.getName());
            preparedStatement.setString(3, clinic.getAddress());
            preparedStatement.setString(4, clinic.getPhoneNumber());
            preparedStatement.setString(5, clinic.getEmail());

            if (preparedStatement.executeUpdate() != 0)
                result = "<p>Clínica actualizada correctamente</p>";
            else result = "<p>Algo ha salido al actualizar la clínica...</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Insert: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) {
            return result;
        } else {
            return result + lastError;
        }
    }

    public String updatePatient(String json) {
        String result = "";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;
        String dni, name, surname, phoneNumber, email, bornDate, clinicCif;
        int rows = 0;
        try {
            connection = this.initDatabase();
            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("update Patient set name=?, surname=?,phoneNumber=?,email=?,bornDate=?,clinic=? where dni=?;");
            preparedStatement.setString(1, clinic.getPatients()[0].getDni());
            preparedStatement.setString(2, clinic.getPatients()[0].getName());
            preparedStatement.setString(3, clinic.getPatients()[0].getSurname());
            preparedStatement.setString(4, clinic.getPatients()[0].getPhoneNumber());
            preparedStatement.setString(5, clinic.getPatients()[0].getEmail());
            preparedStatement.setString(6, clinic.getPatients()[0].getBornDate());
            preparedStatement.setString(7, clinic.getCif());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (preparedStatement.executeUpdate() != 0)
                result = "<p>Paciente actualizar correctamente</p>";
            else result = "<p>Algo ha salido al actualizar el paciente...</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Insert: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result.toString();
        else return result + lastError;

    }

    public String updateTreatment(String json) {
        String result = "";
        Connection connection = null;
        Clinic clinic = FileController.readJson(json);
        PreparedStatement preparedStatement = null;

        try {
            connection = this.initDatabase();

            //statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("update Treatment set description=?,date=?,price=?,isPaid=?,patient=? where code=?;");
            preparedStatement.setString(1, clinic.getPatients()[0].getTreatments()[0].getCode());
            preparedStatement.setString(2, clinic.getPatients()[0].getTreatments()[0].getDescription());
            preparedStatement.setString(3, clinic.getPatients()[0].getTreatments()[0].getDate());
            preparedStatement.setString(4, String.valueOf(clinic.getPatients()[0].getTreatments()[0].getPrice()));
            preparedStatement.setString(5, String.valueOf(clinic.getPatients()[0].getTreatments()[0].isPaid()));
            preparedStatement.setString(6, clinic.getPatients()[0].getDni());


            if (preparedStatement.executeUpdate() != 0)
                result = "<p>Tratamiento actualizado correctamente</p>";
            else result = "<p>Algo ha salido mal connection la sentencia Update Treatment</p>";
            //En este caso es una orden hacia la BBDD, y no tenemos
            //ResultSet para iterar, las cosas pueden ir bien, o mal, nada más
            //que hacer entonces aquí

        } catch (Exception exception) {
            lastError = lastError + "<p>Error accediendo a la BBDD Insert: " + exception.getMessage() + "</p>";
            exception.printStackTrace();
        } finally {
            // Liberamos recursos. Cerramos sentencia y conexión
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception exception) {
                lastError = lastError + "<p>Error cerrando la BBDD: " + exception.getMessage() + "</p>";
                exception.printStackTrace();

            }
        }
        if (lastError.isEmpty()) return result;
        else return result + lastError;

    }
}