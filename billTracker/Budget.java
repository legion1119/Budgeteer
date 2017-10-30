package billTracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Budget {

	public static void main(String[] args) throws ParseException  
	{
		String pattern = ("MM/dd/yy");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String testBill = "Test Pay";
		double testPay = 1234.45;
		
		Date date;
		date = simpleDateFormat.parse("10/4/17");
		
		PayCheck samplePay = new PayCheck();
		//samplePay.storePay(testBill, testPay, date);
		samplePay.averageMonthlyIncome();
		
		/*Bill sampleBill = new Bill();
		sampleBill.storeBill(testBill, testPay, date);*/
		
	}
}
