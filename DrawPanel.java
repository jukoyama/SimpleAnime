import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Dimension;
/*import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;*/


public class DrawPanel extends JPanel {
	int x = 0, y = 0, x1, y1, x2, y2;
	Color currentColor = Color.black;
	Color currentBackColor = Color.red;
	Float currentWidth = 1.0f;
	BufferedImage bufferImage = null;
	Graphics2D bufferGraphics = null;
	Dimension   size;

	private void createBuffer(int width, int height) {
		//バッファ用のImageとGraphicsを用意する
		bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		bufferGraphics = bufferImage.createGraphics(); //getGraphicsと似ているが、戻り値がGraohics2D
		bufferGraphics.setBackground(Color.white);
		bufferGraphics.clearRect(0, 0, width, height); //バッファクリア
	}

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

	public void setPenColor(Color newColor) {
		currentColor = newColor;
	}

	public void setPenWidth(float newWidth) {
		currentWidth = newWidth;
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
		/*g.translate(x, y);*/

		if(null == bufferGraphics) {
			this.createBuffer(this.getWidth(), this.getHeight()); //バッファがまだ作ってなければ作る
		}
		bufferGraphics.setColor(currentColor);
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
