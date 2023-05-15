package com.politecnicomalaga.ClinicTest;

import com.politecnicomalaga.model.Clinic;
import com.politecnicomalaga.model.Patient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
public class Tester {

    @BeforeAll
    public static void setUp() throws Exception {
        Clinic miClinica= new Clinic("clinica", "address","phoneNumber","email", "cif");
    }

    @Test
    public void addPatient() throws Exception {
        Clinic miClinica= new Clinic("clinica", "address","phoneNumber","email", "cif");
        assertNull(miClinica.getPatients());
        for(int i=0; i<500;i++){
            miClinica.addPatient(new Patient("name","surname", "phone_number", "email", String.valueOf(i), "bornDate"));
        }
        assertTrue(miClinica.getPatients().length==500);
    }

    @Test
    public void removePatient() {
        Clinic miCLinica= new Clinic("clinica", "address","phoneNumber","email", "cif");
        miCLinica.addPatient(new Patient("name","surname", "phone_number", "email", "1", "bornDate"));
        miCLinica.removePatient("1");
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