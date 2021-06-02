import java.awt.Graphics;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OpeningWindow extends JPanel {

	JFrame window=new JFrame();
	
	OpeningWindow (){
		
		window.add(this);
		
		try {
			File sound=new File("music.wav");
			AudioInputStream music=AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(music);
			clip.start();
		} catch(Exception e) {System.out.println("e");}
		
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setSize(1000,600);
		window.setResizable(false);
		window.setLocation(50,50);
		
		try {
			Thread.sleep(5000);
			window.dispose();
		} catch(Exception e) {System.out.println("e2");}
		
	}
	
	public void paint(Graphics g) {
		
		ImageIcon background = new ImageIcon("newOceanBackground3.png");
		
		g.drawImage(background.getImage(), 0, 0, null);
	}
	
}
