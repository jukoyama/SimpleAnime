import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class ColorChooser extends JFrame {

	private static final long serialVersionUID = -9031046610974332358L;
	private JSlider r,g,b;
	private int rValue,gValue,bValue;
	private Color color = (Color.WHITE);
	private JPanel panel;
	public JLabel label1 = new JLabel("RGB Color: "+rValue+","+gValue+","+bValue);

	public ColorChooser(){
		super("RGB Colour Picker");

		add(label1, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		setSize(400, 400);
		setVisible(true);

		GridBagConstraints gbc = new GridBagConstraints();
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(color);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx  = 0.5;



		r = new JSlider(JSlider.HORIZONTAL,0,255,0);
		r.setMajorTickSpacing(50);
		r.setPaintLabels(true);

		g = new JSlider(JSlider.HORIZONTAL,0,255,0);
		g.setMajorTickSpacing(50);
		g.setPaintLabels(true);

		b = new JSlider(JSlider.HORIZONTAL,0,255,0);
		b.setMajorTickSpacing(50);
		b.setPaintLabels(true);

		event e = new event();
		r.addChangeListener(e);
		g.addChangeListener(e);
		b.addChangeListener(e);



		gbc.gridx =0;
		gbc.gridy =0;
		panel.add(r, gbc);
		gbc.gridx =0;
		gbc.gridy =1;
		panel.add(g, gbc);
		gbc.gridx =0;
		gbc.gridy =2;
		panel.add(b,gbc);


		JLabel spacetaker = new JLabel();
		gbc.gridx =0;
		gbc.gridy =3;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.PAGE_END;
		panel.add(spacetaker,gbc);
	}


	public class event implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			rValue = r.getValue();
			gValue = g.getValue();
			bValue = b.getValue();
			color = new Color(rValue,gValue,bValue);
			panel.setBackground(color);
			label1.setText("RGB Color: "+rValue+","+gValue+","+bValue);

		}

	}

}
	
