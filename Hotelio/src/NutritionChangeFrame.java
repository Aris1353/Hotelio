import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


public class NutritionChangeFrame extends JFrame {
	private JTextField nutritionText1;
	private JTextField nutritionText2;
	private JButton okButton;
	private ReservationManager rm;
	
	
	public NutritionChangeFrame(ReservationManager rm) {
		this.rm=rm;
		nutritionText1 = new JTextField();
		nutritionText1.setText("");
		nutritionText1.setColumns(10);
		
		nutritionText2 = new JTextField();
		nutritionText2.setText("");
		nutritionText2.setColumns(10);
		
		JLabel nutrition1 = new JLabel("\u0397\u03BC\u03B9\u03B4\u03B9\u03B1\u03C4\u03C1\u03BF\u03C6\u03AE");
		
		JLabel nutrition2 = new JLabel("\u03A0\u03BB\u03AE\u03C1\u03B7\u03C2 \u0394\u03B9\u03B1\u03C4\u03C1\u03BF\u03C6\u03AE");
		
		okButton = new JButton("\u039F\u039A");
		ButtonListener listener = new ButtonListener(this);
		okButton.addActionListener(listener);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(nutrition1)
						.addComponent(nutrition2))
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(nutritionText2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nutritionText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(50))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(115)
					.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nutritionText1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nutrition1))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nutrition2)
						.addComponent(nutritionText2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		getContentPane().setLayout(groupLayout);
		
		
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	
	
	class ButtonListener implements ActionListener{


		private NutritionChangeFrame frame;
		public ButtonListener(NutritionChangeFrame frame){
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton){
				double nutrition1 = Double.parseDouble(nutritionText1.getText());
				double nutrition2 = Double.parseDouble(nutritionText2.getText());
				NutritionCost nc = new NutritionCost();
				nc.setNutrition1(nutrition1);
				nc.setNutrition2(nutrition2);
				rm.getDfm().nutritionCostSaveFile(nc);
				JOptionPane.showMessageDialog(null, "Save Complete","DONE",JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
			}
			
		}
	}
}
