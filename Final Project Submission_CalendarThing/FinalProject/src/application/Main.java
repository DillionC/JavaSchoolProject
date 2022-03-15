package application;	

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Exercises");
			primaryStage.setScene(scene);
			primaryStage.show();
			
//////////////Calendar///////////////////////////////////////////////////
			ProgramCalendar calendar = new ProgramCalendar();
			GridPane calendarPane = new GridPane();
			calendarPane.getStyleClass().add("calendar-grid-pane");
			
			Pane[] dayOfWeekStringPane = new Pane[7];
			Pane[] dayOfWeekIntPane = new Pane[7];
			
			for(int i = 0; i < 7; i++) {
				dayOfWeekStringPane[i] = new Pane();
				dayOfWeekIntPane[i] = new Pane();
			}
			
			for(int i = 0; i < 7; i++) {
				Text text = new Text(""+calendar.date[i].getDayOfWeek());
				Text number = new Text(""+calendar.date[i].getDayOfMonth());
				
				text.setX(2);
				text.setY(12);
				
				number.setX(2);
				number.setY(12);

				dayOfWeekStringPane[i].getChildren().add(text);
				dayOfWeekIntPane[i].getChildren().add(number);
				
				dayOfWeekStringPane[i].setMinWidth(75);
				dayOfWeekIntPane[i].setMinHeight(75);
				
				// I can't find the right colors for these.
				dayOfWeekStringPane[i].getStyleClass().add("calendar-pane-string");
				dayOfWeekIntPane[i].getStyleClass().add("calendar-pane-int");
								
				calendarPane.add(dayOfWeekStringPane[i], i, 0);
				calendarPane.add(dayOfWeekIntPane[i], i, 1);
				
				// Grow With Window except vgrow on string
				calendarPane.setHgrow(dayOfWeekStringPane[i], Priority.ALWAYS);
				calendarPane.setHgrow(dayOfWeekIntPane[i], Priority.ALWAYS);
				calendarPane.setVgrow(dayOfWeekIntPane[i], Priority.ALWAYS);
			}
			
			calendarPane.setAlignment(Pos.CENTER);
			
			root.setCenter(calendarPane);
			
			
////////////// Clock/////////////////////////////////////////////////////
			ProgramClock clock = new ProgramClock();
			clock.updateLocalTime();
			Timer clockTimer = new Timer();
			
			Text clockText = new Text();
			VBox clockBox = new VBox();
			clockBox.getChildren().add(clockText);
			clockBox.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK,null,null)));
			
			TimerTask clockTask = new TimerTask() {
				public void run() {
					//WIP Needs more time but I don't have that.
					String hour,min,sec;
					
					if(clock.getHour() <= 9) {
						hour = "0"+clock.getHour();
					}
					
					else {
						hour = ""+clock.getHour();
					}
					
					if(clock.getMinute() <= 9) {
						min = "0"+clock.getMinute();
					}
					
					else {
						min = ""+clock.getMinute();
					}

					if(clock.getSecond() <= 9) {
						sec = "0"+clock.getSecond();
					}
					
					else {
						sec = ""+clock.getSecond();
					}
					
					clockText.setText(hour+":"+min+":"+sec);
					
				}
			};
			
			clockTimer.scheduleAtFixedRate(clockTask, 0, 1);
			
			clockBox.setAlignment(Pos.BOTTOM_RIGHT);
			root.setBottom(clockBox);
			
			
			
////////////// Timer//////////////////////////////////////////////////
			ProgramTimer programTimer = new ProgramTimer();
			Timer timerTimer = new Timer();
			GridPane timerGrid = new GridPane();
			
			Text timerText = new Text();
			timerText.setFont(new Font(20));
			Button startTimer = new Button("Start");
			Button stopTimer = new Button("Stop/Cancel");
			
			TextField hoursTimer = new TextField("0");
			TextField minsTimer = new TextField("0");
			TextField secondsTimer = new TextField("0");
			
			timerGrid.add(new Text("Hours:"), 0, 1);
			timerGrid.add(new Text("Mins:"), 1, 1);
			timerGrid.add(new Text("Seconds:"), 2, 1);
			// These have to be filled. Need to create error thing.
			timerGrid.add(hoursTimer, 0, 2);
			timerGrid.add(minsTimer, 1, 2);
			timerGrid.add(secondsTimer, 2, 2);
			
			timerGrid.add(startTimer, 0, 3);
			timerGrid.add(stopTimer, 1, 3);
			
			timerGrid.add(timerText, 1, 0);
			
			timerGrid.setAlignment(Pos.CENTER);
			
			
//////////////Menu////////////////////////////////////////////////////
			VBox menuBox = new VBox();
			Menu menu = new Menu("Navigation");
			MenuBar menuBar = new MenuBar();
			
			menuBar.setPrefWidth(1000);
			menuBox.getChildren().add(menuBar);
			menuBar.getMenus().add(menu);
			
			
			MenuItem menuTimer = new MenuItem("Timer");
			MenuItem menuCalendar = new MenuItem("Calendar");
			menu.getItems().addAll(menuTimer,menuCalendar);
			root.setTop(menuBox);
			
			
//////////////EVENTS////////////////////////////////////////////////////////////////////////
			// Timer
			menuTimer.addEventHandler(ActionEvent.ACTION, e -> {
				root.setCenter(timerGrid);
			} );
			// Note: Rapid clicking of start and stop speeds up the clock(This can be fixed by stopping the clock and waiting 1-2 seconds.) and sometimes crashes the program for some reason.
			// Timer Start
			startTimer.addEventHandler(ActionEvent.ACTION, e -> {
				programTimer.setTimer(Integer.parseInt(hoursTimer.getText()), Integer.parseInt(minsTimer.getText()), Integer.parseInt(secondsTimer.getText()));
				programTimer.startTimer();
				TimerTask task = new TimerTask() {
					public void run() {
						// WIP
						String hour,min,sec;
						if(programTimer.getHour() <= 9) {
							hour = "0"+programTimer.getHour();
						}
						
						else {
							hour = ""+programTimer.getHour();
						}
						
						if(programTimer.getMin() <= 9) {
							min = "0"+programTimer.getMin();
						}
						
						else {
							min = ""+programTimer.getMin();
						}

						if(programTimer.getSec() <= 9) {
							sec = "0"+programTimer.getSec();
						}
						
						else {
							sec = ""+programTimer.getSec();
						}
						timerText.setText(hour+":"+min+":"+sec);
						
					}
					
				};
			
				timerTimer.scheduleAtFixedRate(task, 0, 1);
				
			});
			
			// Timer Stop
			stopTimer.addEventFilter(ActionEvent.ACTION, e -> {
				// Can't cancel.
				timerTimer.purge();
				programTimer.stopTimer(true);
			} );
			
			
			// Calendar
				menuCalendar.addEventHandler(ActionEvent.ACTION, e -> {
					root.setCenter(calendarPane);
				});
			
			
			
			
			// Program Closes
			primaryStage.setOnCloseRequest(e -> {
				//Stop Clock
				clock.stopClock(true);
				clockTimer.cancel();
				clockTimer.purge();
				
				//Stop Timer
				programTimer.stopTimer(true);
				timerTimer.cancel();
				timerTimer.purge();
			});
			
		} 
		
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
