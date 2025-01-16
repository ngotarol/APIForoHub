API REST para registro topicos de foro.
Challenge Alura

Acciones implementadas:

Autenticacion:
1. Obtener Bearer Token:
  Metodo POST, url http://{tu-servidor}/login
  Ejemplo de body:
  {			
  			"correoElectronico":"User1@correo.correo",
  			"contrasena":"12345"
  }

Usuario
1. Registro de usuario:
   Metodo POST, url http://{tu-servidor}/usuarios
    Ejemplo de body:
     {
        	"nombre": "Natalia",
        	"correoElectronico": "User1@correo.correo",
        	"contrasena": "12345",
        	"perfil": {
        		  "nombre": "Usuario Comun"
          	}
    }
   La contraseña se encripta con Bcrypt.

Topicos
1. Registro de topico
   Metodo POST, url http://{tu-servidor}/topicos
   Registra un nuevo topico, crea el perfil y curso si no existe.
   Ejemplo de body:
     {
      	"titulo": "Este es un titulo",
      	"mensaje": "Este es un mensaje",
      	"estado": "false",
      	"autor": {
      		"nombre": "Natalia",
      		"correoElectronico": "User1@correo.correo",
      		"contrasena": "12345",
      		"perfil": {
      			"nombre": "Usuario Comun"
      		}
      	},
      	"curso": {
      		"nombre": "Springboot",
      		"categoria": "Desarrollo"
      	}
  }

3. Listar topicos    
   Metodo GET, url http://{tu-servidor}/topicos
   Entrega el listado de 10 en 10 de los topicos registrados, ordenados descendentemente, por fecha de creacion.
   Ejemplo de respuesta:
      {
      	"content": [
      		{
      			"id": 9,
      			"titulo": "Este es un titulo6",
      			"mensaje": "Este es un mensaje6",
      			"fechaCreacion": "2025-01-16T02:42:35.601968",
      			"estado": false,
      			"autor": "Marcelo",
      			"curso": "Springboot2"
      		},
      		{
      			"id": 8,
      			"titulo": "Este es un titulo5",
      			"mensaje": "Este es un mensaje5",
      			"fechaCreacion": "2025-01-16T02:42:16.666376",
      			"estado": false,
      			"autor": "Natalia",
      			"curso": "Springboot2"
      		},
      		{
      			"id": 7,
      			"titulo": "Este es un titulo3",
      			"mensaje": "Este es un mensaje3",
      			"fechaCreacion": "2025-01-15T00:23:07.771392",
      			"estado": false,
      			"autor": "Natalia",
      			"curso": "Springboot2"
      		},
      		{
      			"id": 5,
      			"titulo": "Este es un titulo2",
      			"mensaje": "Este es un mensaje2",
      			"fechaCreacion": "2025-01-14T22:42:58.775646",
      			"estado": false,
      			"autor": "Natalia",
      			"curso": "Springboot2"
      		},
      		{
      			"id": 4,
      			"titulo": "Este es un titulo1",
      			"mensaje": "Este es un mensaje1",
      			"fechaCreacion": "2025-01-14T22:42:42.749891",
      			"estado": false,
      			"autor": "Natalia",
      			"curso": "Springboot1"
      		}
      	],
      	"pageable": {
      		"pageNumber": 0,
      		"pageSize": 10,
      		"sort": {
      			"empty": false,
      			"sorted": true,
      			"unsorted": false
      		},
      		"offset": 0,
      		"paged": true,
      		"unpaged": false
      	},
      	"last": true,
      	"totalElements": 5,
      	"totalPages": 1,
      	"first": true,
      	"numberOfElements": 5,
      	"size": 10,
      	"number": 0,
      	"sort": {
      		"empty": false,
      		"sorted": true,
      		"unsorted": false
      	},
      	"empty": false
      }

4. Actualizar topico
   Metodo PUT, url http://{tu-servidor}/topicos/{id}
    Ejemplo de body:
   {
	"titulo":"Este es un titulo nuevo",
	"mensaje":"Este es un mensaje3",
	"estado":"false",
	"autor":{
			"nombre":"Natalia",
			"correoElectronico":"User1@correo.correo",
			"contrasena":"12345",
			"perfil":{
				"nombre":"Usuario Comun"
				}
			},
	"curso":{
			"nombre":"Springboot",
			"categoria":"Desarrollo"
			}
}

5. Borrar topico
   Metodo Delete, url http://{tu-servidor}/topicos/{id}
   
