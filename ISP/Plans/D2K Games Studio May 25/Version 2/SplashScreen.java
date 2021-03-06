import javax.swing.*;
import java.awt.*;
/**
 * The screen that displays a animation of a lazer hitting mirrors, displaying the
 * company logo
 * 
 * @author Calvin Chan, JunHee Cho
 * @version 1 May 18th, 2012
 */
public class SplashScreen extends JPanel
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * The JLabel to put the animation images on
   */
  private JLabel animation;
  /**
   * Adds a jlabel taking up the entire jpanel.
   */
  public SplashScreen()
  {
    animation=new JLabel(new ImageIcon("images/splash/test0001.gif"));
    animation.setPreferredSize(new Dimension(640,500));
    add(animation);
  }
  /**
   * Runs through images to simulate a animated image
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>zeros                 String               stores the zeros that will be added to the file name</PRE>A
   */
  public synchronized void run()
  {
    for(int x=1;x<=216;x++)
    {
      String zeros="000";
      for(int y=1; y<Integer.toString(x).length();y++)
      {
        zeros=zeros.substring(1);
      }
      animation.setIcon(new ImageIcon("images/splash/test"+zeros+x+".gif"));
      try{
        Thread.sleep(10);
      }
      catch(InterruptedException ie)
      {
      }
    }
    try{
      Thread.sleep(2000);
    }
    catch(InterruptedException ie)
    {
    }
  }
}