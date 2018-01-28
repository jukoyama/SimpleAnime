import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import java.awt.geom.AffineTransform;

public class DrawPanel extends JPanel {
	int x = 0, y = 0, x1, y1, x2, y2;
	Color currentColor = Color.black;
	Color currentBackColor = Color.white;
	Float currentWidth = 1.0f;
	int airWidth = 30;
	int currentSize = 1;
	int currentcursorsize = 1;
	BufferedImage bufferImage = null;
	Graphics2D bufferGraphics = null;
	Dimension   size;
	int cursorwidth;
	int cursorheight;
	//BufferedImage image;

	public void cursorpen(int cursorsize) {

		Image image = new ImageIcon("edit4.png").getImage();
		int cursorwidth = image.getWidth(this);
		int cursorheight = image.getHeight(this);

		BufferedImage bi = new BufferedImage(cursorsize * 4 + 64, cursorsize * 4 + 64, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setPaint(Color.black);
		g2d.drawOval(cursorsize/2, cursorsize/2,  cursorsize, cursorsize);
		g2d.drawImage(image, cursorsize, cursorsize, cursorwidth/4, cursorheight/4, this);
		g2d.dispose();
		setCursor(getToolkit().createCustomCursor(bi, new Point(cursorsize, cursorsize), "oval"));
	}

	public void cursoreraser(int cursorsize) {

		Image image = new ImageIcon("eraser.png").getImage();
		int cursorwidth = image.getWidth(this);
		int cursorheight = image.getHeight(this);

		BufferedImage bi = new BufferedImage(cursorsize * 4 + 64, cursorsize * 4 + 64, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setPaint(Color.black);
		g2d.drawOval(cursorsize/2, cursorsize/2,  cursorsize, cursorsize);
		g2d.drawImage(image, cursorsize, cursorsize, cursorwidth/4, cursorheight/4, this);
		g2d.dispose();
		setCursor(getToolkit().createCustomCursor(bi, new Point(cursorsize, cursorsize), "oval"));
	}

	public void createBuffer(int width, int height) {
		//バッファ用のImageとGraphicsを用意する
		bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		bufferGraphics = bufferImage.createGraphics(); //getGraphicsと似ているが、戻り値がGraohics2D
		bufferGraphics.setBackground(currentBackColor);

		bufferGraphics.clearRect(0, 0, width, height); //バッファクリア
	}

	/*private void NewFile(JFrame thisframe) {

		NewImage dialog = new NewImage(thisframe,true, 100, 200);
		Dimension d = dialog.getDimension();

		if (d != null) {
			createBuffer(d.width, d.height);
			canvas.newBlankImage(d.width,d.height,defImgBckColor);
			updateSizeLabel();

			lastUsedFile = null;
			setTitle(defTitle);
			setImageSaved();

			// update lastImgWidth, lastImgHeight
			lastImgWidth = d.width;
			lastImgHeight = d.height;
		}
	}*/

	public void openFile(File file2open) {
		BufferedImage pictureImage;
		try {
			pictureImage = ImageIO.read(file2open);
		} catch(Exception e) {
			System.out.println("Error: reading file=" + file2open.getName());
			return;
		}
		//画像に合わせたサイズでbufferImageとbufferGraphicsを作り直して画像を読み込む
		//ImageIO.readの戻り値をbufferImageに代入するのでは駄目
		this.createBuffer(pictureImage.getWidth(), pictureImage.getHeight());
		bufferGraphics.drawImage(pictureImage, 0, 0, this);
		repaint();	//画像を表示するためにpaintComponentを呼ぶ
	}

	public void saveFile(File file2save) {
		try {
			ImageIO.write(bufferImage, "jpg", file2save);
		} catch(Exception e) {
			System.out.println("Error: writing file=" + file2save.getName());
			return;
		}
	}

	public void stamp(int x1, int y1) {
		Image stampImage = new ImageIcon("star.png").getImage();
		int stampWidth = stampImage.getWidth(this)/50;
		int stampHeight = stampImage.getHeight(this)/50;
		int stampx = stampWidth/2;
		int stampy = stampHeight/2;
		if(null == bufferGraphics) {
			this.createBuffer(this.getWidth(), this.getHeight()); //バッファがまだ作ってなければ作る
		}
		//bufferGraphics.setTransform(AffineTransform.getScaleInstance(0.05, 0.05));
		bufferGraphics.drawImage(stampImage, x1 - stampx, y1 - stampy, stampWidth + currentSize, stampHeight + currentSize, this);
		repaint();	//画像を表示するためにpaintComponentを呼ぶ
	}

	public void airbrush(int x1, int y1) {
		Random rand = new Random();                                //new Random class
    int brushPoints[][];

		brushPoints = new int[(airWidth * airWidth) / 10][2];    //create a new two-dimensional array of variable size
    for (int i = 0; i < (airWidth * airWidth) / 10; i++) {
			int pts[] = new int[2];
      pts[0] = rand.nextInt(airWidth*6/5);      //fill the array with random integers
      pts[1] = rand.nextInt(airWidth*6/5);
			bufferGraphics.setColor(currentColor);
			bufferGraphics.setStroke(new BasicStroke(1 ,BasicStroke.CAP_ROUND ,BasicStroke.JOIN_MITER));
      bufferGraphics.drawOval(x1 + pts[0], y1 + pts[1], 1, 1);  //draw a rectangle to create a brush effect
      brushPoints[i] = pts;
		}
		if(null == bufferGraphics) {
			this.createBuffer(this.getWidth(), this.getHeight()); //バッファがまだ作ってなければ作る
		}
		/*bufferGraphics.drawImage(stampImage, x1 - sW2, y1 - sH2, stampWidth/10, stampHeight/10, this);*/
		repaint();	//画像を表示するためにpaintComponentを呼ぶ
	}

	public void setPenColor(Color newColor) {
		currentColor = newColor;
	}

	public void setPenWidth(float newWidth) {
		currentWidth = newWidth;
	}

	public void setAirWidth(int newWidth2) {
		airWidth = newWidth2;
	}

	public void setStampWidth(int newWidth) {
		currentSize = newWidth;
	}

	public void setCursorSize(int newSize) {
		currentcursorsize = newSize;
	}

	public void clear() {
		if(null == bufferGraphics) {
			this.createBuffer(this.getWidth(), this.getHeight()); //バッファがまだ作ってなければ作る
		}
		/*backColor = initColor;
		bufferGraphics.setColor(backColor);*/
		bufferGraphics.clearRect(0, 0, getWidth(), getHeight());
		repaint();
	}

	public void check(int x1, int y1, int x2, int y2) {
			if (x1 > x2)
			{
					int z = 0;
					z = x1;
					x1 = x2;
					x2 = z;
			}
			if (y1 > y2)
			{
					int z = 0;
					z = y1;
					y1 = y2;
					y2 = z;
			}
	}

	public void clearRect (int x1, int y1, int w, int h){
		if(null == bufferGraphics) {
			this.createBuffer(this.getWidth(), this.getHeight()); //バッファがまだ作ってなければ作る
		}
		bufferGraphics.clearRect(x1, y1, w, h);
	}

	public void EmptyRect (int x1, int y1, int x2, int y2) {
		if(null == bufferGraphics) {
			this.createBuffer(this.getWidth(), this.getHeight()); //バッファがまだ作ってなければ作る
		}
		int w = x2 - x1;
		int h = y2 - y1;
		if (w < 0) {  // width がマイナスなら x をずらす
    	w = -w;
    	x1 = x1 - w;
		}
		if (h < 0) {  // height がマイナスなら y をずらす
    	h = -h;
    	y1 = y1 - h;
		}
		check(x1,y1,x2,y2);
		bufferGraphics.setColor(currentColor);
		bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND ,BasicStroke.JOIN_MITER));
		bufferGraphics.drawRect(x1, y1, w, h);
		repaint();
	}

	public void EmptyOval (int x1, int y1, int x2, int y2) {
		if(null == bufferGraphics) {
			this.createBuffer(this.getWidth(), this.getHeight()); //バッファがまだ作ってなければ作る
		}
		int w = x2 - x1;
		int h = y2 - y1;
		if (w < 0) {  // width がマイナスなら x をずらす
    	w = -w;
    	x1 = x1 - w;
		}
		if (h < 0) {  // height がマイナスなら y をずらす
    	h = -h;
    	y1 = y1 - h;
		}
		check(x1,y1,x2,y2);
		bufferGraphics.setColor(currentColor);
		bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND ,BasicStroke.JOIN_MITER));
		bufferGraphics.drawOval(x1, y1, w, h);
		repaint();
	}

	public void drawLine(int x1, int y1, int x2, int y2){
		/*if(null == bufferGraphics) {
			this.createBuffer(this.getWidth(), this.getHeight()); //バッファがまだ作ってなければ作る
		}*/
		this.cursorpen(currentcursorsize);
		bufferGraphics.setColor(currentColor);
		bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND ,BasicStroke.JOIN_MITER));
		bufferGraphics.drawLine(x1, y1, x2, y2);
		repaint(); //再描画するためpaintComponentを呼び出す。
	}

	public void EraserLine(int x1, int y1, int x2, int y2){
		if(null == bufferGraphics) {
			this.createBuffer(this.getWidth(), this.getHeight()); //バッファがまだ作ってなければ作る
		}
		this.cursoreraser(currentcursorsize);
		bufferGraphics.setColor(Color.white);
		bufferGraphics.setStroke(new BasicStroke(currentWidth ,BasicStroke.CAP_ROUND ,BasicStroke.JOIN_MITER));
		bufferGraphics.drawLine(x1, y1, x2, y2);
		repaint(); //再描画するためpaintComponentを呼び出す。
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);	 //他に描画するものがあるかもしれないので親を呼んでおく
		if (null != bufferImage) g2.drawImage(bufferImage, 0, 0, this);	//バッファを表示する
	}
}
