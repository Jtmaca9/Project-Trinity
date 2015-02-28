import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

	static boolean debugMode = false;

	static Map currentMap;

	MainMenu mainMenu;

	int mapNumber;
	static String[] maps;
	Scanner scanner;

	static Image grassTile;
	static Image waterTile;
	static Image healProjectileImage;
	static Image damageProjectileImage;
	static Image healProjectileIcon;
	static Image damageProjectileIcon;
	static Image abilityCooldown;
	static Image monsterImage;
	
	Image title;
	Image startGame;
	Image mapEditor;
	Image selectionArrow;

	int mouseXpos;
	int mouseYpos;

	public ProjectTrinity() {
		// Set the application window name
		super("ProjectTrinity");
	}

	public static void main(String[] arguments) {

		try {
			// Set up the screen and graphic options
			AppGameContainer app = new AppGameContainer(new ProjectTrinity());
			app.setDisplayMode((int) width, (int) height, true);
			app.setShowFPS(true);
			app.setVSync(true);
			app.start();

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		loadMaps();
		
		tileX = (int) (width / 32);
		tileY = (int) (height / 32);
		gameState = "mainmenu";
		mainMenu = new MainMenu(mapNumber, width, height);
		loadImages();

	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {

		if (gameState == "game") {
			currentMap.update();
		}

		else if (gameState == "mainmenu") {
		}

	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {

		// ////////////////
		// Draw Main Menu//
		// ///////////////
		if (gameState == "mainmenu") {
			mainMenu.render(g, container);
		}

		// ///////////
		// Draw Game//
		// //////////
		else if (gameState == "game") {
			g.setColor(Color.black);
			g.fillRect((float) 0, (float) 0, (float) width, (float) height);
			currentMap.render(g, container);
			g.setColor(Color.white);

			// ////////////
			// Debug Menu//
			// ///////////
			if (debugMode) {
				g.drawString("Player 1: (" + currentMap.player1.get_vxpos()
						+ ", " + currentMap.player1.get_vypos() + ")" + " ("
						+ currentMap.player1.get_gridXpos() + ", "
						+ currentMap.player1.get_gridYpos() + ")", 20, 200);
				if (currentMap.playerCount == 2) {
					g.drawString("Player 2: (" + currentMap.player2.get_vxpos()
							+ ", " + currentMap.player2.get_vypos() + ")"
							+ " (" + currentMap.player2.get_gridXpos() + ", "
							+ currentMap.player2.get_gridYpos() + ")", 20, 400);
					g.drawString("Offset(" + screenOffsetX + ","
							+ screenOffsetY + ")", 20, 600);
				}
			}
		}

		// /////////////////
		// Draw Map Editor//
		// ////////////////
		else if (gameState == "mapeditor") {

		}

	}

	public void keyPressed(int key, char c) {
		if (gameState == "game") {

			if (key == Input.KEY_D) {
				currentMap.player1.set_moveRight(true);
			}

			if (key == Input.KEY_A) {
				currentMap.player1.set_moveLeft(true);
			}

			if (key == Input.KEY_W) {
				currentMap.player1.set_moveUp(true);
			}

			if (key == Input.KEY_S) {
				currentMap.player1.set_moveDown(true);
			}
			if (key == Input.KEY_T) {
				currentMap.player1.ability(currentMap.player1.ability1);
			}
			if (key == Input.KEY_Y) {
				currentMap.player1.ability(currentMap.player1.ability2);
			}

			if (key == Input.KEY_SPACE) {
				currentMap.enemies[0].findNewPath();
			}
			if (key == Input.KEY_NUMPAD0) {

			if (key == Input.KEY_K) {
				currentMap.player2.ability(currentMap.player2.ability1);
			}
			if (key == Input.KEY_L) {
				currentMap.player2.ability(currentMap.player2.ability2);
			}
			if (key == Input.KEY_COMMA) {
				if (debugMode) {
					debugMode = false;
				} else {
					debugMode = true;
				}
			}
			if (currentMap.playerCount == 2) {
				if (key == Input.KEY_RIGHT) {
					currentMap.player2.set_moveRight(true);
				}

				if (key == Input.KEY_LEFT) {
					currentMap.player2.set_moveLeft(true);
				}

				if (key == Input.KEY_UP) {
					currentMap.player2.set_moveUp(true);
				}

				if (key == Input.KEY_DOWN) {
					currentMap.player2.set_moveDown(true);
				}
			}

		} else if (gameState == "mainmenu") {

			if (key == Input.KEY_UP || key == Input.KEY_W) {
				if (mainMenu.screen == "main") {
					if (mainMenu.menuSelection == 0) {
						mainMenu.menuSelection += 1;
					} else {
						mainMenu.menuSelection = 0;
					}
				} else if (mainMenu.screen == "levelSelect") {
					if (mainMenu.menuSelection > 0) {
						mainMenu.menuSelection--;
					}
				}
			} else if (key == Input.KEY_DOWN || key == Input.KEY_S) {
				if (mainMenu.screen == "main") {
					if (mainMenu.menuSelection == 0) {
						mainMenu.menuSelection = 1;
					} else {
						mainMenu.menuSelection = 0;
					}
				} else if (mainMenu.screen == "levelSelect") {
					if (mainMenu.menuSelection < (maps.length - 1)) {
						mainMenu.menuSelection++;
					}

				}
			}

			if (key == Input.KEY_ENTER) {
				if (mainMenu.screen == "main") {
					if (mainMenu.menuSelection == 0) {
						mainMenu.screen = "levelSelect";
					} else if (mainMenu.menuSelection == 1) {
						gameState = "mapeditor";
					}
				} else if (mainMenu.screen == "levelSelect") {
					screenOffsetX = 0;
					screenOffsetY = 0;
					currentMap = new Map(maps[mainMenu.menuSelection],
							"test", 2);
					gameState = "game";
				}

			}
		}

		if (key == Input.KEY_ESCAPE) {
			if (gameState == "game" || gameState == "mapeditor") {
				gameState = "mainmenu";
			} else if (mainMenu.screen != "main") {
				mainMenu.screen = "main";
				mainMenu.menuSelection = 0;
			} else {
				System.exit(0);
			}
		}
		}
	}

	public void keyReleased(int key, char c) {
		if (gameState == "game") {
			if (key == Input.KEY_D) {
				currentMap.player1.set_moveRight(false);

			}

			if (key == Input.KEY_A) {
				currentMap.player1.set_moveLeft(false);

			}

			if (key == Input.KEY_W) {
				currentMap.player1.set_moveUp(false);

			}

			if (key == Input.KEY_S) {
				currentMap.player1.set_moveDown(false);

			}
			if (currentMap.playerCount == 2) {
				if (key == Input.KEY_RIGHT) {
					currentMap.player2.set_moveRight(false);

				}

				if (key == Input.KEY_LEFT) {
					currentMap.player2.set_moveLeft(false);

				}

				if (key == Input.KEY_UP) {
					currentMap.player2.set_moveUp(false);

				}

				if (key == Input.KEY_DOWN) {
					currentMap.player2.set_moveDown(false);

				}
			}
		}

	}

	void loadMaps() {

		try {
			scanner = new Scanner(new File("Data/Maps/MapConfig.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int p = 0;
		int a = 0;

		while (scanner.hasNext()) {
			if (a == 0) {
				mapNumber = scanner.nextInt();
				maps = new String[mapNumber];
				a++;
			}

			maps[p++] = scanner.next();
		}
	}

	void loadImages() {
		try {
			grassTile = new Image("Data/Images/Grass.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			waterTile = new Image("Data/Images/Water.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			healProjectileImage = new Image("Data/Images/healProjectile.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			damageProjectileImage = new Image(
					"Data/Images/damageProjectile.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			healProjectileIcon = new Image("Data/Images/healProjectileIcon.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			damageProjectileIcon = new Image(
					"Data/Images/damageProjectileIcon.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			abilityCooldown = new Image("Data/Images/abilityCooldown.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			monsterImage = new Image("Data/Images/Monster.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}