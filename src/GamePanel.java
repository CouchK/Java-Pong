import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;

//Game board
public class GamePanel extends JPanel implements Runnable
{
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	public GamePanel()
	{
		//Create paddles and ball
		newPaddles();
		newBall();
		
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new ActionListener());
		this.setPreferredSize(SCREEN_SIZE);
		
		//Start thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall()
	{
		random = new Random();
		
		//Start ball at center of window
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), (GAME_HEIGHT/2) - (BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);
	}
	
	public void newPaddles()
	{
		//Paddle on Left
		paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		//Paddle on Right
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
	}
	
	public void paint(Graphics g)
	{
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	public void draw(Graphics g)
	{
		//Draw Paddles
		paddle1.draw(g);
		paddle2.draw(g);
		
		//Draw ball
		ball.draw(g);
		
		//Draw board line and score
		score.draw(g);
	}
	
	public void move()
	{
		//Allows panels and ball to move smoothly
		 paddle1.move();
		 paddle2.move();
		 ball.move();
	}
	
	public void checkCollision()
	{
		//Stops paddles from going off screen
		if(paddle1.y <= 0)
		{
			paddle1.y = 0;
		}
		if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
		{
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
		
		if(paddle2.y <= 0)
		{
			paddle2.y = 0;
		}
		if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
		{
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
		
		//Bounce ball off top/bottom screen
		if(ball.y <= 0)
		{
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= (GAME_HEIGHT - BALL_DIAMETER))
		{
			ball.setYDirection(-ball.yVelocity);
		}
		
		//Bounce ball off paddles
		if(ball.intersects(paddle1))
		{
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.setXDirection(ball.xVelocity);
		}
		
		if(ball.intersects(paddle2))
		{
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.setXDirection(-ball.xVelocity);
		}
		
		//If ball goes off board
		//Give player point and create new paddles and ball
		if(ball.x <= 0) //Ball passed left boundary
		{
			score.player2++;
			newPaddles();
			newBall();
		}
		
		if(ball.x >= GAME_WIDTH - BALL_DIAMETER) //Ball passed right boundary
		{
			score.player1++;
			newPaddles();
			newBall();
		}
	}
	
	public void run()
	{
		//Game loop
		long lastTime = System.nanoTime();
		double amntOfTicks = 60.0;
		double nanosec = 1000000000 / amntOfTicks;
		double delta = 0;
		
		while(true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / nanosec;
			lastTime = now;
			if(delta >= 1)
			{
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	public class ActionListener extends KeyAdapter 
	{
		public void keyPressed(KeyEvent e)
		{
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		
		public void KeyReleased(KeyEvent e)
		{
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
	
}
