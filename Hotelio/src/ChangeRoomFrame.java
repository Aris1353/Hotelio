import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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




public class ChangeRoomFrame extends JFrame {

	private ReservationManager rm;

	private TableModel model;
	private JTextField textField;
	private JTable table;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JScrollPane scrollPane;
	private ArrayList<Reservation> reservations;
	private SearchListener searchListener = new SearchListener();
	private ButtonListener buttonListener = new ButtonListener(this);


	public ChangeRoomFrame(ReservationManager rm) {
		this.setPreferredSize(new Dimension(1000,600));
		this.rm = rm;


		textField = new JTextField();
		textField.setColumns(10);

		JLabel label = new JLabel("\u0395\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF");

		button = new JButton("\u0391\u03BD\u03B1\u03B6\u03AE\u03C4\u03B7\u03C3\u03B7");
		button.addActionListener(searchListener);


		table = new JTable();
		scrollPane = new JScrollPane(table);
		

		button_1 = new JButton("\u0386\u03BA\u03C5\u03C1\u03BF");
		button_1.addActionListener(buttonListener);

		button_2 = new JButton("Αλλαγή");
		button_2.addActionListener(buttonListener);

		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 254, Short.MAX_VALUE)
							.addGap(126)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 255, Short.MAX_VALUE)
							.addGap(96))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 734, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addGap(82))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(432))
		);
		this.getContentPane().setLayout(groupLayout);

		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				reservations = new ArrayList<Reservation>();
				reservations = rm.searchClient(name);
				if(reservations.size() == 0 )
					JOptionPane.showMessageDialog(null, "Δεν υπάρχει πελάτης με τέτοιο όνομα", "Προσοχή" , JOptionPane.INFORMATION_MESSAGE);
				else{
					model = new TableModel(reservations);
					table.setModel(model);
				}
			}

		}
	}

	class ButtonListener implements ActionListener{

		ChangeRoomFrame crf;
		
		public ButtonListener(ChangeRoomFrame crf){
			this.crf = crf;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button_2){
				int row = table.getSelectedRow();
				Reservation selectedReservation = reservations.get(table.convertRowIndexToModel(row));
				rm.changeRoom(selectedReservation);
				crf.repaint();
			}
			else if(e.getSource() == button_1){
				crf.dispose();
			}

		}
	}
}