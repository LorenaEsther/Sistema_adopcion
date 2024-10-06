document.addEventListener("DOMContentLoaded", function () {
    // Obtener la URL actual
    const currentPath = window.location.pathname;

    // Obtener todos los elementos <a> con la clase "nav-link"
    const navLinks = document.querySelectorAll('.nav-link');

    // Iterar sobre cada enlace
    navLinks.forEach(function (link) {
        // Obtener el atributo href del enlace
        const linkPath = link.getAttribute('href');

        // Comparar el href del enlace con la URL actual
        if (linkPath === currentPath) {
            // Si coinciden, agregar la clase 'active' al enlace
            link.classList.add('active');
        } else {
            // Si no coinciden, remover la clase 'active'
            link.classList.remove('active');
        }
    });
});
