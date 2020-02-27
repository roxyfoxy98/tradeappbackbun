# Trading Platform Application

In order to configure the docker container with MySQL database, you need to do the following steps:

1. docker pull mysql/mysql-server:latest - pull  mysql/mysql-server image
2. docker run -p 3306:3306 -v /tmp:/tmp --name 'the name of the container' -e MYSQL_ROOT_PASSWORD='password of the root user' -e MYSQL_ROOT_HOST=% -e MYSQL_DATABASE='the name of the database' -d mysql/mysql-server - This command will create your container based on mysql/mysql-server image
3. docker ps - to view if the server is up 
4. docker logs 'the name of your container' - with this command you can check some information related to docker container
5. docker exec -it 'the name of your container' mysql -u root -p - this command is useful to connect to MySQL  environment ( -p required the password of your root user)
6. CREATE USER 'root'@'localhost' IDENTIFIED BY 'newpassword'; - you can create your own user using this command. If you do that, you need to grant all privileges for this user.
7. GRANT ALL PRIVILEGES ON database_name.* TO 'username'@'localhost';