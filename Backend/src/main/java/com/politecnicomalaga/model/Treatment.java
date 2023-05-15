package com.politecnicomalaga.model;

import com.politecnicomalaga.controller.BDAdaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Treatment extends HttpServlet {
    public enum treatmentAttributes {CODE, DESCRIPTION, DATE, PRICE, IS_PAID};
    protected String code;
    protected String description;
    protected String date;
    protected float price;
    protected boolean isPaid;

    //Constructor
    public Treatment(String code, String description, String date, float price) {
        this.code = code;
        this.description = description;
        this.date = date;
        this.price = price;

        isPaid = (price == 0f);
    }

    public Treatment(String csv) {
        String[] attributes = csv.split(";");

        if (attributes[0].equals("Tratamiento")) {
            this.code = attributes[1];
            this.description = attributes[2];
            this.date = attributes[3];
            this.price = Float.valueOf(attributes[4]);
            this.isPaid = Boolean.valueOf(attributes[5]);
        } else {
            this.code = "";
            this.description = "";
            this.date = "";
            this.price = 0f;

            isPaid = true;
        }
    }

    //Getters y Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float charge() {
        this.isPaid = true;
        return price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean compare(String field, treatmentAttributes treatmentAttributes) {
        char comparator;
        String json;
        switch (treatmentAttributes) {
            case CODE -> {
                return this.code.contains(field);
            }
            case DESCRIPTION -> {
                return this.description.contains(field);
            }
            case DATE -> {
                return this.date.contains(field);
            }
            case IS_PAID -> {
                if (field.equals("true")) return isPaid;
                else return !isPaid;
            }
            case PRICE -> {
                comparator = field.charAt(0);
                if (comparator == '>') {
                    json = field.substring(1);
                    return Double.parseDouble(json) < price;
                } else if (comparator == '<') {
                    json = field.substring(1);
                    return Double.parseDouble(json) > price;
                } else {
                    return Double.parseDouble(field) == price;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("%6s#" +
                        "%30s#" +
                        "%10s#" +
                        "%4.2f# " +
                        "IS_PAID: %b",
                this.code,
                this.description,
                this.date,
                this.price,
                this.isPaid);
    }

    public String toCSV() {
        return String.format("Tratamiento;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%s;" +
                        "%b\n",
                this.code,
                this.description,
                this.date,
                this.price,
                this.isPaid);
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
            case "all" -> bdAdaptor.getTreatments();
            case "insert" -> bdAdaptor.insertTreatment(json);
            case "select" -> bdAdaptor.selectTreatment(json);
            case "delete" -> bdAdaptor.deleteTreatment(json);
            default -> "<p>Parámetro desconocido</p>";
        };

        try (PrintWriter printWriter = response.getWriter()) {

            printWriter.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Get Clínica Resultados</title>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "</head>\n" +
                    "<body style=\"background-color:red;\"><p>Resultado:</p>\n" +
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