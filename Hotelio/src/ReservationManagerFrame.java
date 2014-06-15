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
	private JTextField SurnameTextField;
	private JButton SearchButton;
	private JButton ChangeButton;
	private JButton DeleteButton;
	private JButton CancelButton; 
	private JScrollPane scrollPane;
	private TableModel model;
	private ArrayList<Reservation> reservations;
	private ButtonListener buttonListener = new ButtonListener(this);


	public ReservationManagerFrame(ReservationManager rm) {
		this.setPreferredSize(new Dimension(1000,600));
		this.rm = rm;


		table = new JTable();


		scrollPane = new JScrollPane(table);


		SurnameTextField = new JTextField();
		SurnameTextField.setColumns(10);

		JLabel SurnameLabel = new JLabel("\u0395\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF");

		SearchButton = new JButton("\u0391\u03BD\u03B1\u03B6\u03AE\u03C4\u03B7\u03C3\u03B7");
		SearchButton.addActionListener(new SearchListener());

		ChangeButton = new JButton("\u0391\u03BB\u03BB\u03B1\u03B3\u03AE");
		ChangeButton.addActionListener(buttonListener);
		ChangeButton.setVisible(false);

		DeleteButton = new JButton("\u0394\u03B9\u03B1\u03B3\u03C1\u03B1\u03C6\u03AE");
		DeleteButton.addActionListener(buttonListener);
		DeleteButton.setVisible(false);

		CancelButton = new JButton("\u0386\u03BA\u03C5\u03C1\u03BF");
		CancelButton.addActionListener(buttonListener);
		CancelButton.setVisible(false);

		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(20)
										.addComponent(SurnameLabel, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(SurnameTextField, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createSequentialGroup()
												.addGap(41)
												.addComponent(SearchButton, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addGroup(groupLayout.createSequentialGroup()
																.addComponent(ChangeButton, GroupLayout.PREFERRED_SIZE, 254, Short.MAX_VALUE)
																.addGap(164)
																.addComponent(DeleteButton, GroupLayout.PREFERRED_SIZE, 255, Short.MAX_VALUE)
																.addGap(84))
																.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 734, GroupLayout.PREFERRED_SIZE)))
																.addGroup(groupLayout.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
																		.addGap(294))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addGap(38)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(ChangeButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(DeleteButton, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
								.addGap(49)
								.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addGap(73))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(36)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(SurnameLabel)
												.addComponent(SurnameTextField))
												.addGap(32)
												.addComponent(SearchButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
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
			String surname = SurnameTextField.getText();
			if(surname.equals("")) //Έλεγχος αν το πεδίο του επωνύμου είναι κενό
				JOptionPane.showMessageDialog(null, "Το πεδίου του επωνύμου είναι κενο", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
			else if(rm.getReservations().size() == 0) //Έλεγχος άμα υπάρχουν κρατήσιες
				JOptionPane.showMessageDialog(null, "Δεν υπάρχουν κρατήσεις", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
			else{
				reservations = new ArrayList<Reservation>();
				reservations = rm.searchClient(surname); //Αναζήτηση πελάτη
				if(reservations.size() == 0 ) //Έλεγχος άμα υπάρχει ο συγκεκριμένος πελάτης
					JOptionPane.showMessageDialog(null, "Δεν υπάρχει πελάτης με τέτοιο όνομα", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
				else{
					model = new TableModel(reservations);
					table.setModel(model);
				}

				ChangeButton.setVisible(true);
				DeleteButton.setVisible(true);
				CancelButton.setVisible(true);
			}

		}
	}

	class ButtonListener implements ActionListener{

		/*Το παρακάτω τμήμα κώδικα χρησιμεύει έτσι ώστε παίρνοντας ως παράμετρο το frame να έχουμε την δυνατότητα
		 * με το πάτημα του κουμπιού "ΟΚ" να κλείνει το συγκεκριμένο παράθυρο  */
		ReservationManagerFrame rmframe;
		public ButtonListener(ReservationManagerFrame rmf){
			this.rmframe = rmf;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ChangeButton){
				int row = table.getSelectedRow();
				Reservation selectedReservation = reservations.get(table.convertRowIndexToModel(row));
				new ModifyReservationFrame(rm , selectedReservation); 
				reservations = new ArrayList<Reservation>();
				reservations = rm.searchClient(SurnameTextField.getText());
				model= new TableModel(reservations);
				table.setModel(model);


			}
			else if(e.getSource() == DeleteButton){

				int row = table.getSelectedRow();
				Reservation sr = reservations.get(table.convertRowIndexToModel(row));
				rm.deleteReservation(sr);
				reservations = new ArrayList<Reservation>();
				reservations = rm.searchClient(SurnameTextField.getText());
				model= new TableModel(reservations);
				table.setModel(model);


			}
			else if(e.getSource() == CancelButton){
				rmframe.dispose();
			}			
		}
	}
}
