import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.*;

public class WindowApplication
{
	public static void main (String argv [])
	{
		JFrame frame = new JFrame("Window Application");
		frame.setSize(350, 150);
        frame.addWindowListener(new WindowDestroyer());
		frame.setVisible(true);
	}
}

class WindowDestroyer implements WindowListener
{
	public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
}
