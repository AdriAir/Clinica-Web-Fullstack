Trabajo en equipo.
Se trata de crear vuestro primer endpoint fullstack real utilizando las clases de acceso a datos de JDBC para MySql y los HttpServlets. Aunque hoy en día todos los frameworks para fullstack (Spring, Quarkus, ...) encapsulan este proceso de una manera más o menos automatizada, es interesante que pongáis en funcionamiento a bajo nivel una arquitectura Cliente - Servidor en internet con el ejercicio que os planteo a continuación:
- Se debe de tener una BBDD en mysql que tenga al menos dos tablas, Pacientes y Tratamientos, donde representemos la persistencia derivada del proyecto trabajado en clase ClinicaDentista. Tendremos las columnas derivadas de los atributos de las clases teniendo en cuenta que para representar la agregación (relación 1 a muchos) en BBDD utilizamos la clave primaria de Pacientes en Tratamientos (aquí clave foránea).
- Utilizando de base el proyecto Backend-TrabajoTaller, refactorizarlo para acceder a la BBDD definida previamente (hay que definit un usuario de acceso a la BBDD que no sea root)
- Tendremos las siguientes "Call" o llamadas API Rest a nuestro HttpServlet:
   --- listaPacientes?parametro=apellidos
   --- listaTratamientos?parametro=dniPaciente
   --- insertPaciente?parametro=jsonPaciente
   --- insertTratamiento?parametro=jsonTratamiento
   --- cobraTratamiento?parametro=codTratamiento;codPaciente

listaPacientes recibirá un string en apellidos un String que buscaremos mediante like '%apellidos%' en el campo apellidos de Pacientes, en la BBDD.
Los métodos listaLoQueSea devolverán o HTML bien formado en forma de tabla o json que tendrá que ser trabajado en el cliente correspondiente. Los insert y cobra... devolverán un "OK: función realizada" o un "error: descripción del error", que podrá ser trabajada en los clientes de manera adecuada.


- Se realizarán alguno de los siguientes clientes (podéis escoger uno, o programarlos todos):
   --- Una aplicación Android que usando OKHttp se conecte a nuestro Backend usando su IP hardcoded. Con las activities necesarias para las distintas opciones del Backend
   --- Una aplicación en Java con interfaz de texto que se conecte con Okhttp para realizar las distintas opciones del Backend
   --- Una aplicación HTML usando forms y "GET" como método HTTP para hacer peticiones en nuestro Backend. Es importante por lo tanto incluir el formateo de los parámetros mediante JavaScript. Esta opción no se ha visto en clase, podéis si queréis intentarlo viendo ejemplos en Internet, como el que podéis encontrar en https://notasweb.me/entrada/realizar-peticiones-post-get-con-ajax-y-jquery o en la web https://www.freecodecamp.org/espanol/news/solicitud-http-en-javascript/

Se establecen dos SPRINTS:
- Uno primero para el Backend. Hasta el 17 de mayo

- Otro segundo para el/los clientes. Hasta el 24 de mayo

Importante: Hay que tener un repositorio organizado en dos carpetas, backend y frontend para hacer VCS del proyecto (gestión de versiones). Las ramas que pongáis son a vuestra elección. El repositorio será privado y el día 24 de mayo se harán públicos.
Se debera de generar un README que contenga como mínimo:
- Reparto de tareas
- Resultado y decisiones a tomar en la reuniones SCRUM
- Funcionalidades hechas, probadas y por hacer.

NOTA: El reparto de las tareas es clave para terminar el proyecto adecuamente.