document.querySelectorAll('.faq-question').forEach(question => {
    question.addEventListener('click', () => {
        const answer = question.nextElementSibling;
        answer.style.display = answer.style.display === 'block' ? 'none' : 'block';
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const sections = document.querySelectorAll('.zone');
    const buttons = document.querySelectorAll('.navegation-consult a');

    buttons.forEach((button, index) => {
        button.addEventListener('click', function() {
            // Ocultar todas las secciones
            sections.forEach(section => {
                section.classList.add('hidden');
            });
            // Mostrar la sección correspondiente
            sections[index].classList.remove('hidden');

            // Restablecer el estilo de todos los botones
            buttons.forEach(btn => {
                btn.style.fontWeight = 'normal'; // Restablece el peso de fuente
            });

            // Aplicar el estilo al botón seleccionado
            button.style.fontWeight = '800'; // Cambia el peso de fuente del botón activo
        });
    });
});