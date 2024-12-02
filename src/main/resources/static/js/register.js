console.log("register.js cargado correctamente");

function funcionDeEliminar(id) {
    const confirmacion = confirm("¿Estás seguro de eliminar este gatito? ¡No podrás revertir esto!");

    if (confirmacion) {
        window.location.href = "registergatito/" + id;
    }
}

function setCatId(enlace) {
    var catId = enlace.getAttribute('data-id');
    document.getElementById('catIdInput').value = catId;
}
    
function submitGatitoForm(event) {
    event.preventDefault();
    var catId = document.getElementById('catIdInput').value;
    var actionUrl = '/admin/registergatito/editar/' + catId;
    var form = document.getElementById('editGatitoForm');
    form.action = actionUrl;
    form.submit();
}

function submitHistorialForm(event) {
    event.preventDefault();
    var catId = document.getElementById('catIdInput').value;
    var actionUrl = '/admin/registergatito/historial/' + catId;
    var form = document.getElementById('historialForm');
    form.action = actionUrl;
    form.submit();
}