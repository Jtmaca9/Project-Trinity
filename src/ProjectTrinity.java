import java.awt.Dimension;
import java.awt.Toolkit;

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
	 
	 Map currentMap;
	 Player1 player1;
	 Player2 player2;
	 
	 static Image grassTile;
	 static Image waterTile;



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
		gameState = "game";
		currentMap = new Map("test");
		loadImages();
		player1 = new Player1("Tank", currentMap.mapSpawnX, currentMap.mapSpawnY);
		



	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if(gameState == "game"){
		player1.move();
		}


	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.fillRect((float)0, (float)0, (float)width,(float) height);
		
		if(gameState == "game"){
		currentMap.render(g, container);
		player1.render(g, container);
		}

	}

	public void keyPressed(int key, char c) {
		

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


		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
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
	}
	

}