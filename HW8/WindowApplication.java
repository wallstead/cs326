import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;

public class WindowApplication extends JFrame
{
    protected DrawColor colorDrawn;
    protected MyColor[] colors = new MyColor[30]; // 30 is arbitrary
    protected int colorCount;
    protected JList listColors;
    protected MyColor currentColor;
    protected JTextField tfRed;
	protected JTextField tfGreen;
    protected JTextField tfBlue;
    protected JButton redUp;
	protected JButton redDown;
    protected JButton greenUp;
	protected JButton greenDown;
    protected JButton blueUp;
	protected JButton blueDown;


	public static void main (String argv [])
	{
        new WindowApplication("Color Sampler");
	}

    public WindowApplication(String title)
    {
        super(title);

        try {
            readFile("input.txt");
        } catch (IOException e) {
            // Do something here
            System.out.println("Failed to read in colors");
        }

		setBounds(100, 100, 350, 300);
		addWindowListener(new WindowDestroyer());

        colorDrawn = new DrawColor();
        MyColor firstColor = colors[0];
        currentColor = colors[0];
        colorDrawn.paintColor = new Color(firstColor.red, firstColor.green, firstColor.blue);

        JLabel redLabel = new JLabel("Red:");
        JLabel greenLabel = new JLabel("Green:");
        JLabel blueLabel = new JLabel("Blue:");
        tfRed = new JTextField(Integer.toString(firstColor.red));
        tfGreen = new JTextField(Integer.toString(firstColor.green));
        tfBlue = new JTextField(Integer.toString(firstColor.blue));
        redUp = new JButton("+");
        redDown = new JButton("-");
        greenUp = new JButton("+");
        greenDown = new JButton("-");
        blueUp = new JButton("+");
        blueDown = new JButton("-");

		getContentPane().setLayout(null); // row,col

		getContentPane().add(colorDrawn);
		getContentPane().add(listColors);
        getContentPane().add(redLabel);
        getContentPane().add(greenLabel);
        getContentPane().add(blueLabel);
        getContentPane().add(tfRed);
		getContentPane().add(tfGreen);
        getContentPane().add(tfBlue);
        getContentPane().add(redUp);
        getContentPane().add(redDown);
        getContentPane().add(greenUp);
        getContentPane().add(greenDown);
        getContentPane().add(blueUp);
        getContentPane().add(blueDown);

        colorDrawn.setBounds(10, 10, 220, 100);
        listColors.setBounds(10+220+10, 10, 100, 260);
        redLabel.setBounds(10, 115, 50, 25);
        greenLabel.setBounds(10, 140, 50, 25);
        blueLabel.setBounds(10, 165, 50, 25);
        tfRed.setBounds(60, 115, 50, 25);
        tfGreen.setBounds(60, 140, 50, 25);
        tfBlue.setBounds(60, 165, 50, 25);
        redDown.setBounds(110, 115, 65, 25);
        redUp.setBounds(170, 115, 65, 25);
        greenDown.setBounds(110, 140, 65, 25);
        greenUp.setBounds(170, 140, 65, 25);
        blueDown.setBounds(110, 165, 65, 25);
        blueUp.setBounds(170, 165, 65, 25);


		setVisible(true);
    }

    public void readFile(String filename) throws IOException
    {
        FileInputStream stream = new FileInputStream(filename);
        InputStreamReader reader = new InputStreamReader(stream);
        StreamTokenizer tokens = new StreamTokenizer(reader);
        String colorName;
        int	red;
        int	green;
        int	blue;

        int colorCounter = 0; // count of colors given

        while (tokens.nextToken() != tokens.TT_EOF)
        {
            colorName = (String) tokens.sval;
            tokens.nextToken();
            red = (int) tokens.nval;
            tokens.nextToken();
            green = (int) tokens.nval;
            tokens.nextToken();
            blue = (int) tokens.nval;

            MyColor color = new MyColor(colorName, red, green, blue);
            colors[colorCounter] = color; // will be at 0 index for first color
            colorCounter++;
            System.out.println(colorName + " " + red + " " + blue + " " + green);
        }

        colorCount = colorCounter;

        listColors = new JList();
		listColors.addListSelectionListener(new ListHandler());
		listColors.setListData(colors);

        stream.close();
    }

    private class ListHandler implements ListSelectionListener
	{
        public void valueChanged(ListSelectionEvent e)
        {
            if ( e.getSource() == listColors )
            {
                if ( !e.getValueIsAdjusting() )
                {
                    int i = listColors.getSelectedIndex();
                    MyColor chosenColor = (MyColor) listColors.getSelectedValue();
                    colorDrawn.paintColor = new Color(chosenColor.red, chosenColor.green, chosenColor.blue);
                    colorDrawn.repaint();
                    tfRed.setText(Integer.toString(chosenColor.red));
                    tfGreen.setText(Integer.toString(chosenColor.green));
                    tfBlue.setText(Integer.toString(chosenColor.blue));
                }
            }
        }
	}

    // private class ActionHandler implements ActionListener
	// {
	// 	public void actionPerformed(ActionEvent e)
	// 	{
	// 		if ( e.getSource() == buttonDone )
	// 		{
	// 			String s1 = tfFirstName.getText();
	// 			String s2 = tfLastName.getText();
	// 			System.out.print("Full name: " + s1 + " " + s2);
	// 		}
	// 		else if ( e.getSource() == buttonCancel )
	// 			System.out.print("You pressed the Cancel button.");
	// 	}
	// }

	private class WindowDestroyer extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}


}

class MyColor {
    String name;
    int	red;
    int	green;
    int	blue;

    public MyColor(String name, int red, int green, int blue) {
        this.name = name;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public String toString()
    {
        return name;
    }
}

class DrawColor extends JComponent
{
    public Color paintColor = Color.green; // default for debug purposes

	public void paint(Graphics g)
	{
		Dimension d = getSize();

		g.setColor(paintColor);
		g.fillRect(1, 1, d.width-2, d.height-2);
	}
}
