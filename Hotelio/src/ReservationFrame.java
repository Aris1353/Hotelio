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


public class ReservationFrame extends JFrame {
	private ReservationManager rm;
	private JTextField textField;
	private JTextField textField_1;
	private JSpinner textField_2;
	private JSpinner textField_3;
	private JLabel lblmmddyyyy;
	private JLabel lblmmddyyyy_1;
	private JLabel label_4;
	private JLabel label_5;
	private JComboBox comboBox ;
	private JComboBox comboBox_1;
	private JButton button;
	private JSpinner spinner;
	private JSpinner spinner_1;

	public ReservationFrame(ReservationManager rm) {
		super();
		this.rm = rm;
		ConfirmButtonListener buttonListener = new ConfirmButtonListener();
		SpinnerModel m = new SpinnerNumberModel(0,0,3,1);
		SpinnerModel model = new SpinnerNumberModel(0, //initial value 
				0, //min
				3, //max
				1);                //step


		textField = new JTextField();
		textField.setText("\u038C\u03BD\u03BF\u03BC\u03B1");
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText("\u0395\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF");
		textField_1.setColumns(10);

		JLabel label = new JLabel("\u0395\u03BD\u03AE\u03BB\u03B9\u03BA\u03B5\u03C2");

		textField_2 = new JSpinner(m);

		JLabel label_1 = new JLabel("\u0391\u03BD\u03AE\u03BB\u03B9\u03BA\u03BF\u03B9");

		textField_3 = new JSpinner(model);

		lblmmddyyyy = new JLabel("\u0386\u03C6\u03B9\u03BE\u03B7(dd/mm/yyyy)\r\n");

		lblmmddyyyy_1 = new JLabel("\u0391\u03BD\u03B1\u03C7\u03CE\u03C1\u03B7\u03C3\u03B7(dd/mm/yyyy)");

		label_4 = new JLabel("\u03A4\u03CD\u03C0\u03BF\u03C2 \u0394\u03C9\u03BC\u03B1\u03C4\u03AF\u03BF\u03C5");

		comboBox = new JComboBox();
		comboBox.addItem("Regular");
		comboBox.addItem("Deluxe");
		comboBox.addItem("Platinum");

		label_5 = new JLabel("\u0394\u03B9\u03B1\u03C4\u03C1\u03BF\u03C6\u03AE");

		comboBox_1 = new JComboBox();
		comboBox_1.addItem("Χωρίς Διατροφή");
		comboBox_1.addItem("Ημιδιατροφή");
		comboBox_1.addItem("Πλήρης Διατροφή");

		button = new JButton("\u0395\u03C0\u03B9\u03B2\u03B5\u03B2\u03B1\u03AF\u03C9\u03C3\u03B7");
		button.addActionListener(buttonListener);


		Date now = new Date();
		now.setHours(12);
		now.setMinutes(30);
		now.setSeconds(00);
		
		Date end = new Date();
		end.setHours(12);
		end.setMinutes(00);
		end.setSeconds(00);
		SpinnerDateModel dateModel = new SpinnerDateModel(now, null, null , Calendar.DAY_OF_YEAR);
		spinner = new JSpinner(dateModel);
		spinner.setModel(new SpinnerDateModel(now, null, null, Calendar.DAY_OF_YEAR));

		
		SpinnerDateModel dateModel1 = new SpinnerDateModel(end, null, null , Calendar.DAY_OF_YEAR);
		spinner_1 = new JSpinner(dateModel1);
		spinner_1.setModel(new SpinnerDateModel(end , null, null, Calendar.DAY_OF_YEAR));
		
		DateFormat df = new SimpleDateFormat("dd MMM yyyy");
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd MMM yyyy HH:mm:ss");
		JSpinner.DateEditor editor1 = new JSpinner.DateEditor(spinner_1, "dd MMM yyyy HH:mm:ss");
		
		JFormattedTextField ftf = editor.getTextField();  
		JFormattedTextField ftf1 = editor1.getTextField();
        ftf.setEditable(false);  
        ftf1.setEditable(false);
        spinner.setEditor(editor);  
        spinner_1.setEditor(editor1);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(27)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(label_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
														.addGap(72)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addGroup(groupLayout.createSequentialGroup()
																		.addComponent(lblmmddyyyy_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addGap(23))
																		.addGroup(groupLayout.createSequentialGroup()
																				.addComponent(lblmmddyyyy, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
																				.addGap(29))
																				.addGroup(groupLayout.createSequentialGroup()
																						.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
																						.addGap(2))
																						.addGroup(groupLayout.createSequentialGroup()
																								.addComponent(label_5, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
																								.addGap(34)))
																								.addGap(22)
																								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																										.addGroup(groupLayout.createSequentialGroup()
																												.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, 126, Short.MAX_VALUE)
																												.addGap(35))
																												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																														.addGroup(groupLayout.createSequentialGroup()
																																.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																																		.addComponent(comboBox, 0, 157, Short.MAX_VALUE)
																																		.addGroup(groupLayout.createSequentialGroup()
																																				.addGap(3)
																																				.addComponent(comboBox_1, 0, 154, Short.MAX_VALUE)))
																																				.addGap(29))
																																				.addGroup(groupLayout.createSequentialGroup()
																																						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 126, Short.MAX_VALUE)
																																						.addGap(35)))))
																																						.addGroup(groupLayout.createSequentialGroup()
																																								.addGap(187)
																																								.addComponent(button, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
																																								.addGap(187))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(32)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
								.addComponent(lblmmddyyyy)
								.addComponent(spinner))
								.addGap(38)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
										.addComponent(lblmmddyyyy_1)
										.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(52)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(label, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
												.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_4))
												.addGap(42)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
														.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGroup(groupLayout.createSequentialGroup()
																.addGap(4)
																.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addComponent(label_5))
																.addGap(43)
																.addComponent(button, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
																.addGap(35))
				);
		getContentPane().setLayout(groupLayout);


		this.setVisible(true);
		this.pack();

	}

	class ConfirmButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = textField.getText();
			String surname = textField_1.getText();
			int adults = (int)textField_2.getValue();
			int underages = (int)textField_3.getValue();

			Date arrival = (Date)spinner.getValue();
			Date departure = (Date)spinner_1.getValue();
			
			System.out.println(departure);
			
			if(arrival.after(departure))
				JOptionPane.showMessageDialog(null, "Η ημερομηνία άφιξης είναι μετά την ημερομηνία αναχώρησης","",JOptionPane.ERROR_MESSAGE);
			else{
				String r = comboBox.getSelectedItem().toString();
				String nutrition = comboBox_1.getSelectedItem().toString();
				Client c;
				if(name.equals("") || surname.equals("") || (adults == 0 && underages == 0) )
					JOptionPane.showMessageDialog(null, "Συμπληρώστε όλα τα πεδία", "Κενά πεδία", JOptionPane.ERROR_MESSAGE);
				else{
					if(nutrition.equals("Χωρίς Διατροφή")){
						c = new Client(name, surname, adults, underages, arrival, departure, 0);
					}
					else if (nutrition.equals("Ημιδιατροφή")){
						c = new Client(name, surname, adults, underages, arrival, departure, 1);
					}
					else{
						c = new Client(name, surname, adults, underages, arrival, departure, 2);
					}


					if(r.equals("Regular")){
						rm.newReservation(c, rm.getRooms(), "Regular");
						
					}
					else if (r.equals("Deluxe")){
						rm.newReservation(c, rm.getRooms(), "Deluxe");
					}
					else{
						rm.newReservation(c, rm.getRooms(), "Platinum");
					}
					

				}
			}
		}
	}
}
