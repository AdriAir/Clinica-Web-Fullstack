<h1>Set Up del proyecto con la aplicación gráfica de Docker en Windows</h1>

1. Clonar el repositorio
2. Instalamos la aplicación de Docker para Windows y reiniciamos el equipo
3. Abrimos la terminal en la raiz del repositorio y ejecutamos los siguientes comandos:
    ```batch
    REM Creamos los contenedores mediante el archivo .yml si no existen. SI TENEMOS EL PUERTO 3306, 8080 o 8081 usando, hay que detenerlos para continuar
    cd Server\Docker
    docker-compose up -d

    REM Copiamos los scripts .sql a la base de datos y los ejecutamos
    cd ..\mysql
    docker cp ClinicaDentista.sql docker-bbdd-1:/ClinicaDentista.sql
    docker cp principal.sql docker-bbdd-1:/principal.sql
    docker exec -it docker-bbdd-1 mysql -u root -p

    REM password: secretone

    source ClinicaDentista.sql
    source principal.sql
    exit
    ```
3. Abrimos la carpeta Backend con intelliJ
4. Desplegamos el menú de maven (lateral derecha) y hacemos doble click en Backend/Plugins/war/war:war
5. Abrimos docker, revisamos que existe un nuevo contenedor llamado "docker", el cual contiene los contenedores "servidorweb-1", "phpmyadmin-1" y "bbdd-1"
6. Ejecutamos el contenedor principal "docker" haciendo click en el botón de play, situado a la derecha
7. Abrimos una nueva terminal en la raiz del repositorio y ejecutamos los siguientes comandos:
    ```batch
    REM Copiamos la app a tomcat
    docker cp Frontend\webapp docker-servidorweb-1:/usr/local/tomcat/webapps/

    REM Copiamos los archivos de usuario y configuración a tomcat
    docker cp Server\tomcat\tomcat-users.xml docker-servidorweb-1:/usr/local/tomcat/conf/tomcat-users.xml
    docker cp Server\tomcat\context.xml docker-servidorweb-1:/usr/local/tomcat/webapps.dist/manager/META-INF/context.xml
    
    REM Entramos al contenedor y añadimos los archivos restantes
    docker exec -it docker-servidorweb-1 /bin/bash
    cp -R webapps.dist/* webapps/
    exit
    ```
8. Abrimos el navegador y comprobamos que tenemos acceso a las siguientes direcciones:
    
    - http://localhost:8080/
    - http://localhost:8081/

9. Entramos en http://localhost:8080/manager/html

    User: "admin"
    Password: "admin"

10. Comprobamos que "/webapp" está ejecutandose
11. Entramos en http://localhost:8080/webapp/
12. Si todo ha ido bien, debe aparecer "TODO write content"
