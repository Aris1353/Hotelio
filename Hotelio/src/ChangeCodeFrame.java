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
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JButton okButton;

	public ChangeCodeFrame(ReservationManager rm){
		this.rm=rm;
		ButtonListener listener = new ButtonListener(this);
		passwordField_1 = new JPasswordField();

		passwordField_2 = new JPasswordField();

		JLabel pass1Label = new JLabel("\u03A0\u03B1\u03BB\u03B9\u03CC\u03C2 \u039A\u03C9\u03B4\u03B9\u03BA\u03CC\u03C2");
		pass1Label.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel pass2Label = new JLabel("\u039A\u03B1\u03B9\u03BD\u03BF\u03CD\u03C1\u03B3\u03B9\u03BF\u03C2 \u039A\u03C9\u03B4\u03B9\u03BA\u03CC\u03C2");
		pass2Label.setFont(new Font("Tahoma", Font.BOLD, 12));

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
												.addComponent(pass1Label)
												.addComponent(pass2Label))
												.addGap(18)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(passwordField_2, 66, 66, 66)
														.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
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
								.addComponent(pass1Label)
								.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(pass2Label)
										.addComponent(passwordField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(39)
										.addComponent(okButton)
										.addContainerGap())
				);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {passwordField_1, passwordField_2});
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {pass1Label, pass2Label});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {passwordField_1, passwordField_2});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {pass1Label, pass2Label});
		getContentPane().setLayout(groupLayout);


		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	class ButtonListener implements ActionListener{

		private ChangeCodeFrame frame;
		public ButtonListener(ChangeCodeFrame frame){
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton){
				if(passwordField_1.getText().toString().equals(rm.getPassword().toString())){
					rm.setPassword(passwordField_2.getText().toString());
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
