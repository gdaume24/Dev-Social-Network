Pour lancer l'application en mode dev vous aurez besoin de Java 23, Angular cli 19 et Docker.

Voici les étapes de lancement :

- Cloner le repository

Lancement de la base de données :
- Lancer docker
- Ecrire un fichier .env dans le dossier db, le fichier .env doit contenir les informations suivantes pour la configuration de la base de données par le compose.yml :
  ```
  MYSQL_USERNAME=votre_username
  MYSQL_ROOT_PASSWORD=votre_root_password
  MYSQL_PASSWORD=votre_password
  ```
  
- Entrer la commande suivante dans la racine du répertoire : db/rebuild.bat

Lancement du back :
- Créer un autre fichier .env dans le dossier back, en définissant les variables suivantes selon vos informations personnelles :
  ```
  MYSQL_URL=votre_url
  MYSQL_USERNAME=votre_username
  MYSQL_PASSWORD=votre_password
  SECURITY_JWT_SECRET_KEY=votre_jwt_secret_key
  SECURITY_JWT_EXPIRATION_TIME=votre_security_jwt_expiration_time
  ```
  
- Lancer le back en allant chercher le fichier de lancement de l'application, clic droit et debug Java sur
-> back\src\main\java\com\network\Application.java

Lancement du front :
```
- cd front
- npm i
- ng serve
```
