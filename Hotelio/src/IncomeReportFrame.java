import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class IncomeReportFrame extends JFrame {

	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private ReservationManager rm;


	public IncomeReportFrame(ReservationManager rm) {
		this.rm = rm;
		double sum[]={0,0,0,0,0,0,0,0,0,0,0,0};
		for(int i=0; i<12; i++){
			for(Reservation r : rm.getReservations()){
				if(i == r.getClient().getDeparture().getMonth() ){
					sum[i] += r.getTotalCost();
				}
			}	
			dataset.addValue(sum[i], ""+sum[i], ""+(i+1));
		}
	}

	public void createChart(ReservationManager rm){


		JFreeChart chart = ChartFactory.createBarChart3D(
				"Αναφορά Εσόδων ανά μήνα",//chart title
				"Μήνας",					//x-axis title
				"Έσοδα",		//y-axis title
				dataset,					// data structure with values
				PlotOrientation.VERTICAL,
				false,						// no legend
				true,						//tooltips
				false);						//urls

		ChartPanel chartPanel = new ChartPanel(chart);

		this.setContentPane(chartPanel);

		this.setTitle("Έσοδα ανά μήνα");

		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


	}
}
