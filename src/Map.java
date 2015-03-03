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
	MonsterSpawner monsterSpawner;
	
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
		
		
		if (gameMode == "test"){
			activeEnemyCount = 22;
			enemies = new Enemy[activeEnemyCount];
			for(int i = 0; i < activeEnemyCount; i++){
				enemies[i] = new Enemy("null", "null", 0, 0, mapWidth, mapHeight, "null");
			}
			enemies[0] = new Enemy("goblin", "goblin", (32 * 6),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[1] = new Enemy("goblin", "goblin", (32 * 7),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[2] = new Enemy("goblin", "goblin", (32 * 45),( 32 * 45), mapWidth, mapHeight, "player1");
			enemies[4] = new Enemy("goblin", "goblin", (32 * 35),( 32 * 45), mapWidth, mapHeight, "player1");
			enemies[5] = new Enemy("goblin", "goblin", (32 * 6),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[6] = new Enemy("goblin", "goblin", (32 * 7),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[7] = new Enemy("goblin", "goblin", (32 * 45),( 32 * 45), mapWidth, mapHeight, "player1");
			enemies[8] = new Enemy("goblin", "goblin", (32 * 35),( 32 * 45), mapWidth, mapHeight, "player1");
			enemies[9] = new Enemy("goblin", "goblin", (32 * 6),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[10] = new Enemy("goblin", "goblin", (32 * 7),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[12] = new Enemy("goblin", "goblin", (32 * 45),( 32 * 45), mapWidth, mapHeight, "player1");
			enemies[13] = new Enemy("goblin", "goblin", (32 * 35),( 32 * 45), mapWidth, mapHeight, "player1");
			enemies[14] = new Enemy("goblin", "goblin", (32 * 6),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[15] = new Enemy("goblin", "goblin", (32 * 7),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[16] = new Enemy("goblin", "goblin", (32 * 45),( 32 * 45), mapWidth, mapHeight, "player1");
			enemies[17] = new Enemy("goblin", "goblin", (32 * 35),( 32 * 45), mapWidth, mapHeight, "player1");
			enemies[18] = new Enemy("goblin", "goblin", (32 * 6),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[19] = new Enemy("goblin", "goblin", (32 * 7),( 32 * 6), mapWidth, mapHeight, "player1");
			enemies[20] = new Enemy("goblin", "goblin", (32 * 45),( 32 * 45), mapWidth, mapHeight, "player1");
			enemies[21] = new Enemy("goblin", "goblin", (32 * 35),( 32 * 45), mapWidth, mapHeight, "player1");
					
		}
		
		monsterSpawner = new MonsterSpawner("MonsterSpawner", (32 * 5),( 32 * 5), mapWidth, mapHeight);
		 
		
	}
	
	void update(){
		
		if (playerCount == 1){
			
		player1.update();
		
		}else if(playerCount == 2){
			
		player1.update();	
		player2.update();
		
		}
		monsterSpawner.update();
		for(int i =  0; i <  mapWidth; i++){
			for(int j = 0; j < mapHeight; j++){
				tiles[i][j].update();		
			}
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
						tiles[j][i] = new Tile(tileData[j][i], j, i, mapWidth, mapHeight);
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
		monsterSpawner.render(g, container);
		for(int k = 0; k < activeEnemyCount; k++){
			enemies[k].render(g, container);
		}

		gui.render(g, container);
		
	}
	
	public int getMapWidth(){
		return mapWidth;
	}
	public int getMapHeight(){
		return mapHeight;
	}
	
	public Tile getTile(int x, int y){
		return tiles[x][y];
	}
	
	

}
