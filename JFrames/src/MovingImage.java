import java.awt.image.*;/**Images(and BufferedImages)*/
import java.awt.*;/**Graphics*/
import java.awt.event.*;/**MouseEvents*/

/**i Represents an image that can be dragged around the screen by the user.*/
public class MovingImage {

	private BufferedImage image;/**BufferedImage to use*/
	/**
	 * x and y are the coordinates of the image.
	 * Width and height represent the image dimensions.
	 * mxOffset and myOffset are the differences between the last mouse press and
	 * the current x,y.
	 */
	private int x, y, width, height, mxOffset, myOffset;
	private boolean mouseDragging = false;/**Whether the mouse is down or not*/
	
	/**
	 * Constructor that takes in an image and a starting x,y position.
	 * @param image - BufferedImage to use
	 * @param x - initial x-coordinate
	 * @param y - initial y-coordinate
	 */
	public MovingImage(BufferedImage image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.getWidth();
		height = image.getHeight();
	}
	
	/**
	 * Drawing method. Draws the BufferedImage at the current x,y.
	 * @param g - Graphics object
	 */
	public void draw(Graphics g) {
		g.drawImage(image,x,y,null);
	}
	
	/**
	 * Called by the mousePressed method in Driver, whenever the mouse is pressed.
	 * If the mouse is pressed inside the coordinates of the MovingImage,
	 * - mxOffset is set to the horizontal distance between the mouse and the image
	 *   origin
	 * - myOffset is set to the vertical distance between the mouse and the image
	 *   origin
	 * - mouseDragging is set to true
	 * Else mouseDragging is set to false.
	 * @param e - MouseEvent object
	 */
	public void mousePressed(MouseEvent e) {
		if (x <= e.getX() && e.getX() <= width && y <= e.getY() && e.getY() <= height) {
			mxOffset = e.getX() - x;
			myOffset = e.getY() - y;
			mouseDragging=true;
		}
		else
			mouseDragging=false;
	}
	
	/**
	 * Called by the mouseReleased method in Driver, whenever the mouse is released.
	 * Sets mouseDragging to false.
	 * @param e - MouseEvent object
	 */
	public void mouseReleased(MouseEvent e) {
		mouseDragging = false;
	}
	
	/**
	 * Called by the mouseDragged method in Driver, whenever the mouse is dragged.
	 * Changes the image's position to the coordinates of the mouse minus the offset
	 * from the mouse to the origin.
	 * @param e - MouseEvent object
	 */
	public void mouseDragged(MouseEvent e) {
		if (mouseDragging) {
			x = e.getX() - mxOffset;
			y = e.getY() - myOffset;
		}
	}
	
}
