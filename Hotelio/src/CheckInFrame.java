import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class CheckInFrame extends JFrame {

	private ReservationManager rm;
	private JTable table;
	private ArrayList<Reservation> temp;

	public CheckInFrame(ReservationManager rm) {
		this.setPreferredSize(new Dimension(800,500));
		this.rm = rm;

		ButtonListener buttonListener = new ButtonListener(this);

		temp = new ArrayList<Reservation>();

		Date today = new Date();
		for(Reservation r : rm.getReservations()){
			if(r.getClient().getArrival().getDate() == today.getDate() && r.getClient().getArrival().getMonth() == today.getMonth() && r.getClient().getArrival().getYear() == today.getYear()){
				temp.add(r);
			}
		}

		if (temp.size() == 0)
			JOptionPane.showMessageDialog(null, "Δεν υπάρχουν αφίξεις για σήμερα", "Check-In", JOptionPane.INFORMATION_MESSAGE);

		else{
			table = new JTable();
			TableModel model = new TableModel(temp);

			JScrollPane scrollPane = new JScrollPane(table);
			table.setModel(model);

			JButton btnCheckin = new JButton("Check-In");
			btnCheckin.addActionListener(buttonListener);

			GroupLayout groupLayout = new GroupLayout(this.getContentPane());
			groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
					.addGroup(groupLayout.createSequentialGroup()
							.addGap(238)
							.addComponent(btnCheckin, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
							.addGap(244))
					);
			groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
							.addGap(65)
							.addComponent(btnCheckin, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
							.addGap(71))
					);
			this.getContentPane().setLayout(groupLayout);

			this.pack();
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}

	class ButtonListener implements ActionListener{

		private CheckInFrame chkFrame;

		public ButtonListener(CheckInFrame frame){
			this.chkFrame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {

			int row = table.getSelectedRow();
			Reservation selectedReservation = temp.get(table.convertRowIndexToModel(row));
			rm.checkInAReservation(selectedReservation);


			JOptionPane.showMessageDialog(null, "Το check-in επιβεβαιώθηκε", "Check-In", JOptionPane.INFORMATION_MESSAGE);

			chkFrame.repaint();
		}
	}
}