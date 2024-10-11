// Constantes para eventos
const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

// Activar y desactivar clase en el contenedor
registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

// Animación en el botón "Atrás"
const buttonUI2 = document.getElementById('button-ui-2');
const arrowSVG = document.getElementById('arrow-svg');

// Sacudida y rotación de la flecha al pasar el ratón
buttonUI2.addEventListener('mouseenter', () => {
    arrowSVG.style.transform = 'translateX(-55px) rotate(-720deg)';
    arrowSVG.style.transition = 'transform 1s ease-in-out';
});

buttonUI2.addEventListener('mouseleave', () => {
    arrowSVG.style.transform = 'translateX(0) rotate(0)';
    arrowSVG.style.transition = 'transform 1s ease-in-out';
});
