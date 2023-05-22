package com.politecnicomalaga.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Clinica")
public class ClinicServlet extends HttpServlet {
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
        String data = request.getParameter("data");

        response.setContentType("text/html;charset=UTF-8");

        String result = "";
        BDAdaptor bdAdaptor = new BDAdaptor();

        result = switch (petitionRequested) {
            case "listaPacientes" -> result = bdAdaptor.listPatients(data); //Apellidos
            case "listaTratamientos" -> result = bdAdaptor.listTreatments(data); //Dni
            case "insertarPaciente" -> result = bdAdaptor.insertPatient(data); //JsonPatient
            case "insertarTratamiento" -> result = bdAdaptor.insertTreatment(data); //JsonTreatment
            case "cobraTratamiento" -> result = bdAdaptor.chargeTreatment(data); //treatmentCode;PatientDni
            default -> result = "<p>Parámetro desconocido</p>";
        };

        try (PrintWriter printWriter = response.getWriter()) {

            printWriter.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Get Clínica Resultados</title>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<style>\n" +
                    "* {" +
                    "color: white;" +
                    "}" +
                    ".btn {" +
                    "color: black;" +
                    "}" +
                    "body {" +
                    "display: flex;" +
                    "justify-content: center;" +
                    "align-items: center;" +
                    "height: 100vh;" +
                    "background-color: black;" +
                    "}" +
                    "table {" +
                    "border: 2px solid white;" +
                    "}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    result +
                    "<br><form action='http://localhost:8080/app/'><br>" +
                    "<input class='btn' type='submit' value='Volver'>" +
                    "</form>" +
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
        return "BackEnd Clinica Servlet";
    }// </editor-fold>
}
