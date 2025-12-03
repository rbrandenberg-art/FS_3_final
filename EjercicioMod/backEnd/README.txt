#Instrucciones para iniciar el proyecto
#Clonar desde github
#Crea una carpeta para guardar el clon del proyecto
#Abrir el visual, la carpeta donde se guardara el clonado.
#Abrir una terminal y ejecutar los codigos
git clone https://github.com/rbrandenberg-art/FS_3_final.git
----------------------------------------------------------------------------------------
cd EjercicioMod
cd backEnd/
./mvnw spring-boot:run
#Abrir un programa para ejecutar las acciones del backend (para visual usar thunder client, postman)
#Para comprobar usar el metodo POST con el siguiente codigo, en la casilla JSON
{
  "title": "Un Título genial",
  "author": "Un Autor Famoso"
}
