#######################################################
       REST "Locks" WebService dojo - 2015
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

 >> curl http://localhost:8080/test/rest/v1/DemoService/bob?paramtwo=helloworld
 <?xml version="1.0" encoding="UTF-8" standalone="yes"?><demoObject name="bob_withOption_helloworld"/>



***************
    Stories
***************

Story 1
=======

Sur requête PUT d'un verrou (.../Locks/<topic>?owner=me), le service renvoie le détail d'un verrou, contenant un id unique.

    Rappel : PUT est idempotent










Story 2
=======

sur requête GET d'un verrou sur un sujet, le service renvoie le détail du verrou existant, ou 404 si pas de verrou créé sur le sujet.














Story 3
=======

sur requête PUT d'un verrou sur un sujet existant, avec un owner différent de celui du verrou, le service rejette la demande














Story 4
=======

Le verrou renvoyé par le service à la création contient une date de création et une date d'expiration.














Story 5
=======

Story 5 : Sur requête PUT ou GET d'un verrou existant, au delà de la date d'expiration du verrou, le comportement du serveur est le même que si la verrou n'avait jamais existé.














Story 6
=======

Les représentations de verrous retournées sur requête PUT ou GET d'un topic contiennent un champ « signature » calculé par le serveur, permettant de vérifier l'authenticité du verrou grâce à une clé publique associée au service.  

Les données signées sont une représentation textuelle du topic, du propriétaire et de la date courante d'expiration, sous la forme : 
«TOPIC_ !_OWNER_ !_AAAAMMJJHHmmss.mse »


    Nota : Cette signature est calculée et vérifiable par des fonctions publiques (fournies dans le  CD.)














Story 2
=======

=> Story 7 : Le service peut répondre de façon nominale à 8 requêtes PUT concernant des verrous de topics différents arrivant au service dans un intervalle d'1 seconde. Le temps de réponse pour chacune des requêtes traitées nominalement ne doit pas excéder 10 secondes.














Story 2
=======

Story 8 : Si le service reçoit 1000 requêtes PUT dans un intervalle de 1 seconde, il doit répondre nominalement à 8 d'entre elles, et indiquer sa surcharge aux autres requêtes.
