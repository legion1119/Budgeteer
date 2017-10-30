package billTracker;

import java.text.ParseException;
import java.util.Date;

public class Bill 
{
	public  void storeBill(String whatBill, double howMuch, Date  whenPay) throws ParseException
	{
		final String debt = "Debt";
		FileMaint billToWrite = new FileMaint();
		whatBill = whatBill.replaceAll("\\s+", "");//remove any white spaces from bill name 
		billToWrite.writeTransaction(whatBill, howMuch, whenPay, debt);	
	}	
}
