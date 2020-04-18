package application;

import java.util.Random;
import javafx.scene.paint.Color;

public class confetti_chooser {

	int r;
	int g; 
	int b;
	
	Random rand = new Random();
	
	Color pickColor(int label) {
		switch(label) {
			case 0:		return rainbow();
			case 1:		return green();
			case 2:		return gray();
			case 3:		return blue();
			case 4:		return yellow();
			case 5:		return purple();
			case 6:		return red();
			case 7:		return blue_yellow();
			case 8:		return ryb();
			case 9:		return Color.AQUA;
			default:	return Color.WHITE;
		}
	}
	Color rainbow() {
		this.r = rand.nextInt(256);
		this.g = rand.nextInt(256);
		this.b = rand.nextInt(256);
		return Color.rgb(r, g, b);
	}	
	Color green() {
		this.r = rand.nextInt(128);
		this.g = rand.nextInt(128) + 128;
		return Color.rgb(r, g, r);
	}	
	Color gray() {
		this.r = rand.nextInt(256);
		return Color.rgb(r, r, r);
	}
	Color blue() {
		this.r = rand.nextInt(128);
		this.g = rand.nextInt(256);
		this.b = rand.nextInt(128) + 128;
		return Color.rgb(r, g, b);
	}
	Color yellow() {
		this.r = rand.nextInt(128) + 128;
		this.b = rand.nextInt(128);
		return Color.rgb(r, r, b);
	}
	Color purple() {
		this.r = rand.nextInt(256);
		this.g = rand.nextInt(128);
		return Color.rgb(r, g, r);
	}
	Color red() {
		this.r = rand.nextInt(128) + 128;
		this.g = rand.nextInt(128);
		return Color.rgb(r, g, g);
	}
	Color blue_yellow() {
		if(rand.nextInt() % 2 == 0) {
			return Color.BLUE;}
		else {return Color.YELLOW;}
	}
	Color ryb() {
		int r = rand.nextInt() % 3;
		if(r == 0) {
			return Color.RED;}
		else if(r == 1) {
			return Color.YELLOW;}
		else {return Color.BLUE;}
	}
}