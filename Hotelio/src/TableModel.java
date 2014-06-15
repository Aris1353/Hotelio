
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class TableModel extends AbstractTableModel {

	List<Reservation> reservations;
	String columnNames[] = {"Επώνυμο","Όνομα","Ενήλικες","Ανήλικοι","’φιξη","Αναχώρηση","Τύπος Δωματίου","Τύπος Διατροφής","Αριθμός Δωματίου","Checked-In"};


	public TableModel(List<Reservation> reservations) {
		super();
		this.reservations = reservations;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return reservations.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Reservation r = null;

		r = reservations.get(row);

		switch (column) {

		case 0:
			return r.getClient().getSurname();

		case 1:
			return r.getClient().getName();

		case 2:
			return r.getClient().getAdults();

		case 3:
			return r.getClient().getUnderages();

		case 4:
			return r.getClient().getArrival().toString();

		case 5:
			return r.getClient().getDeparture().toString();

		case 6:
			return r.getRoom().getType();

		case 7:
			if(r.getClient().getNutrition()==0)
				return "ΧΩΡΙΣ ΔΙΑΤΡΟΦΗ";
			else if(r.getClient().getNutrition()==1)
				return "ΗΜΙΔΙΑΤΡΟΦΗ";
			return "ΠΛΗΡΗΣ ΔΙΑΤΡΟΦΗ";

		case 8:
			return r.getRoom().getId();

		case 9:
			if(r.getClient().isCheckedIn())
				return "ΝΑΙ";
			return "ΟΧΙ";
		}
		return r;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

}
