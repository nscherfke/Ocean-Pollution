
public class Dropper extends Thread {

	Level l;
	Trash[][] trash;
	int speed;

	Dropper(Level l, Trash[][] trash, int speed) {
		this.l = l;
		this.trash = trash;
		this.speed=speed;
	}


	public void run() {

		while(trash[0][0].getyAxis()<500) {
			
			for (int i=0; i<trash.length; i++) {
				for (int j=0; j<trash[i].length; j++) {

					trash[i][j].setyAxis(trash[i][j].getyAxis()+8);
					//System.out.println(trash[i][j].getyAxis());
				}
			}
			
			try {
				Thread.sleep(speed);
				}
			catch(Exception e) {System.out.print("e");}
			
			l.repaint();
		}
	}


}
