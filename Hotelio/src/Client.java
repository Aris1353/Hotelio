import java.io.Serializable;
import java.util.Date;



public class Client  implements Serializable{

	
	private String name;
	private String surname;
	private int adults;
	private int underages;
	private Date arrival;
	private Date departure;
	private int nutrition;
	private boolean checkedIn=false;;
	
	public Client(String name, String surname, int adults, int underages,
			Date arrival, Date departure, int nutrition) {
		super();
		this.name = name;
		this.surname = surname;
		this.adults = adults;
		this.underages = underages;
		this.arrival = arrival;
		this.departure = departure;
		this. nutrition = nutrition;
	}
	
	

	public boolean isCheckedIn() {
		return checkedIn;
	}



	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	public int getAdults() {
		return adults;
	}

	
	public void setAdults(int adults) {
		this.adults = adults;
	}

	
	public int getUnderages() {
		return underages;
	}

	
	public void setUnderages(int underages) {
		this.underages = underages;
	}

	
	public Date getArrival() {
		return arrival;
	}

	
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	
	public Date getDeparture() {
		return departure;
	}

	
	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	
	public int getNutrition() {
		return nutrition;
	}

	
	public void setNutrition(int nutrition) {
		this.nutrition = nutrition;
	}
	
	public int totalNumberOfPersons(){
		return adults + underages;
	}




	
	
	

}
