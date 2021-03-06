import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import javax.imageio.stream.*;
/**
 * The screen that displays a animation of a lazer hitting mirrors, displaying the
 * company logo
 * 
 * @author Calvin Chan, JunHee Cho
 * @version 3 May 31st, 2012
 * 
 * version 3 changes - compatible with aniamted gifs.
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
    animation=new JLabel(new ImageIcon("images/logo/logo2.png"));
    animation.setPreferredSize(new Dimension(700,550));
    add(animation);
  }
  /**
   * Runs through images to simulate a animated image
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>zeros                 String               stores the zeros that will be added to the file name</PRE>A
   *//*
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
   }*/
  /**
   * Opens a gif and runs it.
   */
  public void run()
  {
    try
    {
      FileInputStream inputStream = new FileInputStream("images/splash/test.gif");
      Iterator readers = ImageIO.getImageReadersBySuffix("gif");
      ImageReader imageReader = (ImageReader) readers.next();
      ImageInputStream imageInputStream = ImageIO.createImageInputStream(inputStream);
      imageReader.setInput(imageInputStream, false);
      for (int i = 0;; ++i) {
        try
        {
          animation.setIcon(new ImageIcon(imageReader.read(i)));
          Thread.sleep(10);
        }
        catch(IndexOutOfBoundsException ioobe)
        {
          break;
        }
        catch(InterruptedException ie)
        {
        }
      }
      inputStream.close();
      try{
        Thread.sleep(2000);
      }
      catch(InterruptedException ie)
      {
      }
    }
    catch(FileNotFoundException fnfe)
    {
    }
    catch(IOException ioe)
    {
    }
  }
}