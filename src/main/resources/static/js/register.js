function abrirModalHistorial(button) {
    // Obtener el ID del gatito desde el atributo data-id
    const gatitoId = button.getAttribute('data-id');

    // Aquí puedes hacer una llamada AJAX para obtener el historial médico del gatito
    // usando el gatitoId y luego cargar los datos en el modal.

    // Simulamos los datos obtenidos dinámicamente
    const historialData = {
        descripcion: "Gatito rescatado, recibió su primera vacuna.",
        dosisVacunas: "1 dosis",
        numeroVisitasVeterinario: "2 visitas"
    };

    // Rellenar los campos del modal con los datos obtenidos
    document.getElementById('descripcion').value = historialData.descripcion;
    document.getElementById('dosisVacunas').value = historialData.dosisVacunas;
    document.getElementById('numeroVisitas').value = historialData.numeroVisitasVeterinario;

    // Mostrar el modal (esto lo gestiona Bootstrap automáticamente)
}
