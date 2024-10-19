function abrirModalHistorial(button) {
    const gatitoId = button.getAttribute('data-id');
    const historialData = {
        descripcion: "Gatito rescatado, recibió su primera vacuna.",
        dosisVacunas: "1 dosis",
        numeroVisitasVeterinario: "2 visitas"
    };

    document.getElementById('descripcion').value = historialData.descripcion;
    document.getElementById('dosisVacunas').value = historialData.dosisVacunas;
    document.getElementById('numeroVisitas').value = historialData.numeroVisitasVeterinario;
}

console.log("register.js cargado correctamente");

function confirmEliminar() {
    return confirm("¿Estás seguro de que deseas eliminar este gatito?");
}









