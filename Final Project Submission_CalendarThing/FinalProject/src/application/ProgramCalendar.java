package application;

import java.time.*;

public class ProgramCalendar {

	// Not sure if I want to display a week or a month to the user. Maybe add method for user to choose.
	// Also no need for really anything other than this because this can return the months as strings and etc.
	// Reminder: date[0] = today.
	public LocalDate[] date = new LocalDate[30];
	
	ProgramCalendar(){
		// Maybe add a method to get users wanted zone or a place where use can input their own date and timezone.
		date[0] = LocalDate.now(ZoneId.systemDefault());
		
		for(int i = 1; i < date.length; i++) {
			date[i] = date[i-1].plusDays(1);
		}
	}
	
}
