import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Tile {
	int type;
	int xpos;
	int ypos;
	int sizeX = 32;
	int sizeY = 32;
	Image arrow;
	MonsterSpawner monsterSpawner;
	int width, height;
	
	//Tile Properties//
	boolean block;
	
	Tile(int t, int x, int y, int mw,int mh){
		type = t;
		xpos = x;
		ypos = y;
		width = mw;
		height = mh;
		init();
		arrow = ProjectTrinity.arrow;
	}
	
	void init(){
		if(type == 0){
			block = false;
		}else if(type == 1){
			block = true;
		}else if(type == 2){
			block = false;
			monsterSpawner = new MonsterSpawner("MonsterSpawner", xpos *sizeX, ypos *sizeY, width, height);
		}
	}
	
	void update(){
		if(type == 2){
		monsterSpawner.update();
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
		if(((xpos == 5) && (ypos ==5))){
		g.drawImage(ProjectTrinity.monsterSpawnerImage, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
		g.drawImage(ProjectTrinity.monsterSpawnerImage, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
		}
		if(ProjectTrinity.debugMode){
			//g.drawString("."+ ProjectTrinity.currentMap.enemies[0].pathFinder.nodes[xpos][ypos].value, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			if(ProjectTrinity.currentMap.enemies[0].pathFinder.nodes[xpos][ypos].dir == "up"){
				arrow.setRotation(0f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[0].pathFinder.nodes[xpos][ypos].dir == "right"){
				arrow.setRotation(90f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[1].pathFinder.nodes[xpos][ypos].dir == "down"){
				arrow.setRotation(180f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[2].pathFinder.nodes[xpos][ypos].dir == "left"){
				arrow.setRotation(270f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[3].pathFinder.nodes[xpos][ypos].dir == "up"){
				arrow.setRotation(0f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[4].pathFinder.nodes[xpos][ypos].dir == "right"){
				arrow.setRotation(90f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[5].pathFinder.nodes[xpos][ypos].dir == "down"){
				arrow.setRotation(180f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[6].pathFinder.nodes[xpos][ypos].dir == "left"){
				arrow.setRotation(270f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[7].pathFinder.nodes[xpos][ypos].dir == "up"){
				arrow.setRotation(0f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[8].pathFinder.nodes[xpos][ypos].dir == "right"){
				arrow.setRotation(90f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[9].pathFinder.nodes[xpos][ypos].dir == "down"){
				arrow.setRotation(180f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[10].pathFinder.nodes[xpos][ypos].dir == "left"){
				arrow.setRotation(270f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[11].pathFinder.nodes[xpos][ypos].dir == "up"){
				arrow.setRotation(0f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[12].pathFinder.nodes[xpos][ypos].dir == "right"){
				arrow.setRotation(90f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[13].pathFinder.nodes[xpos][ypos].dir == "down"){
				arrow.setRotation(180f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}else if(ProjectTrinity.currentMap.enemies[14].pathFinder.nodes[xpos][ypos].dir == "left"){
				arrow.setRotation(270f);
				g.drawImage(arrow, (xpos * sizeX) - ProjectTrinity.screenOffsetX, (ypos * sizeY) - ProjectTrinity.screenOffsetY);
			}
		}
	}
	
	boolean getBlock(){
		return block;
	}
	

}
