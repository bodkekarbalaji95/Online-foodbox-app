FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=foodbox
# Expose the MySQL port
EXPOSE 3306

COPY mysql_db.sql /docker-entrypoint-initdb.d/

