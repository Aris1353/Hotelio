import java.io.Serializable;


public class NutritionCost implements Serializable {

	private double nutrition1;//Τιμή ημιδιατροφής
	private double nutrition2;//Τιμή πλήρης διατροφής



	public double getNutrition1() {
		return nutrition1;
	}

	public void setNutrition1(double nutrition1) {
		this.nutrition1 = nutrition1;
	}

	public double getNutrition2() {
		return nutrition2;
	}

	public void setNutrition2(double nutrition2) {
		this.nutrition2 = nutrition2;
	}



}
