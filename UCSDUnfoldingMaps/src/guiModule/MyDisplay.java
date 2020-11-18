package guiModule;

import processing.core.PApplet;

public class MyDisplay extends PApplet{

	public void setup() {
		size(400, 400);
		background(100 ,100 ,100);
	}
	
	public void draw() {
		int faceRadius = (int)((width+height)*0.8/4);
		fill(255,255,0);
		ellipse(width/2, height/2, faceRadius*2, faceRadius*2 );
		
		int eyeSize = (int)(faceRadius*0.2);
		int eyeYPos = (int)(height/2-faceRadius/3);
		int eyeXPos = (int)(faceRadius*0.4); // distance to center
		fill(0,0,0);
		ellipse(width/2+eyeXPos, eyeYPos, eyeSize, eyeSize*2);
		ellipse(width/2-eyeXPos, eyeYPos, eyeSize, eyeSize*2);
		
		int mouthYPos = (int)(height/2+faceRadius/2);
		int mouthHeight = (int)(faceRadius/2);noFill();
		arc(width/2, mouthYPos, faceRadius, mouthHeight, 0, PI);
		
	}
	
}
