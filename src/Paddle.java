import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle
{
	int id;
	int yVelocity;
	final int speed = 5;
	
	public Paddle(int posX, int posY, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id)
	{
		super(posX, posY, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}
	
	public void keyPressed(KeyEvent e)
	{
		//Move the paddles
		switch(id)
		{
			case 1: //Paddle1
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
					//Move up on y axis
					setYDirection(-speed);
					move();
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
					//Move down on y axis
					setYDirection(speed);
					move();
				}
				break;
			case 2: //Paddle 2
				if(e.getKeyCode() == KeyEvent.VK_UP)
				{
					//Move up on y axis
					setYDirection(-speed);
					move();
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					//Move down on y axis
					setYDirection(speed);
					move();
				}
				break;
		}		
	}
	
	public void keyReleased(KeyEvent e)
	{
		switch(id)
		{
			case 1: //Player 1
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
					//Move up on y axis
					setYDirection(0);
					move();
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
					//Move down on y axis
					setYDirection(0);
					move();
				}
				break;
			case 2: //Player 2
				if(e.getKeyCode() == KeyEvent.VK_UP)
				{
					//Move up on y axis
					setYDirection(0);
					move();
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					//Move down on y axis
					setYDirection(0);
					move();
				}
				break;
		}	
	}
	
	//Only need y because paddles only move up and down
	public void setYDirection(int yDirection)
	{
		yVelocity = yDirection;
	}
	
	public void move()
	{
		y = y + yVelocity;
	}
	
	public void draw(Graphics g)
	{
		//Draw paddles and set colors
		if(id == 1)
		{
			g.setColor(Color.blue);
		}
		else
		{
			g.setColor(Color.green);
		}
		g.fillRect(x, y, width, height);
	}
}
