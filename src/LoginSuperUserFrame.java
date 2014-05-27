import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;




public class LoginSuperUserFrame extends JFrame {

	private ReservationManager rm;
	private JButton EisodosButton;
	private JButton AkiroButton;
	private JPasswordField passwordField_1;
	
	public LoginSuperUserFrame(ReservationManager rm) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setType(Type.POPUP);
		setResizable(false);
		
		ButtonListener buttonListener = new ButtonListener();
		
		JLabel EisodosLabel = new JLabel("\u0395\u0399\u03A3\u0391\u0393\u0395\u03A4\u0395 \u039A\u03A9\u0394\u0399\u039A\u039F \u0394\u0399\u0391\u03A7\u0395\u0399\u03A1\u0399\u03A3\u03A4\u0397");
		EisodosLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		passwordField_1 = new JPasswordField();
		
		JLabel PasswordLabel = new JLabel("\u039A\u03C9\u03B4\u03B9\u03BA\u03CC\u03C2 :");
		PasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		EisodosButton = new JButton("\u0395\u03AF\u03C3\u03BF\u03B4\u03BF\u03C2");
		EisodosButton.addActionListener(buttonListener);
		AkiroButton = new JButton("\u0386\u03BA\u03C5\u03C1\u03BF");		
		AkiroButton.addActionListener(buttonListener);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(62)
					.addComponent(EisodosLabel)
					.addContainerGap(63, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(114)
					.addComponent(PasswordLabel, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(AkiroButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(EisodosButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addGap(192))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(EisodosLabel)
					.addGap(89)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(PasswordLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addComponent(EisodosButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(AkiroButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(73))
		);
		getContentPane().setLayout(groupLayout);
		
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	
	}
	
	
	
	class ButtonListener implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == EisodosButton ){
				SuperuserFrame suf = new SuperuserFrame(rm);
			}
			else if(e.getSource() == AkiroButton){
				
			}


		}

	}
}
