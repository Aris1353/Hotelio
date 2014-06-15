import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


//Η κλάση αυτή έχει κατασκευαστεί ώστε να προσομοιώνει ένα ΔΩΜΑΤΙΟ
public class Room implements Serializable{


	private int numberOfBeds;
	private int id;
	private ArrayList<Period> calendar ; /*Κάθε δωμάτιο έχει το δικό του ημερολόγιο, στο οποίο είναι καταχωρημένες
	 									  *οι ημερομηνίες στις οποίες υπάρχει κράτηση*/
	private double cost;
	private String type;


	public Room(int numberOfBeds, int id, double cost, String type) {
		super();
		this.numberOfBeds = numberOfBeds;
		this.id = id;
		this.cost = cost;
		this.type = type;
		calendar = new ArrayList<Period>();

	}

	//Έλεγχος αν το δωμάτιο είναι διαθέσιμο στην συγκεκριμένη περίοδο
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
				/*Έλεγχος αν η ημερομηνία αναχώρησης είναι πριν από την ημερομηνία άφιξης που βρίσκεται
				 *στην πρώτη θέση στο ημερολόγιο ή η ημερομηνία αφιξης είναι μετά την αναχώρηση της τελευταίας θέσης του ημερολογίου*/
				return true; 
			}
			else{ //Έλεγχος για ενδιάμεσες περιόδους
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
