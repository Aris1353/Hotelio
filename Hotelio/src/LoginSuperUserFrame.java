import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;




public class LoginSuperUserFrame extends JFrame {

	private ReservationManager rm;
	private JButton LoginButton;
	private JButton CancelButton;
	private JPasswordField password;

	public LoginSuperUserFrame(ReservationManager rm) {
		this.rm=rm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setType(Type.POPUP);
		setResizable(false);

		ButtonListener buttonListener = new ButtonListener(this);

		JLabel LoginLabel = new JLabel("\u0395\u0399\u03A3\u0391\u0393\u0395\u03A4\u0395 \u039A\u03A9\u0394\u0399\u039A\u039F \u0394\u0399\u0391\u03A7\u0395\u0399\u03A1\u0399\u03A3\u03A4\u0397");
		LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));

		password = new JPasswordField();

		JLabel PasswordLabel = new JLabel("\u039A\u03C9\u03B4\u03B9\u03BA\u03CC\u03C2 :");
		PasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		LoginButton = new JButton("\u0395\u03AF\u03C3\u03BF\u03B4\u03BF\u03C2");
		LoginButton.addActionListener(buttonListener);
		CancelButton = new JButton("\u0386\u03BA\u03C5\u03C1\u03BF");		
		CancelButton.addActionListener(buttonListener);


		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(62)
						.addComponent(LoginLabel)
						.addContainerGap(63, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(114)
								.addComponent(PasswordLabel, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
										.addComponent(password, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
										.addComponent(LoginButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
										.addGap(192))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(43)
						.addComponent(LoginLabel)
						.addGap(89)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(PasswordLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGap(39)
								.addComponent(LoginButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addGap(31)
								.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addGap(73))
				);
		getContentPane().setLayout(groupLayout);

		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

	}



	class ButtonListener implements ActionListener{

		/*Το παρακάτω τμήμα κώδικα χρησιμεύει έτσι ώστε παίρνοντας ως παράμετρο το frame να έχουμε την δυνατότητα
		 * με το πάτημα του κουμπιού "’κυρο" να κλείνει το συγκεκριμένο παράθυρο  */
		private LoginSuperUserFrame frame;
		public ButtonListener(LoginSuperUserFrame frame){
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == LoginButton ){

				if(password.getText().toString().equals(rm.getPassword())){  //Έλεγχος αν ο χρήστης πληκτρολόγησε το σωστό κωδικό
					SuperuserFrame suf = new SuperuserFrame(rm);
					frame.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null,"Wrong PassWord","Error",JOptionPane.ERROR_MESSAGE);
					password.setText(null);
					System.out.println(rm.getPassword());
				}
			}
			else if(e.getSource() == CancelButton){
				frame.dispose();
			}


		}

	}
}
