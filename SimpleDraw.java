import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class SimpleDraw extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	int lastx, lasty, newx, newy, eraser=0, rect=0, oval = 0;
	DrawPanel panel;
  JLabel label;
  JLabel label2;
  Graphics g;
  JFileChooser fileChooser;
  Component component;
  JFrame f;

  private void initButton() {
    JPanel p = new JPanel();

    ImageIcon icon1 = new ImageIcon("./004-edit.png");
    JButton button1 = new JButton(icon1);

    ImageIcon icon2 = new ImageIcon("./002-eraser.png");
    JButton button2 = new JButton(icon2);

    ImageIcon icon3 = new ImageIcon("./008-paint-brush.png");
    JButton button3 = new JButton(icon3);

    ImageIcon icon4 = new ImageIcon("./001-bucket.png");
    JButton button4 = new JButton(icon4);

    ImageIcon icon5 = new ImageIcon("./003-select-1.png");
    JButton button5 = new JButton(icon5);

    ImageIcon icon6 = new ImageIcon("./005-ellipse.png");
    JButton button6 = new JButton(icon6);

    ImageIcon icon7 = new ImageIcon("./007-painting-palette.png");
    JButton button7 = new JButton(icon7);

    GridLayout layout = new GridLayout(4, 2);
    layout.setHgap(10);
    layout.setVgap(5);
    p.setLayout(layout);

    p.add(button1);
    p.add(button2);
    p.add(button3);
    p.add(button4);
    p.add(button5);
    p.add(button6);
    p.add(button7);

    getContentPane().add(p, BorderLayout.CENTER);
    this.setVisible(true);
  }

  /*private void initButton() {
    JButton button1 = new JButton("button1");
    JButton button2 = new JButton("button2");
    JButton button3 = new JButton("button3");
    JButton button4 = new JButton("button4");
    JButton button5 = new JButton("button5");
    JButton button6 = new JButton("button6");
    JButton button7 = new JButton("button7");
    JButton button8 = new JButton("button8");
    JButton button9 = new JButton("button9");
    JButton button10 = new JButton("button10");

    JPanel p = new JPanel();
    GridLayout layout = new GridLayout(5, 2);
    layout.setHgap(10);
    layout.setVgap(5);
    p.setLayout(layout);

    button1.setPreferredSize(new Dimension(10, 10));
    button2.setPreferredSize(new Dimension(10, 10));
    button3.setPreferredSize(new Dimension(10, 10));
    button4.setPreferredSize(new Dimension(10, 10));
    button5.setPreferredSize(new Dimension(10, 10));
    button6.setPreferredSize(new Dimension(10, 10));
    button7.setPreferredSize(new Dimension(10, 10));
    button8.setPreferredSize(new Dimension(10, 10));
    button9.setPreferredSize(new Dimension(10, 10));
    button10.setPreferredSize(new Dimension(10, 10));

    p.add(button1);
    p.add(button2);
    p.add(button3);
    p.add(button4);
    p.add(button5);
    p.add(button6);
    p.add(button7);
    p.add(button8);
    p.add(button9);
    p.add(button10);


    getContentPane().add(p, BorderLayout.CENTER);
  }*/

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

    /*JMenu menuEraser = new JMenu("Eraser");
    menuPen.add(menuEraser);

    JMenu menuRect = new JMenu("Rectangle");
    menuPen.add(menuRect);*/

    this.setJMenuBar(menubar);

    label = new JLabel("");
    fileChooser = new JFileChooser();
    JPanel panel = new JPanel();
    panel.add(label);
    Container container = getContentPane();
    container.add(panel);
  }

  public void mouseClicked (MouseEvent arg0) {
    Point point = arg0.getPoint();
    label2.setText("x:" + point.x + ",y:" + point.y);
  }

  public void mouseEntered (MouseEvent arg0) {
    lastx = arg0.getX();
    lasty = arg0.getY();
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
    if (eraser == 1) {
      /*panel.clearRect(lastx, lasty, w, h);*/
    }
    else if (rect == 1) {
      repaint();
    }
    else if (oval == 1) {
      repaint();
    }
    else if (eraser != 1 || rect != 1 || oval != 1) {
      panel.drawLine(lastx,lasty,newx,newy);
      lastx=newx;
  		lasty=newy;
    }
	}

  public void mouseMoved(MouseEvent arg0) {
  }


	private void init() {
		this.setTitle("Simple Draw");
		/*this.setSize(300, 200);*/
    this.setBounds(200, 200, 500, 300);
		panel=new DrawPanel();
		this.getContentPane().add(panel);
    panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel.setPenColor(Color.black);

    label2 = new JLabel("座標を表示");

    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(label2, BorderLayout.PAGE_END);
	}

  private void init2() {
		this.setTitle("Tool Bar");
    this.setBounds(800, 200, 150, 300);
		this.setVisible(true);
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
    else if (command == "Choose color") {
      eraser = 0;
      JColorChooser colorchooser = new JColorChooser();
      Color color = colorchooser.showDialog(this,"choose color", Color.blue);
      panel.setPenColor(color);
    }
    else if (command == "Eraser") {
      eraser = 1;
      rect = 0;
      oval = 0;
    }
    else if (command == "Rectangle") {
      eraser = 0;
      rect = 1;
      oval = 0;
    }
    else if (command == "Oval") {
      eraser = 0;
      rect = 0;
      oval = 1;
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

    //コンストラクタに引数を指定
		SimpleDraw f1 = new SimpleDraw();
		SimpleDraw f2 = new SimpleDraw();
		/*AwtFrameTest f3 = new AwtFrameTest("FrameTest3");*/

    f1.initMenu();
    f1.init();

    f2.init2();
    f2.initButton();

	}

}
