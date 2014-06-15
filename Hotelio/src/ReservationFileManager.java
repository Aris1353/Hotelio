import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

//Δημιουργία αρχείου για την αποθήκευση των κρατήσεων του ξενοδοχείου
public class ReservationFileManager implements Serializable{

	public void reservationsSaveFile(ArrayList<Reservation> reservations){

		try {
			FileOutputStream fileOut = new FileOutputStream("reservations.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(reservations);
			out.close();
			fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}

	public ArrayList<Reservation> reservationsOpenFile(){

		ArrayList<Reservation> reservations = null;
		try {
			FileInputStream fileIn = new FileInputStream("reservations.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			reservations = (ArrayList) in.readObject();
			in.close();
			fileIn.close();	

		} catch (IOException e) {
			e.printStackTrace();
			reservations = new ArrayList<Reservation>();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			reservations = new ArrayList<Reservation>();
		}
		return reservations;
	}




}
