import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*Δημιουργία αρχείου για την αποθήκευση των τιμών για τα είδη διατροφής*/
public class DataFileManager implements Serializable{

	public void nutritionCostSaveFile(NutritionCost nc){

		try {
			FileOutputStream fileOut = new FileOutputStream("nutritionCost.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(nc);
			out.close();
			fileOut.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}

	public NutritionCost nutritionCostOpenFile(){

		NutritionCost nc = null;
		try {
			FileInputStream fileIn = new FileInputStream("nutritionCost.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			nc = (NutritionCost) in.readObject();
			in.close();
			fileIn.close();	

		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return nc;
	}

}
