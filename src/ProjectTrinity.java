import java.awt.Dimension;
import java.awt.Toolkit;




import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ProjectTrinity extends BasicGame {

	 
	 static float screenOffsetX;
	 static float screenOffsetY;
	 float screenOffsetScale;
	 
	 String gameState;
	 static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 static double width = screenSize.getWidth();
	 static double height = screenSize.getHeight();
	 static int tileX;
	 static int tileY;
	 
	 static Map currentMap;
	 static Player1 player1;
	 static Player2 player2;
	 
	 static Image grassTile;
	 static Image waterTile;
	 Image title;
	 Image startGame;
	 Image mapEditor;
	 Image selectionArrow;
	 
	 int mouseXpos;
	 int mouseYpos;
	 
	int menuSelection = 1;



	public ProjectTrinity() {
		// Set the application window name
		super("ProjectTrinity");
	}

	public static void main(String[] arguments) {
		
		
		try {
			// Set up the screen and graphic options
			AppGameContainer app = new AppGameContainer(new ProjectTrinity());
			app.setDisplayMode((int)width, (int)height, true);
			app.setShowFPS(true);
			app.setVSync(true);
			app.start();
			

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		tileX = (int) (width/32);
		tileY = (int) (height/32);
		gameState = "mainmenu";
		currentMap = new Map("test");
		loadImages();
		player1 = new Player1("Tank", currentMap.mapSpawnX, currentMap.mapSpawnY);
		player2 = new Player2("Healer", currentMap.mapSpawnX, currentMap.mapSpawnY);
		



	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
	
		
		
		
		if(gameState == "game"){
		player1.update();
		player2.update();
		}
		
		else if(gameState == "mainmenu"){
			mainMenuUpdate();
		}


	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		
		
		//////////////////
		//Draw Main Menu//
		/////////////////
		if(gameState == "mainmenu"){
			g.setColor(Color.white);
			g.fillRect((float)0, (float)0, (float)width,(float) height);
			g.drawImage(title, (float) (width/2 - 266), 20);
			g.drawImage(startGame, (float) (width/2)- 100, 200);
			g.drawImage(mapEditor, (float) (width/2) -100, 400);
			
			if (menuSelection == 1){
				g.drawImage(selectionArrow, (float) (width/2)+ 100, 200);
			}else if(menuSelection == 2){
				g.drawImage(selectionArrow, (float) (width/2)+ 100, 400);
			}
			
		}
		
		
		
		/////////////
		//Draw Game//
		////////////
		else if(gameState == "game"){
		g.setColor(Color.black);
		g.fillRect((float)0, (float)0, (float)width,(float) height);
		currentMap.render(g, container);
		player1.render(g, container);
		player2.render(g, container);
		g.setColor(Color.white);
		g.drawString("Player 1: (" + player1.get_vxpos() + ", " + player1.get_vypos() + ")" + " (" + player1.get_gridXpos() + ", " + player1.get_gridYpos() + ")", 20, 200);
		g.drawString("Player 2: (" + player2.get_vxpos() + ", " + player2.get_vypos() + ")" + " (" + player2.get_gridXpos() + ", " + player2.get_gridYpos() + ")", 20, 400);
		
		}
		
		
		///////////////////
		//Draw Map Editor//
		//////////////////
		else if(gameState == "mapeditor"){
			
		}

	}

	public void keyPressed(int key, char c) {
		if(gameState == "game"){

		if (key == Input.KEY_D) {
			player1.set_moveRight(true);
		}

		if (key == Input.KEY_A) {
			player1.set_moveLeft(true);
		}

		if (key == Input.KEY_W) {
			player1.set_moveUp(true);
		}

		if (key == Input.KEY_S) {
			player1.set_moveDown(true);
		}
		
		if (key == Input.KEY_RIGHT) {
			player2.set_moveRight(true);
		}

		if (key == Input.KEY_LEFT) {
			player2.set_moveLeft(true);
		}

		if (key == Input.KEY_UP) {
			player2.set_moveUp(true);
		}

		if (key == Input.KEY_DOWN) {
			player2.set_moveDown(true);
		}
		
		}else if(gameState == "mainmenu"){
			
			if (key == Input.KEY_UP || key == Input.KEY_DOWN || key == Input.KEY_W || key == Input.KEY_S) {
				if(menuSelection == 1){
					menuSelection = 2;
				}else{
					menuSelection = 1;
				}
			}
			
			if (key == Input.KEY_ENTER) {
				if(menuSelection == 1){
					gameState = "game";
				}else{
					gameState = "mapeditor";
				}
			}
		}


		if (key == Input.KEY_ESCAPE) {
			if(gameState == "game" || gameState == "mapeditor"){
				gameState = "mainmenu";
			}else{
			System.exit(0);
			}
		}

	}

	public void keyReleased(int key, char c) {

		if (key == Input.KEY_D) {
			player1.set_moveRight(false);

		}

		if (key == Input.KEY_A) {
			player1.set_moveLeft(false);

		}

		if (key == Input.KEY_W) {
			player1.set_moveUp(false);

		}

		if (key == Input.KEY_S) {
			player1.set_moveDown(false);

		}
		
		if (key == Input.KEY_RIGHT) {
			player2.set_moveRight(false);

		}

		if (key == Input.KEY_LEFT) {
			player2.set_moveLeft(false);

		}

		if (key == Input.KEY_UP) {
			player2.set_moveUp(false);

		}

		if (key == Input.KEY_DOWN) {
			player2.set_moveDown(false);

		}



	}
	
	void loadImages(){
		try {
			grassTile = new Image("Data/Images/Grass.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			waterTile = new Image("Data/Images/Water.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			title = new Image("Data/Images/Title.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			startGame = new Image("Data/Images/StartGame.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mapEditor = new Image("Data/Images/MapEditor.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			selectionArrow = new Image("Data/Images/SelectionArrow.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void mainMenuUpdate(){
		
		
	}
	

}