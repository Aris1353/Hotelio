import java.util.Comparator;


public class ReservationCompare implements Comparator<Reservation> {

	@Override
	public int compare(Reservation r1, Reservation r2) {
		return r1.getClient().getArrival().compareTo(r2.getClient().getArrival());
	}



}
