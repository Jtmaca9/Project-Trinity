import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Tile {
	int type;
	int xpos;
	int ypos;
	int sizeX = 32;
	int sizeY = 32;
	Image tileImage;
	
	//Tile Properties//
	boolean block;
	
	Tile(int t, int x, int y){
		type = t;
		xpos = x;
		ypos = y;
		init();
	}
	
	void init(){
		if(type == 0){
			block = false;
			try {
				tileImage = new Image("Data/Images/Grass.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type == 1){
			block = true;
			try {
				tileImage = new Image("Data/Images/Water.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void render(Graphics g, GameContainer container){
		g.drawImage(tileImage, (xpos * sizeX) - ProjectTrinity.screenOffsetX, ypos * sizeY - ProjectTrinity.screenOffsetY);
	}

}
