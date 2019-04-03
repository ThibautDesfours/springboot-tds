http://127.0.0.1:8081 : écran de connexion


2 utilisateurs :
id : 21703420	mdp : tibo
id : jcheron	mdp : prof

Si l'identifiant et le mot de passe sont corrects -> redirection vers /logError/
Sinon -> redirection vers /scripts/ et vers l'index

index :
	- Indentité de l'utilisateur, son indentifiant et son adresse email
	- Ses scripts avec possibilité de les modifier, de les consulter pleinement et de les supprimer

Ajout de script : formulaire complet d'ajout d'un script

Consultation de script :  
	- Consultation de tous les scripts avec possibilité de les consulter pleinement
	- Si l'utilisateur est l'auteur du script alors il a la possibilité de supprimer et de modifier le script

Possibilté de retourner à l'index via l'onglet au nom de l'utilisateur à droite

Possibilité de se déconnecter via le bouton Logout