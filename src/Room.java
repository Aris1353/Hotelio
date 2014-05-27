import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;



public class Room implements Serializable{

	
	private int numberOfBeds;
	private int id;
	private boolean checkedIn;
	private ArrayList<Period> calendar;
	private double cost;
	private String type;
	Date d = new Date(2013,4,30);
	Date g = new Date(2013,5,5);	

	public Room(int numberOfBeds, int id, boolean checkedIn, double cost, String type) {
		super();
		this.numberOfBeds = numberOfBeds;
		this.id = id;
		this.checkedIn = checkedIn;
		this.cost = cost;
		this.type = type;
		calendar.add(new Period(d,g));
		calendar.add(new Period(new Date(2013,5,10), new Date(2013,5,15)));
		
	}

	public boolean isAvailable(Date arrival, Date departure){
		if (calendar.size() == 0){
			return true;
		}
		else if(calendar.size() == 1){
			if(departure.before(calendar.get(0).getArrivalDate()) || arrival.after(calendar.get(0).getDepartureDate())){
				return true;
			}
		}
		else{
			if(departure.before(calendar.get(0).getArrivalDate()) || arrival.after(calendar.get(calendar.size()-1).getDepartureDate())) {
				return true;
			}
			else{
				for(int i=0; i<calendar.size(); i++){
					if(arrival.after(calendar.get(i).getDepartureDate()) && departure.before(calendar.get(i+1).getArrivalDate())){
						return true;
					}
				}
			}
		}



		return false;

	}

	public void calendarSort(){
		Collections.sort(calendar,new PeriodCompare());
	}
	
	public int searchDate(Date arrival){
		int i = 0;
		
		while(true){
			if(arrival.equals(calendar.get(i).getArrivalDate())){
				return i;
			}
			i++;
			
		}
	}

	
	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	
	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public boolean isCheckedIn() {
		return checkedIn;
	}

	
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	
	public ArrayList<Period> getCalendar() {
		return calendar;
	}

	
	public void setCalendar(ArrayList<Period> calendar) {
		this.calendar = calendar;
	}

	
	public double getCost() {
		return cost;
	}

	
	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	

	
	
	
	
	







}
