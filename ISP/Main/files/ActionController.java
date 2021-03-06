package files;
import java.awt.event.*;
import javax.swing.*;
/**
 * Controls all ActionEvents fired from all buttons in the program. It determines what happens
 * depending on what button was pressed
 * 
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 * 
 * <br>version 3 changes - Added more actions for buttons
 * <br>version 4 changes - added code for more buttons, added comfirmation(),getLevel() and setLevel()
 */
class ActionController implements ActionListener
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * A reference variable to allow this class to connect to the mainmenu program. (Run reference
   * variable requiring methods from it)
   */
  private MainMenu parent;
  /**
   * A reference variable to allow this class to coonec to the gamescreen program. Gives access to
   * control the pauseMenu.
   */
  private GameScreen gameScreen;
  /**
   * To remember what level the program is currently on.
   */
  private int level;
  /**
   * Contructor which keeps a reference variable of the mainmenu program where it was
   * created in and the gameScreen.
   * @param parent The mainmenu program which created this class. Needed to call methods from it.
   * @param gameScreen the GameScreen object connected to the mainMenu.
   */
  public ActionController(MainMenu parent,GameScreen gameScreen)
  {
    this.parent=parent;
    this.gameScreen=gameScreen;
  }
  /**
   * The method that is called when a button is pressed. It determines which button was 
   * pressed and what to do.
   * @param ae Contains information about the event in which the button was pressed
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>temp                  String               stores the action command (the button pressed)</PRE>
   */
  public void actionPerformed(ActionEvent ae)
  {
    String temp=ae.getActionCommand();
    try
    {
      if(temp.substring(0,6).equals("level/"))
      {
        level=Integer.parseInt(temp.substring(6));
        setLevel();
      }
    }
    catch(StringIndexOutOfBoundsException sioobe)
    {
    }
    if(temp.equals("exit"))
    {
      System.exit(0);
    }
    else if(temp.equals("print"))
    {
      parent.print();
    }
    else if(temp.equals("play"))
    {
      parent.switchScreens("LevelSelect");
    }
    else if(temp.equals("run"))
    {
      gameScreen.animate();
    }
    else if(temp.equals("help"))
    {
      parent.switchScreens("Help");
    }
    else if(temp.equals("pause"))
    {
      gameScreen.showPauseMenu();
      gameScreen.stopDrawing();
    }
    else if(temp.equals("credits"))
    {
      parent.switchScreens("Credits");
    }
    else if(temp.equals("resumegame"))
    {
      gameScreen.hidePauseMenu();
    }
    else if(temp.equals("highscores"))
    {
      parent.switchScreens("HighScores");
      parent.readScores();
      parent.unselect();
    }
    else if(temp.equals("nextlevel"))
    {
      level++;
      setLevel();
    }
    else if(temp.equals("restartlevel"))
    {
      gameScreen.hidePauseMenu();
      gameScreen.resetLevel();
      parent.timerControl(0);
    }
    else if(temp.equals("replay"))
    {
      parent.switchScreens("GameScreen");
      gameScreen.resetLevel();
      parent.timerControl(0);
    }
    else if(temp.equals("quittodesk"))
    {
      if(comfirmation())
        System.exit(0);
    }
    else if(temp.equals("clear"))
    {
      if(JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all highscores?", "WARNING", JOptionPane.YES_NO_OPTION)==0)
      {
        parent.switchScreens("MainMenu");
        parent.clearHighScores();
      }
    }
    else if(temp.equals("backtomain"))
    {
      parent.switchScreens("MainMenu");
    }
    else if(temp.equals("quittomain"))
    {
      if(comfirmation())
      {
        gameScreen.hidePauseMenu();
        parent.switchScreens("MainMenu");
      }
    }
    else
    {
      if(temp.equals("back"))
      {
        parent.switchScreens("MainMenu");
      }
    }
  }
  /**
   * Asks the user a question and returns the answer
   * @return the users answer to the question
   */
  public boolean comfirmation()
  {
    return JOptionPane.showConfirmDialog(null, "WARNING: All changes to the level will be lost", "Are you sure you want to stop playing?", JOptionPane.YES_NO_OPTION)==0;
  }
  /**
   * Returns the current level
   * @return the current level the program is on
   */
  public int getLevel()
  {
    return level;
  }
  /**
   * Sets the program to go to a certain level.
   */
  public void setLevel()
  {
    parent.switchScreens("GameScreen");
    if(level>18)
    {
      JOptionPane.showMessageDialog (null, "You've finished the game! Congratulations! You will be returned to level 1. \nTry beating your old times on all the levels!", "Congraulations!", JOptionPane.INFORMATION_MESSAGE);
      level=1;
    }
    gameScreen.readLevel(level);
    parent.timerControl(0);
  }
}