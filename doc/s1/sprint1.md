1) The sprint goal:
	The goal of this sprint was to create a GUI that displays the board and allows the game to run properly. The GUI would then be a base for us to code the rest of our goal.

	The goal is also to implement the four design patterns in user stories 1.1 to 1.4. We expect to have most of the functions associated with these design patterns to run properly as well.


2) All stories for this sprint and any decisions to add/delete stories:
	1.1 Graphical User Interface, continue to implement.
	1.2 Different Move Behaviours, completed.
	1.3 Timer, continue to implement
	1.4 Logs, completed.


3) A current assessment of team capacity (i.e. how much you expect to complete)

 We expect to finish most of what we have set off to do namely:
	Implementing the GUI
	Creating a functional move logging system
	Setting move logic according to conform with our implementation 
	Creating a functional timer to indicate the amount of time players have
	Adding ability to allow pieces to make a special move (e.g Making a diagonal move )
	

4) Participants in the sprint process (i.e. who did what)
	GUI (MVC) - Jahangir Minhas
	Logging System (Builder Pattern) - Junaid Khan
	Move Behaviour (Strategy Pattern) - Behrouz Akhbari
	Timer(Observer Pattern) - William Chik
	
	
 
5) A breakdown of tasks completed.

MVC - Classes worked on : ThreeMusketeers, MainView, GameScenePanel, MainController, MainApp, GameModePanel, ModePanelActionListener, SideSelectionActionListener, SubmitMoveActionListener.
	Worked on the main menu screen where all the game modes are displayed. Added action listeners for all the mode buttons and the load board button. Setup the main game screen where the board is displayed and created all the buttons (restart, undo, newGame, saveGame, createLog, saveLog).

Builder Pattern- Classes worked on: LogActionListener, ThreeMusketeers, GameScenePanel, MainView, LoadBoardActionListener
Worked on logic to create a functional Logging system which allows for users to track all moves that have been played thus far and also has the additional feature to allow users to access previous states of the board and thus effectively track and improve their gameplay further.

Strategy Pattern - Classes worked on: MoveBehaviour, MoveDiagonally, MoveNormally, Three Musketeers.
Made two different move behaviors, diagonal movement and normal movement. Used the strategy pattern to write clean and good quality code. Additionally debugged the move logic in ThreeMusketeers so that it works smoothly with the MVC pattern.

Observer Pattern - Classes worked on: Timer, Observer, Observable, ThreeMusketeers
Worked on creating a timer so humanagent would only have a certain amount of time to input a valid move or the game would make a random move for the player. The timer itself and the random move is working as intended but still needs to be integrated into the base GUI. 
