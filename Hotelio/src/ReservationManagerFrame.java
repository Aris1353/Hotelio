import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;




public class ReservationManagerFrame extends JFrame {

	private ReservationManager rm;
	private JTable table;
	private JTextField textField_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3; 
	private JScrollPane scrollPane;
	private TableModel model;
	private ArrayList<Reservation> reservations;
	private ButtonListener buttonListener = new ButtonListener(this);


	public ReservationManagerFrame(ReservationManager rm) {
		this.setPreferredSize(new Dimension(1000,600));
		this.rm = rm;


		table = new JTable();


		scrollPane = new JScrollPane(table);


		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel label = new JLabel("\u0395\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF");

		button = new JButton("\u0391\u03BD\u03B1\u03B6\u03AE\u03C4\u03B7\u03C3\u03B7");
		button.addActionListener(new SearchListener());

		button_1 = new JButton("\u0391\u03BB\u03BB\u03B1\u03B3\u03AE");
		button_1.addActionListener(buttonListener);
		button_1.setVisible(false);

		button_2 = new JButton("\u0394\u03B9\u03B1\u03B3\u03C1\u03B1\u03C6\u03AE");
		button_2.addActionListener(buttonListener);
		button_2.setVisible(false);

		button_3 = new JButton("\u0386\u03BA\u03C5\u03C1\u03BF");
		button_3.addActionListener(buttonListener);
		button_3.setVisible(false);

		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(20)
										.addComponent(label, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(41)
												.addComponent(button, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 254, Short.MAX_VALUE)
																.addGap(164)
																.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 255, Short.MAX_VALUE)
																.addGap(84))
																.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 734, GroupLayout.PREFERRED_SIZE)))
																.addGroup(groupLayout.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
																		.addGap(294))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addGap(38)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
								.addGap(49)
								.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addGap(73))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(36)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(label)
												.addComponent(textField_1))
												.addGap(32)
												.addComponent(button, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
												.addGap(432))
				);
		this.getContentPane().setLayout(groupLayout);

		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}


	class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name = textField_1.getText();
			if(name.equals(""))
				JOptionPane.showMessageDialog(null, "Το πεδίου του επωνύμου είναι κενο", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
			else if(rm.getReservations().size() == 0)
				JOptionPane.showMessageDialog(null, "Δεν υπάρχουν κρατήσεις", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
			else{
				reservations = new ArrayList<Reservation>();
				reservations = rm.searchClient(name);
				if(reservations.size() == 0 )
					JOptionPane.showMessageDialog(null, "Δεν υπάρχει πελάτης με τέτοιο όνομα", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
				else{
					model = new TableModel(reservations);
					table.setModel(model);
				}

				button_1.setVisible(true);
				button_2.setVisible(true);
				button_3.setVisible(true);
			}

		}
	}

	class ButtonListener implements ActionListener{

		ReservationManagerFrame rmframe;


		public ButtonListener(ReservationManagerFrame rmf){
			this.rmframe = rmf;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button_1){
				int row = table.getSelectedRow();
				Reservation selectedReservation = reservations.get(table.convertRowIndexToModel(row));
				new ModifyReservationFrame(rm , selectedReservation); 
				reservations = new ArrayList<Reservation>();
				reservations = rm.searchClient(textField_1.getText());
				model= new TableModel(reservations);
				table.setModel(model);
				

			}
			else if(e.getSource() == button_2){

				int row = table.getSelectedRow();
				Reservation sr = reservations.get(table.convertRowIndexToModel(row));
				rm.deleteReservation(sr);
				reservations = new ArrayList<Reservation>();
				reservations = rm.searchClient(textField_1.getText());
				model= new TableModel(reservations);
				table.setModel(model);
				

			}
			else if(e.getSource() == button_3){
				rmframe.dispose();
			}			
		}
	}
}
