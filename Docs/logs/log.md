LOS SIGUIENTES PROBLEMAS NO ESTÁN SOLUCIONADOS:

1. La conexión se mantiene NULL cuando nos conectamos via el BD Adaptor.
Pensamos que tiene que haber algún error en la clase, pues desde el
própio PHP MyAdmin podemos entrar. Otra cosa más a añadir es que
tampoco podemos entrar desde una teminal remota, quizá, al usar
DOCKER, PHP MyAdmin puede acceder y nosotros no, pero no estamos seguros

2. Al pasar un HTTP Query con un parámetro formateado como JSON, tenemos un
problema, pues el formato contiene caracteres incorrectos.
"The valid characters are defined in RFC 7230 and RFC 3986"
Hemos estado investigando, y resulta que TOMCAT 9 no permite
usar "[" y "]" en las HTTP Queries, por lo que probamos a usar
tomcat 8, cosa que terminó en muchos más errores, por lo que
hemos revertido el cambio.
Pensamos que podriamos pasarlo todo a CSV, pero tendríamos que
cambiarlo todo y eliminar el parseo a JSON realizado en Js.

Tenemos en mente terminar de corregir todos estos errores la primera semana de junio, cuando terminemos todos los examenes