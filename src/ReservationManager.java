import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;




public class ReservationManager implements Serializable{


	private ReservationFileManager resfm;
	private RoomFileManager roomsfm;
	private ArrayList<Room> rooms;
	private ArrayList<Reservation> reservations;
	private DataFileManager dfm;
	private ArrayList<Double> nutritionCost;



	public ReservationManager(ReservationFileManager resfm,RoomFileManager roomsfm) {
		this.resfm = resfm;
		this.roomsfm = roomsfm;
		this.rooms = roomsfm.roomOpenFile();
		this.reservations = resfm.reservationsOpenFile();
		//this.nutritionCost = dfm.nutritionCostOpenFile();
	}


	public ArrayList<Room> searchFreeRoom(Date arrival, Date departure, ArrayList<Room> rooms){
		ArrayList<Room> freeRooms = new ArrayList<Room>();

		for(Room r : rooms){
			if (r.isAvailable(arrival,departure)){
				freeRooms.add(r);
			}
		}
		return freeRooms;
	}


	public void checkInAReservation(Reservation r){

		r.getRoom().setCheckedIn(true);
		resfm.reservationsSaveFile(reservations);
	}

	public double checkOutAReservation(Reservation r, double extraCosts, double discounts){

		double reservationCost = 0;
		reservationCost = r.getRoom().getCost();
		if(r.getClient().getNutrition() == 1){
			reservationCost += nutritionCost.get(1);
		}
		else if(r.getClient().getNutrition() == 2){
			reservationCost += nutritionCost.get(2);
		}
		reservationCost += extraCosts;
		reservationCost = (1-discounts)*reservationCost;

		r.getRoom().setCheckedIn(false);

		r.setTotalCost(reservationCost);

		resfm.reservationsSaveFile(reservations);

		return reservationCost;
	}

	public void newReservation(Client c, ArrayList<Room> rooms, String room){
		ArrayList<Room> freeRoomsforReservation = searchFreeRoom(c.getArrival(), c.getDeparture(), rooms);
		if(freeRoomsforReservation.size() == 0)
			JOptionPane.showMessageDialog(null, "Δεν υπάρχουν διαθέσιμα δωμάτια για τις επιλεγμένες ημερομηνίες", "Πλήρης",JOptionPane.WARNING_MESSAGE);
		boolean done = false;
		int i=0;
		while(!done && i < freeRoomsforReservation.size()){
			Room r = freeRoomsforReservation.get(i);
			if(r.getType().equals(room)){
				if(c.totalNumberOfPersons() == r.getNumberOfBeds()){
					reservations.add(new Reservation(c,r));
					JOptionPane.showMessageDialog(null, "Η κράτησή σας ολοκληρώθηκε με επιτυχία", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
					done = true;
					r.calendarSort();
					resfm.reservationsSaveFile(reservations);
					roomsfm.roomSaveFile(rooms);
				}
				else
					JOptionPane.showMessageDialog(null, "Δεν υπάρχουν διαθέσιμα δωμάτια για τον επιλεγμένο αριθμό ατόμων για τις επιλεγμένες ημερομηνίες", "Πλήρης",JOptionPane.WARNING_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "Δεν υπάρχουν διαθέσιμα δωμάτια του επιλεγμένου τύπου για τις επιλεγμένες ημερομηνίες", "Πλήρης",JOptionPane.WARNING_MESSAGE);

			i++;
		}
	}


	public ArrayList<Reservation> searchClient(String name){
		ArrayList<Reservation> reservationsByName = new ArrayList<Reservation>();
		for(Reservation r : reservations){
			if(r.getClient().getSurname().equals(name)){
				reservationsByName.add(r);
			}
		}
		return reservationsByName;
	}

	public void changeRoom(Reservation r, Room room){
		r.getRoom().setCheckedIn(false);

		int index = r.getRoom().searchDate(r.getClient().getArrival());
		r.getRoom().getCalendar().set(index, new Period(r.getClient().getArrival(), new Date()));
		room.getCalendar().add(new Period(new Date(), r.getClient().getDeparture()));
		r.setRoom(room);
		r.getRoom().setCheckedIn(true);
		resfm.reservationsSaveFile(reservations);

	}

	public ArrayList<Room> searchFreeSameRooms(ArrayList<Room> rooms, Reservation res){

		ArrayList<Room> freeRooms = searchFreeRoom(new Date(), res.getClient().getDeparture(), rooms);
		ArrayList<Room> sameFreeRooms = new ArrayList<Room>();

		for(Room r : freeRooms){
			if (res.getRoom().getClass().equals(r.getClass()) && res.getClient().totalNumberOfPersons()== r.getNumberOfBeds()){
				sameFreeRooms.add(r);
			}
		}
		return sameFreeRooms;
	}


	public void deleteReservation(Reservation r){

		reservations.remove(r);
		resfm.reservationsSaveFile(reservations);

	}


	public ArrayList<Room> getRooms() {
		return rooms;
	}


	public ArrayList<Reservation> getReservations() {
		return reservations;
	}


	public ReservationFileManager getResfm() {
		return resfm;
	}


	public void setResfm(ReservationFileManager resfm) {
		this.resfm = resfm;
	}


	public RoomFileManager getRoomsfm() {
		return roomsfm;
	}


	public void setRoomsfm(RoomFileManager roomsfm) {
		this.roomsfm = roomsfm;
	}
	
	






}





