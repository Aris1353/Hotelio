import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class ModifyReservationFrame extends JFrame {
	private ReservationManager rm;
	private Reservation temp;
	private Reservation r;
	private JTextField NameTextField;
	private JTextField SurnameTextField;
	private JSpinner NumberOfAdultsTextField;
	private JSpinner NumberOfUnderagesTextField;
	private JLabel ArrivalLabel;
	private JLabel DepartureLabel;
	private JLabel RoomTypeLabel;
	private JLabel NutritionTypeLabel;
	private JComboBox RoomTypeComboBox ;
	private JComboBox NutritionTypeComboBox;
	private JButton ConfirmButton;
	private JSpinner ArrivalSpinner;
	private JSpinner DepartureSpinner;


	public ModifyReservationFrame(ReservationManager rm, Reservation r) {
		super();
		this.temp = r;/*Κρατάμε την κράτηση σε μια προσωρινή μεταβλητή έτσι ώστε αν δεν γίνει με επιτιχία η
		 *νέα κρατήση να μην χαθούν τα στοιχεία*/
		this.r = r;
		this.rm = rm;
		ConfirmButtonListener buttonListener = new ConfirmButtonListener(this);
		SpinnerModel m = new SpinnerNumberModel(r.getClient().getAdults(),0,3,1);
		SpinnerModel model = new SpinnerNumberModel(r.getClient().getUnderages(), //initial value 
				0, //min
				3, //max
				1);                //step


		NameTextField = new JTextField();
		NameTextField.setText(r.getClient().getName());
		NameTextField.setColumns(10);

		SurnameTextField = new JTextField();
		SurnameTextField.setText(r.getClient().getSurname());
		SurnameTextField.setColumns(10);

		JLabel AdultsLabel = new JLabel("\u0395\u03BD\u03AE\u03BB\u03B9\u03BA\u03B5\u03C2");

		NumberOfAdultsTextField = new JSpinner(m);

		JLabel UnderagesLabel = new JLabel("\u0391\u03BD\u03AE\u03BB\u03B9\u03BA\u03BF\u03B9");

		NumberOfUnderagesTextField = new JSpinner(model);

		ArrivalLabel = new JLabel("\u0386\u03C6\u03B9\u03BE\u03B7(mm/dd/yyyy)\r\n");

		DepartureLabel = new JLabel("\u0391\u03BD\u03B1\u03C7\u03CE\u03C1\u03B7\u03C3\u03B7(mm/dd/yyyy)");

		RoomTypeLabel = new JLabel("\u03A4\u03CD\u03C0\u03BF\u03C2 \u0394\u03C9\u03BC\u03B1\u03C4\u03AF\u03BF\u03C5");

		RoomTypeComboBox = new JComboBox();
		RoomTypeComboBox.addItem("Regular");
		RoomTypeComboBox.addItem("Deluxe");
		RoomTypeComboBox.addItem("Platinum");

		NutritionTypeLabel = new JLabel("\u0394\u03B9\u03B1\u03C4\u03C1\u03BF\u03C6\u03AE");

		NutritionTypeComboBox = new JComboBox();
		NutritionTypeComboBox.addItem("Χωρίς Διατροφή");
		NutritionTypeComboBox.addItem("Ημιδιατροφή");
		NutritionTypeComboBox.addItem("Πλήρης Διατροφή");

		ConfirmButton = new JButton("\u0395\u03C0\u03B9\u03B2\u03B5\u03B2\u03B1\u03AF\u03C9\u03C3\u03B7");
		ConfirmButton.addActionListener(buttonListener);


		Date now = r.getClient().getArrival();


		Date end = r.getClient().getDeparture();
		System.out.println(end);
		SpinnerDateModel dateModel = new SpinnerDateModel(now, null, null , Calendar.DAY_OF_YEAR);
		ArrivalSpinner = new JSpinner(dateModel);
		ArrivalSpinner.setModel(new SpinnerDateModel(now, null, null, Calendar.DAY_OF_YEAR));

		SpinnerDateModel dateModel1 = new SpinnerDateModel(end, null, null , Calendar.DAY_OF_YEAR);
		DepartureSpinner = new JSpinner(dateModel1);
		DepartureSpinner.setModel(new SpinnerDateModel(end , null, null, Calendar.DAY_OF_YEAR));

		DateFormat df = new SimpleDateFormat("dd MMM yyyy");
		JSpinner.DateEditor de_ArrivalSpinner = new JSpinner.DateEditor(ArrivalSpinner, "dd MMM yyyy HH:mm:ss");
		JSpinner.DateEditor de_DepartureSpinner = new JSpinner.DateEditor(DepartureSpinner, "dd MMM yyyy HH:mm:ss");

		JFormattedTextField ftf = de_ArrivalSpinner.getTextField();  
		JFormattedTextField ftf1 = de_DepartureSpinner.getTextField();
		ftf.setEditable(false);  
		ftf1.setEditable(false);
		ArrivalSpinner.setEditor(de_ArrivalSpinner);  
		DepartureSpinner.setEditor(de_DepartureSpinner);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(27)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(SurnameTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(NameTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(UnderagesLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
												.addComponent(AdultsLabel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(NumberOfUnderagesTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(NumberOfAdultsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
														.addGap(72)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(DepartureLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addGap(23))
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(ArrivalLabel, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
																				.addGap(29))
																				.addGroup(groupLayout.createSequentialGroup()
																						.addComponent(RoomTypeLabel, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
																						.addGap(2))
																						.addGroup(groupLayout.createSequentialGroup()
																								.addComponent(NutritionTypeLabel, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
																								.addGap(34)))
																								.addGap(22)
																								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																										.addGroup(groupLayout.createSequentialGroup()
																												.addComponent(DepartureSpinner, GroupLayout.PREFERRED_SIZE, 126, Short.MAX_VALUE)
																												.addGap(35))
																												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																														.addGroup(groupLayout.createSequentialGroup()
																																.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																																		.addComponent(RoomTypeComboBox, 0, 157, Short.MAX_VALUE)
																																		.addGroup(groupLayout.createSequentialGroup()
																																				.addGap(3)
																																				.addComponent(NutritionTypeComboBox, 0, 154, Short.MAX_VALUE)))
																																				.addGap(29))
																																				.addGroup(groupLayout.createSequentialGroup()
																																						.addComponent(ArrivalSpinner, GroupLayout.PREFERRED_SIZE, 126, Short.MAX_VALUE)
																																						.addGap(35)))))
																																						.addGroup(groupLayout.createSequentialGroup()
																																								.addGap(187)
																																								.addComponent(ConfirmButton, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
																																								.addGap(187))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(32)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(NameTextField, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
								.addComponent(ArrivalLabel)
								.addComponent(ArrivalSpinner))
								.addGap(38)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(SurnameTextField, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
										.addComponent(DepartureLabel)
										.addComponent(DepartureSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(52)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(AdultsLabel, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
												.addComponent(NumberOfAdultsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(RoomTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(RoomTypeLabel))
												.addGap(42)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(UnderagesLabel, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
														.addComponent(NumberOfUnderagesTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGroup(groupLayout.createSequentialGroup()
																.addGap(4)
																.addComponent(NutritionTypeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addComponent(NutritionTypeLabel))
																.addGap(43)
																.addComponent(ConfirmButton, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
																.addGap(35))
				);
		getContentPane().setLayout(groupLayout);


		this.setVisible(true);
		this.pack();

	}

	class ConfirmButtonListener implements ActionListener{

		/*Το παρακάτω τμήμα κώδικα χρησιμεύει έτσι ώστε παίρνοντας ως παράμετρο το frame να έχουμε την δυνατότητα
		 * με το πάτημα του κουμπιού "ΟΚ" να κλείνει το συγκεκριμένο παράθυρο  */
		private ModifyReservationFrame mrf;
		public ConfirmButtonListener(ModifyReservationFrame mrf){
			this.mrf=mrf;
		}
		@Override
		public void actionPerformed(ActionEvent e) {

			rm.deleteReservation(r);
			boolean done = false;//flag, αν η αλλαγή της κράτησης έγινε με επιτυχία
			String name = NameTextField.getText();
			String surname = SurnameTextField.getText();
			int adults = (int)NumberOfAdultsTextField.getValue();
			int underages = (int)NumberOfUnderagesTextField.getValue();

			Date arrival = (Date)ArrivalSpinner.getValue();
			Date departure = (Date)DepartureSpinner.getValue();
			if(arrival.after(departure))
				JOptionPane.showMessageDialog(null, "Η ημερομηνία άφιξης είναι μετά την ημερομηνία αναχώρησης","",JOptionPane.ERROR_MESSAGE);
			else{
				String r = RoomTypeComboBox.getSelectedItem().toString();
				String nutrition = NutritionTypeComboBox.getSelectedItem().toString();
				Client c;
				if(name.equals("") || surname.equals("") || (adults == 0 && underages == 0) )
					JOptionPane.showMessageDialog(null, "Συμπληρώστε όλα τα πεδία", "Κενά πεδία", JOptionPane.ERROR_MESSAGE);
				else{
					if(nutrition.equals("Χωρίς Διατροφή")){
						c = new Client(name, surname, adults, underages, arrival, departure, 1);
					}
					else if (nutrition.equals("Ημιδιατροφή")){
						c = new Client(name, surname, adults, underages, arrival, departure, 2);
					}
					else{
						c = new Client(name, surname, adults, underages, arrival, departure, 3);
					}


					if(r.equals("Regular")){
						done = rm.newReservation(c, rm.getRooms(), "Regular");
					}
					else if (r.equals("Deluxe")){
						done = rm.newReservation(c, rm.getRooms(), "Deluxe");
					}
					else{
						done = rm.newReservation(c, rm.getRooms(), "Platinum");
					}
				}
			}

			if(done == false){
				rm.newReservation(temp.getClient(), rm.getRooms(), temp.getRoom().getType());
				JOptionPane.showMessageDialog(null, "Η κράτηση έμεινε η ίδια","",JOptionPane.ERROR_MESSAGE);
			}

			mrf.dispose();

		}
	}
}
