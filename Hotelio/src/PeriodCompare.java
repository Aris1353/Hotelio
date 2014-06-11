import java.util.Comparator;


public class PeriodCompare implements Comparator<Period>{
	
	@Override
	public int compare(Period p1, Period p2) {
		return  p1.getArrivalDate().compareTo(p2.getArrivalDate());
	}
}


