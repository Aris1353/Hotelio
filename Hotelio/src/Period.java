import java.io.Serializable;
import java.util.Date;


//Δημιουργία κλάσης που μας δίνει την δυνατότητα σύνδεσης 2 ημερομηνιών, στην περίπτωσή μας μια άφιξη και μια αναχώρηση
public class Period implements Serializable{


	private Date arrivalDate;
	private Date departureDate;


	public Period(Date arrivalDate, Date departureDate) {
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

}





