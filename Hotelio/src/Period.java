import java.io.Serializable;
import java.util.Date;


//���������� ������ ��� ��� ����� ��� ���������� �������� 2 �����������, ���� ��������� ��� ��� ����� ��� ��� ���������
public class Period implements Serializable{


	private Date arrivalDate;
	private Date departureDate;


	public Period(Date arrivalDate, Date departureDate) {
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

}





