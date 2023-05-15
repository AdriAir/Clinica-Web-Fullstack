package com.politecnicomalaga.ClinicTest;

import com.politecnicomalaga.model.Clinic;
import com.politecnicomalaga.model.Patient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class Tester {

    @BeforeAll
    public static void setUp() throws Exception {
        Clinic miClinica = new Clinic("clinica", "address", "phoneNumber", "email", "cif");
    }

    @Test
    public void addPatient() throws Exception {
        Clinic miClinica = new Clinic("clinica", "address", "phoneNumber", "email", "cif");
        assertNull(miClinica.getPatients());
        for (int i = 0; i < 500; i++) {
            miClinica.addPatient(new Patient("name", "surname", "phone_number", "email", String.valueOf(i), "bornDate"));
        }
        assertEquals(500, miClinica.getPatients().length);
    }

    @org.junit.Test
    public void removePatient() {
    }

    @org.junit.Test
    public void searchPatients() {
    }

    @org.junit.Test
    public void getPatient() {
    }

    @org.junit.Test
    public void getPatients() {
    }

    @org.junit.Test
    public void updatePatient() {
    }
}