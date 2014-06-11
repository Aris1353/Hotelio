import java.util.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class CompletenessReportFrame extends JFrame {


	private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private ReservationManager rm;


	public CompletenessReportFrame(ReservationManager rm) {
		this.rm = rm;

		double eachMonth[]={0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

		for(int i=0; i<12; i++){
			double monthDays = 0.0;
			for(int j=0; j<31; j++){
				int sum = 0;
				Date d = new Date();
				d.setMonth(i);
				d.setDate(j);
				d.setHours(00);
				d.setMinutes(00);
				d.setSeconds(00);
				for(Reservation r : rm.getReservations()){
					if(d.after(r.getClient().getArrival()) && d.before(r.getClient().getDeparture())){
						sum++;
					}
				}
				monthDays += sum / (double)rm.getRooms().size();
			}
			eachMonth[i] = monthDays / 30;
			dataset.addValue(eachMonth[i]*100, ""+eachMonth[i], ""+(i+1));
		}
	}

	public void createChart(ReservationManager rm){


		JFreeChart chart = ChartFactory.createBarChart3D(
				"Αναφορά Πληρότητας ανά μήνα",//chart title
				"Μήνας",					//x-axis title
				"Πληρότητα %",		//y-axis title
				dataset,					// data structure with values
				PlotOrientation.VERTICAL,
				false,						// no legend
				true,						//tooltips
				false);						//urls

		ChartPanel chartPanel = new ChartPanel(chart);

		this.setContentPane(chartPanel);

		this.setTitle("Πληρότητα ανά μήνα");

		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


	}


}
