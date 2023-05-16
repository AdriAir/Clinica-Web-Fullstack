package com.politecnicomalaga.model;

import com.politecnicomalaga.controller.BDAdaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Patient extends HttpServlet {
    public enum patientAttributes {NAME, SURNAME, DNI, BORN_DATE, PHONE_NUMBER, EMAIL};
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String dni;
    private String bornDate;

    private ArrayList<Treatment> treatments;

    //Comportamiento

    //Constructor
    public Patient(String name, String surname, String phoneNumber, String email, String dni, String bornDate) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dni = dni;
        this.bornDate = bornDate;
        treatments = new ArrayList<>();
    }


    public Patient(String csv) {
        String[] lines = csv.split("\n");
        //Me vendrá una línea mínimo para paciente
        String[] attributes = lines[0].split(";");
        if (attributes[0].equals("Paciente")) {
            this.name = attributes[1];
            this.surname = attributes[2];
            this.phoneNumber = attributes[3];
            this.email = attributes[4];
            this.dni = attributes[5];
            this.bornDate = attributes[6];
        } else {
            return;
        }

        //Después de 0 a n tratamientos
        treatments = new ArrayList<>();

        //Si las líneas son más de 1... Hay tratamientos
        for (int i = 1; i < lines.length; i++) {
            //trabajo el tratamiento
            Treatment treatment = new Treatment(lines[i]);
            //lo pongo en la lista
            this.treatments.add(treatment);
        }

    }

    //Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }


    //CRUD tratamientos del paciente
    public boolean addTreatment(Treatment treatment) {

        if (this.getTreatment(treatment.getCode()) == null && treatment.getPrice() >= 0f) {
            treatments.add(treatment);
            return true;
        }
        return false;

    }

    public boolean removeTreatment(String code) {
        Treatment treatment = this.getTreatment(code);
        if (treatment != null) {
            if (treatment.isPaid()) {
                return (treatments.remove(treatment));
            }
        }
        return false;
    }

    public Treatment[] searchTreatment(String field, Treatment.treatmentAttributes treatmentAttributes) {
        ArrayList<Treatment> result = new ArrayList<>();

        for (Treatment treatment : treatments) {
            if (treatment.compare(field, treatmentAttributes)) {
                result.add(treatment);
            }
        }

        if (result.size() > 0) {
            Treatment[] treatments = new Treatment[result.size()];
            return result.toArray(treatments);
        }
        return null;
    }

    public Treatment getTreatment(String code) {

        for (Treatment treatment : treatments) {
            if (treatment.getCode().equals(code)) {
                return treatment;
            }
        }

        return null;
    }

    public Treatment[] getTreatments() {
        if (treatments.size() == 0) return null;
        Treatment[] treatments = new Treatment[this.treatments.size()];
        return this.treatments.toArray(treatments);
    }

    public float chargeTreatment(String code) {
        Treatment treatment = this.getTreatment(code);
        if (treatment != null) {
            if (!treatment.isPaid()) {
                treatment.charge();
                return treatment.getPrice();
            }
        }
        return -1f;
    }


    public boolean isAllPaid() {

        for (Treatment treatment : treatments) {
            if (!treatment.isPaid()) {
                return false;
            }
        }

        return false;
    }

    public boolean compare(String field, patientAttributes patientAttributes) {

        return switch (patientAttributes) {
            case NAME -> this.name.contains(field);
            case SURNAME -> this.surname.contains(field);
            case DNI -> this.dni.contains(field);
            case BORN_DATE -> this.bornDate.contains(field);
            case EMAIL -> this.email.contains(field);
            case PHONE_NUMBER -> this.phoneNumber.contains(field);
        };

    }

    //Setter multi-field. Se le da el valor y el atributo y se asigna el valor en el atributo designado
    public void setValue(String field, patientAttributes patientAttributes) {

        switch (patientAttributes) {
            case NAME -> this.setName(field);
            case SURNAME -> this.setSurname(field);
            case DNI -> this.setDni(field);
            case BORN_DATE -> this.setBornDate(field);
            case EMAIL -> this.setEmail(field);
            case PHONE_NUMBER -> this.setPhoneNumber(field);
        }

    }

    @Override
    public String toString() {
        return String.format("%15s#" +
                        "%25s#" +
                        "%9s#" +
                        "%10s#" +
                        "%20s#" +
                        "%13s",
                this.name,
                this.surname,
                this.phoneNumber,
                this.email,
                this.dni,
                this.bornDate);
    }

    public String toCSV() {
		/* Para recordar el orden de los atributos
		 * this.name = attributes[1];
			this.surname = attributes[2];
			this.phoneNumber = attributes[3];
			this.email = attributes[4];
			this.dni = attributes[5];
			this.bornDate = attributes[6];
		 */
        StringBuilder result = new StringBuilder(String.format("Paciente;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s\n",
                this.name,
                this.surname,
                this.phoneNumber,
                this.email,
                this.dni,
                this.bornDate));
        for (Treatment treatment : this.treatments) {
            result.append(treatment.toCSV());
        }
        return result.toString();
    }

    //HTTP SERVLET

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String petitionRequested = request.getParameter("petition");
        String json = request.getParameter("data");

        response.setContentType("text/html;charset=UTF-8");

        String result = "";
        BDAdaptor bdAdaptor = new BDAdaptor();

        result = switch (petitionRequested) {
            case "all" -> result = bdAdaptor.getPatients();
            case "insert" -> result = bdAdaptor.insertPatient(json);
            case "select" -> result = bdAdaptor.selectPatient(json);
            //case "delete" -> result = bdAdaptor.deletePatient(json);
            //case "update" -> result = bdAdaptor.updatePatient(json);
            default -> result = "<p>Parámetro desconocido</p>";
        };

        try (PrintWriter printWriter = response.getWriter()) {

            printWriter.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Get Paciente Resultados</title>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<style>\n" +
                    "* {" +
                    "color: white;" +
                    "}" +
                    "body {" +
                    "display: flex;" +
                    "justify-content: center;" +
                    "align-items: center;" +
                    "background-color: black;\n" +
                    "}" +
                    "table {" +
                    "border: 2px solid white;" +
                    "}" +
                    "<style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    result +
                    "</body>\n" +
                    "</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "BackEnd TrabajoTaller Servlet";
    }// </editor-fold>


}