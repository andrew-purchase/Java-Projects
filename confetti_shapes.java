package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import java.util.Random;

import javafx.fxml.FXML;

public class confetti_shapes extends Shape{
	
	Random rand = new Random();
	
	
	public Shape randomShape(int label) {
		char c = (char) ((char) rand.nextInt(94) + 32);
		
		switch(label) {
			case 0:		return new Rectangle(rand.nextInt(4) + 2, rand.nextInt(4) + 2);
			case 1:		return new Circle(rand.nextInt(4) + 2);
			case 2:		return new Text(String.valueOf(c));	
			case 3: 
		        Polygon triangle = new Polygon();
		        triangle.getPoints().addAll(new Double[]{
		        		(double)rand.nextInt(20) - 40, (double) (rand.nextInt(20) - 40),
			            (double)rand.nextInt(20) - 40, (double) (rand.nextInt(20) - 40),
		            (double)rand.nextInt(20) - 40, (double)rand.nextInt(20) - 40 });
		        return triangle;
			default: 	return randomShape(rand.nextInt(4));
		}
	}
	
	
	

}
