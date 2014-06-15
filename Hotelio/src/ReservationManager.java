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

	//Η μέθοδος αυτή βρίσκει όλα τα διαθέσιμα δωμάτια για μια συγκεκριμένη περίοδο
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

		double reservationCost = 0; //Αρχικοποίηση
		reservationCost = r.getRoom().getCost(); //Παίρνουμε το κόστος του δωματίου
		reservationCost += getNutritionCost(r.getClient().getNutrition()); //Παίρνουμε το κόστος για την διατροφή του πελάτη
		reservationCost += extraCosts; //Προσθέτουμε τα επιπλέον έξοδα του πελάτη
		reservationCost = (1-discounts/100)*reservationCost; //Τελικό κόστος

		r.getClient().setCheckedIn(false); //κάνουμε το check-in false

		r.setTotalCost(reservationCost);

		resfm.reservationsSaveFile(reservations); //Αποθήκευση αλλαγών
		roomsfm.roomSaveFile(rooms); //Αποθήκευση αλλαγών

		return reservationCost;
	}

	//Δημιουργία νέας κράτησης
	public boolean newReservation(Client c, ArrayList<Room> rooms, String room){

		boolean done = false;//flag, αν έγινε επιτυχώς η κράτηση
		if(rooms.size() == 0){			//ελεγχος αν υπάρχουν δωμάτια στο σύστημα
			JOptionPane.showMessageDialog(null, "Δεν υπάρχουν καταχωρημένα δωμάτια στο πρόγραμμα");
		}
		else{
			ArrayList<Room> freeRoomsforReservation = searchFreeRoom(c.getArrival(), c.getDeparture(), rooms);
			if(freeRoomsforReservation.size() == 0)   //έλεγχος αν υπάρχουν διαθέσιμα δωμάτια
				JOptionPane.showMessageDialog(null, "Δεν υπάρχουν διαθέσιμα δωμάτια για τις επιλεγμένες ημερομηνίες", "Πλήρης",JOptionPane.WARNING_MESSAGE);
			else{

				boolean flagType = false;//flag, αν υπάρχει δωμάτιου ίδιου τύπου που επιθυμεί ο πελάτης
				boolean flagBeds = false;//flag,αν υπάρχει δωμάτιο με αριθμό κρεβατιών όσο το σύνολο των ατόμων της κράτησης
				int i=0;
				while(!done && i < freeRoomsforReservation.size()){
					Room r = freeRoomsforReservation.get(i);
					if(r.getType().equals(room)){  //Έλεγχος τύπου δωματίου
						flagType = true;
						if(c.totalNumberOfPersons() == r.getNumberOfBeds()){ //Έλεγχος αν τα κρεβάτια που έχει το δωμάτιο είναι ίσα με το συνολικό αριθμό ατόμων του πελάτη
							flagBeds = true;
							reservations.add(new Reservation(c,r));

							JOptionPane.showMessageDialog(null, "Η κράτησή σας ολοκληρώθηκε με επιτυχία", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
							done = true;
							r.calendarSort();  //Ταξινόμηση του ημερολογίου του δωματίου που δεσμεύτηκε
							Collections.sort(reservations, new ReservationCompare()); //Ταξινόμηση τω κρατήσεων
							resfm.reservationsSaveFile(reservations); //Αποθήκευση αλλαγών που έγινε στο πίνακα με τις κρατήσεις
							roomsfm.roomSaveFile(rooms); //Αποθήκευση των αλλαγών που έγιναν στο πίνακα με τα δωμάτια
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

	//Αναζήτηση αν υπάρχει πελάτης με επώνυμο "name" που έχει κάνει κράτηση
	public ArrayList<Reservation> searchClient(String surname){
		ArrayList<Reservation> reservationsByName = new ArrayList<Reservation>();
		for(Reservation r : reservations){
			if(r.getClient().getSurname().equals(surname)){
				reservationsByName.add(r);
			}
		}
		return reservationsByName;
	}

	//Αναζήτηση διαθέσιμων δωματίων που πληρούν τις απαιτήσεις του πελάτη
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

		//Εύρεση της θέσης της ημερομηνίας της κράτηση στο ημερολόγιο του δωματίου
		int index = -1;
		for(int i=0;i<r.getRoom().getCalendar().size();i++){
			if(r.getClient().getArrival().getMonth() == r.getRoom().getCalendar().get(i).getArrivalDate().getMonth() &&
					r.getClient().getDeparture().getMonth() == r.getRoom().getCalendar().get(i).getDepartureDate().getMonth()
					&& r.getClient().getArrival().getDate() == r.getRoom().getCalendar().get(i).getArrivalDate().getDate()
					&& r.getClient().getDeparture().getDate() == r.getRoom().getCalendar().get(i).getDepartureDate().getDate()){
				index = i;
			}
		}

		//Εύρεση της θέσης του δωματίου στο πίνακα με τα δωμάτια
		int index2 = -1;
		for(int j=0;j<rooms.size();j++){
			if(rooms.get(j).getId() == r.getRoom().getId()){
				index2=j;
				break;
			}
		}


		rooms.get(index2).getCalendar().remove(index);//Διαγραφή περιόδου
		roomsfm.roomSaveFile(rooms); //Αποθήκευση αλλαγών
		reservations.remove(r);		//Διαγραφή κράτησης
		resfm.reservationsSaveFile(reservations); //Αποθήκευση αλλαγών

	}

	public void changeRoom(Reservation r){

		ArrayList<Room> freeSameRooms = searchFreeSameRooms(rooms, r);//Ευρεση διαθέσιμων δωματίων που πληρούν τις απαιτήσεις του πελάτη
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





