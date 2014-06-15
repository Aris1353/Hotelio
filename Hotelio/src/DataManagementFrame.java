import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;


public class DataManagementFrame extends JFrame {
	private ReservationManager rm;
	private JTextField NumberOfRoomsTextField;
	private JTextField CostTextField;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private JButton saveButton, CancelButton;
	private JComboBox Type;
	private JComboBox NumberOfBeds;
	private JButton resetButton;
	private JButton codeButton;
	private JButton nutritionButton;

	public DataManagementFrame(ReservationManager rm){
		ButtonListener listener = new ButtonListener(this);
		this.rm = rm;
		NumberOfRoomsTextField = new JTextField();
		NumberOfRoomsTextField.setColumns(10);

		CostTextField = new JTextField();
		CostTextField.setColumns(10);


		saveButton = new JButton("\u0391\u03C0\u03BF\u03B8\u03AE\u03BA\u03B5\u03C5\u03C3\u03B7");

		Type = new JComboBox();
		Type.setModel(new DefaultComboBoxModel(new String[] {"Regular", "Deluxe", "Platinum"}));


		NumberOfBeds = new JComboBox();

		NumberOfBeds.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		CancelButton = new JButton("\u0386\u03BA\u03C5\u03C1\u03BF");
		CancelButton.addActionListener(listener);
		saveButton.addActionListener(listener);

		JLabel NumberOfBedsLabel = new JLabel("\u0391\u03C1\u03B9\u03B8\u03BC\u03CC\u03C2 \u039A\u03BB\u03B9\u03BD\u03CE\u03BD");

		JLabel TypeLabel = new JLabel("\u03A4\u03CD\u03C0\u03BF\u03C2");

		JLabel NumberOfRoomsLabel = new JLabel("\u03A0\u03BB\u03AE\u03B8\u03BF\u03C2 \u0394\u03C9\u03BC\u03B1\u03C4\u03AF\u03C9\u03BD \u03B1\u03BD\u03B1 \u03A4\u03CD\u03C0\u03BF");

		JLabel CostLabel = new JLabel("\u03A4\u03B9\u03BC\u03AE");

		JLabel DataManagerLabel = new JLabel("\u0395\u03C0\u03B5\u03BE\u03B5\u03C1\u03B3\u03B1\u03C3\u03AF\u03B1 \u0394\u03B5\u03B4\u03BF\u03BC\u03AD\u03BD\u03C9\u03BD");
		DataManagerLabel.setFont(new Font("Tahoma", Font.BOLD, 21));

		resetButton = new JButton("RESET");
		resetButton.addActionListener(listener);

		codeButton = new JButton("\u0391\u03BB\u03BB\u03B1\u03B3\u03AE \u03BA\u03C9\u03B4\u03B9\u03BA\u03BF\u03CD");

		codeButton.addActionListener(listener);

		nutritionButton = new JButton("\u0391\u03BB\u03BB\u03B1\u03B3\u03AE \u03A4\u03B9\u03BC\u03CE\u03BD \u0394\u03B9\u03B1\u03C4\u03C1\u03BF\u03C6\u03AE\u03C2");
		nutritionButton.addActionListener(listener);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(196)
										.addComponent(CancelButton))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addGap(52)
																				.addComponent(NumberOfBeds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																				.addGroup(groupLayout.createSequentialGroup()
																						.addGap(30)
																						.addComponent(NumberOfBedsLabel)))
																						.addGap(42)
																						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
																								.addComponent(TypeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addComponent(Type, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																								.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
																								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																										.addComponent(NumberOfRoomsTextField, 135, 135, 135)
																										.addComponent(NumberOfRoomsLabel, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
																										.addGap(39)
																										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																												.addComponent(CostTextField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
																												.addComponent(CostLabel)))
																												.addGroup(groupLayout.createSequentialGroup()
																														.addGap(80)
																														.addComponent(DataManagerLabel, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
																														.addGap(27)
																														.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
																														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																																.addGroup(groupLayout.createSequentialGroup()
																																		.addGap(18)
																																		.addComponent(saveButton))
																																		.addGroup(groupLayout.createSequentialGroup()
																																				.addPreferredGap(ComponentPlacement.RELATED)
																																				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																																						.addComponent(nutritionButton)
																																						.addComponent(codeButton))))))
																																						.addGap(50))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(32)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(DataManagerLabel)
												.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
												.addGap(49)
												.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
														.addComponent(NumberOfBedsLabel)
														.addComponent(TypeLabel)
														.addComponent(NumberOfRoomsLabel)
														.addComponent(CostLabel)))
														.addGroup(groupLayout.createSequentialGroup()
																.addGap(31)
																.addComponent(codeButton)
																.addGap(18)
																.addComponent(nutritionButton)))
																.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																		.addGroup(groupLayout.createSequentialGroup()
																				.addPreferredGap(ComponentPlacement.UNRELATED)
																				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
																						.addComponent(CostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addComponent(NumberOfRoomsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addComponent(saveButton)
																						.addComponent(Type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																						.addGap(37)
																						.addComponent(CancelButton))
																						.addGroup(groupLayout.createSequentialGroup()
																								.addGap(12)
																								.addComponent(NumberOfBeds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																								.addContainerGap(59, Short.MAX_VALUE))
				);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {codeButton, nutritionButton});
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {NumberOfRoomsTextField, CostTextField});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {codeButton, nutritionButton});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {CostTextField, NumberOfBedsLabel, CostLabel});
		getContentPane().setLayout(groupLayout);


		this.setVisible(true);
		this.pack();
	}



	class ButtonListener implements ActionListener{

		/*Το παρακάτω τμήμα κώδικα χρησιμεύει έτσι ώστε παίρνοντας ως παράμετρο το frame να έχουμε την δυνατότητα
		 * με το πάτημα του κουμπιού "ΟΚ" να κλείνει το συγκεκριμένο παράθυρο  */
		private DataManagementFrame frame;
		public ButtonListener(DataManagementFrame frame){
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent e) {


			if(e.getSource() == saveButton ){
				int numberOfBeds = Integer.parseInt(NumberOfBeds.getSelectedItem().toString());
				String type = Type.getSelectedItem().toString();
				int numberOfRooms = Integer.parseInt(NumberOfRoomsTextField.getText());
				double cost = Double.parseDouble(CostTextField.getText());

				int count1 = 0;//Πλήθος δωματίων REGULAR 
				int count2 = 0;//Πλήθος δωματίων DELUXE
				int count3 = 0;//Πλήθος δωματίων PLATINUM
				//Οι παραπάνω μετρητές μας εξυπηρετούν ώστε τα δωμάτια να έχουν τον κατάλληλο αναγνωστικό αριθμό(id)
				for(Room r: rm.getRooms()){
					if(r.getType().equals("Regular")){
						count1++;
					}
					else if(r.getType().equals("Deluxe")){
						count2++;
					}
					else if(r.getType().equals("Platinum")){
						count3++;
					}
				}
				
				//Το παρακάτω τμήμα κώδικα προσθέτει στο πίνακα των δωματίων τα καινούργια δωμάτια που έχει πληκτρολογήσει ο HOTEL MANAGER
				if(type == "Regular"){
					for(int i=0;i<numberOfRooms;i++){
						rm.getRooms().add(new Room(numberOfBeds,i+count1+100,cost,type));
					}
				}
				else if(type == "Deluxe"){
					for(int i=0;i<numberOfRooms;i++){
						rm.getRooms().add(new Room(numberOfBeds,i+count2+200,cost,type));
					}
				}
				else if(type == "Platinum"){
					for(int i=0;i<numberOfRooms;i++){
						rm.getRooms().add(new Room(numberOfBeds,i+count3+300,cost,type));
					}
				}
				JOptionPane.showMessageDialog(null, "Changes Saved","Save",JOptionPane.INFORMATION_MESSAGE);

			}
			else if(e.getSource() == CancelButton){
				frame.dispose();

			}
			else if(e.getSource() == resetButton){

				systemReset();

				JOptionPane.showMessageDialog(null, "Reset Completed","RESET",JOptionPane.WARNING_MESSAGE);

				System.exit(0);

			}

			else if(e.getSource() == codeButton){
				ChangeCodeFrame ccf = new ChangeCodeFrame(rm);
			}

			else if(e.getSource() == nutritionButton){
				NutritionChangeFrame ncf = new NutritionChangeFrame(rm);
			}

			rm.getRoomsfm().roomSaveFile(rm.getRooms());

		}
	}

	public void systemReset(){
		/*ΥΛΟΠΟΙΗΣΗ ΤΗΣ ΛΕΙΤΟΥΡΓΙΑΣ RESER*/
		rm.getReservations().clear(); //διαγραφή όλων των κρατήσεων
		rm.getRooms().clear(); //διαγραφή ολων των δωματίων
		rm.getResfm().reservationsSaveFile(rm.getReservations()); //αποθήκευση αλλαγών
		rm.getRoomsfm().roomSaveFile(rm.getRooms()); //αποθήκευση αλλαγών

	}
}
