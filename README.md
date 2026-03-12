# Wumpus World Game (Java)

## Overview

This project is a Java console implementation of the Wumpus World, a classic Artificial Intelligence environment where an agent explores a grid world, avoids hazards (pits and the Wumpus), and attempts to find the gold. The agent can also shoot an arrow to kill the Wumpus.

## Features

> 4×4 grid-based world
>  Random placement of Wumpus, pits, and gold
> Percepts system (stench, breeze, glitter)
> Keyboard-controlled agent movement
> Arrow shooting to kill the Wumpus
> Score tracking
> Hidden grid during gameplay and full grid revealed at game over

## Classes in the Project

## 1. WumpusGame

This class represents the game environment (world).

Responsibilities:

> Creates the grid world
> Randomly places gold, pits, and the Wumpus
> Tracks the agent position
> Generates percepts (stench, breeze, glitter)
> Handles agent movement
> Handles arrow shooting
> Updates score and game status
> Displays the game grid
## 2. WumpusAgent

This class represents the player-controlled agent.
## Responsibilities:

> Accepts keyboard input from the player
> Allows the player to move the agent
> Allows the player to shoot an arrow
> Displays the percepts received from the environment
> Controls the main game loop
 ## 3. Main

This class is the entry point of the program.

Responsibilities:

> Creates the game world
> Creates the agent
> Starts the game execution

## Controls
# Key       Action
 W          Move Up
 S          Move Down
 A          Move Left
 F          shoot Arrow
 Q          Quit Game

## Percepts
# Stench
 Wumpus is in a neighboring square.
# Breeze
 A pit is in a neighboring square
# Glitter
Gold is in the current square  

## Scoring System

# Event                              Score
 Move                                -1
 Kill wumpus                         +500
 Find Gold                           +1000
 Fall into pit / Eaten by Wumpus     -1000

## How to Run

Compile the program:

bash
javac WumpusGame.java

Run the program:

bash
java Wumpus.Main

Purpose

This project demonstrates important Artificial Intelligence concepts, including:

> Intelligent agents
> Environment perception
* Grid-based environments
* Decision making under uncertainty
