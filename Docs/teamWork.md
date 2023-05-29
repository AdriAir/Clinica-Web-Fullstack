Reparto de tareas:

    - Adrián Borio (Scrum Master):
        - Soporte y JDBC: Modificación de Clases (métodos para SQL y Servlet)
        - BDAdaptor: Formato de las tablas para HTML
        - Docker SetUp (TomCat Web Server && MySql Server && phpMyAdmin)
        - Finalización del Frontend con Bootsrap5 y ultimos detalles

    - Antonio Cervantes:
        - Control de Excepciones en todo el modelo
        - Corrección de problemas:
            - isAllPaid() devuelve siempre false.
            - Al buscar por dni devuelve todos los valores que contengan el carácter, es decir, si tenemos un dni que es "23" y otro que es "230", devuelve los dos, cosa que para otros datos está bien, pero para el dni preferimos que devuelva el dato concreto.

    - Sandra Román:
        - Usuario en MySql
        - Base de datos
        - BDAdaptor
        - Frontend (Creación de formularios y base js)

    - Javier Marrón:
        - Test del modelo completo

    - Ruben Fernández:
        - JDBC: BDAdaptor (SQL Queries)

    - Jose Gómez:
        - Testing: Testing de la Clínica
        - BDAdaptor

Objetivos:

    Vamos a dividir el trabajo de la siguiente forma:

    BACKEND:

    Revisión del modelo:

        [X] Añadir Control de Exepciones
        [X] Revisar el correcto funcionamiento del modelo (tests)

    Base de datos:

        [X] Crear un usuarios con privilegios para realizar consultas e insertar datos
        [X] Crear controlador para manejar una base de datos en MySQL para las 3 tablas (clínica, pacientes y tratamientos) con la librería JDBC
        [X] Controlar las HTTPServlets

    Montar Servidor:

        [X] Configurar el servicio web y sql (puertos y tal)
        [X] Iniciar el servidor e intentar hacer una pequeña prueba con url manuales

<h3>

- [VER REGISTRO DE PROBLEMAS ABIERTOS](./logs/log.md)

</h3>