package com.politecnicomalaga.tests;

import com.politecnicomalaga.model.Clinic;
import com.politecnicomalaga.model.Patient;
import org.junit.jupiter.api.Test;

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
    public void removePatient() {
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