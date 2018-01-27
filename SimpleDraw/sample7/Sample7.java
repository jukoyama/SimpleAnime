import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Sample7 extends JFrame implements MouseListener {
	Point start;
	Point end;
	boolean isDraw = false;

	public static void main(String[] args){
		new Sample7();
	}

	Sample7(){
		int appWidth = 300;
		int appHeight = 200;
 		setSize(appWidth, appHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintFreeLine(g);
	}

	void paintFreeLine(Graphics g) {
		if (isDraw) {
			g.drawRect(start.x, start.y, end.x-start.x, end.y-start.y);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		isDraw = false;
		start = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isDraw = true;
		end = new Point(e.getX(), e.getY());
		super.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
