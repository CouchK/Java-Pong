import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle
{
	Random random; 
	int xVelocity;
	int yVelocity;
	final int initialSpeed = 5;
	
	public Ball(int posX, int posY, int width, int height)
	{
		super(posX, posY, width, height);
		
		random = new Random();
		int randXDirection = random.nextInt(2);
		int randYDirection = random.nextInt(2);
		
		//If 0 go left, if 1 go right
		if(randXDirection == 0)
		{
			randXDirection--;
		}
		setXDirection(randXDirection * initialSpeed);
		
		if(randYDirection == 0)
		{
			randYDirection--;
		}
		setYDirection(randYDirection * initialSpeed);
	}
	
	public void setXDirection(int randXDirection)
	{
		xVelocity = randXDirection;
	}
	
	public void setYDirection(int randYDirection)
	{
		yVelocity = randYDirection;
	}
	
	public void move()
	{
		x += xVelocity;
		y += yVelocity;
	}
	
	public void draw(Graphics g)
	{
		//Create ball and set color
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}
