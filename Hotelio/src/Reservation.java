import java.io.Serializable;

public class Reservation implements Serializable{
	
	
	private Client client;
	private Room room;
	private double totalCost;
	
	public Reservation(Client client, Room room) {
		this.client = client;
		this.room = room;
		room.getCalendar().add(new Period(client.getArrival(), client.getDeparture()));
		
	}

	public Client getClient() {
		return client;
	}

	
	public void setClient(Client client) {
		this.client = client;
	}

	
	public Room getRoom() {
		return room;
	}

	
	public void setRoom(Room room) {
		this.room = room;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	
	
	

}
