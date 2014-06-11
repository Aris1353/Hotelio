import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


public class CostFrame extends JFrame {
	
	private ReservationManager rm;
	private Reservation r;
	private JTextField textField;
	private JTextField textField_1;
	public CostFrame(ReservationManager rm, Reservation r) {
		
		this.rm = rm;
		this.r = r;
		
		JLabel label = new JLabel("\u0395\u03C0\u03B9\u03C3\u03C0\u03C1\u03CC\u03C3\u03B8\u03B5\u03C4\u03B5\u03C2 \u03A7\u03C1\u03B5\u03CE\u03C3\u03B5\u03B9\u03C2");
		
		JLabel label_1 = new JLabel("\u0388\u03BA\u03C0\u03C4\u03C9\u03C3\u03B7");
		
		textField = new JTextField("0");
		textField.setColumns(10);
		
		textField_1 = new JTextField("0");
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u039F\u039A");
		button.addActionListener(new ButtonListener(this));
		
		JLabel label_2 = new JLabel("%");
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
							.addGap(25)))
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_2)))
					.addGap(53))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(177)
					.addComponent(button, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
					.addGap(192))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(113)
					.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	class ButtonListener implements ActionListener{

		private CostFrame costFrame;

		public ButtonListener(CostFrame frame){
			this.costFrame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Double extraCosts = Double.parseDouble(textField.getText());
			Double discount = Double.parseDouble(textField_1.getText());
			
			double totalcost = rm.checkOutAReservation(r, extraCosts, discount);
			costFrame.repaint();
			JOptionPane.showMessageDialog(null, "Το τελικό κόστος είναι: " + totalcost,"Συνολικό κόστος", JOptionPane.INFORMATION_MESSAGE);
			costFrame.dispose();
		}
	}
}
