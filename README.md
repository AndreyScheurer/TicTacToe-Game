# 🎮 Tic-Tac-Toe Game (QuadStrike) - JavaFX Projekt

A JavaFX-based TicTacToe game with a dynamically growing board and clean Maven structure.  
Originally developed as a learning project, now shared on GitHub to showcase my development skills and project structure understanding.

---

## 🧠 Features

- GUI-based and console-based game modes
- Auto-growing board (starts at 1x1 and expands)
- Win condition: four matching symbols in a row (horizontal, vertical, diagonal)
- Modular codebase (Board, Controller, Logic, GUI)
- Built with Maven – clean and portable project structure

---

## 🎮 Gameplay

- Player 1 = X, Player 2 = O
- The game starts on a 1x1 board
- Each move may trigger the board to expand
- First player to align 4 symbols wins


<img src="img/JavaFX4.png" width="700" alt="fig1"/>


---

ConsoleApp:

WELCOME TO THE QUADSTRIKE APP
=============================

Enter name of Player 1: Andrey
Enter name of Player 2: Gregor

+–––+
|   | 0
+–––+
  0 

Andrey > 0/0
+–––+–––+–––+
|   |   |   | 1
+–––+–––+–––+
|   | X |   | 0
+–––+–––+–––+
|   |   |   | -1
+–––+–––+–––+
 -1   0   1 

Gregor > 1/0
+–––+–––+–––+–––+
|   |   |   |   | 1
+–––+–––+–––+–––+
|   | X | O |   | 0
+–––+–––+–––+–––+
|   |   |   |   | -1
+–––+–––+–––+–––+
 -1   0   1   2 

Andrey > 0/-1
+–––+–––+–––+–––+
|   |   |   |   | 1
+–––+–––+–––+–––+
|   | X | O |   | 0
+–––+–––+–––+–––+
|   | X |   |   | -1
+–––+–––+–––+–––+
|   |   |   |   | -2
+–––+–––+–––+–––+
 -1   0   1   2 

Gregor > 

---

## 🛠️ Technologies Used

| Technology / Tool | Purpose                          |
|-------------------|----------------------------------|
| Java 21           | Core programming language        |
| JavaFX            | GUI framework                    |
| FXML              | Interface layout definition      |
| Maven             | Build and dependency management  |
| Git & GitHub      | Version control and collaboration |

---

## 🚀 How to Run Locally (with Maven)

### ✅ Requirements

- Java 21 or higher
- Maven installed and configured

### 📦 Clone and Build the Project

```bash
git clone https://github.com/AndreyScheurer/TicTacToe-Game.git
cd TicTacToe-Game
mvn clean install
```

---

### 🖥️ Run Console Version
To play the game in the console (CLI) mode:

👉 Option 1: From IntelliJ
Run the following class manually:
src/main/java/ch/github/andreyscheurer/tictactoe/ConsoleApp.java


👉 Option 2: From Terminal (with Maven)
### 🔹 For Bash or Linux/macOS:
```bash
mvn exec:java -Dexec.mainClass="ch.github.andreyscheurer.tictactoe.ConsoleApp"
```
### 🔸 For PowerShell (Windows):
```bash
mvn exec:java "-Dexec.mainClass=ch.github.andreyscheurer.tictactoe.ConsoleApp"
```
---

### 🪟 Run GUI Version (JavaFX)
To play the game with a graphical user interface:

👉 Option 1: From IntelliJ
Run the following class manually: 
src/main/java/ch/github/andreyscheurer/tictactoe/JavaFXApp.java


👉 Option 2: From Terminal (with Maven)
```bash
mvn javafx:run
```
