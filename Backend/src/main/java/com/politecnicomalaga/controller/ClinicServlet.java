package com.politecnicomalaga.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

            printWriter.println("<!doctype html>" +
                    "<html lang='es'>" +
                    "<head>" +
                        "<title>Get Clínica Resultados</title>" +
                        "<!-- Required meta tags -->" +
                        "<meta charset='utf-8'>" +
                        "<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>" +
                        "<!-- Bootstrap CSS v5.2.1 -->" +
                        "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css' rel='stylesheet'" +
                            "integrity='sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT' crossorigin='anonymous'>" +
                        "<style>" +
                            "* {color: white;}" +
                            ".btn {color: black;}" +
                            "body {" +
                                "display: flex;" +
                                "flex-direction: column;" +
                                "justify-content: center;" +
                                "align-items: center;" +
                                "height: 100vh;" +
                                "background-color: black;" +
                            "}" +
                            "table {border: 2px solid white;}" +
                        "</style>" +
                    "</head>" +
                    "<body>" +
                        result +
                        "<br>" +
                        "<a name='' id='' class='btn btn-primary' href='http://localhost:8080/app/' role='button'>Volver</a>" +
                        "<!-- Bootstrap JavaScript Libraries -->" +
                        "<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js'" +
                            "integrity='sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3' crossorigin='anonymous'>" +
                            "</script>" +
                        "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js'" +
                            "integrity='sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz' crossorigin='anonymous'>" +
                            "</script>" +
                    "</body>" +
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
