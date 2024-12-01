// Datos para el gráfico de barras
const datosBarras = {
    labels: ['Gatos Disponibles', 'Solicitudes Pendientes', 'Total Adopciones', 'Usuarios Registrados'],
    datasets: [{
            label: 'Estadísticas',
            data: [gatosDisponibles, solicitudesPendientes, totalAdopciones, usuariosRegistrados],
            backgroundColor: [
                'rgba(75, 192, 192, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)'
            ],
            borderColor: [
                'rgba(75, 192, 192, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderWidth: 1
        }]
};

// Configuración del gráfico de barras
const configBarras = {
    type: 'bar',
    data: datosBarras,
    options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
};

// Crear el gráfico de barras
new Chart(
        document.getElementById('graficoBarras'),
        configBarras
        );

// Datos para el gráfico de pastel
const datosPastel = {
    labels: ['Gatos Disponibles', 'Solicitudes Pendientes', 'Total Adopciones', 'Usuarios Registrados'],
    datasets: [{
            data: [gatosDisponibles, solicitudesPendientes, totalAdopciones, usuariosRegistrados],
            backgroundColor: [
                'rgba(75, 192, 192, 0.6)',
                'rgba(255, 159, 64, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(153, 102, 255, 0.6)'
            ]
        }]
};

// Configuración del gráfico de pastel
const configPastel = {
    type: 'pie',
    data: datosPastel,
    options: {
        responsive: true,
        maintainAspectRatio: false, // Permite ajustar el tamaño del gráfico
    }
};

// Crear el gráfico de pastel
const graficoPastel = new Chart(
        document.getElementById('graficoPastel'),
        configPastel
        );

//boton imprimir reporte
function imprimirReporte() {
    const {jsPDF} = window.jspdf;
    const doc = new jsPDF();

    // Título del reporte
    doc.setFontSize(16);
    doc.text("Reporte de Adopciones", 105, 10, {align: "center"});

    // Datos del reporte
    doc.setFontSize(12);
    doc.text(`Gatos Disponibles: ${gatosDisponibles}`, 10, 30);
    doc.text(`Solicitudes Pendientes: ${solicitudesPendientes}`, 10, 40);
    doc.text(`Total Adopciones: ${totalAdopciones}`, 10, 50);
    doc.text(`Usuarios Registrados: ${usuariosRegistrados}`, 10, 60);

    // Capturar el gráfico de barras
    const graficoBarras = document.getElementById("graficoBarras");
    const graficoBarrasImg = graficoBarras.toDataURL("image/png");

    // Capturar el gráfico de pastel
    const graficoPastel = document.getElementById("graficoPastel");
    const graficoPastelImg = graficoPastel.toDataURL("image/png");

    // Agregar el gráfico de barras al PDF
    doc.addImage(graficoBarrasImg, "PNG", 10, 70, 90, 60); // Ajusta las coordenadas y tamaño

    // Agregar el gráfico de pastel al PDF
    doc.addImage(graficoPastelImg, "PNG", 110, 70, 90, 60); // Ajusta las coordenadas y tamaño

    // Guardar el PDF
    doc.save("reporte-adopciones-con-graficos.pdf");
}


