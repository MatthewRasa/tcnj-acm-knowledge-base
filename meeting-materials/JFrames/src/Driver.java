/**
 * Any comments preceded with '!' are necessary to run any frame.
 * Any comments preceded with 'm' are used for the mouse tracker.
 * Any comments preceded with 'i' are used for the moving image.
 */
import javax.swing.*;/**! JFrames and JApplets*/
import java.awt.*;/**! Graphics*/
import java.awt.event.*;/**! Mouse/Key/MouseMotion Listeners*/
import java.awt.image.*;/**i Images(and BufferedImages)*/
import java.io.*;/**i Used for finding files on computer*/
import javax.imageio.ImageIO;/**i Used for importing an image file*/

/**
 * !
 * Our driver class needs to extend JFrame. The JFrame is the physical window you see
 * which holds the application.
 * 
 * Our class must implement Runnable, which is responsible for threading our application
 * and constantly updating the display.
 * 
 * MouseListener, MouseMotionListener, and KeyListener are all optional, but are
 * necessary if we want to receive and process input of one of those types from the
 * user.
 */
public class Driver extends JFrame implements MouseListener, MouseMotionListener, KeyListener, Runnable {

	/**! Constants keeping track of frame width and height. Adjust as needed.*/
	private final static int FRAME_WIDTH=500, FRAME_HEIGHT=500;
	/**! Static thread object we will use for this application*/
	private static Thread thread;
	/**m Keeps track of the last-known mouse coordinates*/
	private int mouseX, mouseY;
	/**i Our moving image*/
	private MovingImage image;
	
	/**!
	 * Constructor for our frame. When it is called, it sets up all frame-related
	 * dependencies, such as size, content pane, etc.
	 * 
	 * Prior to that, any class-related variables should be initialized.
	 */
	public Driver() {
		/* i
		 * Loads the image 'elephant.jpg' from its file into the program.
		 * Then, a custom scaling algorithm I made is applied to the image
		 * (there is probably an automatic way, but I couldn't find it).
		 */
		try {
			BufferedImage elephant = ImageIO.read(new File("elephant.jpg")),
					      scaledImage = new BufferedImage(FRAME_WIDTH,FRAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);
			double widthScaleFactor = (double)elephant.getWidth()/scaledImage.getWidth(),
				   heightScaleFactor = (double)elephant.getHeight()/scaledImage.getHeight();
			for (int xIndex = 0; xIndex < scaledImage.getWidth(); xIndex++) {
				for (int yIndex = 0; yIndex < scaledImage.getHeight(); yIndex++)
					scaledImage.setRGB(xIndex,yIndex,elephant.getRGB((int)(xIndex*widthScaleFactor), (int)(yIndex*heightScaleFactor)));
			}
			//image is initialized with the new, scaled imaged of the elephant.
			image = new MovingImage(scaledImage,0,0);
		}
		catch (IOException e) {

		}
		//! Makes it so the program exits when the frame is closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* !
		 * Sets it so that the frame displays whatever we have in our JPanel,
		 * DrawingPanel.
		 * THIS IS IMPORTANT TO DO!!
		 * Nothing will display without it.
		 */
		setContentPane(new DrawingPanel());
		addMouseListener(this);//! Adds our mouseListener
		addMouseMotionListener(this);//! Adds our MouseMotionListener
		addKeyListener(this);//! Adds our KeyListener
		setVisible(true);//! Makes the frame visible
		setFocusable(true);//! Makes the frame focusable
		setSize(FRAME_WIDTH,FRAME_HEIGHT);//! Sets the frame size
	}

	/**! Main method of our program. The program starts here.*/
	public static void main(String[] args) {
		/*! Create a new Driver. Doing so will call the Driver() constructor*/
		Driver frame = new Driver();
		
		/* Any static init() methods of classes should be called right here, before
		 * the thread is started. Or else they will not be called:(
		 */
		
		/*! Initialize thread. Send in the Driver we made as an argument.*/
		thread = new Thread(frame);
		/*! Starts the thread. The run() method is now called.*/
		thread.start();
	}
	
	/**! JPanel used for drawing. This is called through run and updates the display.*/
	public class DrawingPanel extends JPanel {
		
		/**!
		 * Method used for drawing on the panel.
		 * MUST be named either paintComponent or paint.
		 * - paintComponent also allows for JComponents to be used.
		 * The Graphics object here should be the only Graphics used in the program.
		 * If other classes need to draw images or shapes, this graphics object should
		 * be passed as a parameter to the method in that class.
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);//! Calls the super class
			image.draw(g);//i Calls the draw method of the MovingImage.*/
			/* m
			 * Draws the mouse tracker, a string (x,y), using the last recorded mouse
			 * coordinates.
			 */
			g.drawString("("+mouseX+","+mouseY+")", mouseX, mouseY);
		}
		
	}
	
	/**!
	 * Method implemented from Runnable.
	 * Infinitely runs, at 60 frames per second(1 second/60 = ~17 milliseconds).
	 * repaint() automatically calls the paintComponent() method of the JPanel.
	 */
	public void run() {
		while(true) {
			repaint();
			try {
				thread.sleep(17);
			}
			catch(InterruptedException e) {
				
			}
		}
		
	}

	/**!
	 * All listener methods are called automatically whenever their corresponding event
	 * occurs.
	 * - Ex: If the user presses the mouse, mousePressed is called.
	 * Each MouseEvent or KeyEvent should have only one instance in the program.
	 * All mouse presses, for instance, should go through THIS mousePress() method, then
	 * it should pass the MouseEvent object as an argument to specific methods of
	 * classes that need it, NOT implement MouseListener elsewhere.
	 */
	
	/**Called when the mouse is clicked(pressed AND released)*/
	public void mouseClicked(MouseEvent e) {
		
	}
	
	/**Called when the mouse is pressed down(either button or scroll button)*/
	public void mousePressed(MouseEvent e) {
		image.mousePressed(e);
	}

	/**Called when the mouse is released*/
	public void mouseReleased(MouseEvent e) {
		image.mouseReleased(e);
	}
	
	/**Called when the mouse enters the frame*/
	public void mouseEntered(MouseEvent e) {
		
	}
		
	
	/**Called when the mouse exits the frame*/
	public void mouseExited(MouseEvent e) {
		
	}
	
	/**Called when the mouse is moved while NOT pressed*/
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	/**Called when the mouse is moved while pressed*/
	public void mouseDragged(MouseEvent e) {
		image.mouseDragged(e);
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**Called when a key is pressed down*/
	public void keyPressed(KeyEvent e) {
		
	}

	/**Called when a key is released*/
	public void keyReleased(KeyEvent e) {
		
	}

	/**Called when a key is typed(pressed AND released)*/
	public void keyTyped(KeyEvent e) {
		
	}

}
