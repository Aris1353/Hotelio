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
	private JButton ReservationButton;
	private JButton CheckinButton;
	private JButton CheckoutButton;
	private JButton ReservationManagerButton;
	private JButton ChangeRoomButton;
	private JButton FreeRoomsButton;
	private JButton LoginButton;

	public MainFrame(ReservationManager rm) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.rm = rm;
		ButtonListener buttonListener = new ButtonListener();

		ReservationButton = new JButton("\u039A\u03C1\u03AC\u03C4\u03B7\u03C3\u03B7 \u0394\u03C9\u03BC\u03B1\u03C4\u03AF\u03BF\u03C5");
		ReservationButton.addActionListener(buttonListener);

		CheckinButton = new JButton("Check-In");
		CheckinButton.addActionListener(buttonListener);

		CheckoutButton = new JButton("Check-Out");
		CheckoutButton.addActionListener(buttonListener);

		ReservationManagerButton = new JButton("\u0394\u03B9\u03B1\u03C7\u03B5\u03AF\u03C1\u03B7\u03C3\u03B7 \u039A\u03C1\u03AC\u03C4\u03B7\u03C3\u03B7\u03C2");
		ReservationManagerButton.addActionListener(buttonListener);

		ChangeRoomButton = new JButton("\u0391\u03BB\u03BB\u03B1\u03B3\u03AE \u0394\u03C9\u03BC\u03B1\u03C4\u03AF\u03BF\u03C5");
		ChangeRoomButton.addActionListener(buttonListener);

		FreeRoomsButton = new JButton("\u0394\u03B9\u03B1\u03B8\u03AD\u03C3\u03B9\u03BC\u03B1 \u0394\u03C9\u03BC\u03AC\u03C4\u03B9\u03B1");
		FreeRoomsButton.addActionListener(buttonListener);

		LoginButton = new JButton("\u0395\u03AF\u03C3\u03BF\u03B4\u03BF\u03C2 \u0394\u03B9\u03B1\u03C7\u03B5\u03B9\u03C1\u03B7\u03C3\u03C4\u03AE");
		LoginButton.addActionListener(buttonListener);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addGap(90)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(ReservationButton)
								.addComponent(CheckinButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(CheckoutButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(ReservationManagerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(ChangeRoomButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
								.addComponent(FreeRoomsButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
								.addGap(132)
								.addComponent(LoginButton)
								.addGap(84))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(170)
										.addComponent(LoginButton))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(14)
												.addComponent(ReservationButton)
												.addGap(37)
												.addComponent(CheckinButton)
												.addGap(40)
												.addComponent(CheckoutButton)
												.addGap(41)
												.addComponent(ReservationManagerButton)
												.addGap(37)
												.addComponent(ChangeRoomButton)
												.addGap(35)
												.addComponent(FreeRoomsButton)))
												.addContainerGap(17, Short.MAX_VALUE))
				);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {ReservationButton, CheckinButton, CheckoutButton, ReservationManagerButton, ChangeRoomButton, FreeRoomsButton});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {ReservationButton, CheckinButton, CheckoutButton, ReservationManagerButton, ChangeRoomButton, FreeRoomsButton});
		getContentPane().setLayout(groupLayout);


		this.setVisible(true);
		this.pack();
	}

	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ReservationButton){
				ReservationFrame resFrame = new ReservationFrame(rm);
			}
			else if(e.getSource() == CheckinButton){
				CheckInFrame chkinFrame = new CheckInFrame(rm);
			}
			else if(e.getSource() == CheckoutButton){
				CheckOutFrame chkoutFrame = new CheckOutFrame(rm);
			}
			else if(e.getSource() == ReservationManagerButton){
				ReservationManagerFrame resFrame = new ReservationManagerFrame(rm);
			}
			else if(e.getSource() == ChangeRoomButton){
				ChangeRoomFrame chrFrame = new ChangeRoomFrame(rm);
			}
			else if(e.getSource() == FreeRoomsButton){
				FreeRoomsFrame freeroomsFrame = new FreeRoomsFrame(rm);
			}
			else if(e.getSource() == LoginButton){
				LoginSuperUserFrame loginSUFrame = new LoginSuperUserFrame(rm);
			}
		}

	}


}
