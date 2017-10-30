package billTracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileMaint 
{
	public void  writeTransaction(String fileName, double newAmount, Date whenPay, String DorC) throws ParseException   
	{
		//create file
		String pathName = "/home/marvin/Transactions/" + DorC;
		Path newFile = Paths.get(fileName);
		File debtFile = new File(pathName, newFile+".txt");//add bill, as file name to path
		//create path if not already created
		if(!debtFile.getParentFile().exists()) 
		{
			debtFile.getParentFile().mkdirs();
		}
		//create file if not already created
		if (!debtFile.exists())
		{
			try
			{
				debtFile.createNewFile();
			} 
			catch (IOException e) 
			{
				System.out.println("File does not exist and cannot be created");
				e.printStackTrace();
			}
		}
		try 
		{
			if(!doesExist(whenPay, newAmount, debtFile)) 
			{
				addToFile(whenPay, newAmount, debtFile);
			}
			else 
			{
				System.out.println("These values already exist in this file");
			}
		}
		catch (IOException e) 
		{
			System.out.println("Could not check file for duplicate values");
			e.printStackTrace();
		}
	}
	
	private boolean doesExist(Date whenPay, double newAmount, File debtFile) throws FileNotFoundException, ParseException 
	{//determine whether or not information already exists within file
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(debtFile));
		try
		{
			while((line = reader.readLine()) != null)
			{
				Pattern date = Pattern.compile("(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/]\\d\\d");
				Pattern currency = Pattern.compile("\\s\\d+.\\d+");
				Matcher dateM = date.matcher(line);
				Matcher currencyM = currency.matcher(line);
				String whatDay = null;
				String howMuch = null;
				while(dateM.find() == true && currencyM.find() ==true)
				{
					MatchResult mrDate = dateM.toMatchResult();
					MatchResult mrCurrency = currencyM.toMatchResult();
					whatDay = mrDate.group(0);
					howMuch = mrCurrency.group(0);
				}
				String pattern = ("MM/dd/yy");
				DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				//Date newDate = simpleDateFormat.parse(whatDay);
				String newDate =  simpleDateFormat.format(whenPay);
				double newCurrency = Double.parseDouble(howMuch);
				if(whatDay.equals(newDate) && newAmount == newCurrency) //did not have to convert to string with .equals
				{
					return true;
				}
				reader.close();
			}
			
		}
		catch (NumberFormatException | IOException e) 
		{
			System.out.println("Could not read file");
			e.printStackTrace();
		}
		return false;
	}

	private void addToFile(Date whenPay, double newAmount, File debtFile) throws IOException 
	{//write new information too file
		FileWriter filewriter = new FileWriter(debtFile, true);
		Formatter formatter = new Formatter(filewriter);
		formatter.format("%-16tD%14f%n", whenPay, newAmount);
		formatter.close();
	}
	
	public void findAverage(File file) 
	{
		if(!tooOld(file))
		{
			
		}
	}
	
	private boolean tooOld(File file)
	{
		
	}
}
