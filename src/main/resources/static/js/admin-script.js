// const hamBurger = document.querySelector(".toggle-btn");

// hamBurger.addEventListener("click", function () {
//   document.querySelector("#sidebar").classList.toggle("expand");
// });


const hamBurger = document.querySelector(".toggle-btn");
const sidebar = document.querySelector("#sidebar");
const mainContent = document.querySelector(".main");

// Función para ajustar el estado del sidebar según el tamaño de la pantalla
function adjustSidebar() {
    if (window.innerWidth <= 768) {
        sidebar.classList.remove("expand");
        mainContent.style.marginLeft = '70px';
    } else {
        sidebar.classList.add("expand");
        mainContent.style.marginLeft = '250px';
    }
}

// Evento al hacer clic en el botón
hamBurger.addEventListener("click", function () {
    sidebar.classList.toggle("expand");

    // Ajustar el margen del contenido principal según el estado de expansión
    if (sidebar.classList.contains("expand")) {
        mainContent.style.marginLeft = '250px';
    } else {
        mainContent.style.marginLeft = '70px';
    }
});

// Ajuste inicial al cargar la página y al redimensionar la ventana
document.addEventListener("DOMContentLoaded", adjustSidebar);
window.addEventListener("resize", adjustSidebar);
