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


var registroModal = document.getElementById('registroModal');

registroModal.addEventListener('show.bs.modal', function (event) {
    // Botón que activó el modal
    var button = event.relatedTarget;
    var modalTitle = registroModal.querySelector('.modal-title');
    var submitButton = registroModal.querySelector('#submitButton');
    var form = document.getElementById('gatitoForm');

    // Verificar si es una edición o un nuevo registro
    var id = button.getAttribute('data-id');
    var nombre = button.getAttribute('data-nombre');
    var color = button.getAttribute('data-color');
    var edadAproximada = button.getAttribute('data-edadaproximada');
    var estado = button.getAttribute('data-estado');
    var descripcion = button.getAttribute('data-descripcion');

    if (id) {
        // Si hay un ID, es una edición
        modalTitle.textContent = 'Editar Miau';
        submitButton.textContent = 'Guardar Cambios';
        form.action = `/admin/editar/${id}`;

        // Cargar los datos en los campos del formulario
        document.getElementById('nombre').value = nombre;
        document.getElementById('color').value = color;
        document.getElementById('edadAproximada').value = edadAproximada;
        document.getElementById('estado').value = estado;
        document.getElementById('descripcion').value = descripcion;
    } else {
        // Si no hay ID, es un nuevo registro
        modalTitle.textContent = 'Registrar Miau';
        submitButton.textContent = 'Registrar';
        form.action = '/admin/registergatito';

        // Limpiar los campos del formulario
        form.reset();
    }
});











