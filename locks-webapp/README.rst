#######################################################
       REST "TheatreBooking" WebService dojo - 2015
#######################################################


**********************
    Prerequisites
**********************

Maven >2 (tested with 3.2.3)

JDK > 7 (tested with Oracle 1.8.0.0_45-b14)

**********************
    Initial status
**********************

running "mvn jetty:run" in the locks-webapp directory will start web service (first launch : requires internet access for dependencies retrieval by maven)

Invoke the web service using curl (or best : using POSTMAN chrome extension):

::

 >> curl http://localhost:8080/test/rest/v1/DemoService/alpha?paramtwo=beta

 <?xml version="1.0" encoding="UTF-8" standalone="yes"?><demoObject name="alpha_withOption_beta"/>
 


***************
    Stories
***************

Dans le cadre d'un système de réservation de places de spectacles, on veut réaliser l'un des micro-services du système : celui en charge de bloquer les places pendant la durée de la phase d'achat pour éviter de vendre plusieurs fois la même place.

Un verrou porte sur une place d'un spectacle.


Story 1
=======

Sur requête PUT d'un verrou sur une place (.../shows/WestSideStory-Paris-20170906-2030/seatlocks/K9?owner=me),
le service renvoie le détail d'un verrou, contenant un id unique.

    Rappel : PUT est idempotent (doit être testé)



Story 2.a
=========

sur requête GET d'un verrou sur une place (.../shows/WestSideStory-Paris-20170906-2030/seatlocks/K9), le service renvoie le détail du verrou existant, ou 404 si pas de verrou créé sur la place.


Story 2.b:
==========

Sur requête GET des verrous d'un spectacle (.../shows/WestSideStory-Paris-20170906-2030/seatlocks), on obtient le détail de tous les verrous du spectacle, ou 404 si pas de verrous sur le spectacle


Story 3
=======

sur requête PUT d'un verrou sur une place déja verrouillee, avec un owner différent de celui du verrou existant, le service rejette la demande



Story 4
=======

Le verrou renvoyé par le service à la création contient une date de création et une date d'expiration (30 minutes plus tard).



Story 5
=======

Story 5 : Sur requête PUT ou GET d'un verrou existant, au delà de la date d'expiration du verrou, le comportement du serveur est le même que si la verrou n'avait jamais existé.



Story 6
=======

Les représentations de verrous retournées sur requête PUT ou GET d'un spectacle contiennent un champ « signature » calculé par le serveur, permettant de vérifier l'authenticité du verrou grâce à une clé publique associée au service.  

Les données signées sont une représentation textuelle du topic, du propriétaire et de la date courante d'expiration, sous la forme : 
«TOPIC_ !_OWNER_ !_AAAAMMJJHHmmss.mse »


    Nota : Cette signature est calculée et vérifiable par des fonctions publiques (fournies avec le futur produit)


Story 7
=======

		Le service peut répondre de façon nominale à 8 requêtes PUT concernant des verrous de topics différents arrivant au service dans un intervalle d'1 seconde. Le temps de réponse pour chacune des requêtes traitées nominalement ne doit pas excéder 3 secondes.







Story 8
=======

Si le service reçoit 1000 requêtes PUT dans un intervalle de 1 seconde, il doit répondre nominalement à 10 d'entre elles, au moins et indiquer sa surcharge aux autres requêtes avec une invitation a réessayer plus tard.
