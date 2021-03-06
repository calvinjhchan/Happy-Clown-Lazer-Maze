import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
/**
 * The MainMenu screen. Holds all other screens and can switch between them.
 * @author Calvin Chan, JunHee Cho
 * @version 3 May 31st, 2012
 * 
 * version 3 changes - added game screen and edited starting image to logo. Also added TimerControl
 */
class MainMenu extends JPanel implements ActionListener
{
  /**
   * Access to the gameScreen;
   */
  private GameScreen gameScreen;
  /**
   * The Jpanel that is set to CardLayout to hold all screens.
   */
  private JPanel card;
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * The background image.
   */
  private BufferedImage image;
  /**
   * The controller for all actionevents
   */
  private ActionController con;
  /**
   * sets image as the background image, and sets up the jpanel and screens.
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>mainMenuPanel         JPanel               stores the panel of the MainMenu </PRE>
   * <PRE>title                 JLabel               stores the JLabel of the title</PRE>
   */
  public MainMenu ()
  {
    try {                
      image = ImageIO.read(new File("images/logo/logo2.PNG"));
    } catch (IOException ex) {
      System.out.println("Error");
    }
    card = new JPanel (new CardLayout ());
    add (card);
    card.setOpaque(false);
    JPanel mainMenuPanel = new JPanel ();
    mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel,BoxLayout.PAGE_AXIS));
    mainMenuPanel.setOpaque(false);
    JLabel title=new JLabel(new ImageIcon("images/title/title.png"));
    mainMenuPanel.add(title);
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainMenuPanel.add(ButtonMaker.makeButton("play",this));
    mainMenuPanel.add (ButtonMaker.makeButton("help",this));
    mainMenuPanel.add(ButtonMaker.makeButton("exit",this));
    card.add (mainMenuPanel, "MainMenu");
    card.add(new LevelSelect(this),"LevelSelect");
    card.add(new Help(this),"Help");
    gameScreen=new GameScreen(this);
    card.add(gameScreen,"GameScreen");
    con=new ActionController(this,gameScreen);
  }
  /**
   * Sends the ActionController the ActionEvent
   * @param ae Contains information about the event in which the button was pressed
   */
  public void actionPerformed(ActionEvent ae)
  {
    con.actionPerformed(ae);
  }
  /**
   * Paints the background image.
   * @param g the Graphics object to draw on
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
  }
  
  /**
   * Allows the screens to be switched
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>temp                  CardLayout           stores an instance of CardLayout</PRE>
   * @param screen the screen to switch to
   */
  public void switchScreens (String screen)
  {
    CardLayout temp = (CardLayout) (card.getLayout ());
    temp.show (card, screen);
  }
  /**
   * Tells the gameScreen to control the timer
   * @param x integer controlling the timer (0 to restart, 1 to continue, 2 to pause, 3 to stop and set to 0)
   */
  public void timerControl(int x)
  {
    gameScreen.timerControl(x);
  }
}
