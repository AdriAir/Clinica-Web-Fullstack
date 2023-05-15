# Utiliza la imagen base de Tomcat
FROM tomcat:latest

# Copia el archivo WAR a la carpeta webapps del contenedor
COPY app.war /usr/local/tomcat/webapps/

# Instala MySQL en el contenedor
RUN apt-get update && apt-get install -y mysql-server

# Expone el puerto 8080 para acceder a la aplicación web de Tomcat
EXPOSE 8080
