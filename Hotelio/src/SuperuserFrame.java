import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;


public class SuperuserFrame extends JFrame {

	private ReservationManager rm;
	private JButton IncomeButton,CompletenessButton,DataManagerButton,LogoutButton;
	private IncomeReportFrame irf;
	private CompletenessReportFrame crf;

	public SuperuserFrame(ReservationManager rm) {
		this.rm = rm;

		ButtonListener buttonListener = new ButtonListener(this);

		IncomeButton = new JButton("\u0391\u03BD\u03B1\u03C6\u03BF\u03C1\u03AC \u0395\u03C3\u03CC\u03B4\u03C9\u03BD");

		CompletenessButton = new JButton("\u0391\u03BD\u03B1\u03C6\u03BF\u03C1\u03AC \u03C0\u03BB\u03B7\u03C1\u03CC\u03C4\u03B7\u03C4\u03B1\u03C2");

		DataManagerButton = new JButton("\u0395\u03C0\u03B5\u03BE\u03B5\u03C1\u03B3\u03B1\u03C3\u03AF\u03B1 \u0394\u03B5\u03B4\u03BF\u03BC\u03AD\u03BD\u03C9\u03BD");

		LogoutButton = new JButton("\u0391\u03C0\u03BF\u03C3\u03CD\u03BD\u03B4\u03B5\u03C3\u03B7");

		IncomeButton.addActionListener(buttonListener);
		CompletenessButton.addActionListener(buttonListener);
		DataManagerButton.addActionListener(buttonListener);
		LogoutButton.addActionListener(buttonListener);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(40)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(CompletenessButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(DataManagerButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(IncomeButton, Alignment.TRAILING))
								.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
								.addComponent(LogoutButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addGap(58))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(28)
						.addComponent(IncomeButton)
						.addGap(49)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(CompletenessButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(LogoutButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
								.addGap(49)
								.addComponent(DataManagerButton)
								.addContainerGap(10, Short.MAX_VALUE))
				);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {IncomeButton, CompletenessButton, DataManagerButton});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {IncomeButton, CompletenessButton, DataManagerButton});
		getContentPane().setLayout(groupLayout);

		this.setVisible(true);
		this.pack();
	}


	class ButtonListener implements ActionListener{

		/*Το παρακάτω τμήμα κώδικα χρησιμεύει έτσι ώστε παίρνοντας ως παράμετρο το frame να έχουμε την δυνατότητα
		 * με το πάτημα του κουμπιού "ΟΚ" να κλείνει το συγκεκριμένο παράθυρο  */
		private SuperuserFrame frame;
		public ButtonListener(SuperuserFrame frame){
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == IncomeButton){
				irf = new IncomeReportFrame(rm);
				irf.createChart(rm);
				irf.setVisible(true);
			}
			else if(e.getSource() == CompletenessButton){
				crf = new CompletenessReportFrame(rm);
				crf.createChart(rm);
				crf.setVisible(true);
			}
			else if(e.getSource() == DataManagerButton){
				DataManagementFrame edf = new DataManagementFrame(rm);
			}
			else if(e.getSource() == LogoutButton){
				frame.dispose();
			}


		}

	}

}
