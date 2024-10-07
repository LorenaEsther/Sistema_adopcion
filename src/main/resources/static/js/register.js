// register.js
document.addEventListener('DOMContentLoaded', function () {
    const registrarNuevoBtn = document.getElementById('registrarNuevoBtn');
    const formularioRegistro = document.getElementById('formularioRegistro');

    // Evento para mostrar/ocultar el formulario de registro
    registrarNuevoBtn.addEventListener('click', function () {
        formularioRegistro.classList.toggle('d-none');
    });
});
document.querySelector('form').addEventListener('submit', function (e) {
    e.preventDefault(); // Prevenir el envío real del formulario

    // Ocultar el modal de registro
    var registerModal = new bootstrap.Modal(document.getElementById('registroModal'));
    registerModal.hide();

    // Mostrar el modal de confirmación
    var confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'));
    confirmationModal.show();
});
document.getElementById('guardarHistorial').addEventListener('click', function () {
    // Ocultar el modal de historial médico
    var historialModal = new bootstrap.Modal(document.getElementById('historialModal'));
    historialModal.hide();

    // Mostrar el modal de confirmación
    var confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'));
    confirmationModal.show();
});



