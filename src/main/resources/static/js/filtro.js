function filterProducts() {
    const searchInput = document.getElementById('searchInput').value.toLowerCase();
    const productItems = document.querySelectorAll('.product-card'); // Selecciona todos los productos

    productItems.forEach(product => {
        const title = product.querySelector('.card-title').textContent.toLowerCase(); // Obtiene el texto del título del producto

        // Comprueba si el título incluye el texto de búsqueda
        if (title.includes(searchInput)) {
            product.parentElement.style.display = ''; // Muestra el producto
        } else {
            product.parentElement.style.display = 'none'; // Oculta el producto
        }
    });
}
