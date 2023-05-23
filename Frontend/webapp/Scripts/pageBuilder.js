let addPage = document.getElementById("page");
let optionSelector = document.getElementById("optionSelect");

let addPatient = document.getElementById("addPatient");
let listPatients = document.getElementById("listPatients");
let addTreatment = document.getElementById("addTreatment");
let listTreatments = document.getElementById("listTreatments");
let chargeTreatment = document.getElementById("chargeTreatment");

let HIDDEN = "display: none;";
let DISPLAYED = "display: unset;";


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