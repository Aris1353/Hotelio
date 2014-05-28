import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;


public class ReservationManagerFrame extends JFrame {

	private ReservationManager rm;
	private JTable table;
	private JButton changeButton,deleteButton,cancelButton, searchButton;
	private JTextField textField;


	public ReservationManagerFrame(ReservationManager rm) {
		this.rm = rm;

		table = new JTable();
		table.setVisible(false);

		ButtonListener listener = new ButtonListener(this);

		changeButton = new JButton("\u0391\u03BB\u03BB\u03B1\u03B3\u03AE");
		changeButton.setVisible(false);
		changeButton.addActionListener(listener);

		deleteButton = new JButton("\u0394\u03B9\u03B1\u03B3\u03C1\u03B1\u03C6\u03AE");
		deleteButton.setVisible(false);
		deleteButton.addActionListener(listener);

		cancelButton = new JButton("\u0391\u03BA\u03CD\u03C1\u03C9\u03C3\u03B7");
		cancelButton.setVisible(false);
		cancelButton.addActionListener(listener);

		JLabel label = new JLabel("\u0395\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF");

		textField = new JTextField();
		textField.setColumns(10);

		searchButton = new JButton("\u0391\u03BD\u03B1\u03B6\u03AE\u03C4\u03B7\u03C3\u03B7");
		SearchListener listener2 = new SearchListener();
		searchButton.addActionListener(listener2);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(label)
										.addGap(18)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(26)
												.addComponent(searchButton)))
												.addPreferredGap(ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(table, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE)
														.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
																.addGap(116)
																.addComponent(changeButton)
																.addGap(138)
																.addComponent(deleteButton)))
																.addGap(26))
																.addGroup(groupLayout.createSequentialGroup()
																		.addContainerGap(415, Short.MAX_VALUE)
																		.addComponent(cancelButton)
																		.addGap(241))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(31)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(label)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(26)
												.addComponent(searchButton))
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(18)
														.addComponent(table, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)))
														.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
														.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																.addComponent(deleteButton)
																.addComponent(changeButton))
																.addGap(18)
																.addComponent(cancelButton)
																.addGap(23))
				);
		getContentPane().setLayout(groupLayout);

		this.setVisible(true);
		this.pack();
	}



	class ButtonListener implements ActionListener{

		private ReservationManagerFrame frame;
		public ButtonListener(ReservationManagerFrame frame){
			this.frame=frame;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == changeButton){
				table.getSelectedRow();
				ArrayList<Room> freeRooms = new ArrayList<Room>();
				//freeRooms = rm.searchFreeSameRooms(rfm.roomOpenFile(), res);
				//rm.newReservation(c, rooms, freeRooms.get(0));	
			}
			else if(e.getSource() == deleteButton){
				table.getSelectedRow();
				//rm.deleteReservation(r);
			}
			else if(e.getSource() == cancelButton){
				frame.dispose();
			}

		}
	}


	class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name = textField.getText();
			if(name.equals(""))
				JOptionPane.showMessageDialog(null, "Το πεδίου του επωνύμου είναι κενο", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
			else if(rm.getReservations().size() == 0)
				JOptionPane.showMessageDialog(null, "Δεν υπάρχουν κρατήσεις", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
			else{
				ArrayList<Reservation> reservations = new ArrayList<Reservation>();
				reservations = rm.searchClient(name);
				if(reservations.size() == 0 )
					JOptionPane.showMessageDialog(null, "Δεν υπάρχει πελάτης με τέτοιο όνομα", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
				else{
					DefaultTableModel model = new DefaultTableModel(); 
					table.setModel(model);
					model.addColumn("Επώνυμο");
					model.addColumn("Όνομα");
					model.addColumn("Ενήλικοι");
					model.addColumn("Ανήλικοι");
					model.addColumn("’φιξη");
					model.addColumn("Αναχώρηση");
					model.addColumn("Τύπος Δωματίου");
					model.addColumn("Τύπος Δωματίου");
					model.addColumn("Checked-In");
					String nutrition;
					for(int i=0; i<=reservations.size(); i++){
						if(reservations.get(i).getClient().getNutrition() == 0)
							nutrition = "-";
						else if(reservations.get(i).getClient().getNutrition() == 1)
							nutrition = "Ημιδιατροφή";
						else{
							nutrition = "Πλήρης Διατροφη";

							model.addRow(new Object[]{reservations.get(i).getClient().getSurname(), reservations.get(i).getClient().getName() 
									,reservations.get(i).getClient().getAdults(), reservations.get(i).getClient().getUnderages(),
									reservations.get(i).getClient().getArrival(), reservations.get(i).getClient().getDeparture(),
									reservations.get(i).getRoom().getClass(), nutrition, 
									reservations.get(i).getRoom().isCheckedIn()});

						}
					}

					changeButton.setVisible(true);
					deleteButton.setVisible(true);
					cancelButton.setVisible(true);
				}

			}
		}		

	}


}
