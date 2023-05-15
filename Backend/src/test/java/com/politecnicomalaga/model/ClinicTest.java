package com.politecnicomalaga.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ClinicTest {
    private Clinic miClinica;
    @Before
    public void init (){
        miClinica = new Clinic("Jose", "Mi dirección", "111222333", "micorreo@gmail.com", "56535643L");

    }
    @Test
    public void addPatient() {
        for (int i = 0; i<=1000; i++){
            miClinica.addPatient(new Patient("Javier", "pakito", "777555444", "prueba@gmail.com", String.valueOf(i), "12/03/2001"));
            for (int j = 0; j<=20; j++){
                miClinica.getPatient(String.valueOf(i)).addTreatment(new Treatment("1234", "Muy malo esta", "12/03/2024", 200));
            }
        }
    }

    @Test
    public void removePatient() {
    }

    @Test
    public void searchPatients() {
    }

    @Test
    public void getPatient() {
    }

    @Test
    public void getPatients() {
    }

    @Test
    public void updatePatient() {
    }
}