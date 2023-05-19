REM Añadir ejecución de SQL
docker run -d --name ClinicaDentista -p 8080:8080 -v app.war:/usr/local/tomcat/webapps/app.war -e MYSQL_HOST=bbdd -e MYSQL_PORT=3306 -e MYSQL_DATABASE=ClinicaDentista -e MYSQL_USER=principal -e MYSQL_PASSWORD=1234 tomcat:latest
pause