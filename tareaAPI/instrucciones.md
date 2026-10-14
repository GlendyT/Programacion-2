Práctica: Integración de API con Aplicación de Escritorio Java
Objetivo: Desarrollar una API que permita obtener información de una tabla de artículos en una base de datos Oracle y consumir esta API desde una aplicación de escritorio Java para mostrar la información en un JTable.

Instrucciones:

API:

Utiliza tu lenguaje de programación backend preferido para crear una API.
Establece una conexión con una base de datos Oracle.
Crea un endpoint que realice un SELECT a una tabla de artículos y retorne los datos en formato JSON.
Aplicación de Escritorio Java:

Diseña una interfaz gráfica que contenga un JTable y un botón para cargar los datos.
Al presionar el botón, la aplicación debe consumir el endpoint de la API y cargar los datos en el JTable.
Implementa el código necesario para convertir la respuesta JSON en un modelo de datos para el JTable.
Conversión de JSON a JTable:

Utiliza la librería org.json para manejar la respuesta JSON.
Itera sobre el JSON y extrae los datos necesarios para agregarlos al modelo del JTable.