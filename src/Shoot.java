import java.awt.Rectangle;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Shoot extends Thread {

	Bubble bubble;
	Level l;
	Trash[][] trash;
	public static int score=0;

	Shoot(Bubble bubble, Level l, Trash[][] trash) {
		this.bubble = bubble;
		this.l = l;
		this.trash = trash;
	}

	public void run() {

		while(bubble.getyAxis()>-1000) {
			bubble.setyAxis(bubble.getyAxis()-10);
			checkCollision();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			l.repaint();
		}
	}
	
	public void checkCollision() {
		Rectangle bubbleRect=new Rectangle(bubble.getxAxis(), bubble.getyAxis(),29,28);
		
		for (int i=0; i<trash.length; i++) {
			for (int j=0; j<trash[i].length; j++) {
				Rectangle trashRect = new Rectangle(trash[i][j].getxAxis(),trash[i][j].getyAxis(),72,64);
				if(bubbleRect.intersects(trashRect)) {
					trash[i][j].setxAxis(5000);
					bubble.setxAxis(4000);	
					score++;
					
					try {
						File sound=new File("bottle.wav");
						AudioInputStream music=AudioSystem.getAudioInputStream(sound);
						Clip clip = AudioSystem.getClip();
						clip.open(music);
						clip.start();
					} catch(Exception e3) {System.out.println("e3");}
				}
			}
		}
		
		
	}

}
