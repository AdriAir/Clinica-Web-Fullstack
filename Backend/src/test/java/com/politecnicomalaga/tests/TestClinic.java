package com.politecnicomalaga.tests;

import com.politecnicomalaga.controller.FileController;
import com.politecnicomalaga.model.Clinic;
import com.politecnicomalaga.model.Patient;
import com.politecnicomalaga.model.Treatment;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestClinic {

    private static Clinic clinic = new Clinic("clinica", "address", "phoneNumber", "email", "cif");
    private static int patientNumber = 500;

    @Test
    public void addPatient() throws Exception {
        for (int i = 0; i < patientNumber; i++) {
            clinic.addPatient(new Patient("name", "surname", "phone_number", "email", String.valueOf(i), "bornDate"));
        }
        assertEquals(patientNumber, clinic.getPatients().length);
    }

    @Test
    public void writeJson() {
        clinic = new Clinic("FRIKAZOS", "AVDA MARMOLES 2", "12312", "sahd@askjkda", "1234543J");
        for (int i = 0; i < patientNumber; i++) {
            clinic.addPatient(new Patient("adri", "borio muñoz", "123123421", "a@asdj.com", String.valueOf(i), "2004-09-26"));
            for (int j = 0; j < 3; j++) {
                clinic.getPatient(String.valueOf(i)).addTreatment(new Treatment(String.valueOf(j), "Descripción 1 del tratamiento", "2020-09-14", 120f));
            }
        }

        try {
            FileController.writeJson(clinic, "C:\\Users\\Abori\\Desktop\\json_text.json");
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }

    }

    @Test
    public void removePatient() {
        clinic = new Clinic("clinica", "address", "phoneNumber", "email", "cif");
        clinic.addPatient(new Patient("name", "surname", "phone_number", "email", "0", "bornDate"));
        clinic.removePatient("0");
        assertNull(clinic.getPatients());
        clinic.addPatient(new Patient("name", "surname", "phone_number", "email", "0", "bornDate"));
        clinic.addPatient(new Patient("name", "surname", "phone_number", "email", "1", "bornDate"));
        clinic.removePatient("0");
        assertEquals(1, clinic.getPatients().length);
    }

    @Test
    public void searchPatients() {
        for (int i = 0; i < patientNumber; i++) {
            clinic.addPatient(new Patient("name", "surname", "phone_number", "email", String.valueOf(i), "bornDate"));
        }
        assertEquals(1, clinic.searchPatients("1", Patient.patientAttributes.DNI).length);
        assertEquals(patientNumber, clinic.searchPatients("na", Patient.patientAttributes.NAME).length);
    }

    @Test
    public void getPatient() {
        for (int i = 0; i < patientNumber; i++) {
            clinic.addPatient(new Patient("name", "surname", "phone_number", "email", String.valueOf(i), "bornDate"));
        }
        assertTrue(clinic.getPatient("2").getDni().equals("2"));
    }

    @Test
    public void getPatients() {
        for (int i = 0; i < patientNumber; i++) {
            clinic.addPatient(new Patient("name", "surname", "phone_number", "email", String.valueOf(i), "bornDate"));
        }
        assertEquals(500, clinic.getPatients().length);
    }

    @Test
    public void updatePatient() {
        for (int i = 0; i < patientNumber; i++) {
            clinic.addPatient(new Patient("name", "surname", "phone_number", "email", String.valueOf(i), "bornDate"));
        }
        clinic.updatePatient("5", "928", Patient.patientAttributes.DNI);
        assertEquals("928", clinic.getPatient("928").getDni());
        clinic.updatePatient("3", "Manu", Patient.patientAttributes.NAME);
        assertEquals("Manu", clinic.getPatient("3").getName());
    }
}