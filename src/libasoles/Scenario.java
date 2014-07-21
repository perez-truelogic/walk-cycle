package libasoles;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.Font;

public class Scenario extends Applet implements KeyListener{

	public int canvasX = 922;
	public int canvasY = 556;
	private Font a_Font;

	public Graphics imageCanvas;
	public Image offscreen;
	public BufferedImage background; /* foreground */
	
	protected ArrayList<Character> characters = new ArrayList<Character>();
	
	public boolean up, down, left, right;
	
	public void initScenario()
	{
		
		// window size
		setSize(canvasX, canvasY);
		
		// render helper (image)
		offscreen = createImage(canvasX, canvasY);
		imageCanvas = offscreen.getGraphics();
		
		// set background		
		try {
			background = ImageIO.read( getClass().getResource("/images/background1.jpg") );
			//foreground = ImageIO.read(getClass().getResource("/images/nubecita1.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		} 	
		
		// global font
		a_Font = new Font("Helvetica", Font.BOLD, 48);
	  	setFont(a_Font);
	  	
	  	// key Listener
	    addKeyListener(this);
	    
	    // add a Character
	    Character mainCharacter = new Character();
	    characters.add(mainCharacter);
	}
	
	private void callCharacterKeyListener(KeyEvent e){
	
		
	}
	
	public void keyTyped(KeyEvent e) {
		
		
	}

	public void keyPressed(KeyEvent e) {
		
		//callCharacterKeyListener(e);
		for(Character character: characters){
	           
			for(int key: character.assignedKeys){
				
				if(e.getKeyCode() == key){
					
					character.keyPressed(e);
					break;
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		
		//callCharacterKeyListener(e);
		for(Character character: characters){
	           
			for(int key: character.assignedKeys){
				
				if(e.getKeyCode() == key){
					
					character.keyReleased(e);
					break;
				}
			}
		}
	}
}
