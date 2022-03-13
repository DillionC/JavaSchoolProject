package application;

import java.util.Timer;
import java.util.TimerTask;


public class ProgramTimer {

	// All sound stuff is handled by Javafx so I can't do anything about that right now.
	// Not sure if I should get a user input that says what the timer is for and when it rings it displays the users input.
	
	private static int secondsTime;
	private static int minTime;
	private static int hourTime;
	private static boolean stop;
	
	public void setTimer(int hours, int minutes, int seconds) {
		secondsTime = seconds;
		minTime = minutes;
		hourTime = hours;
		stop = false;
	}
	
	public void startTimer() {
		Timer timer = new Timer();
		TimerTask task = new  TimerTask() {
			
			public void run() {
				// This is the only way I could work around some errors that were popping up. 
				//Also in the final version of this class all the print statments will be gone. Mainly here for verification.
				if(ProgramTimer.hourTime != 0 && ProgramTimer.minTime == 0 && ProgramTimer.secondsTime == 0 && ProgramTimer.stop == false) {
					ProgramTimer.hourTime -= 1;
					ProgramTimer.minTime = 59;
					ProgramTimer.secondsTime = 59;
				}
				else if(ProgramTimer.minTime != 0 && ProgramTimer.secondsTime == 0 && ProgramTimer.stop == false) {
					ProgramTimer.minTime -= 1;
					ProgramTimer.secondsTime = 59;
				}
				else if(ProgramTimer.secondsTime != 0 && ProgramTimer.stop == false) {
					ProgramTimer.secondsTime -= 1;
				}
				else {
					timer.cancel();
					timer.purge();
					ProgramTimer.secondsTime = 0;
					ProgramTimer.minTime = 0;
					ProgramTimer.hourTime = 0;
					ProgramTimer.stop = false;
				}
				
			}
			
		};
		
		timer.scheduleAtFixedRate(task, 0, 1000);
		
	}
	
	public int getHour() {
		return hourTime;
	}
	
	public int getMin() {
		return minTime;
	}
	
	public int getSec() {
		return secondsTime;
	}
	
	public void stopTimer(boolean choice) {
		stop = choice;
	}
	
}
