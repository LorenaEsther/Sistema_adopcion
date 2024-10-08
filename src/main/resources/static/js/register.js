document.addEventListener('DOMContentLoaded', function () {
    console.log('Documento cargado completamente');

    // Verificar si Bootstrap está disponible
    if (typeof bootstrap !== 'undefined') {
        console.log('Bootstrap está cargado correctamente');
    } else {
        console.error('Bootstrap NO está cargado. Verifica la inclusión de los scripts de Bootstrap.');
    }

    // Verificar si el botón "Registrar Nuevo" existe
    const registrarNuevoBtn = document.getElementById('registrarNuevoBtn');
    if (registrarNuevoBtn) {
        console.log('Botón "Registrar Nuevo" encontrado');
    } else {
        console.error('Botón "Registrar Nuevo" NO encontrado');
    }

    // Verificar si el modal de registro existe
    const registroModal = document.getElementById('registroModal');
    if (registroModal) {
        console.log('Modal de registro encontrado');
    } else {
        console.error('Modal de registro NO encontrado');
    }

    // Verificar si el formulario en el modal de registro existe
    const registroForm = document.querySelector('#registroModal form');
    if (registroForm) {
        console.log('Formulario de registro encontrado');

        // Escuchar el evento de envío del formulario
        registroForm.addEventListener('submit', function (e) {
            e.preventDefault(); // Prevenir el envío real del formulario
            console.log('Formulario de registro enviado');

            // Ocultar el modal de registro
            try {
                var registerModalInstance = new bootstrap.Modal(document.getElementById('registroModal'));
                console.log('Modal de registro instanciado');
                registerModalInstance.hide();
                console.log('Modal de registro ocultado');
            } catch (error) {
                console.error('Error al ocultar el modal de registro:', error);
            }

            // Mostrar el modal de confirmación
            try {
                var confirmationModalInstance = new bootstrap.Modal(document.getElementById('confirmationModal'));
                console.log('Modal de confirmación instanciado');
                confirmationModalInstance.show();
                console.log('Modal de confirmación mostrado');
            } catch (error) {
                console.error('Error al mostrar el modal de confirmación:', error);
            }
        });
    } else {
        console.error('Formulario de registro NO encontrado');
    }

    // Verificar si el botón "Guardar Historial" existe
    const guardarHistorialBtn = document.getElementById('guardarHistorial');
    if (guardarHistorialBtn) {
        console.log('Botón "Guardar Historial" encontrado');

        // Evento para el botón de guardar historial médico
        guardarHistorialBtn.addEventListener('click', function () {
            console.log('Botón "Guardar Historial" presionado');

            // Ocultar el modal de historial médico
            try {
                var historialModalInstance = new bootstrap.Modal(document.getElementById('historialModal'));
                console.log('Modal de historial médico instanciado');
                historialModalInstance.hide();
                console.log('Modal de historial médico ocultado');
            } catch (error) {
                console.error('Error al ocultar el modal de historial médico:', error);
            }

            // Mostrar el modal de confirmación
            try {
                var confirmationModalInstance = new bootstrap.Modal(document.getElementById('confirmationModal'));
                console.log('Modal de confirmación instanciado');
                confirmationModalInstance.show();
                console.log('Modal de confirmación mostrado');
            } catch (error) {
                console.error('Error al mostrar el modal de confirmación:', error);
            }
        });
    } else {
        console.error('Botón "Guardar Historial" NO encontrado');
    }
});
