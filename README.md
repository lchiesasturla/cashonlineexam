Cash online - Examen backend:

Motor de base de datos utilizado: MySQL

El repositorio esta compuesto de dos carpetas:

-examen
-scripts

En la carpeta de scripts se encontrara un script llamado createDB.sql, por lo que el primer paso debe ser ejecutar el script en un motor de MySQL. Este creara el esquema para la posterior creacion de tablas e insercion de datos por parte de la aplicacion.

Posteriormente, se debe importar el proyecto de la carpeta examen. Una vez importado se debe modificar el archivo application.properties en el path: "src/main/resources", lo que se debera modificar son las siguientes propiedades:

spring.datasource.username = (Aca se debe poner el usuario con el que accedan al motor de base de datos)
spring.datasource.password =(Aca se debe poner la contraseña con la que accedan al motor de base de datos)

Una vez modificado eso se debe hacer un build & clean, este creara las tablas e insertara 4 usuarios y 8 prestamos. Una vez hecho esto se debe deployar la aplicacion, la cual creara otros 5 usuarios y 8 prestamos mas. Ahi ya se pueden probar los endpoints solicitados.

GET - users/{id}
POST - users
DELETE - users/{id}
GET - loans?page={page}&size={size}&user_id={user_id} Este ultimo parametro opcional.

Un saludo y ante cualquier duda o problema se pueden contactar por lucaschiesa84@gmail.com
