import javax.swing.*;
import java.awt.*;
/**
 * The help screen. Displays information on how to play the game and a button to get back to
 * the mainmenu screen.
 * @author Calvin Chan, JunHee Cho
 * @version 1 May 18th, 2012
 */
class Help extends JPanel
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
   * Sets up default settings for the screen, including transparency, size, and layout.
   * It adds a title image and a back button
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>temp                  JLabel               stores a JLabel</PRE>
   * <PRE>back                  JButton              stores a back button</PRE>
   * <PRE>backPanel             JPanel               stores the JPanel</PRE>
   */
  public Help(MainMenu parent)
  {
    this.parent=parent;
    setOpaque(false);
    setPreferredSize (new Dimension (640, 500));
    setLayout(new BorderLayout(5,5));
    JLabel temp=new JLabel("HELP");
    add(temp,BorderLayout.NORTH);
    JButton back=ButtonMaker.makeButton("back",parent);
    JPanel backPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    backPanel.setOpaque(false);
    backPanel.add(back);
    add(backPanel,BorderLayout.SOUTH);
  }
}