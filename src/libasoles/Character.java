package libasoles;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Character implements Runnable{

	public String name = "character1";
	
	public int initial_position_x = 80;
	public int initial_position_y = 320;
	
	public int position_x = initial_position_x;
	public int position_y = initial_position_y;
	
	public int[] assignedKeys = { 37, 38, 39, 40 };
	
	public int[] walkPositionCounters = {1, 1, 1, 1}; // left, up, right, down
	public int walkCycleStepLength = 2;
	public int walkCycleVelocity = 5;
		
	public BufferedImage me;
	public BufferedImage[] walk = new BufferedImage[19];
	public boolean up, down, left, right;
	
	public Character() {
				
		createCycle(); // fill walk cycle sprint
		
		Thread th = new Thread(this);
		th.start();
		
		me = walk[9]; // initial position
	}
	
	private BufferedImage[] createCycle(){
		
		try {
   			
			// right direction
			for(int i = 1; i < 9; i++){
				
				walk[i] = ImageIO.read(getClass().getResource("/images/"+ name +"/walk_backward/walkCycle" + i + ".png"));
			}
			
			// left direction
			for(int i = 1; i < 9; i++){
				
				walk[i+8] = ImageIO.read(getClass().getResource("/images/"+ name +"/walk_forward/walkCycle" + i + ".png"));
			}
			
		} catch (IOException e1) {
		
			e1.printStackTrace();
		} 
		
		return walk;
	}
	
	public void run() {
				
		while(true){
		
			// reset walk cycle position to first position (left or right)
			if(walkPositionCounters[0]>=(8*walkCycleVelocity)){
				walkPositionCounters[0] = 1;
			}
			if(walkPositionCounters[2]>=(8*walkCycleVelocity)){
				walkPositionCounters[2] = 1;
			}
			
			// move character;
			if(left == true){
				position_x-=walkCycleStepLength;
				++walkPositionCounters[0];		
				
				// change walk cycle position 
				if (walkPositionCounters[0] % walkCycleVelocity == 0)
					me = walk[ (walkPositionCounters[0] / walkCycleVelocity)  ];
			}
			if(right == true){
				position_x+=walkCycleStepLength;
				++walkPositionCounters[2];		
				
				if (walkPositionCounters[2] % walkCycleVelocity == 0)
					me = walk[ (walkPositionCounters[2] / walkCycleVelocity) + 8 ];
			}
			if(up == true){
				
				//y--;
			}
			if(down == true){
				//y++;
			}		
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == 37){
			left = true;
		}
		if(e.getKeyCode() == 38){
			up = true;
		}
		if(e.getKeyCode() == 39){
			right = true;
		}
		if(e.getKeyCode() == 40){
			down = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == 37){
			left = false;
		}
		if(e.getKeyCode() == 38){
			up = false;
		}
		if(e.getKeyCode() == 39){
			right = false;
		}
		if(e.getKeyCode() == 40){
			down = false;
		}
	}
}
