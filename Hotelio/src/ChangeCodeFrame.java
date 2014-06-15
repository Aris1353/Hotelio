import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ChangeCodeFrame extends JFrame {

	private ReservationManager rm;
	private JPasswordField OLDPasswordField;
	private JPasswordField NEWPasswordField;
	private JButton okButton;

	public ChangeCodeFrame(ReservationManager rm){
		this.rm=rm;
		ButtonListener listener = new ButtonListener(this);
		OLDPasswordField = new JPasswordField();

		NEWPasswordField = new JPasswordField();

		JLabel OLDPasswordLabel = new JLabel("\u03A0\u03B1\u03BB\u03B9\u03CC\u03C2 \u039A\u03C9\u03B4\u03B9\u03BA\u03CC\u03C2");
		OLDPasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel NEWPasswordLabel = new JLabel("\u039A\u03B1\u03B9\u03BD\u03BF\u03CD\u03C1\u03B3\u03B9\u03BF\u03C2 \u039A\u03C9\u03B4\u03B9\u03BA\u03CC\u03C2");
		NEWPasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		okButton = new JButton("\u0391\u03C0\u03BF\u03B8\u03AE\u03BA\u03B5\u03C5\u03C3\u03B7");
		okButton.addActionListener(listener);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(20)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(OLDPasswordLabel)
												.addComponent(NEWPasswordLabel))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(NEWPasswordField, 66, 66, 66)
														.addComponent(OLDPasswordField, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
														.addGroup(groupLayout.createSequentialGroup()
																.addGap(81)
																.addComponent(okButton)))
																.addContainerGap(37, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(55)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(OLDPasswordLabel)
								.addComponent(OLDPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(NEWPasswordLabel)
										.addComponent(NEWPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(39)
										.addComponent(okButton)
										.addContainerGap())
				);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {OLDPasswordField, NEWPasswordField});
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {OLDPasswordLabel, NEWPasswordLabel});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {OLDPasswordField, NEWPasswordField});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {OLDPasswordLabel, NEWPasswordLabel});
		getContentPane().setLayout(groupLayout);


		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	class ButtonListener implements ActionListener{

		/*Το παρακάτω τμήμα κώδικα χρησιμεύει έτσι ώστε παίρνοντας ως παράμετρο το frame να έχουμε την δυνατότητα
		 * με το πάτημα του κουμπιού "ΟΚ" να κλείνει το συγκεκριμένο παράθυρο  */
		private ChangeCodeFrame frame;
		public ButtonListener(ChangeCodeFrame frame){
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton){
				if(OLDPasswordField.getText().toString().equals(rm.getPassword().toString())){   //Έλεγχος αν η τιμή που καταχωρήθηκε είναι ίδια με τον κωδικό
					rm.setPassword(NEWPasswordField.getText().toString()); //Αλλαγή κωδικού
					JOptionPane.showMessageDialog(null, "Η αλλαγή έγινε επιτυχώς","Done",JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				}

				else{
					JOptionPane.showMessageDialog(null, "Λάθος Κωδικός","Error",JOptionPane.ERROR_MESSAGE);
				}
			}

		}

	}
}
