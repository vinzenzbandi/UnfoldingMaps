package module6;

import processing.core.PGraphics;

class VScrollbar
{
  int sWidth, sHeight;    	// width and height of slider bar
  int xPos, yPos;         	// x and y position of bar
  float sliderPos, newSPos; // y position of slider
  int sposBottom, sposTop;  // Position when the slider is at the bottom or top of the bar
  int loose;              	// how loose/heavy
  boolean over;           	// is the mouse over the slider?
  boolean locked;
  float ratio;
  float value;				// value of the slider

  VScrollbar (int x, int y, int w, int h, float maxVal, int l) {
    sWidth = w;
    sHeight = h;
    int movingDistance = sHeight - sWidth;
    ratio = maxVal / (float)movingDistance;
    xPos = x - sWidth / 2;
    yPos = y;
    sposTop = yPos;
    sposBottom = yPos + sHeight - sWidth;
    sliderPos = sposBottom; // set slider at bottom of bar
    newSPos = sliderPos;
    loose = l;
  }
  
  public boolean update(int mouseX, int mouseY, boolean mousePressed) {
	// this method updates the position of the slider and returns true if 
	  // it has changed 
    if(over(mouseX, mouseY)) {
      over = true;
    } else {
      over = false;
    }
    if(mousePressed && over) {
      locked = true;
    }
    if(!mousePressed) {
      locked = false;
    }
    if(locked) {
      newSPos = constrain(mouseY-sWidth/2, sposTop, sposBottom);
    }
    if(Math.abs(newSPos - sliderPos) > 1) {
      sliderPos = sliderPos + (newSPos-sliderPos)/loose;
    }
    // update value and check if it has changed
    float oldValue = value;
    value = (float) ((float)Math.round(((sposBottom - sliderPos) * ratio) * 10.0) / 10.0);;
    if (value != oldValue) {
    	return true;
    }
    return false;
  }

  int constrain(int val, int minv, int maxv) {
    return Math.min(Math.max(val, minv), maxv);
  }

  boolean over(int mouseX, int mouseY) {
	// Returns true if the mouse position is over the scroll bar
    if(mouseX > xPos && mouseX < xPos+sWidth &&
    mouseY > yPos && mouseY < yPos+sHeight) {
      return true;
    } else {
      return false;
    }
  }

  void display(PGraphics pg) 
  {
	pg.pushStyle();
	// draw the slider
	pg.noStroke();
	pg.fill(200);
	pg.rect(xPos, yPos, sWidth, sHeight);
	if(over || locked) {
	  pg.fill(0);
	} else {
	  pg.fill(125);
	}
	pg.rect(xPos, sliderPos, sWidth, sWidth);
	pg.popStyle();
  }

  float getValue() {
    return value;
  }
}
