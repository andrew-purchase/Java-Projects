package application;

import java.util.Timer;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import java.util.TimerTask;
import java.awt.Font;
import java.awt.Label;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.TextField;
import java.util.Random;
import java.util.TimerTask;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Confetti extends Application {
	int pressCount = 0;
	@Override
	public void start(Stage stage){
			
		//starting things
		int MaxX = 1000; int MaxY = 800;
		
		Pane canvas = new Pane();
		canvas.resize(MaxX, MaxY);
		Scene scene = new Scene(canvas, MaxX, MaxY, Color.BLACK);
		
		Text score = new Text(1000, 450, "" + pressCount);
		score.setFill(Color.SNOW);
		canvas.getChildren().add(score);
		
        stage.setTitle("Piñata");
        stage.setScene(scene);
        stage.show();	
    
        //create the pinata, place in random location\
		Rectangle pinata = new Rectangle(12, 12, Color.CYAN);
		Random rand = new Random();
		pinata.relocate(rand.nextInt(MaxX), rand.nextInt(MaxY));
	
		canvas.getChildren().add(pinata);
		
		//make pinata blink
		FadeTransition blink = new FadeTransition(Duration.millis(500), pinata);		
		blink.setFromValue(1.0);
		blink.setToValue(0.0);
		blink.setAutoReverse(true);
		blink.setCycleCount(FadeTransition.INDEFINITE);				
		blink.play();
		
		//make pinata reappear at random lcoations
		Timeline asfd = new Timeline(new KeyFrame(Duration.seconds(2),
		        new EventHandler<ActionEvent>() {
		            public void handle(ActionEvent ae) {
						pinata.relocate(rand.nextInt(MaxX), rand.nextInt(MaxY));		            	
		            }
		        }
		));
		asfd.setCycleCount(Animation.INDEFINITE);
		asfd.play();       

		//when mouse clicked
	    scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {		
							
		        //tracks the location of the mouse
				PointerInfo pointer = MouseInfo.getPointerInfo();
				java.awt.Point here = pointer.getLocation();
				int mouseX = (int) here.getX();
				int mouseY = (int) here.getY(); 
				
				//blast area
				Rectangle blast = new Rectangle(12, 12, Color.TRANSPARENT);
				blast.relocate(mouseX - 6, mouseY - 6);
				canvas.getChildren().add(blast);
				
								
				//did pinata get hit?
				boolean hit= pinata.getBoundsInParent().intersects(blast.getBoundsInParent());
				if(hit == true) {	
					for(int i = 0; i < 10; i++) {
					
					//add random color
				    Random rand = new Random();
//					int r = rand.nextInt(256);
//					int g = rand.nextInt(256);
//					int b = rand.nextInt(256);
//					Color rainbow = Color.rgb(r, g, b);	
					
					//green		
					int r = rand.nextInt(128);
					int g = rand.nextInt(128) + 128;
					int b = rand.nextInt(128);
					Color green = Color.rgb(r, g, b);						
					Rectangle confetti = new Rectangle(5, 5, green);

					//gray
//					int rgb = rand.nextInt(256);
//					Color gray = Color.rgb(rgb, rgb, rgb);						
//					Rectangle confetti = new Rectangle(5, 5, gray);					
					
					//make confetti
//					Rectangle confetti = new Rectangle(5, 5, rainbow);
					confetti.relocate(mouseX, mouseY);
					canvas.getChildren().add(confetti);	
					
					//confetti moves around 
					Timeline explode = new Timeline();
					explode.setCycleCount(0);
					explode.setAutoReverse(true);
					explode.getKeyFrames().add(new KeyFrame(Duration.millis(900),
							 new KeyValue (confetti.translateXProperty(), rand.nextInt(60) - 30),
							 new KeyValue (confetti.translateYProperty(), rand.nextInt(60) - 30)));
					explode.play();
					//move pinata
					pinata.relocate(rand.nextInt(MaxX), rand.nextInt(MaxY));

					}
					//update score
				pressCount++;
				canvas.getChildren().remove(score);
				score.setText("" + pressCount);
				score.setFill(Color.SNOW);
				canvas.getChildren().add(score);  			
				}
			//update score if no hit
			if(hit==false) {
				pressCount--;
				canvas.getChildren().remove(score);
				score.setText("" + pressCount);
				score.setFill(Color.SNOW);
				canvas.getChildren().add(score);  			
				}
			}
	    });
	}
	   
	public void handle(MouseEvent mouseEvent) {
		//Platform.exit();
		System.exit(0);
	}

	public static void main(String[] args) {
		launch();
	}
	
}
