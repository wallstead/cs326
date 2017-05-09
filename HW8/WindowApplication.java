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

        colorDrawn.paintColor = new Color(firstColor.red, firstColor.green, firstColor.blue);
        listColors = new JList();
		listColors.addListSelectionListener(new ListHandler());

		getContentPane().setLayout(null); // row,col

		getContentPane().add(colorDrawn);
		getContentPane().add(listColors);

        colorDrawn.setBounds(10, 10, 220, 100);
        listColors.setBounds(10+220+10, 10, 100, 250);

		setVisible(true);

        String colors[] = {"Red", "Green", "Blue"};
		listColors.setListData(colors);
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
            System.out.println(colorName + " " + red + " " + blue + " " + green + colorCount);
        }

        colorCount = colorCounter;

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
                String s = (String) listColors.getSelectedValue();
                System.out.println("Position " + i + " selected: " +s);
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
