docker stop bbdd
docker rm -f bbdd
docker run -d -p 3306:3306 --name bbdd -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_USER=principal -e MYSQL_PASSWORD=1234 mysql
pause