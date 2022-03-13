package application;

import java.time.*;
import java.util.Timer;
import java.util.TimerTask;

public class ProgramClock {
	
	// Going to put a clock somewhere on screen. I'm thinking top right corner with the menu bar.
	
	LocalTime time;
	int secondTime;
	int minTime;
	int hourTime;
	boolean stop = false;
	
	public void updateLocalTime() {
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		
		//Updates the clock every second. Might have to put another of these to update the string in javafx every time this updates.
			public void run() {
				if(!stop) {
					time = LocalTime.now(ZoneId.systemDefault());
					secondTime = time.getSecond();
					minTime = time.getMinute();
					hourTime = time.getHour();
				}
				else {
					timer.cancel();
					timer.purge();
				}
			}
			
		};
		
		timer.scheduleAtFixedRate(task, 0, 50);
	}

	public int getSecond() {
		return secondTime;
	}
	
	public int getMinute() {
		return minTime;
	}

	public int getHour() {
		return hourTime;
	}
	
	public void stopClock(boolean choice) {
		stop = choice;
	}
	
	
}
