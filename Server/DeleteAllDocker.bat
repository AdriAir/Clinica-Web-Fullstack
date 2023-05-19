FOR /f "tokens=*" %i IN ('docker ps -q') DO docker stop %i
FOR /f "tokens=*" %i IN ('docker ps -aq') DO docker rm %i
