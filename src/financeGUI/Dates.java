package financeGUI;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Dates{
	
	protected static String[] calcDates(int numOfRepayments, String dateStart, int billing){
		//This method calculates each billing date
		String []dates = new String [numOfRepayments];
	    LocalDate date = LocalDate.parse(dateStart);
		for(int i = 0; i < numOfRepayments; i++) {
		    date = date.plus(billing, ChronoUnit.MONTHS);
		    dates[i] = date.toString();
			}
			return dates;
	}
	protected static void adjustRate(double rate, int billing) {
		//Adjust interest rate converting from annual rate to fractionate rate
		double fraction = 0;
		fraction = (double) 1/(12/billing);
		rate = Math.pow(1 + rate,fraction) - 1;
	}
}
