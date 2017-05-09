import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WindowApplication extends JFrame
{
	public static void main (String argv [])
	{
        new WindowApplication("Window Application");
	}

    public WindowApplication(String title)
    {
    	super(title);		// call constructor of base class
    	setSize(350, 150);
    	addWindowListener(new WindowDestroyer());
    	setVisible(true);
    }

	private class WindowDestroyer extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
}
