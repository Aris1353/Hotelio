import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFrame;


public class SuperuserFrame extends JFrame {

	private ReservationManager rm;
	private JButton EsodaButton,PlirotitaButton,EpeksergasiaButton,AposindesiButton;
	private IncomeReportFrame irf;
	
	public SuperuserFrame(ReservationManager rm) {
		this.rm = rm;
		irf = new IncomeReportFrame();
		ButtonListener buttonListener = new ButtonListener();
		
		EsodaButton = new JButton("\u0391\u03BD\u03B1\u03C6\u03BF\u03C1\u03AC \u0395\u03C3\u03CC\u03B4\u03C9\u03BD");
		
		PlirotitaButton = new JButton("\u0391\u03BD\u03B1\u03C6\u03BF\u03C1\u03AC \u03C0\u03BB\u03B7\u03C1\u03CC\u03C4\u03B7\u03C4\u03B1\u03C2");
		
		EpeksergasiaButton = new JButton("\u0395\u03C0\u03B5\u03BE\u03B5\u03C1\u03B3\u03B1\u03C3\u03AF\u03B1 \u0394\u03B5\u03B4\u03BF\u03BC\u03AD\u03BD\u03C9\u03BD");
		
		AposindesiButton = new JButton("\u0391\u03C0\u03BF\u03C3\u03CD\u03BD\u03B4\u03B5\u03C3\u03B7");
		
		EsodaButton.addActionListener(buttonListener);
		PlirotitaButton.addActionListener(buttonListener);
		EpeksergasiaButton.addActionListener(buttonListener);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(PlirotitaButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(EpeksergasiaButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(EsodaButton, Alignment.TRAILING))
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addComponent(AposindesiButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(58))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(EsodaButton)
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(PlirotitaButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(AposindesiButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addComponent(EpeksergasiaButton)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {EsodaButton, PlirotitaButton, EpeksergasiaButton});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {EsodaButton, PlirotitaButton, EpeksergasiaButton});
		getContentPane().setLayout(groupLayout);
		
		this.setVisible(true);
		this.pack();
	}
	
	
	class ButtonListener implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == EsodaButton){
				irf.createChart(rm);
				irf.setVisible(true);
			}
			else if(e.getSource() == PlirotitaButton){
				CompletenessReportFrame apf = new CompletenessReportFrame(rm);
			}
			else if(e.getSource() == EpeksergasiaButton){
				DataManagementFrame edf = new DataManagementFrame(rm);
			}
			else if(e.getSource() == AposindesiButton){
				
			}


		}

	}
	
}
