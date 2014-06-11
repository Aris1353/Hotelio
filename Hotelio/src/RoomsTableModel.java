import java.util.List;

import javax.swing.table.AbstractTableModel;


public class RoomsTableModel extends AbstractTableModel {

	List<Room> rooms;
	String columnNames[] = {"Τύπος Δωματίου","Κόστος","Αριθμός Κλινών","Αριθμός Δωματίου"};


	public RoomsTableModel(List<Room> rooms) {
		super();
		this.rooms = rooms;
	}



	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return rooms.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Room r = null;

		r = rooms.get(row);

		switch (column) {

		case 0:
			return r.getType();

		case 1:
			return r.getCost();

		case 2:
			return r.getNumberOfBeds();

		case 3:
			return r.getId();
		}
		return r;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

}
