# Concept

# Source d'information

On a besoin de:
- poster
- trailer
- informations textuelles à propos du film
  - titre
  - acteurs
  - synopsis
  - dates
  - des rankings venant de je ne sais-ou
  - ...

Il faut évaluer quelles sources conviendrait le mieux à nos besoins.

## themoviedb.org
Je crois que c'est le meilleur

## omdbapi.com
- Va chercher les poster sur l'api IMDb

## myapifilms.com
- Pas compris, est-ce une metaAPI?

## imdb.com
- ne fournit pas d'API officielle...

## trailleraddict.com
- Pour obtenir les trailer des films
- Il y a une API

<hr>

# Première exécution
Une marche à suivre est montrée à l'utilisateur pour qu'il saisisse les types
de films qu'il aime. Il devra saisir les même champs que dans les réglages.  

<hr>

## Page principale

Il y a 3 onglets:

- Seen
- Suggestion
- To See

### Onglet SEEN
C'est uniquement une liste des films vus. Il est possible de la trier selon différents critères (ranking, date de sortie, acteurs, titre, durée, ...)

#### List item

#### entrée dans la liste "Seen"

#### sortie de la liste "Seen"

<hr>

### Onglet SUGGESTION
Option 1 : De manière un peu ennuyante, on aurait une liste des films.
Option 2 : Faire la liste Tinder style

On pourrait mettre en grisé les films qui vont sortir dans quelques temps.

#### List item
Quel est le contenu?

#### entrée dans la liste "Suggestion"
Les propositions se font sur la base:
- des settings enregistrées par l'utilisateur (types de films favoris, années, acteurs, etc.)
- des films de l'onglet "Seen"
- des films de l'onglet "To See"
- des films à l'affiche en ce moment.

#### sortie de la liste "Suggestion"
- l'utilisateur valide le film et il passe dans la liste "To See"
- l'utilisateur enlève le film et ce dernier est supprimé (Il va dans une liste de film supprimés)

<hr>

### Onglet "TO SEE"
C'est la liste des films à voir.

#### List item
Quel est le contenu?

#### entrée dans la liste "To See"
- viens des films validés depuis la liste "Suggestion"

#### sortie de la liste "To See"
- l'utilisateur clique sur un bouton "Seen". il lui est alors demandé de mettre une note et un commentaire (max 140 caractère, comme un tweet) au film. Il peut également partager son impression sur les réseaux sociaux si il le souhaite.

<hr>

## Les listes de films

### Critères de tri standards

Selon:
- ranking
- date de sortie
- acteurs
- titre
- durée
- ...

### Critères de tri personnalisés
Pour la liste des films à voir, il faudrait éventuellement faire une liste
avec un tri fait par l'utilisateur; un drag'n'drop pour la liste des films à voir.
Comme ça, l'utilisateur pourrait faire une sorte de ranking subjectif des films qu'il veut voir.

<hr>

## Fiche d'un film

### Contenu

- affiche
- titre
- année
- genre


<hr>

## Notifications

### Notification "Hey watch a film dude!"
Si le téléphone de l'utilisateur ne bouge pas et qu'il à sont domicile, on pourrait lui proposer de regarder un des films qu'il a
placé dans sa liste "To see".

### Notification "Film's out at the movie theatre!"
A la date de sortie d'un film suggéré, une notification est envoyée à l'utilisateur et celui-ci peut aller voir dans quelles
salles de cinémas il pourrait aller voir le film dans les environs de chez lui (donc il y aurait une géolocalisation pour ce point.)

### Notification "Film's out in DVD!"
A la date de sortie d'un film suggéré en DVD, une notification est envoyée à l'utilisateur.


## Idées
- [N] Les utilisateurs peuvent ajouter des films eux-même à la BD?

