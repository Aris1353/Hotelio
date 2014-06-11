import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JOptionPane;




public class ReservationManager implements Serializable{


	private ReservationFileManager resfm;
	private RoomFileManager roomsfm;
	private ArrayList<Room> rooms;
	private ArrayList<Reservation> reservations;
	private DataFileManager dfm;
	private CodeFileManager cfm;



	public ReservationManager(ReservationFileManager resfm,RoomFileManager roomsfm,CodeFileManager cfm,DataFileManager dfm) {
		this.resfm = resfm;
		this.roomsfm = roomsfm;
		this.rooms = roomsfm.roomOpenFile();
		this.reservations = resfm.reservationsOpenFile();
		this.cfm=cfm;
		this.dfm=dfm;
	}


	public double getNutritionCost(int i){
		NutritionCost cost = dfm.nutritionCostOpenFile();
		if(i == 1)
			return cost.getNutrition1();
		else if(i == 2)
			return cost.getNutrition2();
		return 0;
		
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

		r.getClient().setCheckedIn(true);
		resfm.reservationsSaveFile(reservations);
		roomsfm.roomSaveFile(rooms);
	}

	public double checkOutAReservation(Reservation r, double extraCosts, double discounts){

		double reservationCost = 0;
		reservationCost = r.getRoom().getCost();
		reservationCost += getNutritionCost(r.getClient().getNutrition());
		reservationCost += extraCosts;
		reservationCost = (1-discounts/100)*reservationCost;

		r.getClient().setCheckedIn(false);
		
		r.setTotalCost(reservationCost);

		resfm.reservationsSaveFile(reservations);
		roomsfm.roomSaveFile(rooms);

		return reservationCost;
	}

	public boolean newReservation(Client c, ArrayList<Room> rooms, String room){
		
		boolean done = false;
		if(rooms.size() == 0){
			JOptionPane.showMessageDialog(null, "Δεν υπάρχουν καταχωρημένα δωμάτια στο πρόγραμμα");
		}
		else{
			ArrayList<Room> freeRoomsforReservation = searchFreeRoom(c.getArrival(), c.getDeparture(), rooms);
			if(freeRoomsforReservation.size() == 0)
				JOptionPane.showMessageDialog(null, "Δεν υπάρχουν διαθέσιμα δωμάτια για τις επιλεγμένες ημερομηνίες", "Πλήρης",JOptionPane.WARNING_MESSAGE);
			else{
				
				boolean flagType = false;
				boolean flagBeds = false;
				int i=0;
				while(!done && i < freeRoomsforReservation.size()){
					Room r = freeRoomsforReservation.get(i);
					if(r.getType().equals(room)){
						flagType = true;
						if(c.totalNumberOfPersons() == r.getNumberOfBeds()){
							flagBeds = true;
							reservations.add(new Reservation(c,r));

							JOptionPane.showMessageDialog(null, "Η κράτησή σας ολοκληρώθηκε με επιτυχία", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
							done = true;
							r.calendarSort();
							Collections.sort(reservations, new ReservationCompare());
							resfm.reservationsSaveFile(reservations);
							roomsfm.roomSaveFile(rooms);
						}	
					}

					i++;
				}
				if(flagType == false)
					JOptionPane.showMessageDialog(null, "Δεν υπάρχουν διαθέσιμα δωμάτια του επιλεγμένου τύπου για τις επιλεγμένες ημερομηνίες", "Πλήρης",JOptionPane.WARNING_MESSAGE);
				else if(flagBeds == false)
					JOptionPane.showMessageDialog(null, "Δεν υπάρχουν διαθέσιμα δωμάτια για τον επιλεγμένο αριθμό ατόμων για τις επιλεγμένες ημερομηνίες", "Πλήρης",JOptionPane.WARNING_MESSAGE);

			}

		}
		return done;
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

		/*Iterator<Period> iter = r.getRoom().getCalendar().iterator();

		while(iter.hasNext()){
			Period p = iter.next();
			if(p.getArrivalDate().equals(r.getClient().getArrival()) && p.getDepartureDate().equals(r.getClient().getDeparture())){
				iter.remove();
			}
		}*/

		for(int i=0;i<r.getRoom().getCalendar().size();i++){
			if(r.getClient().getArrival().getMonth() == r.getRoom().getCalendar().get(i).getArrivalDate().getMonth() &&
					r.getClient().getDeparture().getMonth() == r.getRoom().getCalendar().get(i).getDepartureDate().getMonth()
					&& r.getClient().getArrival().getDate() == r.getRoom().getCalendar().get(i).getArrivalDate().getDate()
					&& r.getClient().getDeparture().getDate() == r.getRoom().getCalendar().get(i).getDepartureDate().getDate()){
				r.getRoom().getCalendar().remove(i);
				roomsfm.roomSaveFile(rooms);
			}
		}


		reservations.remove(r);		
		resfm.reservationsSaveFile(reservations);

	}

	public void changeRoom(Reservation r){

		ArrayList<Room> freeSameRooms = searchFreeSameRooms(rooms, r);
		if(freeSameRooms.size() == 0){
			JOptionPane.showMessageDialog(null, "Δεν υπάρχουν διαθέσιμα δωμάτια" , "Πλήρης" , JOptionPane.WARNING_MESSAGE);
		}
		else{
			for(int i=0; i<r.getRoom().getCalendar().size(); i++ ){
				if(r.getClient().getArrival().equals(r.getRoom().getCalendar().get(i).getArrivalDate())){
					r.getRoom().getCalendar().remove(i);
					
				}
			}
			
			r.setRoom(freeSameRooms.get(0));
			r.getRoom().getCalendar().add(new Period(r.getClient().getArrival(),r.getClient().getDeparture()));
			JOptionPane.showMessageDialog(null, "Η αλλαγή δωματίου έγινε επιτυχώς. Το νέο δωμάτιο είναι το "+r.getRoom().getId(), "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
		}
		
		resfm.reservationsSaveFile(reservations);
		roomsfm.roomSaveFile(rooms);
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


	public String getPassword() {
		String password;
		password = cfm.OpenEncryptedFile();
		return password;
	}


	public void setPassword(String password) {
		cfm.SaveEncryptedFile(password);
	}



	public DataFileManager getDfm() {
		return dfm;
	}
}





