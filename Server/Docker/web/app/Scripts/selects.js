function updateTableOptions() {
    var optionSelect = document.getElementById("optionSelect");
    var tableSelect = document.getElementById("tableSelect");
    var selectedOption = optionSelect.value;

    if (selectedOption == "Añadir" || selectedOption == "Borrar") {
        tableSelect.options[2].style.display = "none";
    } else {
        tableSelect.options[2].style.display = "block";
    }
}

// No funciona
document.getElementById("boton1").addEventListener("click", function () {
    fetch("/Pages/SelectPage.html")
        .then(response => response.text())
        .then(data => {
            document.getElementById("opciones").innerHTML = data;
        })
        .catch(error => {
            console.error("Error al cargar el archivo:", error);
        });
});