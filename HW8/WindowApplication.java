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
            System.out.println("Failed to read in colors: " + e);
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
        tfRed.setEditable(false); // project spec says only editable by buttons
        tfGreen = new JTextField(Integer.toString(firstColor.green));
        tfGreen.setEditable(false);
        tfBlue = new JTextField(Integer.toString(firstColor.blue));
        tfBlue.setEditable(false);
        redUp = new JButton("+");
        redUp.addActionListener(new ActionHandler());
        redDown = new JButton("-");
        redDown.addActionListener(new ActionHandler());
        greenUp = new JButton("+");
        greenUp.addActionListener(new ActionHandler());
        greenDown = new JButton("-");
        greenDown.addActionListener(new ActionHandler());
        blueUp = new JButton("+");
        blueUp.addActionListener(new ActionHandler());
        blueDown = new JButton("-");
        blueDown.addActionListener(new ActionHandler());

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
                    currentColor = chosenColor;
                    // System.out.println(currentColor);
                }
            }
        }
	}

    private class ActionHandler implements ActionListener
	{

        public boolean inRange(int val) {
            if (val <= 255 && val >= 0) {
                return true;
            } else {
                return false;
            }
        }

		public void actionPerformed(ActionEvent e)
		{
            /* rename window */
            setTitle("Color Sampler*");

			if ( e.getSource() == redUp )
			{
                int currentValue = Integer.parseInt(tfRed.getText());
                int requestedValue = currentValue+5;
                if (inRange(requestedValue)) {
                    tfRed.setText(Integer.toString(requestedValue));
                    currentColor.red = requestedValue;
                    colorDrawn.paintColor = new Color(currentColor.red, currentColor.green, currentColor.blue);
                    colorDrawn.repaint();
                }
			}
			else if ( e.getSource() == redDown ) {
                // System.out.print("You pressed the Cancel button.");
                int currentValue = Integer.parseInt(tfRed.getText());
                int requestedValue = currentValue-5;
                if (inRange(requestedValue)) {
                    tfRed.setText(Integer.toString(requestedValue));
                    currentColor.red = requestedValue;
                    colorDrawn.paintColor = new Color(currentColor.red, currentColor.green, currentColor.blue);
                    colorDrawn.repaint();
                }
            } else if ( e.getSource() == greenUp ) {
                // System.out.print("You pressed the Cancel button.");
                int currentValue = Integer.parseInt(tfGreen.getText());
                int requestedValue = currentValue+5;
                if (inRange(requestedValue)) {
                    tfGreen.setText(Integer.toString(requestedValue));
                    currentColor.green = requestedValue;
                    colorDrawn.paintColor = new Color(currentColor.red, currentColor.green, currentColor.blue);
                    colorDrawn.repaint();
                }
            } else if ( e.getSource() == greenDown ) {
                // System.out.print("You pressed the Cancel button.");
                int currentValue = Integer.parseInt(tfGreen.getText());
                int requestedValue = currentValue-5;
                if (inRange(requestedValue)) {
                    tfGreen.setText(Integer.toString(requestedValue));
                    currentColor.green = requestedValue;
                    colorDrawn.paintColor = new Color(currentColor.red, currentColor.green, currentColor.blue);
                    colorDrawn.repaint();
                }
            } else if ( e.getSource() == blueUp ) {
                // System.out.print("You pressed the Cancel button.");
                int currentValue = Integer.parseInt(tfBlue.getText());
                int requestedValue = currentValue+5;
                if (inRange(requestedValue)) {
                    tfBlue.setText(Integer.toString(requestedValue));
                    currentColor.blue = requestedValue;
                    colorDrawn.paintColor = new Color(currentColor.red, currentColor.green, currentColor.blue);
                    colorDrawn.repaint();
                }
            } else if ( e.getSource() == blueDown ) {
                // System.out.print("You pressed the Cancel button.");
                int currentValue = Integer.parseInt(tfBlue.getText());
                int requestedValue = currentValue-5;
                if (inRange(requestedValue)) {
                    tfBlue.setText(Integer.toString(requestedValue));
                    currentColor.blue = requestedValue;
                    colorDrawn.paintColor = new Color(currentColor.red, currentColor.green, currentColor.blue);
                    colorDrawn.repaint();
                }
            }

		}
	}

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
