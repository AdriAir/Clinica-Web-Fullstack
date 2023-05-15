/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.TrabajoTallerServidor;

/**
 *
 * @author andres
 */

public class Proveedor implements Comparable<Proveedor>{
    //estado
    protected String nombreProveedor;
    protected String fechaAlta;
    protected String direccionPostal;
    protected String numeroTelefono;
    protected String email;
    protected String personaDeContacto;


    //Comportamiento


    public Proveedor(String nombreProveedor, String fechaAlta, String direccionPostal, String numeroTelefono, String email, String personaDeContacto) {
        this.nombreProveedor = nombreProveedor;
        this.fechaAlta = fechaAlta;
        this.direccionPostal = direccionPostal;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.personaDeContacto = personaDeContacto;
    }

    public Proveedor(String sCSV) {
        String[] listaParametros = sCSV.split(";");

        this.nombreProveedor = listaParametros[0];
        this.fechaAlta = listaParametros[1];
        this.direccionPostal = listaParametros[2];
        this.numeroTelefono= listaParametros[3];
        this.email = listaParametros[4];
        this.personaDeContacto = listaParametros[5];
    }

    @Override
    public String toString() {
        return nombreProveedor + ';' +
                fechaAlta +  ';' +
                direccionPostal + ';' +
                numeroTelefono + ';' +
                email + ';' +
                personaDeContacto;
    }

    @Override
    public int compareTo(Proveedor proveedor) {
        return (proveedor.nombreProveedor+proveedor.email).compareTo(this.nombreProveedor+this.email);
    }
}