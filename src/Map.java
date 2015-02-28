import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Map {
	String mapName;
	String gameMode;
	
	int playerCount;
	int mapWidth;
	int mapHeight;
	int mapHeightPx;
	int mapWidthPx;
	int mapSpawnX;
	int mapSpawnY;
	
	Scanner scanner;
	
	int[][] tileData;
	int[] mapInfo;
	
	public static Tile[][] tiles;
	
	static Player1 player1;
	static Player2 player2;
	
	Enemy[] enemies;
	int activeEnemyCount;
	
	GUI gui;
	
	Map(String name, String game, int pc){
		mapName = name;
		gameMode = game;
		playerCount = pc;
		init();
	}
	
	void init(){
		loadMapInfo();
		loadMap();
		
		mapWidthPx = (mapWidth ) *32;
		mapHeightPx = (mapHeight ) *32;
	
		if (playerCount == 1){
			player1 = new Player1("player1","Warrior", mapSpawnX, mapSpawnY);
		}
		else if (playerCount == 2){
			player1 = new Player1("player1", "Warrior",  mapSpawnX, mapSpawnY);
			player2 = new Player2("player2","Priest",  mapSpawnX, mapSpawnY);
		}
		
		gui = new GUI(playerCount);
		//enemies//
		activeEnemyCount = 1;
		enemies = new Enemy[activeEnemyCount];
		for(int i = 0; i < activeEnemyCount; i++){
			enemies[i] = new Enemy("null", "null", 0, 0, mapWidth, mapHeight);
		}
		
		if (gameMode == "test"){
			enemies[0] = new Enemy("monster", "monster", (32 * 5),( 32 * 5), mapWidth, mapHeight);
			
		}
		
		
		//player2Path = new PathFinder(mapWidth, mapHeight, "player2");
		 
		
	}
	
	void update(){
		if (playerCount == 1){
			
		player1.update();
		
		}else if(playerCount == 2){
			
		player1.update();	
		player2.update();
		
		}
		
		
		for(int i = 0; i < activeEnemyCount; i++){
		 
			enemies[i].update();
		}
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
		for(int i = player1.gridXpos - (ProjectTrinity.tileX); i <  player1.gridXpos + (ProjectTrinity.tileX); i++){
			for(int j =  player1.gridYpos - (ProjectTrinity.tileY); j < player1.gridYpos + (ProjectTrinity.tileY); j++){
				if(i >= 0 && i < mapWidth && j >= 0 && j < mapHeight)
				tiles[i][j].render(g, container);
				
			}
		}
		
		if (playerCount == 1){
		player1.render(g, container);
		}else if (playerCount == 2){
		player1.render(g, container);
		player2.render(g, container);
		}
		
		for(int k = 0; k < activeEnemyCount; k++){
			enemies[k].render(g, container);
		}
		
		gui.render(g, container);
		
	}
	
	

}
