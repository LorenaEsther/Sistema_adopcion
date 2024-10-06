// Código existente
const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

// Nuevo código para manejar las donaciones
document.getElementById('donationForm').addEventListener('submit', function (e) {
    e.preventDefault();
    
    // Obtener los valores del formulario
    const donationAmount = document.getElementById('donationAmount').value;
    const paymentMethod = document.getElementById('paymentMethod').value;
    
    // Validar que se haya ingresado un monto válido
    if (donationAmount && paymentMethod) {
        // Mostrar mensaje de confirmación
        document.getElementById('donationConfirmationAmount').textContent = donationAmount;
        document.getElementById('confirmationMessage').classList.remove('d-none');

        // Limpiar el formulario
        document.getElementById('donationForm').reset();
    } else {
        alert('Por favor, complete todos los campos.');
    }
});
