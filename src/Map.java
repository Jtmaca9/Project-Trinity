import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Map {
	String mapName;
	int mapWidth;
	int mapHeight;
	int mapHeightPx;
	int mapWidthPx;
	int mapSpawnX;
	int mapSpawnY;
	Scanner scanner;
	int[][] tileData;
	int[] mapInfo;
	Tile[][] tiles;
	
	Map(String name){
		mapName = name;
		init();
	}
	
	void init(){
		loadMapInfo();
		loadMap();
		mapWidthPx = mapWidth *32;
		mapHeightPx = mapHeight *32;
	}
	
	void loadMapInfo(){
		if (new File("Data/Maps/" + mapName + "-Info.map").isFile()){
			try {
				scanner = new Scanner(new File("Data/Maps/" + mapName + "-Info.map"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int p = 0;
			mapInfo = new int[4];
			
			
			while(scanner.hasNextInt()){
				
				mapInfo[p++] = scanner.nextInt();
				
			}
			
			mapWidth = mapInfo[0];
			mapHeight = mapInfo[1];
			mapSpawnX = mapInfo[2];
			mapSpawnY = mapInfo[3];
			
		}
	}
	
	
	
	
	void loadMap(){
		if (new File("Data/Maps/" + mapName + ".map").isFile()){
			try {
				scanner = new Scanner(new File("Data/Maps/" + mapName + ".map"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			tileData = new int[mapWidth][mapHeight];
			tiles = new Tile[mapWidth][mapHeight];
			
			for(int i = 0; i < mapWidth; i++){
				for(int j = 0; j < mapHeight; j++){
					if(scanner.hasNextInt()){
						tileData[j][i] = scanner.nextInt();
						tiles[j][i] = new Tile(tileData[j][i], j, i);
					}
				}
			}
		}
	
	}
	
	void render(Graphics g, GameContainer container){
		for(int i = ProjectTrinity.player1.gridXpos - (ProjectTrinity.tileX); i <  ProjectTrinity.player1.gridXpos + (ProjectTrinity.tileX); i++){
			for(int j =  ProjectTrinity.player1.gridYpos - (ProjectTrinity.tileY); j < ProjectTrinity.player1.gridYpos + (ProjectTrinity.tileY); j++){
				if(i >= 0 && i < mapWidth && j >= 0 && j < mapHeight)
				tiles[i][j].render(g, container);
			}
		}
	}
	
	

}
