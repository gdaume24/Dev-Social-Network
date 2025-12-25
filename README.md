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

## Comment rendre le repository privé

Pour rendre ce repository privé sur GitHub, suivez ces étapes :

1. Allez sur la page principale du repository sur GitHub
2. Cliquez sur **Settings** (Paramètres) dans le menu du repository
3. Faites défiler vers le bas jusqu'à la section **Danger Zone** (Zone de danger)
4. Cliquez sur **Change visibility** (Changer la visibilité)
5. Sélectionnez **Make private** (Rendre privé)
6. Lisez attentivement l'avertissement sur les implications de rendre le repository privé
7. Tapez le nom du repository pour confirmer
8. Cliquez sur **I understand, make this repository private** (Je comprends, rendre ce repository privé)

**Note importante :** Seuls les propriétaires du repository peuvent modifier sa visibilité. Si vous n'êtes pas le propriétaire, vous devrez demander au propriétaire de faire ce changement.
