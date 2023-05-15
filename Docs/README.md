Reparto de tareas:

    - Adrián Borio (Scrum Master):
        - Soporte y JDBC: Modificación de Clases (métodos para SQL y Servlet)

    - Antonio Cervantes:
        - Control de Excepciones

    - Sandra Román:
        - Usuario en MySql
        - Base de datos

    - Javier Marrón:
        - Testing: ClinicTest

    - Ruben Fernández:
        - JDBC: BDAdaptor

    - Jose Gómez:
        - Testing: Comienzo de la clase ClinicTest

Resultado y decisiones a tomar en la reuniones SCRUM:

    Hemos comentado y dividido el trabajo para realizar el backend:

    DÍA 1:

        - Trabajo repartido
        - Elaboración del modelo
        - Revisión y control de excepciones
        - Montaje de controladores para SQL (JDBC) y HTTPServlets (Tomcat)

    DÍA 2:

    DÍA 3 (Entrega):

Objetivos:

    Vamos a dividir el trabajo de la siguiente forma:

    BACKEND:

    Revisión del modelo:

        [] Añadir Control de Exepciones
        [] Revisar el correcto funcionamiento del modelo (no hace falta usar tests, pero a quien le toque, si lo hace, le irá mejor para el examen)

    Base de datos:

        [] Crear un usuarios con privilegios para realizar consultas e insertar datos
        [] Crear controlador para manejar una base de datos en MySQL para las 3 tablas (clínica, pacientes y tratamientos) con la librería JDBC
        [] Crear controlador para poder interconectar el servicio MySQL con el servicio web creado en un servlet (app para el server) Tomcat ejecutado en un contenedor de software (Docker)

    Montar Servidor:

        [] Configurar el servicio web y sql (puertos y tal)
        [] Iniciar el servidor e intentar hacer una pequeña prueba con url manuales


    FRONTEND:

    Adelanto: Para el frontend realizaremos un formulario en HTML y css que, mediante JavaScript, traduzca los datos y los inserte en la barra de búsqueda con el formato de consulta definido en el controlador sql del servlet hecho en java.