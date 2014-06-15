import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;




public class FreeRoomsFrame extends JFrame {

	private ReservationManager rm;
	private JSpinner arrivalSpinner;
	private JSpinner deparureSpinner;
	private JTable table;
	private JButton btnSearch;
	private JButton button;
	private RoomsTableModel model;
	private ButtonListener buttonListener = new ButtonListener(this);


	public FreeRoomsFrame(ReservationManager rm) {
		this.setPreferredSize(new Dimension(800,500));
		this.rm = rm;
		Date now = new Date();
		now.setHours(12);
		now.setMinutes(00);
		now.setSeconds(00);
		SpinnerDateModel model1 = new SpinnerDateModel(now, null, null, Calendar.DAY_OF_WEEK);   

		arrivalSpinner = new JSpinner(model1);
		arrivalSpinner.setModel(new SpinnerDateModel(now, null, null, Calendar.DAY_OF_WEEK));

		deparureSpinner = new JSpinner(model1);
		deparureSpinner.setModel(new SpinnerDateModel(now , null, null, Calendar.DAY_OF_YEAR));

		DateFormat df = new SimpleDateFormat("dd MMM yyyy");
		JSpinner.DateEditor editor = new JSpinner.DateEditor(arrivalSpinner, "dd MMM yyyy HH:mm:ss");
		JSpinner.DateEditor editor1 = new JSpinner.DateEditor(deparureSpinner, "dd MMM yyyy HH:mm:ss");

		JFormattedTextField ftf = editor.getTextField();  
		JFormattedTextField ftf1 = editor1.getTextField();
		ftf.setEditable(false);  
		ftf1.setEditable(false);
		arrivalSpinner.setEditor(editor);  
		deparureSpinner.setEditor(editor1);


		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);


		btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(buttonListener);

		button = new JButton("\u0386\u03BA\u03C5\u03C1\u03BF");
		button.addActionListener(buttonListener);

		JLabel label = new JLabel("\u0391\u03C0\u03CC");

		JLabel label_1 = new JLabel("\u039C\u03AD\u03C7\u03C1\u03B9");


		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(28)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(deparureSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(arrivalSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
														.addGroup(groupLayout.createSequentialGroup()
																.addContainerGap()
																.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
																.addGroup(groupLayout.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(button, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(44)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
								.addComponent(arrivalSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(41)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
										.addComponent(deparureSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(44)
										.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
										.addGap(81)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addGap(123))
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
												.addContainerGap())
				);
		this.getContentPane().setLayout(groupLayout);

		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	class ButtonListener implements ActionListener{

		/*Το παρακάτω τμήμα κώδικα χρησιμεύει έτσι ώστε παίρνοντας ως παράμετρο το frame να έχουμε την δυνατότητα
		 * με το πάτημα του κουμπιού "’κυρο" να κλείνει το συγκεκριμένο παράθυρο  */
		FreeRoomsFrame frFrame;
		public ButtonListener(FreeRoomsFrame frFrame){
			this.frFrame = frFrame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == btnSearch){
				/*Το παρακάτω τμήμα κώδικα μας δείχνει ας υπάρχουν διαθέσιμα δωμάτια για τις ημερομηνίες 
				 *"άφιξη" και "αναχώρηση" που έχει ορίσει ο χρήστης*/
				Date arrival = (Date)arrivalSpinner.getValue();
				Date departure = (Date)deparureSpinner.getValue();
				ArrayList<Room> freeRooms = rm.searchFreeRoom(arrival, departure, rm.getRooms());
				model = new RoomsTableModel(freeRooms);
				table.setModel(model);

			}
			else if(e.getSource() == button){
				frFrame.dispose();
			}
		}

	}
}
