import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class SimpleDraw extends JFrame implements ActionListener, MouseListener, MouseMotionListener, ChangeListener {

	int lastx, lasty, newx, newy, eraser=0, rect=0, oval = 0, pen=1, brush=0, airbrush=0;
	DrawPanel panel;
  JLabel label, label2;
	JSlider slider;
  Graphics g;
  JFileChooser fileChooser;
	int s2, iconSize=48;
	Color newstate = new Color(51,51, 51);
	JButton pencil_bt;
	JPanel p0, p1, p2;
	JPanel pencil_bk, eraser_bk, brush_bk, airbrush_bk, rectangle_bk, oval_bk, pallete_bk, clear_bk;

	private void brushtype(int newpen, int neweraser, int newrect, int newoval, int newbrush, int newairbrush) {

		pen = newpen;
		eraser = neweraser;
		rect = newrect;
		oval = newoval;
		brush = newbrush;
		airbrush = newairbrush;

	}

	private static ImageIcon getIcon(Color c) {

		BufferedImage bi_bt = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d_bt = (Graphics2D)bi_bt.getGraphics();
		g2d_bt.setColor(c);
		g2d_bt.fillRect(0, 0, 20, 20);
		return new ImageIcon(bi_bt);
	}

	private void addButton(JPanel back, JPanel p, JButton buttonItem, String iconitem, String actionName, ActionListener listener) {

		Image image = new ImageIcon(iconitem).getImage();

    ImageIcon ButtonIcon = new ImageIcon(iconitem);
		buttonItem = new JButton(ButtonIcon);
    buttonItem.setActionCommand(actionName);
    buttonItem.addActionListener(listener);
		buttonItem.setContentAreaFilled(false);
    buttonItem.setBorderPainted(false);
		back.setOpaque(true);
		back.setBackground(new Color(102,102,102));
		back.add(buttonItem);
		p.add(back);
  }

	private void backstate(JPanel button_bk, Color newcolor) {
		button_bk.setOpaque(true);
		button_bk.setBackground(newstate);
	}

	private void backstate_reset() {
		pencil_bk.setBackground(new Color(102,102,102));
		eraser_bk.setBackground(new Color(102,102,102));
		brush_bk.setBackground(new Color(102,102,102));
		airbrush_bk.setBackground(new Color(102,102,102));
		rectangle_bk.setBackground(new Color(102,102,102));
		oval_bk.setBackground(new Color(102,102,102));
		pallete_bk.setBackground(new Color(102,102,102));
		clear_bk.setBackground(new Color(102,102,102));
	}

	public void initButton() {

		slider = new JSlider(1,100);
		slider.setValue(1);
		slider.setPaintTicks(true);
		slider.addChangeListener(this);

		p0 = new JPanel();

		p1 = new JPanel();
		p2 = new JPanel();

		pencil_bk = new JPanel();
	 	eraser_bk = new JPanel();
		brush_bk = new JPanel();
		airbrush_bk = new JPanel();
		rectangle_bk = new JPanel();
		oval_bk = new JPanel();
		pallete_bk = new JPanel();
		clear_bk = new JPanel();

		pencil_bt = new JButton();
		JButton eraser_bt = new JButton();
		JButton brush_bt = new JButton();
		JButton airbrush_bt = new JButton();
		JButton rectangle_bt = new JButton();
		JButton oval_bt = new JButton();
		JButton pallete_bt = new JButton();
		JButton clear_bt = new JButton();

		addButton(pencil_bk, p1, pencil_bt, "icon/pencil-icon.png", "pen", this);
		addButton(eraser_bk, p1, eraser_bt, "icon/eraser-icon.png", "Eraser", this);
		addButton(brush_bk, p1, brush_bt, "icon/star-icon.png", "brush", this);
		addButton(airbrush_bk, p1, airbrush_bt, "icon/airbrush-icon.png", "airbrush", this);
		addButton(rectangle_bk, p1, rectangle_bt, "icon/rectangle-icon.png", "Rectangle", this);
		addButton(oval_bk, p1, oval_bt, "icon/oval-icon.png", "Oval", this);
		addButton(pallete_bk, p1, pallete_bt, "icon/pallet-icon.png", "Pallette", this);
		addButton(clear_bk, p1, clear_bt, "icon/clear-icon.png", "clear", this);

		GridLayout layout = new GridLayout(8, 1);
    layout.setHgap(10);
    layout.setVgap(10);
    p1.setLayout(layout);

		p2.setLayout(new BorderLayout(0, 0));

		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setText("ブラシの大きさ：" + slider.getValue());

		//p2の中身の配置
		p2.add(slider, BorderLayout.NORTH);
    p2.add(label, BorderLayout.SOUTH);

		//p0の中身の配置
		/*p0.add(p1, BorderLayout.WEST);
		p0.add(p2, BorderLayout.EAST);*/

    Container container = getContentPane();
    container.add(p0, BorderLayout.WEST);
		this.setVisible(true);

	}

  private void addMenuItem(JMenu targetMenu, String itemName, String actionName, ActionListener listener) {

    JMenuItem menuItem = new JMenuItem(itemName);
    menuItem.setActionCommand(actionName);
    menuItem.addActionListener(listener);
    targetMenu.add(menuItem);

  }

  private void initMenu() {

    JMenuBar menubar = new JMenuBar();

    JMenu menuFile = new JMenu("File");
    this.addMenuItem(menuFile,"New","New",this);
    this.addMenuItem(menuFile,"Open...","Open",this);
    this.addMenuItem(menuFile,"Save...","Save",this);
    menubar.add(menuFile);

    JMenu menuPen = new JMenu("Pen");
    menubar.add(menuPen);

    JMenu menuColor = new JMenu("Color");
    this.addMenuItem(menuColor,"Black","Black",this);
    this.addMenuItem(menuColor,"Blue","Blue",this);
    this.addMenuItem(menuColor,"Yellow","Yellow",this);
    this.addMenuItem(menuColor,"Green","Green",this);
    this.addMenuItem(menuColor,"Red","Red",this);
    this.addMenuItem(menuColor,"Eraser","Eraser",this);
    this.addMenuItem(menuColor,"Rectangle","Rectangle",this);
    this.addMenuItem(menuColor,"Oval","Oval",this);
    this.addMenuItem(menuColor,"Choose color","Choose color",this);
    menuPen.add(menuColor);

    JMenu menuWidth = new JMenu("Width");
    this.addMenuItem(menuWidth,"width1","width1",this);
    this.addMenuItem(menuWidth,"width5","width5",this);
    this.addMenuItem(menuWidth,"width10","width10",this);
    this.addMenuItem(menuWidth,"width20","width20",this);
    menuPen.add(menuWidth);

    this.setJMenuBar(menubar);

    label2 = new JLabel("");
    fileChooser = new JFileChooser();
    JPanel panel = new JPanel();
    panel.add(label2);
    Container container = getContentPane();
    container.add(panel);
  }

  public void mouseClicked (MouseEvent arg0) {
  }

	public void stateChanged(ChangeEvent arg0) {
		float s = slider.getValue();
		s2 = slider.getValue();
		label.setText("ブラシの大きさ：" + s2);
		panel.cursoreraser(s2);
		panel.cursorpen(s2);
		panel.setCursorSize(s2);
		panel.setPenWidth(s);
		panel.setAirWidth(s2);
		panel.setStampWidth(s2);
	}

  public void mouseEntered (MouseEvent arg0) {
    lastx = arg0.getX();
    lasty = arg0.getY();
		if(eraser == 1) {
			panel.cursoreraser(s2);
		}
		else {
			panel.cursorpen(s2);
		}
  }

  public void mouseExited (MouseEvent arg0) {
  }

  public void mousePressed(MouseEvent arg0) {
    lastx =arg0.getX();
    lasty =arg0.getY();
  }

  public void mouseReleased (MouseEvent arg0) {
    newx = arg0.getX();
    newy = arg0.getY();
    if (rect == 1) {
      panel.EmptyRect(lastx, lasty, newx, newy);
      repaint();
    }
    else if (oval == 1) {
      panel.EmptyOval(lastx, lasty, newx, newy);
      repaint();
    }
  }

	public void mouseDragged(MouseEvent arg0) {
		newx=arg0.getX();
		newy=arg0.getY();
		if (rect == 1) {
      repaint();
    }
    else if (oval == 1) {
      repaint();
    }
    else if (pen == 1) {
      panel.drawLine(lastx,lasty,newx,newy);
      lastx=newx;
  		lasty=newy;
    }
		else if (eraser == 1 ) {
			pen = 0;
      panel.EraserLine(lastx,lasty,newx,newy);
      lastx=newx;
  		lasty=newy;
    }
		else if (brush == 1) {
			pen = 0;
      panel.stamp(newx, newy);
      repaint();
    }
		else if (airbrush == 1) {
      panel.airbrush(newx, newy);
      repaint();
    }
	}

  public void mouseMoved(MouseEvent arg0) {
  }


	private void init() {
		this.setTitle("Simple Draw");
    this.setBounds(100, 100, 1200, 900);
		panel=new DrawPanel();
		this.getContentPane().add(panel);
    panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel.setPenColor(Color.black);
		panel.createBuffer(this.getWidth()-20, this.getHeight()-20);
		panel.cursorpen(1);

    getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(p0, BorderLayout.WEST);

		p0.add(p1, BorderLayout.WEST);
		p0.add(p2, BorderLayout.EAST);

		p0.setBackground(new Color(102,102,102));
		p1.setBackground(new Color(102,102,102));
		p2.setBackground(new Color(102,102,102));
	}

  public void actionPerformed (ActionEvent arg0) {
    String command = arg0.getActionCommand();
    if (command == "New") {
    }
    else if (command == "Open") {
      int returnVal = fileChooser.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        panel.openFile(fileChooser.getSelectedFile());
      }
    }
    else if (command == "Save") {
      int returnVal = fileChooser.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        panel.saveFile(fileChooser.getSelectedFile());
      }
    }
    else if (command == "Black") {
      eraser = 0;
      panel.setPenColor(Color.black);
    }
    else if (command == "Blue"){
      eraser = 0;
      panel.setPenColor(Color.blue);
    }
    else if (command == "Yellow") {
      eraser = 0;
      panel.setPenColor(Color.yellow);
    }
    else if (command == "Green") {
      eraser = 0;
      panel.setPenColor(Color.green);
    }
    else if (command == "Red") {
      eraser = 0;
      panel.setPenColor(Color.red);
    }
    else if (command == "Pallette") {
			backstate_reset();
			backstate(pallete_bk, Color.gray);
      JColorChooser colorchooser = new JColorChooser();
      Color color = colorchooser.showDialog(this,"choose color", Color.blue);
      panel.setPenColor(color);
    }
    else if (command == "Eraser") {
			brushtype(0, 1, 0, 0, 0, 0);
			backstate_reset();
			backstate(eraser_bk, Color.gray);
    }
		else if (command == "pen") {
			brushtype(1, 0, 0, 0, 0, 0);
			backstate_reset();
			backstate(pencil_bk, Color.gray);
		}
    else if (command == "Rectangle") {
			//panel.setPenWidth(1);
			brushtype(0, 0, 1, 0, 0, 0);
			backstate_reset();
			backstate(rectangle_bk, Color.gray);
    }
    else if (command == "Oval") {
      brushtype(0, 0, 0, 1, 0, 0);
			backstate_reset();
			backstate(oval_bk, Color.gray);
    }
		else if (command == "brush") {
			brushtype(0, 0, 0, 0, 1, 0);
			backstate_reset();
			backstate(brush_bk, Color.gray);
		}
		else if (command == "airbrush") {
			brushtype(0, 0, 0, 0, 0, 1);
			backstate_reset();
			backstate(airbrush_bk, Color.gray);
		}
		else if (command == "clear") {
			backstate_reset();
			backstate(clear_bk, Color.gray);
			panel.clear();
		}
    else if (command == "width1") {
      panel.setPenWidth(1);
    }
    else if (command == "width5") {
      panel.setPenWidth(5);
    }
    else if (command == "width10") {
      panel.setPenWidth(10);
    }
    else if (command == "width20") {
      panel.setPenWidth(20);
    }
  }

	public static void main(String[] args) {
		SimpleDraw frame=new SimpleDraw();
		frame.initButton();
		frame.initMenu();
    frame.init();
	}

}
