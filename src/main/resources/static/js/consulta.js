document.querySelectorAll('.faq-question').forEach(question => {
  question.addEventListener('click', () => {
      const answer = question.nextElementSibling;
      const icon = question.querySelector('i');

      // Toggle display of the answer
      answer.style.display = answer.style.display === 'block' ? 'none' : 'block';

      // Toggle between up and down icons
      if (icon.classList.contains('fa-chevron-down')) {
          icon.classList.remove('fa-chevron-down');
          icon.classList.add('fa-chevron-up');
      } else {
          icon.classList.remove('fa-chevron-up');
          icon.classList.add('fa-chevron-down');
      }
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