package billTracker;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

public class PayCheck 
{
	public  void storePay(String whosMoney, double howMuch, Date  whenPay) throws ParseException
	{
		final String credit = "Credit";
		FileMaint payToWrite = new FileMaint();
		whosMoney = whosMoney.replaceAll("\\s+", "");//remove any white spaces from bill name 
		payToWrite.writeTransaction(whosMoney, howMuch, whenPay, credit);	
	}	
	
	public void averageMonthlyIncome() 
	{
		//averages all incomes per month
		File folder = new File("/home/marvin/Transactions/Credit");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++)
		{
			File file = listOfFiles[i];
			FileMaint avg = new FileMaint();
			avg.findAverage(file);
		}
	}
	
	public void averageIndividualIncome()
	{
		//averages income by individual
	}
}
