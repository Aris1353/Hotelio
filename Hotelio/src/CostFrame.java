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
	private JTextField ExtraCostTextField;
	private JTextField DiscountButton;
	public CostFrame(ReservationManager rm, Reservation r) {

		this.rm = rm;
		this.r = r;

		JLabel ExtraCostLabel = new JLabel("\u0395\u03C0\u03B9\u03C3\u03C0\u03C1\u03CC\u03C3\u03B8\u03B5\u03C4\u03B5\u03C2 \u03A7\u03C1\u03B5\u03CE\u03C3\u03B5\u03B9\u03C2");

		JLabel DiscountLabel = new JLabel("\u0388\u03BA\u03C0\u03C4\u03C9\u03C3\u03B7");

		ExtraCostTextField = new JTextField("0");
		ExtraCostTextField.setColumns(10);

		DiscountButton = new JTextField("0");
		DiscountButton.setColumns(10);

		JButton okButton = new JButton("\u039F\u039A");
		okButton.addActionListener(new ButtonListener(this));

		JLabel PercentLabel = new JLabel("%");


		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(ExtraCostLabel, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(DiscountLabel, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
										.addGap(25)))
										.addGap(70)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(ExtraCostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(DiscountButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(PercentLabel)))
														.addGap(53))
														.addGroup(groupLayout.createSequentialGroup()
																.addGap(177)
																.addComponent(okButton, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
																.addGap(192))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(33)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(ExtraCostLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(ExtraCostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(36)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(DiscountLabel, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
										.addComponent(DiscountButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(PercentLabel))
										.addGap(113)
										.addComponent(okButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap())
				);
		getContentPane().setLayout(groupLayout);

		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	class ButtonListener implements ActionListener{

		/*Το παρακάτω τμήμα κώδικα χρησιμεύει έτσι ώστε παίρνοντας ως παράμετρο το frame να έχουμε την δυνατότητα
		 * με το πάτημα του κουμπιού "ΟΚ" να κλείνει το συγκεκριμένο παράθυρο  */
		private CostFrame costFrame;
		public ButtonListener(CostFrame frame){
			this.costFrame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Double extraCosts = Double.parseDouble(ExtraCostTextField.getText()); 
			Double discount = Double.parseDouble(DiscountButton.getText());

			double totalcost = rm.checkOutAReservation(r, extraCosts, discount);
			costFrame.repaint();
			JOptionPane.showMessageDialog(null, "Το τελικό κόστος είναι: " + totalcost,"Συνολικό κόστος", JOptionPane.INFORMATION_MESSAGE);
			costFrame.dispose();
		}
	}
}
