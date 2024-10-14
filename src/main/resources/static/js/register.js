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

function funcionDeEliminar(gatitoId) {
    if (confirm("¿Estás seguro de que deseas eliminar este gatito?")) {
        fetch(`/admin/eliminar/${gatitoId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content') // Asegúrate de tener este meta tag en tu HTML
            }
        })
        .then(response => {
            if (response.ok) {
                alert("Gatito eliminado exitosamente");
                window.location.reload();  // Recargar la página para reflejar los cambios
            } else {
                alert("Error al eliminar el gatito");
            }
        })
        .catch(error => {
            console.error("Error al eliminar el gatito:", error);
        });
    }
}





