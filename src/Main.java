import java.util.ArrayList;
import java.util.Date;


public class Main {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		Client c = new Client("Aris", "Papa", 3, 0, new Date(2013, 0, 3), new Date(2013, 0, 6) , 1);
		ArrayList<Reservation> reservations ;
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		//rooms = roomfm.roomOpenFile();
		
		/*rooms.add(new Regular(3, 2, false, 15));
		rooms.add(new Platinum(3, 3, false, 15));
		rooms.add(new Platinum(3, 4, false, 15));
		rooms.add(new Platinum(3, 5, false, 15));
		rooms.add(new Deluxe(3, 6, false, 15));
		rooms.add(new Deluxe(3, 7, false, 15));
		rooms.add(new Deluxe(3, 8, false, 15));
		rooms.add(new Regular(3, 9, false, 15));
		rooms.add(new Regular(3, 10, false, 15));
		
		roomfm.roomSaveFile(rooms);
		
		reservations = resfm.reservationsOpenFile();
		ReservationManager rm = new ReservationManager(resfm, roomfm );
		rm.searchFreeRoom(new Date(2013,5,6),new Date(2013,5,9), rooms);
		//System.out.println(c.getArrival().toString() + c.getDeparture().toString());
		
		*/
		//rooms.add(new Room(3, 1, false, 15,"Regular"));
		//reservations.add(new Reservation(c,rooms.get(0)));
		RoomFileManager roomfm = new RoomFileManager();
		ReservationFileManager resfm = new ReservationFileManager();
		
		//roomfm.roomSaveFile(rooms);
		//resfm.reservationsSaveFile(reservations);
		
		ReservationManager rm = new ReservationManager(resfm, roomfm);
		
		MainFrame mf = new MainFrame(rm);
	}

}


