import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class CheckInFrame extends JFrame {
	
	private ReservationManager rm;
	private JTable table;
	private DefaultTableModel tableModel;

	public CheckInFrame(ReservationManager rm) {
		this.setPreferredSize(new Dimension(600,500));
		this.rm = rm;
		
		
	}
}

/*for(Reservation r : rm.getReservations()){
	model.addRow(new String[]{r.getClient().getSurname().toString(),
			r.getClient().getName().toString(),
			Integer.toString(r.getClient().getAdults()),
			Integer.toString(r.getClient().getUnderages()),
			r.getClient().getArrival().toString(),
			r.getClient().getDeparture().toString(),
			r.getRoom().toString(),
			Integer.toString(r.getClient().getNutrition()),
			Boolean.toString(r.getRoom().isCheckedIn())
			
	});
}
table.setModel(model);
*/