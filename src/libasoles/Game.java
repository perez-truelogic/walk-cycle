package libasoles;

import java.awt.Graphics;

public class Game extends Scenario{
	
	public void init(){
		
		initScenario();	
	}
	
	public void paint(Graphics appletCanvas){
		
		imageCanvas.clearRect(0, 0, canvasX, canvasY);
		imageCanvas.drawImage(background, 0, 0, this);
		
		// draw characters
		for(Character character: characters){
			//debug( character.name );
			imageCanvas.drawImage(character.me, character.position_x, character.position_y, this);
		}
		
		//imageCanvas.drawImage(foreground, 0, 0, this);
		appletCanvas.drawImage(offscreen, 0, 0, this);
	
		// refresh canvas
        try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
        repaint();
	}
	
	public void update(Graphics appletCanvas){
		
		paint(appletCanvas);
	}
	
	public void debug(String txt){
		
		System.out.println(txt);
	}
}
