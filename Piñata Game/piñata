package application;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class pinata extends Application {
	int pressCount = 0;
	@Override
	public void start(Stage stage){
			
		//starting things
		Random rand = new Random();
		int MaxX = 1000; int MaxY = 800;
		
		Pane canvas = new Pane();
		canvas.resize(MaxX, MaxY);
		Scene scene = new Scene(canvas, MaxX, MaxY, Color.BLACK);
		
		//score count
		Text score = new Text(1000, 450, "" + pressCount);
	    score.setStyle("-fx-font-family: Roboto Mono; -fx-font-size: 16pt;");
		score.setFill(Color.SKYBLUE);
		canvas.getChildren().add(score);
		
		//setting stage
        stage.setTitle("Piñata");
        stage.setScene(scene);
        stage.show();	
    
        //create the pinata, place in random location
		Rectangle pinata = new Rectangle(12, 12, Color.CYAN);
		pinata.relocate(rand.nextInt(MaxX), rand.nextInt(MaxY));
		canvas.getChildren().add(pinata);
		
		//make pinata blink
		FadeTransition blink = new FadeTransition(Duration.seconds(2), pinata);		
		 blink.setFromValue(1.0);
		 blink.setToValue(0.0);
   		 blink.setAutoReverse(false);
	  	 blink.setCycleCount(FadeTransition.INDEFINITE);				
	  	 blink.play();
		
		//make pinata rotate
		RotateTransition rt = new RotateTransition(Duration.seconds(2), pinata);
	     rt.setByAngle(720);
	     rt.setCycleCount(RotateTransition.INDEFINITE);
	     rt.play();		
	
		//make pinata reappear at random locations
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
					
					int shapeType = rand.nextInt(5);		//shape of confetti
					int colorType = rand.nextInt(11);		//color of confetti
					int special = rand.nextInt(6);			//likelihood of a special explosion
					int amount = rand.nextInt(10) + 10;		//amount of confetti
					
					int translateForExplosion = rand.nextInt(100) + 40;	//distance confetti flies
					for(int i = 0; i < amount; i++) {
						
						confetti_shapes c = new confetti_shapes();
						
						//define shape and color of confetti
						Shape confetti = c.randomShape(shapeType);
						confetti_chooser color = new confetti_chooser();
						confetti.setFill(color.pickColor(colorType));
						
						//define shape and color of explosion
						Shape explosion = c.randomShape(shapeType);
						explosion.setFill(color.pickColor(colorType));
						
						//special explosion
						if(special == 0) {
							translateForExplosion = rand.nextInt(200) + 60;	//flies farther
							amount = 100;									//more confetti
							if(i % 3 == 0) {								//one third is white
								confetti.setFill(Color.WHITE);
							}
						}
						
					//make explosion start where the mouse is
					explosion.relocate(mouseX, mouseY);
					canvas.getChildren().add(explosion);	
					
					//animate initial explosion
					Timeline first_exp = new Timeline();
					first_exp.setCycleCount(0);
					first_exp.getKeyFrames().add(new KeyFrame(Duration.millis(900),
							 new KeyValue (explosion.translateXProperty(), rand.nextInt(translateForExplosion * 2) - translateForExplosion),
							 new KeyValue (explosion.translateYProperty(), rand.nextInt(translateForExplosion * 2) - translateForExplosion)));
					first_exp.play();
					
					//animate fade effect
					FadeTransition b = new FadeTransition(Duration. millis(900), explosion);		
					 b.setFromValue(1.0);
					 b.setToValue(0.0);
					 b.setAutoReverse(false);
					 b.setCycleCount(1);				
					 b.play();					
					
					//make confetti
					confetti.relocate(mouseX, mouseY);
					canvas.getChildren().add(confetti);	
					
					//animate confetti explosion
					Timeline explode = new Timeline();
					explode.setCycleCount(0);
					explode.setAutoReverse(true);
					explode.getKeyFrames().add(new KeyFrame(Duration.millis(900),
							 new KeyValue (confetti.translateXProperty(), rand.nextInt(translateForExplosion) - translateForExplosion/2),
							 new KeyValue (confetti.translateYProperty(), rand.nextInt(translateForExplosion) - translateForExplosion/2)));
					explode.play();	
					}
				//update score
				pressCount++;
				canvas.getChildren().remove(score);
				score.setText("" + pressCount);
				if(pressCount >= 0) {
					score.setFill(Color.SKYBLUE);	//blue of positive
				} else{score.setFill(Color.PINK);}		//pink if negative
				canvas.getChildren().add(score); 
				}
			//subtract score -1 if no hit
			if(hit==false) {
				pressCount--;
				canvas.getChildren().remove(score);
				score.setText("" + pressCount);
				if(pressCount >= 0) {
					score.setFill(Color.SKYBLUE);
				} else{score.setFill(Color.PINK);}				
				canvas.getChildren().add(score);  			
				}
			}
	    });
	}
	
	//handle mouse event
	public void handle(MouseEvent mouseEvent) {
		//Platform.exit();
		System.exit(0);
	}
	//run program
	public static void main(String[] args) {
		launch();
	}
	
}
