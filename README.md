# PIS Projekt
This game is a Bullet-Hell game in the style of Windows XP.
The player starts the game via Internet Explorer and is then confronted 
with a multitude of viruses, bugs and glitches, and scammers that have gained 
access to your computer. The challenge is to protect the system from this 
digital invasion.

![Start_Screenhot.jpg](res%2Fimg%2FStart_Screenhot.jpg)
![Playing_Screenshot.jpg](res%2Fimg%2FPlaying_Screenshot.jpg)

## Libraries
- [Processing](https://processing.org/)
  - [Sound](https://processing.org/reference/libraries/sound/index.html)
- [ControlP5](https://www.sojamo.de/libraries/controlP5/)
- [Jsyn](https://github.com/philburk/jsyn/releases)

## Usage
Copy the repository to your computer and execute the "src/Main.java" file. \
WARNING: Lower your volume before you start the game

## Example
``` jshelllanguage
jshell -class-path .\out\production\PISProjekt
import Models.*
Model model = new Model()
model.startNewGame(100,100)
model.getPlayer().getsHit()
model.checkGameOver()
model.getState()

// $8 ==> GAME_OVER
```