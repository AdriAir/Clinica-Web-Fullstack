let addPage = document.getElementById("page");
let optionSelector = document.getElementById("optionSelect");

let addPatient = document.getElementById("addPatient");
let listPatients = document.getElementById("listPatients");
let addTreatment = document.getElementById("addTreatment");
let listTreatments = document.getElementById("listTreatments");
let chargeTreatment = document.getElementById("chargeTreatment");

let HIDDEN = "display: none;";
let DISPLAYED = "display: unset;";

let httpQuery = "";
let data;

//AddPatient Fields
let addPatient_name = document.getElementById("addPatient_name");
let addPatient_surname = document.getElementById("addPatient_surname");
let addPatient_phoneNumber = document.getElementById("addPatient_phoneNumber");
let addPatient_email = document.getElementById("addPatient_email").value;
let addPatient_dni = document.getElementById("addPatient_dni");
let addPatient_bornDate = document.getElementById("addPatient_bornDate");
//ListPatients Fields
let listPatients_surname = document.getElementById("listPatients_surname");
//AddTreatment Fields
let addTreatment_patient = document.getElementById("addTreatment_patient");
let addTreatment_code = document.getElementById("addTreatment_code");
let addTreatment_description = document.getElementById("addTreatment_description");
let addTreatment_date = document.getElementById("addTreatment_date");
let addTreatment_price = document.getElementById("addTreatment_price");
let addTreatment_isPaid = document.getElementById("addTreatment_isPaid");
//ListTreatments Fields
let listTreatments_patient = document.getElementById("listTreatments_patient");
//ChargeTreatment Fields
let chargeTreatment_patient = document.getElementById("chargeTreatment_patient");
let chargeTreatment_treatment =document.getElementById("chargeTreatment_treatment");

function buildPage() {
    if (optionSelector.value === "addPatient") {
        addPatient.style = DISPLAYED;
        listPatients.style = HIDDEN;
        addTreatment.style = HIDDEN;
        listTreatments.style = HIDDEN;
        chargeTreatment.style = HIDDEN;
    } else if (optionSelector.value === "listPatients") {
        addPatient.style = HIDDEN;
        listPatients.style = DISPLAYED;
        addTreatment.style = HIDDEN;
        listTreatments.style = HIDDEN;
        chargeTreatment.style = HIDDEN;
    } else if (optionSelector.value === "addTreatment") {
        addPatient.style = HIDDEN;
        listPatients.style = HIDDEN;
        addTreatment.style = DISPLAYED;
        listTreatments.style = HIDDEN;
        chargeTreatment.style = HIDDEN;
    } else if (optionSelector.value === "listTreatments") {
        addPatient.style = HIDDEN;
        listPatients.style = HIDDEN;
        addTreatment.style = HIDDEN;
        listTreatments.style = DISPLAYED;
        chargeTreatment.style = HIDDEN;
    } else if (optionSelector.value === "chargeTreatment") {
        addPatient.style = HIDDEN;
        listPatients.style = HIDDEN;
        addTreatment.style = HIDDEN;
        listTreatments.style = HIDDEN;
        chargeTreatment.style = DISPLAYED;
    }
}

function generateQuery() {
    if (optionSelector.value === "addPatient") {
        httpQuery = "http://localhost:8080/app/Clinica?petition=insertarPaciente&data=";
        data = {
            "name": "Clínica DAW",
            "address": "Calle Mármoles 23",
            "phoneNumber": "6263728934",
            "email": "daw.clinica@gmail.com",
            "cif": "3213212J",
            "patients": [
                {
                    "name": addPatient_name.value,
                    "surname": addPatient_surname.value,
                    "phoneNumber": addPatient_phoneNumber.value,
                    "email": addPatient_email.value,
                    "dni": addPatient_dni.value,
                    "bornDate": addPatient_bornDate.value,
                    "treatments": [

                    ]
                }
            ]
        };
        httpQuery += JSON.stringify(data);
    } else if (optionSelector.value === "listPatients") {
        httpQuery = "http://localhost:8080/app/Clinica?petition=listaPacientes&data=";
        data = listPatients_surname.value;
        httpQuery += JSON.stringify(data);
    } else if (optionSelector.value === "addTreatment") {
        httpQuery = "http://localhost:8080/app/Clinica?petition=insertarTratamiento&data=";
        data = {
            "name": "Clinica DAW",
            "address": "Calle Marmoles 23",
            "phoneNumber": "6263728934",
            "email": "daw.clinica@gmail.com",
            "cif": "3213212J",
            "patients": [
                {
                    "name": "addPatient_name",
                    "surname": "addPatient_surname",
                    "phoneNumber": "addPatient_phoneNumber",
                    "email": "addPatient_email",
                    "dni": addTreatment_patient.value,
                    "bornDate": "1970-01-01",
                    "treatments": [
                        {
                            "code": addTreatment_code.value,
                            "description": addTreatment_description.value,
                            "date": addTreatment_date.value,
                            "price": addTreatment_price.value,
                            "isPaid": addTreatment_isPaid.checked
                        }
                    ]
                }
            ]
        };
        httpQuery += JSON.stringify(data);
    } else if (optionSelector.value === "listTreatments") {
        httpQuery = "http://localhost:8080/app/Clinica?petition=listaTratamientos&data=";
        data = listTreatments_patient.value;
        httpQuery += data;
    } else if (optionSelector.value === "chargeTreatment") {
        httpQuery = "http://localhost:8080/app/Clinica?petition=cobraTratamiento&data=";
        data = chargeTreatment_treatment.value + ";" + chargeTreatment_patient.value;
        httpQuery += data;
    }

    window.location.href = httpQuery;

}