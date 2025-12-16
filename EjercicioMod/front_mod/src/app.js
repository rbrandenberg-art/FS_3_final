const API_URL = 'http://localhost:8080/api/books';

// --- FUNCIÓN PARA LISTAR (GET) ---
async function listarLibros() {
    const cuerpo = document.getElementById('cuerpoTabla');
    try {
        const resp = await fetch(API_URL);
        const libros = await resp.json();
        
        cuerpo.innerHTML = ""; 
        libros.forEach(l => {
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${l.id}</td>
                <td>${l.title}</td>
                <td>${l.author}</td>
                <td class="text-center">
                    <button class="btn-delete" onclick="eliminarLibro(${l.id})">Eliminar</button>
                </td>
            `;
            cuerpo.appendChild(fila);
        });
    } catch (e) {
        console.error("Error al listar:", e);
    }
}

// --- FUNCIÓN PARA ELIMINAR (DELETE) ---
async function eliminarLibro(id) {
    if (!confirm("¿Estás seguro de que deseas eliminar este libro?")) return;

    try {
        const resp = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (resp.ok) {
            listarLibros(); // Refrescar la tabla después de borrar
        } else {
            alert("No se pudo eliminar el libro");
        }
    } catch (e) {
        console.error("Error al eliminar:", e);
    }
}

// --- LÓGICA PARA GUARDAR (POST) ---
document.getElementById('btnGuardar').addEventListener('click', async () => {
    const title = document.getElementById('titulo').value;
    const author = document.getElementById('autor').value;

    if (!title || !author) return alert("Completa los campos");

    try {
        await fetch(API_URL, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({ title, author })
        });
        
        document.getElementById('titulo').value = "";
        document.getElementById('autor').value = "";
        listarLibros();
        
    } catch (e) {
        alert("Error al guardar");
    }
});

document.getElementById('btnListar').addEventListener('click', listarLibros);
listarLibros();