document.addEventListener('DOMContentLoaded', function () {
    console.log('DOM completamente cargado y analizado.');

    // Selecciona el botón de "Registrar Miaus"
    const registrarMiausBtn = document.getElementById('registrarMiausBtn');
    console.log('Botón encontrado:', registrarMiausBtn);

    // Selecciona el contenedor dinámico
    const contenidoDinamico = document.getElementById('contenidoDinamico');
    console.log('Contenedor dinámico encontrado:', contenidoDinamico);

    // Verifica si el botón y el contenedor existen
    if (registrarMiausBtn && contenidoDinamico) {
        console.log('Botón y contenedor existen, agregando evento de clic.');

        // Agrega el evento de clic al botón
        registrarMiausBtn.addEventListener('click', function (event) {
            event.preventDefault(); // Prevenir el comportamiento predeterminado
            console.log('Botón "Registrar Miaus" clicado, iniciando fetch.');

            // Cargar el contenido del archivo HTML dentro del contenedor dinámico
            fetch('/admin/registergatito')
                    .then(response => {
                        console.log('Respuesta del fetch recibida:', response);
                        if (!response.ok) {
                            throw new Error('Error al cargar el contenido: ' + response.status);
                        }
                        return response.text();
                    })
                    .then(data => {
                        console.log('Contenido recibido:', data);
                        contenidoDinamico.innerHTML = data; // Inserta el contenido en el contenedor
                    })
                    .catch(error => {
                        console.error('Error al cargar el contenido dinámico:', error);
                    });
        });
    } else {
        console.error('No se encontró el botón o el contenedor dinámico.');
    }
});
