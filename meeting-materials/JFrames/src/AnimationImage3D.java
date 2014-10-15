import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

public class AnimationImage3D {

	public static ArrayList<AnimationImage3D> globalList;
	private Point[][][] list;
	private BufferedImage display,background;
	private int width, height, depth;
	
	/**
	 * Initializes globalList, a list containing all instances of AnimationImage3D.
	 */
	public static void init() {
		globalList = new ArrayList<>();
		//Animation.init();
	}
	
	/**
	 * Calls the runAnimations method of the Animation inner-class, which then
	 * runs all Animations.
	 */
	public static void runAnimations() {
		Animation.runAnimations();
	}
	
	/**
	 * Calls the updateDrawings method of every AnimationImage3D as well as
	 * the update method of every animation.
	 * The order in which these methods are called is predetermined by the priority of
	 * each Animation(priority is a byte with values -256 to 255).
	 * The priority of updateDrawings is 0, so any animations with priority values less
	 * than 0 are called before the drawings are updated, and any animations with priority
	 * values greater than 0 are called after.
	 */
	public static void update() {
		/*for (Animation a: Animation.animationList) {
			if (a.priority == 0) {
				for (AnimationImage3D i: AnimationImage3D.globalList)
					i.updateDrawings();
			}
			a.update();
			
		}*/
		for (AnimationImage3D i: AnimationImage3D.globalList)
			i.updateDrawings();
	}
	
	/**
	 * Adds this object to the globalList,
	 * initializes list, display, and background,
	 * and defines the dimensions.
	 * @param width - the width of the 3D image
	 * @param height - the height of the 3D image
	 * @param depth - the depth of the 3D image
	 */
	public AnimationImage3D(int width, int height, int depth) {
		globalList.add(this);
		list = new Point[width][height][depth];
		display = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		background = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	/**
	 * Takes the colors in this 3D image and updates the 2D BufferedImage display with them.
	 */
	public void updateDrawings() {
		
		for (int xIndex = 0; xIndex < width; xIndex++) {
			for (int yIndex = 0; yIndex < height; yIndex++) {
				for (int zIndex = 0; zIndex < depth; zIndex++) {
					if (list[xIndex][yIndex][zIndex] != null) {
						display.setRGB(xIndex, yIndex, getRGB(xIndex,yIndex,zIndex));
						break;
					}
				}
			}
		}
		/*for (int index = 0; index < rotateSphereData.size(); index++) {
			for (int index0 = 0; index0 < rotateSphereData.getElement().size(); index0++) {
				background.setRGB(xHub + index/rotateSphereRadius,yHub + index0/rotateSphereRadius,rotateSphereData.getElement());
				rotateSphereData.getElement().traverse(1);
			}
			rotateSphereData.traverse(1);
		}*/
	}
	
	/**
	 * Draws both the background and the BufferedImage display in the drawing panel.
	 * @param g - Graphics object of the main drawing panel
	 * @param x - x-coordinate of the image
	 * @param y - y-coordinate of the image
	 */
	public void draw(Graphics g, int x, int y) {
		g.drawImage(background, x, y, null);
		g.drawImage(display, x, y, null);
	}
	
	/**
	 * Sets a pixel in the background image to a color.
	 * @param x - x-coordinate of pixel
	 * @param y - y-coordinate of pixel
	 * @param color - Color object to set the pixel to
	 */
	private void setBackground(int x, int y, Color color) {
		background.setRGB(x, y, color.getRGB());
	}
	
	/**
	 * Sets a pixel in the 3D image to a color.
	 * @param x - x-coordinate of pixel
	 * @param y - y-coordinate of pixel
	 * @param z - z-coordinate of pixel
	 * @param color - Color object to set the pixel to
	 */
	public void setPoint(int x, int y, int z, Color color) {
		if (list[x][y][z] == null)
			list[x][y][z] = new Point(color);
		else
			list[x][y][z].color = color;
	}
	
	/**
	 * @return the width of the AnimationImage3D.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return the height of the AnimationImage3D.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return the depth of the AnimationImage3D.
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * Returns the Color object at the specified coordinates in the 3D image.
	 * @param x - x-coordinate of color
	 * @param y - y-coordinate of color
	 * @param z - z-coordinate of color
	 * @return the Color object at the specified coordinates in the 3D image.
	 */
	public Color getColor(int x, int y, int z) {
		return list[x][y][z].getColor();
	}
	
	/**
	 * Returns the Color object of the original color(first color ever set) at the
	 * specified coordinates in the 3D image.
	 * @param x - x-coordinate of color
	 * @param y - y-coordinate of color
	 * @param z - z-coordinate of color
	 * @return the Color object at the specified coordinates in the 3D image.
	 */
	public Color getColorOriginal(int x, int y, int z) {
		return list[x][y][z].getColorOriginal();
	}
	
	/**
	 * Returns the integer representation of the Color object at the specified
	 * coordinates in the 3D image.
	 * @param x - x-coordinate of color
	 * @param y - y-coordinate of color
	 * @param z - z-coordinate of color
	 * @return the integer representation Color object at the specified coordinates
	 * in the 3D image.
	 */
	public int getRGB(int x, int y, int z) {
		return getColor(x,y,z).getRGB();
	}
	
	/**
	 * Returns the integer representation Color object of the original color
	 * (first color ever set) at the specified coordinates in the 3D image.
	 * @param x - x-coordinate of color
	 * @param y - y-coordinate of color
	 * @param z - z-coordinate of color
	 * @return the integer representation Color object at the specified coordinates
	 * in the 3D image.
	 */
	public int getOriginalRGB(int x, int y, int z) {
		return getColorOriginal(x,y,z).getRGB();
	}
	
	/**
	 * Class that represents a point in the 3D image list
	 */
	public class Point {
		
		private Color color, colorOriginal;
		
		/**
		 * Initializes the color and the original color of this point.
		 * @param color - color to initialize values voice
		 */
		public Point(Color color) {
			this.color = color;
			this.colorOriginal = new Color(color.getRGB());
		}
		
		/**
		 * @return
		 */
		public Color getColor() {
			return color;
		}
		
		public Color getColorOriginal() {
			return colorOriginal;
		}
		
	}
	
	public void drawSphere(double xHub, double yHub, double zHub, double radius, Color color) {
		for (double tilt = 0; tilt <= Math.PI; tilt += 1/radius) {
			double rotRadius = radius*Math.sin(tilt);
			for (double rotation = 0; rotation < 2*Math.PI; rotation += 1/rotRadius) {
				double currentDepth = zHub + rotRadius*Math.sin(rotation);
				setBackground((int)(xHub + rotRadius*Math.cos(rotation)), (int)(yHub - radius*Math.cos(tilt)), new Color(0,0,0,255));
				if (tilt == Math.PI && rotRadius == 2*Math.PI - 1)
					System.out.println("0:" + (int)(xHub + rotRadius*Math.cos(rotation)) + "," + (int)(yHub - radius*Math.cos(tilt))+ "," + (int)currentDepth);
				if (rotation == Math.PI && tilt == Math.PI/2)
					setPoint((int)(xHub + rotRadius*Math.cos(rotation)), (int)(yHub - radius*Math.cos(tilt)), (int)currentDepth, new Color(255,0,0));
				else
					setPoint((int)(xHub + rotRadius*Math.cos(rotation)), (int)(yHub - radius*Math.cos(tilt)), (int)currentDepth, new Color(color.getRed(),color.getGreen(),color.getBlue(),(int)(color.getAlpha() - color.getAlpha()*(currentDepth/getDepth()))));
			}
		}
	}
	
	public static class Animation {
		
		private static ArrayList<Animation> animationList;
		private byte priority;
		
		public static void init() {
			animationList = new ArrayList<>();
			new Animation((byte)0);
		}
		
		public static void runAnimations() {
			for (Animation a: animationList)
				a.run();
		}
		
		public Animation(byte priority) throws IllegalArgumentException {
			if (!(this instanceof Animation) && priority == 0)
				throw new IllegalArgumentException();
			this.priority = priority;
			for (byte index = 0; index < animationList.size(); index++) {
				if (priority <= animationList.get(index).priority) {
					animationList.add(index,this);
					return;
				}
			}
			animationList.add(this);
		}
		
		public void run() {
			
		}
		
		public void update() {
			
		}
		
	}
	
}
