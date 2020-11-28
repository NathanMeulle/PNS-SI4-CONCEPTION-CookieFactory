# The Cookie Factory
### SI4 Projet
The goal of this project is to create a cookie factory 🏭

![Java CI with Maven](https://github.com/PNS-Conception/cookiefactory-20-21-team-l/workflows/Java%20CI%20with%20Maven/badge.svg)

<img src="https://github.com/PNS-Conception/cookiefactory-20-21-team-l/blob/master/cookie-picture.jpg" width="200">

## Plan
- [Presentation](#Presentation)
- [Fonctionnalités livrées](#Fonctionnalités-livrées)
- [Team](#Team)
- [How to contribute](#How-to-contribute)
- [SonarQube](#SonarQube)

## Presentation

This project allows customers to order cookies online from a variety of recipes and then pick them up at the store of their choice 🍪

![](https://img.shields.io/badge/Code-Java-informational?style=flat&logo=java&logoColor=white&color=4581E5)
![](https://img.shields.io/badge/Tools-Cucumber-informational?style=flat&logo=Cucumber&logoColor=white&color=4581E5)
![](https://img.shields.io/badge/Tools-SonarQube-informational?style=flat&logo=sonarqube&logoColor=white&color=4581E5)
![](https://img.shields.io/badge/Tools-Docker-informational?style=flat&logo=docker&logoColor=white&color=4581E5)
![](https://img.shields.io/badge/Tools-Maven-informational?style=flat&logo=maven&logoColor=white&color=4581E5)
![](https://img.shields.io/badge/Editor-IntelliJ_IDEA-informational?style=flat&logo=intellij-idea&logoColor=white&color=4581E5)


## Fonctionnalités livrées

| User Story | Fichier Feature | Scénarios |
| --- | --- | --- |
| <ul><li>En tant que manager </li><li>Je veux créer une nouvelle recette de cookie</li><li>Afin de proposer une nouvelle recette sur l’ensemble de la chaine </li></ul>| CreateRecipe.feature |  <ul><li>No Cookie by default</li><li>Adding Cookie</li></ul> |
| <ul><li>En tant que client (anonyme) </li><li>Je veux commander un cookie</li><li>Afin de réserver mes cookies </li></ul> |<ul><li> IntegrationOrdering.feature</li><li>AddingToOrder.feature</li></ul> | <ul><li>No cookie by default in the anonymous order</li><li>add a choco cookie in the anonymous order</li><li>No cookie by default in the order</li><li>add a choco cookie in the order</li><li>Anonymous makes an order</li><li>Anonymous can't make an order because out of stock</li><li>Client makes an order</li><li>Client can't make an order because out of stock</li></ul> |
|<ul><li>En tant que client anonyme</li><li>Je veux adhérer</li><li>Afin de comptabiliser mes cookies et être identifié</li></ul>| BecomeMember.feature | <ul><li>He is anonymous and wants to register</li><li>He wants to register again while being a member</li><li>An other client can't register with same information</li></ul> |
| <ul><li>En tant que cook </li><li>Je veux enregistrer mes nouveaux stocks</li><li>Afin de remplir ma cuisine</li></ul>| ManageStock.feature | <ul><li>By default, no ingredients in stock</li><li>Adding Ingredient with its number</li></ul> |
| <ul><li>En tant que cook </li><li>Je veux m’assurer que la commande est réalisable </li><li>Afin d’anticiper les ruptures de stock et ainsi réaliser la commande </li></ul>| MakeOrder.feature | <ul><li>Make an order with enough ingredients</li><li>Make an order without enough ingredients</li></ul> |
| <ul><li>En tant que client</li><li>Je veux récupérer ma commande</li><li>Afin d'obtenir mes cookies </li></ul>| PickUpOrderfeature | <ul><li>pick up an order already cooked</li><li>can't pick up if not paid</li><li>pick up an order not cooked yet</li></ul> |
| <ul><li>En tant que manager</li><li>Je veux accéder aux horaires de mon magasin</li><li>Afin de modifier les horaires d'ouverture/fermeture</li></ul>| ChangeSchedule.feature | <ul><li>Change Openning Hours</li><li>Add a day off</li></ul> |
| <ul><li>En tant que client adhérant</li><li>Je veux adhérer au Loyalty Program</li><li>Afin de bénéficier de 10% sur mes commandes</li></ul>| <ul><li>BecomeMember.feature</li><li>PayOrder.feature</li></ul> | <ul><li>He wants to subscribe to the loyalty program</li><li>A registered client wants to use the loyalty program : Cookie counter</li><li>A registered client wants to use the loyalty program : counter reinitialization</li><li>A registered client wants to use the loyalty program : discount</li></ul> |
| <ul><li>En tant que client</li><li>Je veux créer mon cookie personnalisé</li><li>Afin de choisir les quantités d’ingrédient</li></ul>| <ul><li>CreatePersonalizedRecipe.feature</li><li>PayOrder.feature</li></ul> | <ul><li>Jhon create his recipe</li><li>Order a personnalized cookie</li></ul> |
| <ul><li>En tant que client </li><li>Je veux commander et choisir l’heure de récupération de mes cookies</li><li>Afin de récupérer ma commande à l’heure indiquée</li></ul>| CookieOnDemand.feature | <ul><li>the client wants to order and choose the pick up time : come on time</li><li>the client wants to order and choose the pick up time : come to early</li><li>the client wants to order and choose a wrong pick up time : store closed</li><li>the client wants to order and choose a pick up time</li></ul> |
| <ul><li>En tant que manager</li><li>Je veux faire des stats sur les cookies commandés ces 30 derniers jours</li><li>Afin de déterminer quel est le Best Of Cookie du mois</li></ul>| PayOrder.feature | <ul><li>Order with a best of store cookie</li></ul> |
| <ul><li>En tant que PDG</li><li>Je veux faire des stats sur les BOC de ces 30 derniers jours</li><li>Afin de déterminer quel est le cookie national</li></ul>| PayOrder.feature | <ul><li>Order with a best of national cookie</li></ul> |
| <ul><li>En tant que client</li><li>Je veux choisir un cookie</li><li>Afin d'en modifier les ingrédients présents </li></ul>| CreatePersonalizedRecipe.feature | <ul><li>Jhon modify a cookie : adding an ingredient</li><li>Jhon modify a cookie : removing an ingredient</li><li>Jhon modify a cookie : non valid</li></ul> |
| <ul><li>En tant que store</li><li>Je veux demander à MarcelEat une livraison</li><li>Afin de livrer mon client à l'adresse souhaitée</li></ul>| NormalDelivery.feature | <ul><li>Sucessful delivery</li></ul> |
| <ul><li>En tant que client</li><li>Je veux sélectionner un autre magasin à proximité de mon choix initial</li><li>Afin de passer commande malgré l'indisponibilité des cookies (plus de stocks) dans mon magasin</li></ul>| KitchenBalancing.feature | <ul><li>Client can't make an order because out of stock but when he change store then his order is done</li></ul> |


## Team
Team ID : L
- [Meulle Nathan](https://github.com/NathanMeulle)
- [Delmotte Vincent](https://github.com/Delmotte-Vincent)
- [Esteve Thibaut](https://github.com/Thibaut-Esteve)
- [Dadiouari Asaréel](https://github.com/AsareelDadiouari)
- [DONTO Komlan Lemuel](https://github.com/LemuelTKF)


## How to contribute
To participate in this project, please have a look at our [contributing.md](https://github.com/PNS-Conception/cookiefactory-20-21-team-l/blob/master/CONTRIBUTING.md) 👀

## SonarQube
In order to provide a quality code, we use SonarQube.

To launch it, here is the command : 

```mvn clean package -Pcoverage sonar:sonar```


##
![Polytech_logo](http://unice.fr/polytechnice/fr/contenus-riches/images/logos/logo-uns-pns)
