import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Level extends JPanel implements KeyListener{
	
	JFrame window = new JFrame();
	Turtle turtle=new Turtle(380, 360, "turtle.png");
	Trash[][] trash= new Trash[2][14];
	int xAxis=12;
	int yAxis=0;
	Bubble[] bubbles=new Bubble[1000];
	int count=0;
	Dropper dropper; 
	private boolean fail=false;

	
	Level(int speed){
		this.setFocusable(true);
		this.addKeyListener(this);
		window.add(this);
		
		for (int i=0; i<trash.length;i++) {
			for(int j=0; j<trash[i].length;j++){
				trash[i][j]=new Trash(xAxis, yAxis,"bottle.png");
				xAxis+=70;
			}
			yAxis+=50;
			xAxis=12;
		}
		
		dropper=new Dropper(this, trash, speed);
		
		dropper.start();
		
		for (int i=0; i<bubbles.length;i++) {
			bubbles[i]=new Bubble(2000,800,"bubble.png");
		}
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setSize(1000,600);
		//window.setResizable(false);
		window.setLocation(50,50);
	}
	
	public void paint (Graphics g) {
		
		ImageIcon background=new ImageIcon("oceanBackground3.png");
		g.drawImage(background.getImage(),0,0, null);
		
		for (int i=0; i<trash.length; i++) {
			for (int j=0; j<trash[i].length; j++) {
				trash[i][j].drawTrash(g);
			}
		}
		
		for (int i=0; i<bubbles.length;i++) {
			bubbles[i].drawBubbles(g);
		}
		
		turtle.drawTurtle(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("", Font.BOLD,22));
		g.drawString("Score = " + Shoot.score, 800, 500);
		levelFailed();
		levelComplete();

	}
	
	public void levelFailed() {
		
			for (int i=0; i<trash.length; i++) {
				for (int j=0; j<trash[i].length; j++) {
					
					if(trash[i][j].getyAxis()>300) {
						fail=true;
						dropper.stop();
						break;
					}
				}
		}
			if (fail) {
				window.dispose();
				JOptionPane.showMessageDialog(null, "Game Over!");
			}
	}
	
	public void levelComplete() {
		if (Shoot.score>=28) {
			window.dispose();
			dropper.stop();
			JOptionPane.showMessageDialog(null, "Level Completed!");
		}
	}
	
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if (turtle.getxAxis()<820)
				turtle.setxAxis(turtle.getxAxis()+20);
			this.repaint();
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			if (turtle.getxAxis()>-100)
				turtle.setxAxis(turtle.getxAxis()-20);
			this.repaint();
		}
		else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			bubbles[count].setxAxis(turtle.getxAxis()+129);
			bubbles[count].setyAxis(turtle.getyAxis()+20);
			Shoot s=new Shoot(bubbles[count],this,trash);
			s.start();
			try {
				File sound=new File("bubble.wav");
				AudioInputStream music=AudioSystem.getAudioInputStream(sound);
				Clip clip = AudioSystem.getClip();
				clip.open(music);
				clip.start();
			} catch(Exception e1) {System.out.println("e1");}
			count++;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
