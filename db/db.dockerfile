FROM mysql:8.4.3

# Copier les fichiers de configuration
# COPY ./mycustom.cnf /etc/mysql/conf.d/custom.cnf
# COPY ./script.sql /docker-entrypoint-initdb.d/

EXPOSE 3306

