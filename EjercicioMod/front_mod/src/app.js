const API_URL = 'http://localhost:8080/api/books';

// --- FUNCIÓN PARA LISTAR (GET) ---
const listarLibros = async () => {
    const cuerpoTabla = document.getElementById('cuerpoTabla');
    try {
        const response = await fetch(API_URL);
        const libros = await response.json();
        cuerpoTabla.innerHTML = "";
        libros.forEach(libro => {
            const fila = document.createElement('tr');
            fila.innerHTML = `<td>${libro.id}</td><td>${libro.title}</td><td>${libro.author}</td>`;
            cuerpoTabla.appendChild(fila);
        });
    } catch (error) {
        console.error("Error al listar:", error);
    }
};

// --- FUNCIÓN PARA GUARDAR (POST) ---
document.getElementById('btnGuardar').addEventListener('click', async () => {
    const title = document.getElementById('titulo').value;
    const author = document.getElementById('autor').value;

    if (!title || !author) {
        alert("Por favor, completa ambos campos");
        return;
    }

    const nuevoLibro = { title, author };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevoLibro)
        });

        if (response.ok) {
            // Limpiar inputs
            document.getElementById('titulo').value = "";
            document.getElementById('autor').value = "";
            // Refrescar la tabla automáticamente
            listarLibros();
        }
    } catch (error) {
        console.error("Error al guardar:", error);
    }
});

// Botón de actualizar
document.getElementById('btnListar').addEventListener('click', listarLibros);

// Cargar datos al abrir la página por primera vez
//listarLibros();