import java.awt.Color;
import javax.swing.JFrame;

//Frame around game board - minimize btn, exit btn
public class GameFrame extends JFrame
{
	GamePanel panel;
	
	public GameFrame() 
	{
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong Game!");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Adjust size to fit around game panel
		this.pack();
		//Appear at middle of screen
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
