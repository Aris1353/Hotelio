import java.util.ArrayList;
import java.util.Date;


public class Main {


	public static void main(String[] args)  {

		RoomFileManager roomfm = new RoomFileManager();
		ReservationFileManager resfm = new ReservationFileManager();
		CodeFileManager cfm = new CodeFileManager();
		DataFileManager dfm = new DataFileManager();

		ReservationManager rm = new ReservationManager(resfm, roomfm,cfm,dfm);

		MainFrame mf = new MainFrame(rm);
	}

}


