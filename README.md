# Wumpus World Game (Java)

# Overview

This project is a Java implementation of the classic Wumpus World game used in Artificial Intelligence to demonstrate **agent-based environments, percepts, and decision making**.

The player controls an agent navigating a grid world containing:

 A Wumpus (dangerous creature)
 Pits (deadly holes)
 Gold (the goal)

The agent must avoid hazards, use percepts to reason about the environment, and attempt to **find the gold**. The agent also has **one arrow** that can be used to kill the Wumpus.
# Features

Grid-based Wumpus World environment
Random placement of:

   Wumpus
   Pits
   Gold
# Percept system

stench → Wumpus nearby
breeze → Pit nearby
glitter → Gold in current cell
Keyboard-based agent movement
* **Arrow shooting system**
* Score tracking
* Hidden world during gameplay
* Full world revealed at game over

---

## Game Controls

| Key   | Action      |
| ----- | ----------- |
| **W** | Move Up     |
| **S** | Move Down   |
| **A** | Move Left   |
| **D** | Move Right  |
| **F** | Shoot Arrow |
| **Q** | Quit Game   |

When shooting an arrow, you must also choose a direction using **W/A/S/D**.

---

## Game Rules

1. The agent starts at position **(0,0)**.
2. The world is a **4 × 4 grid**.
3. The world contains:

   * **1 Wumpus**
   * **2 Pits**
   * **1 Gold**
4. If the agent:

   * Falls into a **pit** → Game Over
   * Meets the **Wumpus** → Game Over
   * Finds the **gold** → Player wins
5. The agent has **only one arrow** to kill the Wumpus.

---

## Percepts

The agent receives clues about nearby hazards:

| Percept     | Meaning                           |
| ----------- | --------------------------------- |
| **Stench**  | Wumpus is in a neighboring square |
| **Breeze**  | Pit is in a neighboring square    |
| **Glitter** | Gold is in the current square     |

Example output:

```
Percepts: {stench=true, breeze=false, glitter=false}
```

---

## Scoring System

| Action          | Score |
| --------------- | ----- |
| Move            | -1    |
| Kill Wumpus     | +500  |
| Find Gold       | +1000 |
| Fall into Pit   | -1000 |
| Eaten by Wumpus | -1000 |

---

## Project Structure

```
WumpusWorld/
│
├── WumpusGame.java
├── WumpusAgent.java
├── Main.java
└── README.md
```

### Classes

**WumpusGame**

* Creates the world grid
* Places hazards
* Handles movement
* Calculates percepts
* Handles arrow shooting

**WumpusAgent**

* Accepts player input
* Controls the agent actions
* Displays percepts

**Main**

* Starts the game

---

## How to Compile and Run

### 1. Compile

```bash
javac WumpusGame.java
```

### 2. Run

```bash
java Wumpus.Main
```

---

## Example Gameplay

```
WORLD GRID

A . . .
. . . .
. . . .
. . . .

Percepts: {stench=true, breeze=false, glitter=false}

Choose Move:
W = Up | S = Down | A = Left | D = Right
F = Shoot Arrow | Q = Quit

## Concepts Demonstrated

This project demonstrates important **Artificial Intelligence concepts**:

* Intelligent agents
* Environment perception
* Grid-world modeling
* Hazard detection
* Decision-based actions
* Game simulation

## Possible Improvements

Future improvements may include:

* Multiple arrows
* Smarter AI agent
* Arrow traveling across the grid
* Graphical user interface (GUI)
* Larger world sizes
* Multiple Wumpus creatures
* Pathfinding algorithms (A*)

