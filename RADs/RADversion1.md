Requirements and Analysis Document for Group 25


  Version: 1.0

  Date: 2015-03-25

  Author: Malin Thelin, Oskar Rutqvist, Björn Bergqvist, Robin Janssson

This version overrides all previous versions.

1 Introduction

This section gives a brief overview of the project.

1.1 Purpose of application 

We are going to make the game Risk as a Java application. However, we will change it so that instead of taking over the world, the player(s) will take over Chalmers. 

1.2 General characteristics of application 
The application is a boardgame, but on your computer. The application will not use any networking. 

The game is turnbased and each player uses their turn to place troops on different territories. If a territory is already occupied by another player’s troops a fight will take place. Deciding the outcome of the fight is done by throwing five dice. Three are “thrown” by the attacker and two are used by the defender. The one who wins the fight is the one who ...

1.3 Scope of application 
We intend to implement the game as a two-player game. The idea is the two players can sit at the same computer and play against each other to conquer the world of Chalmers. The game won’t be playable alone unless the player decides to play against her-/himself. 

1.4 Objectives and success criteria of the project 
It should be possible to win the game by vanquishing your foes.
The project is deemed to be finished when we have a fully implemented Risk-type game, with the rules determined by the original. Two players should be able to play the game on the same computer, taking turns. One player wins when the other is out of troops and territories.

1.5 Definitions, acronyms and abbreviations 
Two-player: The application is not made for only one player nor more than two. E.g. it’s for exactly two players. 
Turnbased: If there are two players (or more) everyone gets their own turn to do objectives. While one player has their turn, other players are unable to do anything.

Boardgame: The application is a simulation of a table top boardgame. 

Java: A programming language that is independent on the platform. 

Chalmers: Objectively the best university in the world. 


JRE: Java Run time Environment, additional software required to run our application

2 Requirements

In this section we specify all requirements

2.1 Functional requirements

Create a list of high level functions here (from the use cases).
Start a new game
select a team (color)
Place your armies
take turns placing forces until there are no more forces to place by either player.
Take a turn
place your new forces (the forces you received at the beginning of this turn)
relocate forces (to adjacent territories) 
fight an enemy (if you relocated your forces to your enemy’s territory)
end turn
Win/Lose


2.2 Non-functional requirements 

2.2.1 Usability 
The interface should be clear and easy to understand. The game should be easy to understand and pick up. Included with the game is a readme file containing the game rules, and an explanation of the interface. 

2.2.2 Reliability 
NA

2.2.3 Performance
Max 2 sec response time, worst case. Performance is a minor issue for our project since its a turn based game and not real-time. 

2.2.4 Supportability
The game will be implemented in such a way that it will be easy to use different maps for the “world”. 

2.2.5 Implementation 
To ensure platform independence the application uses the Java Environment. To play the game on a computer it must therefore have JRE installed and configured, and of course, also the game.

2.2.6 Packaging and installation
NA

2.2.7 Legal 
NA

2.3 Application models

2.3.1 Use case model 

UML and a list of UC names (text for all in appendix)

2.3.2 Use cases priority


0, Start a game
1, Perform combat (roll dice)
2, Move troops
3, Place troops

2.3.3 Domain model

UML, text and pictures.

2.3.4 User interface 

Text to motivate a picture.

2.4 References


Risk board game: http://en.wikipedia.org/wiki/Risk_%28game%29

  APPENDIX 

  GUI

  Domain model

  Use case texts


