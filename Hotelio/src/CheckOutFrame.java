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
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CheckOutFrame extends JFrame {

	private ReservationManager rm;
	private JTable table;
	private ArrayList<Reservation> temp;
	
	public CheckOutFrame(ReservationManager rm) {
		

		this.setPreferredSize(new Dimension(800,500));
		this.rm = rm;

		ButtonListener buttonListener = new ButtonListener(this);

		temp = new ArrayList<Reservation>();

		for(Reservation r : rm.getReservations()){
			if(r.getClient().isCheckedIn()){
				temp.add(r);
			}
		}

		if (temp.size() == 0)
			JOptionPane.showMessageDialog(null, "Δεν υπάρχουν checked-in δωμάτια", "Check-Out", JOptionPane.INFORMATION_MESSAGE);
		
		else{
		table = new JTable();
		TableModel model = new TableModel(temp);

		JScrollPane scrollPane = new JScrollPane(table);
		table.setModel(model);

		JButton btnCheckout = new JButton("Check-Out");
		btnCheckout.addActionListener(buttonListener);
		

		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(238)
						.addComponent(btnCheckout, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
						.addGap(244))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
						.addGap(65)
						.addComponent(btnCheckout, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
						.addGap(71))
				);
		this.getContentPane().setLayout(groupLayout);

		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		}
	}

	class ButtonListener implements ActionListener{

		private CheckOutFrame chkFrame;

		public ButtonListener(CheckOutFrame frame){
			this.chkFrame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {

			int row = table.getSelectedRow();
			Reservation selectedReservation = temp.get(table.convertRowIndexToModel(row));
			CostFrame costFrame = new CostFrame(rm, selectedReservation);

			chkFrame.repaint();
		}
	}
}
