import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Tile {
	int type;
	int xpos;
	int ypos;
	int sizeX = 32;
	int sizeY = 32;
	
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
		}else if(type == 1){
			block = true;
		}
	}
	
	void render(Graphics g, GameContainer container){
		if(type == 0){
		g.drawImage(ProjectTrinity.grassTile, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
		g.drawImage(ProjectTrinity.grassTile, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
		}else if(type == 1){
		g.drawImage(ProjectTrinity.waterTile, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
		g.drawImage(ProjectTrinity.waterTile, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
		}
	}
	

}
