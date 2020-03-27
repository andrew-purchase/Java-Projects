package application;

import javafx.scene.shape.*;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Confetti extends Application {
	@Override
	public void start(Stage stage){
			
		//starting things
		int MaxX = 1000; int MaxY = 800;
		Pane canvas = new Pane();
		canvas.resize(MaxX, MaxY);
		Scene scene = new Scene(canvas, MaxX, MaxY, Color.BLACK);
		
        stage.setTitle("Piñata");
        stage.setScene(scene);
        stage.show();	
    
        //create the pinata, place in random location\
		Rectangle pinata = new Rectangle(12, 12, Color.CYAN);
		Random rand = new Random();
		pinata.relocate(rand.nextInt(MaxX), rand.nextInt(MaxY));
		canvas.getChildren().add(pinata);
		
		//when mouse clicked
	    scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {		
							
		        //tracks the location of the mouse
				PointerInfo pointer = MouseInfo.getPointerInfo();
				java.awt.Point here = pointer.getLocation();
				int mouseX = (int) here.getX();
				int mouseY = (int) here.getY(); 
				System.out.println("x\t" + mouseX);
				System.out.println("y\t" + mouseY);
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
					int r = rand.nextInt(256);
					int g = rand.nextInt(256);
					int b = rand.nextInt(256);
					Color rainbow = Color.rgb(r, g, b);	
					
					//make confetti
					Rectangle confetti = new Rectangle(5, 5, rainbow);
					confetti.relocate(mouseX, mouseY);
					canvas.getChildren().add(confetti);	
					
					//confetti moves around 
					Timeline explode = new Timeline();
					explode.setCycleCount(0);
					explode.setAutoReverse(true);
					explode.getKeyFrames().add(new KeyFrame(Duration.millis(1200),
							 new KeyValue (confetti.translateXProperty(), rand.nextInt(60) - 30),
							 new KeyValue (confetti.translateYProperty(), rand.nextInt(60) - 30)));
					explode.play();
					//move pinata
					pinata.relocate(rand.nextInt(MaxX), rand.nextInt(MaxY));
					}
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
