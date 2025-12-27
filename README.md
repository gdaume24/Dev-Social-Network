🚀 Projet Full Stack Java/Angular
Une application robuste utilisant Java 23, Spring Boot et Angular 19.

🛠 Tech Stack
Frontend: Angular 19, Tailwind CSS

Backend: Java 23, Spring Boot, Spring Security (JWT)

Infrastructure: Docker, MySQL

⚙️ Prérequis
Avant de commencer, assurez-vous d'avoir :

Java 23 & Maven

Angular CLI 19

Docker Desktop

🏁 Lancement Rapide
1. Base de données (Docker)
Allez dans le dossier db/.

Créez un fichier .env (basé sur .env.example).

Lancez la commande : db/rebuild.bat (ou docker-compose up -d).

2. Backend (Spring Boot)
Configurez votre .env dans le dossier back/ (Variables JDBC & JWT).

Lancez Application.java depuis votre IDE ou via ./mvnw spring-boot:run.

3. Frontend (Angular)
Bash

cd front
npm install
ng serve
Accédez à l'application sur : http://localhost:4200


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
  MYSQL_URL=votre_url_de_base_de_données
  MYSQL_USERNAME=votre_username_de_base_de_données
  MYSQL_PASSWORD=votre_password_de_base_de_données
  SECURITY_JWT_SECRET_KEY=votre_jwt_secret_key
  SECURITY_JWT_EXPIRATION_TIME=votre_security_jwt_expiration_time
  ```
- Lancer le back en allant chercher le fichier de lancement de l'application, clic droit et debug Java sur
  -> back\src\main\java\com\network\Application.java

Lancement du front :

```
cd front
npm i
ng serve
```

Allez sur l'URL fournie dans le terminal et profitez du spectacle
