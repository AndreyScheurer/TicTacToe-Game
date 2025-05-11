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


## 🛠️ Key Skills & Tools

| Category               | Description                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| 💻 Programming Language | Java 21                                                                     |
| 🎨 GUI Development      | JavaFX (FXML, Controllers, Scene Switching)                                 |
| 🖥️ CLI Development      | Console-based game with user input handling                                 |
| ⚙️ Build Tool           | Maven (`pom.xml`, plugins, dependency management)                           |
| 🔧 Project Structure     | Java package naming, modular structure, clean code                         |
| 📦 Execution            | Run via IntelliJ or terminal (Bash / PowerShell)                            |
| 🔁 Git & GitHub         | Commit, push, pull, force push, branches, `.gitignore`, remote handling     |
| 📝 Documentation        | `README.md` with setup instructions, screenshots, and run options           |
| 🧠 Platform Awareness   | Shell compatibility (Bash vs. PowerShell), Maven run configuration          |

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
