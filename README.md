# product-ms-hex
## Microservicio con Arquitectura Hexagonal y MongoDB
### Paso 1
Clonar el repositorio y abrir el proyecto con Intellij IDEA

### Paso 2
Abrir el archivo de propiedades /src/main/java/resources/applicaction.properties

Luego poner la cadena de conexión para MongoDB en la variable "spring.data.mongodb.uri"

### Paso 3
Ejecutar el Proyecto

### Paso 4
Para probar se tienen los siguientes curls (Postman):

#### Agregar un Producto
curl --location 'http://localhost:9091/product' \
--header 'Content-Type: application/json' \
--data '{
"description": "Llave Hexagonal",
"price": 20
}'

#### Actualizar un Producto: Copiar un id luego de agregar un Producto
curl --location --request PUT 'http://localhost:9091/product/pegar_id' \
--header 'Content-Type: application/json' \
--data '{
"description": "Llave Hexagonal",
"price": 19.45
}'

### Obtener todos los productos
curl --location 'http://localhost:9091/product/all'

### Obtener un producto por id: Copiar un id luego de agregar un Producto
curl --location 'http://localhost:9091/product/pegar_id'

### Eliminar un producto por id: Copiar un id luego de agregar un Producto
curl --location --request DELETE 'http://localhost:9091/product/6618a44d98ec581c990b5425'
