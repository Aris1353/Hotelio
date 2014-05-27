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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;


public class DataManagementFrame extends JFrame {
	private ReservationManager rm;
	private JTextField plithosTextField;
	private JTextField timiTextField;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private JButton ApothikeusiButton, AkiroButton;
	private JComboBox tipos;
	private JComboBox klines;

	public DataManagementFrame(ReservationManager rm){
		ButtonListener listener = new ButtonListener();
		this.rm = rm;
		//this.rooms=rm.getRooms();
		plithosTextField = new JTextField();
		plithosTextField.setColumns(10);
		
		timiTextField = new JTextField();
		timiTextField.setColumns(10);
		
		
		ApothikeusiButton = new JButton("\u0391\u03C0\u03BF\u03B8\u03AE\u03BA\u03B5\u03C5\u03C3\u03B7");
		
		tipos = new JComboBox();
		tipos.setModel(new DefaultComboBoxModel(new String[] {"Regular", "Deluxe", "Platinum"}));
		
		
		klines = new JComboBox();
		
		klines.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		AkiroButton = new JButton("\u0386\u03BA\u03C5\u03C1\u03BF");
		AkiroButton.addActionListener(listener);
		ApothikeusiButton.addActionListener(listener);
		
		JLabel lblNewLabel = new JLabel("\u0391\u03C1\u03B9\u03B8\u03BC\u03CC\u03C2 \u039A\u03BB\u03B9\u03BD\u03CE\u03BD");
		
		JLabel lblNewLabel_1 = new JLabel("\u03A4\u03CD\u03C0\u03BF\u03C2");
		
		JLabel lblNewLabel_2 = new JLabel("\u03A0\u03BB\u03AE\u03B8\u03BF\u03C2 \u0394\u03C9\u03BC\u03B1\u03C4\u03AF\u03C9\u03BD \u03B1\u03BD\u03B1 \u03A4\u03CD\u03C0\u03BF");
		
		JLabel lblNewLabel_3 = new JLabel("\u03A4\u03B9\u03BC\u03AE");
		
		JLabel lblNewLabel_4 = new JLabel("\u0395\u03C0\u03B5\u03BE\u03B5\u03C1\u03B3\u03B1\u03C3\u03AF\u03B1 \u0394\u03B5\u03B4\u03BF\u03BC\u03AD\u03BD\u03C9\u03BD");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 21));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(196)
							.addComponent(AkiroButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(30)
									.addComponent(lblNewLabel))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(52)
									.addComponent(klines, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tipos, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(plithosTextField, 135, 135, 135)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
							.addGap(39)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(timiTextField, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3))
							.addGap(18)
							.addComponent(ApothikeusiButton)))
					.addGap(50))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(171)
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
					.addGap(170))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_4)
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(timiTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(plithosTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(ApothikeusiButton)
								.addComponent(tipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(37)
							.addComponent(AkiroButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(klines, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {plithosTextField, timiTextField});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {timiTextField, lblNewLabel, lblNewLabel_3});
		getContentPane().setLayout(groupLayout);
		
		
		
		
		
		
		
		
		this.setVisible(true);
		this.pack();
	}
	
	
	
	class ButtonListener implements ActionListener{
	
			
			public void actionPerformed(ActionEvent e) {
				
				int numberOfBeds = Integer.parseInt(klines.getSelectedItem().toString());
				String type = tipos.getSelectedItem().toString();
				int numberOfRooms = Integer.parseInt(plithosTextField.getText());
				double cost = Double.parseDouble(timiTextField.getText());
				if(e.getSource() == ApothikeusiButton ){
					for(int i=0; i<numberOfRooms; i++){
						rm.getRooms().add(new Room(numberOfBeds,i+100,false,cost,type));
					}
				}
				else if(e.getSource() == AkiroButton){
					
					
				}
	
	
			}
	
		}
}
