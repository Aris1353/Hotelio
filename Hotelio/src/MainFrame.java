import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;


public class MainFrame extends JFrame {
	private ReservationManager rm;
	private JButton KratisiButton;
	private JButton CheckinButton;
	private JButton CheckoutButton;
	private JButton DiaxirisiButton;
	private JButton AllagiButton;
	private JButton DiathesimaButton;
	private JButton EisodosButton;

	public MainFrame(ReservationManager rm) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.rm = rm;
		ButtonListener buttonListener = new ButtonListener();

		KratisiButton = new JButton("\u039A\u03C1\u03AC\u03C4\u03B7\u03C3\u03B7 \u0394\u03C9\u03BC\u03B1\u03C4\u03AF\u03BF\u03C5");
		KratisiButton.addActionListener(buttonListener);

		CheckinButton = new JButton("Check-In");
		CheckinButton.addActionListener(buttonListener);

		CheckoutButton = new JButton("Check-Out");
		CheckoutButton.addActionListener(buttonListener);

		DiaxirisiButton = new JButton("\u0394\u03B9\u03B1\u03C7\u03B5\u03AF\u03C1\u03B7\u03C3\u03B7 \u039A\u03C1\u03AC\u03C4\u03B7\u03C3\u03B7\u03C2");
		DiaxirisiButton.addActionListener(buttonListener);

		AllagiButton = new JButton("\u0391\u03BB\u03BB\u03B1\u03B3\u03AE \u0394\u03C9\u03BC\u03B1\u03C4\u03AF\u03BF\u03C5");
		AllagiButton.addActionListener(buttonListener);

		DiathesimaButton = new JButton("\u0394\u03B9\u03B1\u03B8\u03AD\u03C3\u03B9\u03BC\u03B1 \u0394\u03C9\u03BC\u03AC\u03C4\u03B9\u03B1");
		DiathesimaButton.addActionListener(buttonListener);

		EisodosButton = new JButton("\u0395\u03AF\u03C3\u03BF\u03B4\u03BF\u03C2 \u0394\u03B9\u03B1\u03C7\u03B5\u03B9\u03C1\u03B7\u03C3\u03C4\u03AE");
		EisodosButton.addActionListener(buttonListener);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addGap(90)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(KratisiButton)
								.addComponent(CheckinButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(CheckoutButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(DiaxirisiButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(AllagiButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(DiathesimaButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
								.addGap(132)
								.addComponent(EisodosButton)
								.addGap(84))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(170)
										.addComponent(EisodosButton))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(14)
												.addComponent(KratisiButton)
												.addGap(37)
												.addComponent(CheckinButton)
												.addGap(40)
												.addComponent(CheckoutButton)
												.addGap(41)
												.addComponent(DiaxirisiButton)
												.addGap(37)
												.addComponent(AllagiButton)
												.addGap(35)
												.addComponent(DiathesimaButton)))
												.addContainerGap(17, Short.MAX_VALUE))
				);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {KratisiButton, CheckinButton, CheckoutButton, DiaxirisiButton, AllagiButton, DiathesimaButton});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {KratisiButton, CheckinButton, CheckoutButton, DiaxirisiButton, AllagiButton, DiathesimaButton});
		getContentPane().setLayout(groupLayout);
		
		
		this.setVisible(true);
		this.pack();
	}

	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == KratisiButton){
				ReservationFrame resFrame = new ReservationFrame(rm);
			}
			else if(e.getSource() == CheckinButton){
				CheckInFrame chkinFrame = new CheckInFrame(rm);
			}
			else if(e.getSource() == CheckoutButton){
				CheckOutFrame chkoutFrame = new CheckOutFrame(rm);
			}
			else if(e.getSource() == DiaxirisiButton){
				ReservationManagerFrame resFrame = new ReservationManagerFrame(rm);
			}
			else if(e.getSource() == AllagiButton){
				ChangeRoomFrame chrFrame = new ChangeRoomFrame(rm);
			}
			else if(e.getSource() == DiathesimaButton){
				FreeRoomsFrame freeroomsFrame = new FreeRoomsFrame(rm);
			}
			else if(e.getSource() == EisodosButton){
				LoginSuperUserFrame loginSUFrame = new LoginSuperUserFrame(rm);
			}
		}

	}


}
