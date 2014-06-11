import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class RoomFileManager implements Serializable {

	public void roomSaveFile(ArrayList<Room> rooms){

		try {
			FileOutputStream fileOut = new FileOutputStream("rooms.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(rooms);
			out.close();
			fileOut.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}

	public ArrayList<Room> roomOpenFile() {

		ArrayList<Room> rooms  ;
		try { 
			FileInputStream fileIn = new FileInputStream("rooms.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			rooms = (ArrayList) in.readObject();
			in.close();
			fileIn.close();	
		
	    } catch (IOException e) {
	        e.printStackTrace();
	        rooms = new ArrayList<Room>();
	    }catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        rooms = new ArrayList<Room>();
	    }
			return rooms;
		}

}
